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

import com.dao.ColorDAO;
import com.model.Color;

/**
 * Servlet implementation class FunctionTestServlet
 */
@WebServlet("/FunctionTestServlet")
public class FunctionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ColorDAO colorDAO = new ColorDAO();
       
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
			case "filter":
				solveProblem(request, response);
				break;
			case "init":
				init(request, response);
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
