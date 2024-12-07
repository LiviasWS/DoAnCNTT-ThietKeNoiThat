package com.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AccountDAO;
import com.dao.ColorDAO;
import com.model.Account;
import com.model.Color;

/**
 * Servlet implementation class FunctionTestServlet
 */
@WebServlet("/FunctionTestServlet")
public class FunctionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ColorDAO colorDAO = new ColorDAO();
	AccountDAO accountDAO = new AccountDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunctionTestServlet() {
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
		if(action == null)
		{
			action = "init";
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
			case "jsTest":
				jsTest(request, response);
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
	
	private void jsTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("Quantity: " + quantity);
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
		AccountDAO accountDAO = new AccountDAO();
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
			address = "MainMenuServlet";
		else
			address = "sign-on.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	private void solveProblem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "function-test.jsp";
		List<Color> colors = colorDAO.GetAllColor();
		String[] selectedColorArray = request.getParameterValues("selectedColors");
		Set<String> selectedColors = new HashSet<>(Arrays.asList(selectedColorArray));
		request.setAttribute("colors", colors);
		request.setAttribute("selectedColors", selectedColors);
		request.setAttribute("colors", colors);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	private void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "function-test.jsp";
		List<Color> colors = colorDAO.GetAllColor();
		request.setAttribute("colors", colors);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
