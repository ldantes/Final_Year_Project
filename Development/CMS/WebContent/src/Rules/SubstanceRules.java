package Rules;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.beans.ServiceUserBean;
import model.beans.StreamBean;
import model.beans.SubstanceBean;
import model.data.cmsQueryServiceUser;
import model.data.cmsQuerySubstance;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.ObjectFilter;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

public class SubstanceRules {

	private static Logger log = Logger.getLogger(SubstanceRules.class);
	
	public static ServiceUserBean adjustSubstanceAccumaltor(ServiceUserBean serviceUserBean)
	{
        //Create KnowledgeBase...
//        KnowledgeBase knowledgeBase = utilities.KnowledgeBaseManager.createKnowledgeBase();
//        //Create a statefull session
//        
//		StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
//		try {
//          
//			ServiceUserBean serviceuser = new ServiceUserBean();
//			String id = "3";
//			serviceuser = cmsQueryServiceUser.searchServiceUsersById(id);
//			StreamBean stream = serviceuser.getStreamDetails();
//			serviceuser.setSubstanceDetails(cmsQuerySubstance.qryServiceUserResults(id));
//			
//			List<SubstanceBean> substances = cmsQuerySubstance.qrySubstances(null);
//			
//			int passes =0;
//			
//			for(int i =0; i < substances.size(); i++)
//			{
//				for(int j =0; j < substances.size(); j++)
//				{
//					if(substances.get(i).getSubstance() == serviceuser.getSubstanceDetails().get(j).getSubstance() )
//					{
//						if(serviceuser.getSubstanceDetails().get(i).getResult() == "Y")
//						{
//							passes++;
//						}
//						
//					}
//				}
//				
//				
//			}
//			passes = substances.size() - passes;
//			log.debug(passes);
//			
//
//                        //Insert fact
//
//
//			session.insert(stream);
//			session.insert(passes);
//			
//						//Fire rules
//			
//			session.fireAllRules(); 
//			
//		serviceuser.setStreamDetails(stream);
//		return serviceuser;
//        
//		} finally {
//			session.dispose();
//		}
		KnowledgeBase knowledgeBase = utilities.KnowledgeBaseManager.createKnowledgeBase();
		 StatelessKnowledgeSession ksession = knowledgeBase.newStatelessKnowledgeSession();
		 
		ServiceUserBean serviceuser   = serviceUserBean;
		 		 
		
		 
		 
		 List<Object> facts = new ArrayList<Object>();
		 facts.add(serviceuser);
		 ksession.execute(facts);
		 System.out.println("Result is " + serviceuser.getName());
		 
		 return serviceuser;
	}

	
	

	////////////////////////
	
  

}