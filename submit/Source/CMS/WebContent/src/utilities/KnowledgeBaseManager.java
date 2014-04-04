package utilities;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

// Leslie Ducray 2014
// Class creates the knowledge base from the string input which represents the file name.
// The code required to create the knowledge base has been adapted from Drools documentation: Example 4.31. Simple StatelessKnowledgeSession execution with a Collection
//adapted to be more abstract allowing various classes to create different knowledge bases based on file name string input.
public class KnowledgeBaseManager {

	public static KnowledgeBase createKnowledgeBase(String drl) {
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
             //Add drl file into builder
				
		builder.add( ResourceFactory.newFileResource( "C:\\xampp\\tomcat\\webapps\\CMS\\WebContent\\src\\Rules\\"+drl ), ResourceType.DRL);
		 if ( builder.hasErrors() ) {
		 System.err.println( builder.getErrors().toString() );
		 }
		
		
		
		 if ( builder.hasErrors() ) {
		 System.err.println( builder.getErrors().toString() );
		 }
		

		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
             //Add to Knowledge Base packages from the builder which are actually the rules from the drl file.
		knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
		return knowledgeBase;
	}

}
