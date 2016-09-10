package Servlets;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.CompanyDAO;
import DAO.ComputerDAO;
import Models.Company;
import Models.Computer;

/**
 * Servlet implementation class addComputerServ
 */
@WebServlet("/add")
public class addComputerServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyDAO companyDAO;
	private SimpleDateFormat dateFormat;
	private ComputerDAO computerDAO;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public addComputerServ() throws ClassNotFoundException, SQLException {
        super();
        companyDAO = new CompanyDAO();
        computerDAO = new ComputerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Company> companies;
		try {
			companies = companyDAO.getAllCompanies();
			request.setAttribute("companies",companies);
			request.getRequestDispatcher( "/addComputer.jsp" ).forward( request, response );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Computer computer = new Computer();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		// check parameters
		/// name
		String name = request.getParameter("name");
	
		/// introduced
				Date introduced = null;
				String introducedParam = request.getParameter("introduced");
				if(introducedParam != null && introducedParam.length() > 0){
					try {
						introduced = (Date) format.parse(introducedParam);
					} catch (ParseException e) {
						e.printStackTrace();
						return;
					}
				}
			/// discontinued
			Date discontinued = null;
			String discontinuedParam = request.getParameter("discontinued");
			if(discontinuedParam != null && introducedParam.length() > 0){
				try {
					discontinued = format.parse(discontinuedParam);
				} catch (ParseException e) {
					e.printStackTrace();
					return;
				}
			}
		/// company
		Company company = null;
		String companyId = request.getParameter("company");
		if(companyId != null && companyId.length() > 0){
				try {
					company = companyDAO.getCompanyById(companyId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			
		}
		computer.setCompany(company);
		// set parameters
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		
		System.out.println(computer.toString());
		// add computer to database
		try {
			computerDAO.addComputer(computer);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// redirect
		response.sendRedirect("/javaWebProject/dash");
	}
	

}


