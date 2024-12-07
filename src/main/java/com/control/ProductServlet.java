package com.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CategoryDAO;
import com.dao.CollectionDAO;
import com.dao.ColorDAO;
import com.dao.MaterialDAO;
import com.dao.ProductDAO;
import com.model.Collection;
import com.model.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ProductDAO productDAO = new ProductDAO();
	CollectionDAO collectionDAO = new CollectionDAO(); 
	ColorDAO colorDAO = new ColorDAO();
	MaterialDAO materialDAO = new MaterialDAO();
	CategoryDAO categoryDAO = new CategoryDAO();
	
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= (String) request.getParameter("action");
		request.setAttribute("colors", colorDAO.GetAllColor());
		request.setAttribute("collections", collectionDAO.getAllCollections());
		request.setAttribute("materials", materialDAO.GetAllMeterial());
		request.setAttribute("categories", categoryDAO.GetAllCategory());
		switch(action)
		{
			case "ListProductByCollection":
				ShowProductByCollection(request, response);
				break;
			case "Search":
				ShowProductBySearching(request, response);
				break;
			case "Filter":
				//ShowProductByFilter(request, response);
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
	
	private void ShowProductByFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String address = "search-page.jsp";
			String color = request.getParameter("color");
			String searchKey = request.getParameter("searchKey");
			List<Product> products = productDAO.getProductByFilter(color);
			request.setAttribute("products", products);
			request.setAttribute("count", products.size());
			request.setAttribute("searchKey", searchKey);
			RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void ShowProductByCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String address= "search-page.jsp";
			List<Product> products = new ArrayList<>();
			String collection = request.getParameter("collection");
			int collectionID = Integer.parseInt(collection);
			products = productDAO.GetProductsByCollection(collectionID);
			request.setAttribute("products", products);
			request.setAttribute("searchKey", collectionDAO.GetCollectionByID(collectionID).getName());
			request.setAttribute("count", products.size());
			RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void ShowProductBySearching(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String address= "search-page.jsp";
			List<Product> products = new ArrayList<>();
			String searchKey = request.getParameter("searchKey");
			products = productDAO.GetProductBySearching(searchKey);
			request.setAttribute("products", products);
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("count", products.size());
			RequestDispatcher dispatcher =  request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
