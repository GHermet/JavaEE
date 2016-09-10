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
 * Servlet implementation class MyServlet
 */
@WebServlet("/dash")
public class dashboardServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDAO computerDAO;

    /**
     * Default constructor. 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public dashboardServ() throws ClassNotFoundException, SQLException {
        // TODO Auto-generated constructor stub
		computerDAO = new ComputerDAO();
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
		// TODO Auto-generated method stub
		if(request.getParameter("search")!=null){
			if(request.getParameter("search").length()>0){
			String query = (String) request.getParameter("search");
			
			try {
				ArrayList<Computer> computers = computerDAO.searchComputers(query);
				request.setAttribute("computers",computers);
				request.getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}else{
		try {
			ArrayList<Computer> computers = computerDAO.getAllComputers();
			request.setAttribute("computers",computers);
			System.out.println(request.getAttribute("computers"));
			request.getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		  } 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
