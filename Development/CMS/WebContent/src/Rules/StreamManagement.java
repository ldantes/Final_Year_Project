package Rules;

import model.beans.ServiceUserBean;
import model.data.cmsQueryServiceUser;

import org.drools.FactHandle;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.DroolsError;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderErrors;
import org.drools.io.ResourceFactory;
import org.drools.rule.*;
import org.drools.rule.Package;
import org.drools.runtime.StatefulKnowledgeSession;

public class StreamManagement {
	

		 private StatefulKnowledgeSession kSession;
		 private org.drools.runtime.rule.FactHandle ruleHandle;
		 
		 
		 public void execute(String id){
		 if (null == kSession) {
		 //load rule and create stateful rule session, if it's not cached
		 KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		 kbuilder.add( ResourceFactory.newFileResource( "rules.drl" ), ResourceType.DRL);
		 if ( kbuilder.hasErrors() ) {
		 System.err.println( kbuilder.getErrors().toString() );
		 }
		 KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		 kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		 
		 kSession = kbase.newStatefulKnowledgeSession();
		 }
		 
		 //create fact
		 ServiceUserBean fact = new ServiceUserBean();
		 fact = cmsQueryServiceUser.searchServiceUsersById(id);
		 
		 
		 //insert fact and invoke first agenda group
		 kSession.getAgenda().getAgendaGroup("stream").setFocus();
		 ruleHandle = kSession.insert(fact);
		 kSession.fireAllRules();
		 kSession.retract(ruleHandle);
		 
		 //reinsert fact and invoke the second agenda group
		 
		 }
		 
		

}
