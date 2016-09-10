package DAO;



import java.sql.*;
import java.util.*;

import Models.Company;
import Models.Computer;

public class JDBCUtil {

public Connection connection;

public Connection getConnection() throws ClassNotFoundException, SQLException{
	if(this.connection==null) Connect();
	return this.connection;
}

public JDBCUtil() throws SQLException, ClassNotFoundException {
	Connect();
}

/**
 * Connection à la base de donnée computer-database-db
 * @throws ClassNotFoundException
 * @throws SQLException
 */
public void Connect() throws ClassNotFoundException, SQLException {
   String driverName = "org.gjt.mm.mysql.Driver";
   Class.forName(driverName);
   String serverName = "localhost:8889";
   String database = "computer-database-db";
   String option ="?zeroDateTimeBehavior=convertToNull";
   String url = "jdbc:mysql://" + serverName + "/" + database + option;
   String username = "root";
   String password = "root";
   connection = DriverManager.getConnection(url, username, password);
   System.out.print("Connected to "+connection.getMetaData());
}

/**
 * Déconnection de la base de donnée
 * @throws SQLException
 */
public void Disconnect() throws SQLException {
   connection.close();
}




	
}





