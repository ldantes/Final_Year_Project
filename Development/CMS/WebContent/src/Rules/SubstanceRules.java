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

public class SubstanceRules {

	private static Logger log = Logger.getLogger(SubstanceRules.class);
	private static Set<String> firedRules =null;
	
	public  ServiceUserBean adjustSubstanceAccumaltor(ServiceUserBean serviceUserBean)
	{
		firedRules =  new HashSet<String>();
		KnowledgeBase knowledgeBase = utilities.KnowledgeBaseManager.createKnowledgeBase("rules.drl");
		StatelessKnowledgeSession ksession = knowledgeBase.newStatelessKnowledgeSession();
		
		ksession.addEventListener( new DefaultAgendaEventListener() {                            
			   public void afterActivationFired(AfterActivationFiredEvent event) {
			       super.afterActivationFired( event );
			       System.out.print(event.getActivation().getRule().getName());
			       getFiredRules().add(""+event.getActivation().getRule().getName()+"");
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