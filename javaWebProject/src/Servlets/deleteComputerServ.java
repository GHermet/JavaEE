package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ComputerDAO;

/**
 * Servlet implementation class deleteComputer
 */
@WebServlet("/delete")
public class deleteComputerServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDAO computerDAO;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public deleteComputerServ() throws ClassNotFoundException, SQLException {
        super();
        // TODO Auto-generated constructor stub
        computerDAO = new ComputerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		{
			// TODO Auto-generated method stub
			String id = request.getParameter("id");
					try {
						computerDAO.removeComputer(id);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		response.sendRedirect("/javaWebProject/dash");
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
