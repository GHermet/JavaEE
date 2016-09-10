package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ComputerDAO;
import Models.Computer;

/**
 * Servlet implementation class computersFromCompanyServ
 */
@WebServlet("/company")
public class computersFromCompanyServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDAO computerDAO;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public computersFromCompanyServ() throws ClassNotFoundException, SQLException {
        super();
        computerDAO = new ComputerDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("search")!=null&&request.getParameter("search").length()>0){
			
				
			String query = (String) request.getParameter("search");
			String company_id = request.getParameter("id");
			ArrayList<Computer> computers = new ArrayList<>();
					try {
						computers = computerDAO.searchComputersFromCompany(query,company_id);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("computers",computers);
					try {
						request.setAttribute("company", computerDAO.getCompanyById(company_id));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.getRequestDispatcher( "/computersFromCompany.jsp" ).forward( request, response );
			 
			}else{
				
				String company_id = request.getParameter("id");
				ArrayList<Computer> computers = new ArrayList<>();
						try {
							computers = computerDAO.getComputersFromCompany(company_id);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("computers",computers);
						try {
							request.setAttribute("company", computerDAO.getCompanyById(company_id));
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.getRequestDispatcher( "/computersFromCompany.jsp" ).forward( request, response );
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
