package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Company;

public class CompanyDAO extends JDBCUtil {
	
	public CompanyDAO() throws SQLException, ClassNotFoundException {
		super();
		
		// TODO Auto-generated constructor stub
	}



public ArrayList<Company> getAllCompanies() throws SQLException, ClassNotFoundException{
	 ArrayList<Company> companies = new ArrayList<Company>();
     Statement stmt = connection.createStatement();
      // recherche de l'ensemble des companies dans la table company
     String sql = "SELECT * FROM company";
     ResultSet rs = stmt.executeQuery(sql);
     while (rs.next()) {
         Company company = new Company();
        company.setId(rs.getLong("id"));
        company.setName(rs.getString("name"));
         companies.add(company);
     }

 return companies;
 
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

}
