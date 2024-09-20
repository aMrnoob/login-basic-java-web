package controllers;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO(); 

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValidUser = false;

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            isValidUser = (userDAO.checkDuplicate("username", username) && userDAO.checkDuplicate("password", password)); 
        }

        if (isValidUser) {
        	User user = new User();
        	user = userDAO.getInforUser(username);
        	
        	request.setAttribute("user", user);
        	request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
        } else {
        	
        	request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không chính xác.");
        	request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
