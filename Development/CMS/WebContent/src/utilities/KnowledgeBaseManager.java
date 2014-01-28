package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;

public class KnowledgeBaseManager {
	
	   /**
     * Create new knowledge base
     */
	public static KnowledgeBase createKnowledgeBase() {
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
             //Add drl file into builder
				
		builder.add( ResourceFactory.newFileResource( "C:\\xampp\\tomcat\\webapps\\CMS\\WebContent\\src\\Rules\\rules.drl" ), ResourceType.DRL);
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
