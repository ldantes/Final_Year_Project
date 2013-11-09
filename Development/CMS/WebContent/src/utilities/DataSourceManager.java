package utilities;


import javax.sql.DataSource;
@SuppressWarnings("static-access")
public class DataSourceManager {	
	
	private static DataSource	dataSource;
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
