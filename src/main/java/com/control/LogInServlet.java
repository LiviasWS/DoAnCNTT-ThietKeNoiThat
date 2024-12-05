package com.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AccountDAO;
import com.model.Account;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountDAO accountDAO = new AccountDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		if(action==null)
		{
			action="signin";
		}
		switch(action)
		{
			case "signin":
				break;
			case "signup":
				signUp(request, response);
				break;
			case "signupredirect":
				signUpRedirect(request, response);
				break;
			default:
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void signUpRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("sign-in.jsp");
	}
	
	private void signIn (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = accountDAO.getAccountByUsername(userName);
		if(account.getPassword().equals(password))
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("MainMenuServlet");
			dispatcher.forward(request, response);
		}
		response.sendRedirect("sign-in.jsp");
	}
	
	private void signUp (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("address");
		Account account = new Account();
		account.setUsername(userName);
		account.setPassword(password);
		account.setAddress(address);
		account.setBirthday("");
		account.setEmail(email);
		account.setGender(gender);
		account.setImage("");
		accountDAO.addAccount(account);
		RequestDispatcher dispatcher = request.getRequestDispatcher("log-on.jsp");
		dispatcher.forward(request, response);
	}

}
