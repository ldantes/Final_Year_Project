package Rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.beans.ServiceUserBean;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.DefaultAgendaEventListener;
import org.drools.runtime.StatelessKnowledgeSession;

//Leslie Ducray 2014
//Establishes connection and session to the rule base for the services user's substance results and their current statuses.

public class SubstanceRules {

	private static Logger log = Logger.getLogger(SubstanceRules.class);
	private static Set<String> firedRules =null; //firedRules will store a set (non duplicated list) of all rules which fired
	
	public  ServiceUserBean applySubstanceRules(ServiceUserBean serviceUserBean)
	{
		firedRules =  new HashSet<String>();
		KnowledgeBase knowledgeBase = utilities.KnowledgeBaseManager.createKnowledgeBase("rules.drl");
		//Creating the knowledge session uses the API statelessknowledgeSession (http://docs.jboss.org/jbpm/v5.1/javadocs/org/drools/runtime/StatelessKnowledgeSession.html)
				/* A stateless knowledge session was used as
				the facts/working memory can be inserted into the Knowledge base session before firing rules and
				the facts can be set by calling public methods on an object while executing rules and after setting these objects, they are returned back with changed values.*/
		StatelessKnowledgeSession ksession = knowledgeBase.newStatelessKnowledgeSession();
		
		//An event listener is added to catch all rules which were matched and fired. these will be used to inform the user.	
		//Below code adapted from Drools documentation : Example 4.28. Adding an AgendaEventListener
		ksession.addEventListener( new DefaultAgendaEventListener() {                            
			   public void afterActivationFired(AfterActivationFiredEvent event) {
			       super.afterActivationFired( event );
			       getFiredRules().add("<li>"+event.getActivation().getRule().getName()+"</li>"); // each fired rule is added with HTML list tags
			   }
			});
		
		ServiceUserBean serviceuser   = serviceUserBean;
		
		List<Object> facts = new ArrayList<Object>();
		facts.add(serviceuser);
		ksession.execute(facts);
		facts.clear();
		return serviceuser;
	}

	public  Set<String> getFiredRules() {
		return firedRules;
	}

	public  void setFiredRules(Set<String> firedRules) {
		SubstanceRules.firedRules = firedRules;
	}

}