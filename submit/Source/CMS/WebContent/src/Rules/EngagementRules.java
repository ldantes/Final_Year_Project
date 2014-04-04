package Rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.beans.AttendanceBean;
import model.beans.ServiceUserBean;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.DefaultAgendaEventListener;
import org.drools.runtime.StatelessKnowledgeSession;
//Leslie Ducray 2014
//Establishes connection and session to the engagement rule base.
//The service user's most recent attendance details are added as a fact.

public class EngagementRules {

	private static Logger log = Logger.getLogger(SubstanceRules.class);
	private static  Set<String> firedRules = null;
	public ServiceUserBean applyEngagmentRules(ServiceUserBean serviceUserBean, AttendanceBean attendanceBean)
	{
		firedRules = new HashSet<String>();
		KnowledgeBase knowledgeBase = utilities.KnowledgeBaseManager.createKnowledgeBase("EngagementRules.drl");
		//Creating the knowledge session uses the API statelessknowledgeSession (http://docs.jboss.org/jbpm/v5.1/javadocs/org/drools/runtime/StatelessKnowledgeSession.html)
		/* A stateless knowledge session was used as
		the facts/working memory can be inserted into the Knowledge base session before firing rules and
		the facts can be set by calling public methods on an object while executing rules and after setting these objects, they are returned back with changed values.*/
		StatelessKnowledgeSession ksession = knowledgeBase.newStatelessKnowledgeSession();
		
		//Below code adapted from Drools documentation : Example 4.28. Adding an AgendaEventListener
		ksession.addEventListener( new DefaultAgendaEventListener() {                            
			   public void afterActivationFired(AfterActivationFiredEvent event) {
			       super.afterActivationFired( event );
			       getFiredRules().add("<li>"+event.getActivation().getRule().getName());
			       System.out.print(event.getActivation().getRule().getName());
			   }
			});
		//end extract code
		ServiceUserBean serviceuser   = serviceUserBean; // A new object is created to be modified within the rule engine, and returned.
		
		List<Object> facts = new ArrayList<Object>();
		facts.add(serviceuser); // the fact service user is populated by the parameter
		facts.add(attendanceBean); // the fact service user is populated by the parameter
		ksession.execute(facts);
		
		return serviceuser;
	}
	
	public Set<String> getFiredRules() {
		return firedRules;
	}
	public  void setFiredRules(Set<String> firedRules) {
		EngagementRules.firedRules = firedRules;
	}

}