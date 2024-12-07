package com.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			case "signOn":
				signOn(request, response);
				break;
			case "signIn":
				signIn(request, response);
				break;
			case "signInRedirect":
				signInRedirect(request, response);
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
	
	private void signInRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "sign-in.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "sign-on.jsp";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userAddress = request.getParameter("address");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setAddress(userAddress);
		account.setEmail(email);
		account.setBirthday(birthday);
		account.setGender(gender);
		account.setPhone(phone);
		accountDAO.addAccount(account);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	private void signOn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address;
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		if(accountDAO.AccountCheck(userName, password))
		{
			address = "MainMenuServlet";
		}
		else
		{
			address = "sign-on.jsp";
		}
		Account account = accountDAO.getAccountByUsername(userName);
		int id = account.getId();
		HttpSession session = request.getSession();
		session.setAttribute("accountID", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
