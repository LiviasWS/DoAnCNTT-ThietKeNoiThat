package com.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CollectionDAO;
import com.dao.ProductDAO;
import com.model.Collection;
import com.model.Product;

/**
 * Servlet implementation class MainMenuServlet
 */
@WebServlet("/MainMenuServlet")
public class MainMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CollectionDAO collectionDAO;
	ProductDAO productDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuServlet() 
    {
        super();
        this.collectionDAO= new CollectionDAO();
        this.productDAO= new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String address="main-menu.jsp";
		Map <Collection, List<Product>> collectionBestSellerMap= GetBestSellerByCollection();
		request.setAttribute("collectionBestSellerMap", collectionBestSellerMap);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Map<Collection, List<Product>> GetBestSellerByCollection() throws ServletException, IOException
	{
		Map <Collection, List<Product>> collectionBestSellerMap= new HashMap<>();
		List<Collection> collections= collectionDAO.getAllCollections();
		for(Collection collection : collections)
		{
			List<Product> bestSellers = productDAO.GetTop4BestSeller(collection.getId());
			collectionBestSellerMap.put(collection, bestSellers);
		}
		return collectionBestSellerMap;
	}
	
}
