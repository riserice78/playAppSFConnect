package service;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import java.net.InetSocketAddress;
import java.io.IOException;


public class ConnectionManager {

	private static ConnectionManager ref;
	private static PartnerConnection connection;
	private static ConnectorConfig config;
	private static String username = "";
	private static String password = "";

	
	private static final String SESSION_KEY = "sessionKey";
	private static final String SESSION_VALUE = "sessionValue";
	
	private ConnectionManager() { }

	public static ConnectionManager getConnectionManager() {
		if (ref == null)
			ref = new ConnectionManager();
		return ref;
	}

	public static ConnectionManager getConnectionManager(String uname, String pword) {
		username = uname;
		password = pword;
		if (ref == null){
			ref = new ConnectionManager();
		}
		return ref;
	}	
	
	public PartnerConnection getConnection() {

		try { 
				config = new ConnectorConfig();
				config.setUsername(username);
				config.setPassword(password);
				connection = Connector.newConnection(config);
				System.out.println("connected!");
		} catch ( ConnectionException ce) {
				System.out.println("ConnectionException " +ce.getMessage());
		}        	
        
		return connection;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}

}
