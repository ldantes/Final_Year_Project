package utilities;

/**
Leslie Ducray - 2014
Spring dataSource bean for storing the database connection information. bean attribute values stored in context.xml
 */
import javax.sql.DataSource;
@SuppressWarnings("static-access")
public class DataSourceManager {	
	
	private static DataSource	dataSource;// dataSource bean in 'context.xml'
	private static String		serviceName="";
	
    public static  DataSource getDataSource(){
    	return dataSource;
    }
    
   
	public void setDataSource(DataSource dataSource){
    	this.dataSource = dataSource;
    }
    
    public static String getServiceName(){
    	return serviceName;
    }
    public void setServiceName(String serviceName){
    	this.serviceName=serviceName;
    }
}
