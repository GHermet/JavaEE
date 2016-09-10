package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import Models.Company;
import Models.Computer;

public class ComputerDAO extends JDBCUtil {


	public ComputerDAO() throws ClassNotFoundException, SQLException{
		
	}

	/**
	 * Ajouter un ordinateur à la base de donnée
	 * @param comp -> Object Computer à ajouter à la base de donnée
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public void addComputer(Computer comp) throws SQLException, ClassNotFoundException {
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
	  Statement stmt = getConnection().createStatement();
	   String sql = "INSERT INTO computer " +
	           "VALUES ('"+comp.getId()+"'" +
	           ", '"+comp.getName()+"'" +
	           ", '"+format.format(comp.getIntroduced())+"'" +
	           ", '"+format.format(comp.getDiscontinued())+"'" +
	           ", '"+comp.getCompany().getId()+"')";

	   stmt.executeUpdate(sql);
	   System.out.println("Computeur added: "+comp.toString());
	}
	/**
	 * Supprimer un ordinateur de la base de donnée via son Id
	 * @param id -> identifiant de l'ordinateur
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void removeComputer(String id) throws SQLException, ClassNotFoundException {
		  Statement stmt = getConnection().createStatement();
		  String sql = "DELETE FROM computer WHERE id="+id;
		  stmt.executeUpdate(sql);
		  System.out.println("Computeur deleted Id: "+id);	
	}
	
	public Company getCompanyById(String companyId) throws SQLException, ClassNotFoundException {
		 Company company = new Company();
	     Statement stmt = connection.createStatement();
	      // recherche de l'ensemble des companies dans la table company
	     String sql = "SELECT * FROM company WHERE id="+companyId;
	     ResultSet rs = stmt.executeQuery(sql);
	    if (rs.first()) {
	        company.setId(rs.getLong("id"));
	        company.setName(rs.getString("name"));
	     }

	 return company;
	 
	}
	
	/**
	 * 
	 * @param company_id
	 * @return 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<Computer> getComputersFromCompany(String company_id) throws ClassNotFoundException, SQLException {
		ArrayList<Computer> ordinateurs = new ArrayList<Computer>();
	    Statement stmt = getConnection().createStatement();
	     // recherche de l'ensemble des ordinateurs d'une entreprise (company_id)
	    String sql = "SELECT * FROM computer WHERE company_id="+company_id;
	    ResultSet rs = stmt.executeQuery(sql);
	    while (rs.next()) {
	        Computer ordinateur = new Computer();
	        ordinateur.setId(rs.getLong("id"));
	       if (rs.getString("name")!=null) ordinateur.setName(rs.getString("name"));
	        if (rs.getDate("introduced")!=null) ordinateur.setIntroduced(rs.getDate("introduced"));
	        if (rs.getDate("discontinued")!=null) ordinateur.setDiscontinued(rs.getDate("discontinued"));
	        Company company = new Company();
	        Statement stmt2 = connection.createStatement();
	        // recherche de l'entreprise associée à l'identifiant entreprise de l'ordinateur (company_id)
	        String sql2 = "SELECT * FROM Company WHERE id=" +company_id;
	        ResultSet rs2 = stmt2.executeQuery(sql2);
	        stmt2.setFetchSize(1);
	        while (rs2.next()) {
	            company.setId(rs2.getLong("id"));
	            company.setName(rs2.getString("name"));
	            System.out.println(company.toString());
	        }
	        ordinateur.setCompany(company);
	        System.out.println(ordinateur.toString());
	        ordinateurs.add(ordinateur);
	    }

	return ordinateurs;
	}
	
/**
 * 
 * @param query
 * @param company_id
 * @return
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
public ArrayList<Computer> searchComputersFromCompany(String query, String company_id) throws ClassNotFoundException, SQLException {
	ArrayList<Computer> ordinateurs = new ArrayList<Computer>();
    Statement stmt = getConnection().createStatement();
     // recherche de l'ensemble des ordinateurs d'une entreprise (company_id)
    String sql = "SELECT * FROM computer WHERE company_id="+company_id+" AND name LIKE "+"'%"+query+"%'";
    //String sql3 = "SELECT * FROM computer WHERE company_id=? AND name LIKE"+"'%"+query+"%'";
  //  PreparedStatement prepStmt = (PreparedStatement) getConnection().prepareStatement(sql3);
   // prepStmt.setString(1,company_id);
   // ResultSet rs = prepStmt.executeQuery(sql3);
    ResultSet rs = stmt.executeQuery(sql);
    
    while (rs.next()) {
        Computer ordinateur = new Computer();
        ordinateur.setId(rs.getLong("id"));
       if (rs.getString("name")!=null) ordinateur.setName(rs.getString("name"));
        if (rs.getDate("introduced")!=null) ordinateur.setIntroduced(rs.getDate("introduced"));
        if (rs.getDate("discontinued")!=null) ordinateur.setDiscontinued(rs.getDate("discontinued"));
        Company company = new Company();
        Statement stmt2 = connection.createStatement();
        // recherche de l'entreprise associée à l'identifiant entreprise de l'ordinateur (company_id)
        String sql2 = "SELECT * FROM Company WHERE id=" +company_id;
        ResultSet rs2 = stmt2.executeQuery(sql2);
        stmt2.setFetchSize(1);
        while (rs2.next()) {
            company.setId(rs2.getLong("id"));
            company.setName(rs2.getString("name"));
            System.out.println(company.toString());
        }
        ordinateur.setCompany(company);
        System.out.println(ordinateur.toString());
        ordinateurs.add(ordinateur);
    }

return ordinateurs;
}

/**
 * Obtenir l'intégralité des ordinateurs dans la base de donnée
 * @return -> ArrayList de tous les ordinateurs de la base de donnée
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
public ArrayList<Computer> getAllComputers() throws SQLException, ClassNotFoundException {
   ArrayList<Computer> ordinateurs = new ArrayList<Computer>();
       Statement stmt = getConnection().createStatement();
        // recherche de l'ensemble des ordinateurs dans la table computer
       String sql = "SELECT * FROM computer";
       ResultSet rs = stmt.executeQuery(sql);
       while (rs.next()) {
           Computer ordinateur = new Computer();
           ordinateur.setId(rs.getLong("id"));
          if (rs.getString("name")!=null) ordinateur.setName(rs.getString("name"));
           if (rs.getDate("introduced")!=null) ordinateur.setIntroduced(rs.getDate("introduced"));
           if (rs.getDate("discontinued")!=null) ordinateur.setDiscontinued(rs.getDate("discontinued"));
           Company company = new Company();
           Statement stmt2 = connection.createStatement();
           // recherche de l'entreprise associée à l'identifiant entreprise de l'ordinateur (company_id)
           String sql2 = "SELECT * FROM Company WHERE id=" + rs.getLong("company_id");
           ResultSet rs2 = stmt2.executeQuery(sql2);
           stmt2.setFetchSize(1);
           while (rs2.next()) {
        	 
               company.setId(rs2.getLong("id"));
               company.setName(rs2.getString("name"));
               System.out.println(company.toString());
           }
           ordinateur.setCompany(company);
           System.out.println(ordinateur.toString());
           ordinateurs.add(ordinateur);
       }

   return ordinateurs;
}

public ArrayList<Computer> searchComputers(String query) throws SQLException {
	ArrayList<Computer> ordinateurs = new ArrayList<Computer>();
    Statement stmt = connection.createStatement();
     // recherche de l'ensemble des ordinateurs dans la table computer contenant $query dans leur nom
    String sql = "SELECT * FROM computer WHERE name LIKE "+"'%"+query+"%'";
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        Computer ordinateur = new Computer();
        ordinateur.setId(rs.getLong("id"));
       if (rs.getString("name")!=null) ordinateur.setName(rs.getString("name"));
        if (rs.getDate("introduced")!=null) ordinateur.setIntroduced(rs.getDate("introduced"));
        if (rs.getDate("discontinued")!=null) ordinateur.setDiscontinued(rs.getDate("discontinued"));
        Company company = new Company();
        Statement stmt2 = connection.createStatement();
        // recherche de l'entreprise associée à l'identifiant entreprise de l'ordinateur (company_id)
        String sql2 = "SELECT * FROM Company WHERE id=" + rs.getLong("company_id");
        ResultSet rs2 = stmt2.executeQuery(sql2);
        stmt2.setFetchSize(1);
        while (rs2.next()) {
     	 
            company.setId(rs2.getLong("id"));
           if (rs2.getString("name")!=null) company.setName(rs2.getString("name"));
            System.out.println(company.toString());
        }
        ordinateur.setCompany(company);
        System.out.println(ordinateur.toString());
        ordinateurs.add(ordinateur);
    }

return ordinateurs;
}








}
