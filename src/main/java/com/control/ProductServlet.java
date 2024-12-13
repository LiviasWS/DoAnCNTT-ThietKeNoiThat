package com.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			case "filter":
				ShowProductByFilter(request, response);
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
		String address = "function-test.jsp";
		String[] colorChecked = request.getParameterValues("color");
		String[] categoryChecked = request.getParameterValues("category");
		String[] collectionChecked = request.getParameterValues("collection");
		String[] materialChecked = request.getParameterValues("material");
		String searchKey = request.getParameter("searchKey");
		Map<String, String> colorMap = createFilterMap(colorChecked);
		Map<String, String> categoryMap = createFilterMap(categoryChecked);
		Map<String, String> collectionMap = createFilterMap(collectionChecked);
		Map<String, String> materialMap = createFilterMap(materialChecked);
		request.setAttribute("colorMap", colorMap);
		request.setAttribute("categoryMap", categoryMap);
		request.setAttribute("collectionMap", collectionMap);
		request.setAttribute("materialMap", materialMap);
		Set<Product> products = new HashSet<>(productDAO.getAllProduct());
		if(searchKey!=null)
		{
			products.retainAll(productDAO.GetProductBySearching(searchKey));
			request.setAttribute("searchKey", searchKey);
		}
		if(colorChecked!=null && colorChecked.length!=0)
		{
			List<Product> productsByColor = productDAO.getProductByColorList(colorChecked);
			products.retainAll(productsByColor);
		}
		if(categoryChecked!=null && categoryChecked.length!=0)
		{
			List<Product> productsByCategory = productDAO.getProductByCategoryList(categoryChecked);
			products.retainAll(productsByCategory);
		}
		if(collectionChecked!=null && collectionChecked.length!=0)
		{
			List<Product> productsByColletion = productDAO.getProductByCollectionList(collectionChecked);
			products.retainAll(productsByColletion);
		}
		if(materialChecked!=null && materialChecked.length!=0)
		{
			List<Product> productsByMaterial = productDAO.getProductByMaterialList(materialChecked);
			products.retainAll(productsByMaterial);
		}
		request.setAttribute("count", products.size());
		request.setAttribute("products", products);
		RequestDispatcher dispathcher = request.getRequestDispatcher(address);
		dispathcher.forward(request, response);
	}
	
	private Map<String, String> createFilterMap(String[] checkedValues) {
	    Map<String, String> filterMap = new HashMap<>();
	    if (checkedValues != null) {
	        for (String value : checkedValues) {
	            filterMap.put(value, "checked");
	        }
	    }
	    return filterMap;
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
