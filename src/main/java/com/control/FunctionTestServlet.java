package com.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.dao.AccountDAO;
import com.dao.CategoryDAO;
import com.dao.CollectionDAO;
import com.dao.ColorDAO;
import com.dao.FeatureDAO;
import com.dao.MaterialDAO;
import com.dao.ProductDAO;
import com.dao.SubImageDAO;
import com.model.Account;
import com.model.Color;
import com.model.Feature;
import com.model.FeatureType;
import com.model.Product;
import com.model.SubImage;

/**
 * Servlet implementation class FunctionTestServlet
 */
@WebServlet("/FunctionTestServlet")
public class FunctionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ColorDAO colorDAO = new ColorDAO();
	AccountDAO accountDAO = new AccountDAO();
	CollectionDAO collectionDAO = new CollectionDAO();
	MaterialDAO materialDAO = new MaterialDAO();
	CategoryDAO categoryDAO = new CategoryDAO();
	ProductDAO productDAO = new ProductDAO();
       
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
		request.setAttribute("colors", colorDAO.GetAllColor());
		request.setAttribute("collections", collectionDAO.getAllCollections());
		request.setAttribute("materials", materialDAO.GetAllMeterial());
		request.setAttribute("categories", categoryDAO.GetAllCategory());
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
				ShowProduct(request, response);
				break;
			case "addToCart":
				addToCart(request, response);
				break;
			case "filter":
				filter(request, response);
				break;
			case "init":
				init(request, response);
				break;
			case "test":
				test(request, response);
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
	
	private void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "function-test.jsp";
		String[] colorChecked = request.getParameterValues("color");
		String[] categoryChecked = request.getParameterValues("category");
		String[] collectionChecked = request.getParameterValues("collection");
		String[] materialChecked = request.getParameterValues("material");
		Map<String, String> colorMap = createFilterMap(colorChecked);
		Map<String, String> categoryMap = createFilterMap(categoryChecked);
		Map<String, String> collectionMap = createFilterMap(collectionChecked);
		Map<String, String> materialMap = createFilterMap(materialChecked);
		request.setAttribute("colorMap", colorMap);
		request.setAttribute("categoryMap", categoryMap);
		request.setAttribute("collectionMap", collectionMap);
		request.setAttribute("materialMap", materialMap);
		Set<Product> products = new HashSet<>(productDAO.getAllProduct());
		if()
		request.setAttribute("products", products);
		RequestDispatcher dispathcher = request.getRequestDispatcher(address);
		dispathcher.forward(request, response);
	}
	
	private void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "function-test.jsp";
		List<Product> products = productDAO.getAllProduct();
		request.setAttribute("products", products);
		Map<String, String> colorMap = new HashMap<>();
		Map<String, String> categoryMap = new HashMap<>();
		Map<String, String> collectionMap = new HashMap<>();
		Map<String, String> materialMap = new HashMap<>();
		colorMap.put("White", "checked");
		request.setAttribute("colorMap", colorMap);
		request.setAttribute("categoryMap", categoryMap);
		request.setAttribute("collectionMap", collectionMap);
		request.setAttribute("materialMap", materialMap);
		RequestDispatcher dispathcher = request.getRequestDispatcher(address);
		dispathcher.forward(request, response);
	}
	
	private void filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "function-test.jsp";
		String[] colorChecked = request.getParameterValues("color");
		String[] categoryChecked = request.getParameterValues("category");
		String[] collectionChecked = request.getParameterValues("collection");
		String[] materialChecked = request.getParameterValues("material");
		Map<String, String> colorMap = createFilterMap(colorChecked);
		Map<String, String> categoryMap = createFilterMap(categoryChecked);
		Map<String, String> collectionMap = createFilterMap(collectionChecked);
		Map<String, String> materialMap = createFilterMap(materialChecked);
		List<Product> productByColor = productDAO.getProductByCategoryList(categoryChecked);
		System.out.println("Product List: ");
		for(Product product : productByColor)
		{
			System.out.println(product.getName());
		}
		request.setAttribute("products", productByColor);
		request.setAttribute("colorMap", colorMap);
		request.setAttribute("categoryMap", categoryMap);
		request.setAttribute("collectionMap", collectionMap);
		request.setAttribute("materialMap", materialMap);
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

	
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int cartQuantity =  Integer.parseInt(request.getParameter("cartQuantity"));
		System.out.println("Cart quantity: " + cartQuantity);
	}
	
	private void jsTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("Quantity: " + quantity);
	}
	
	private void ShowProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		SubImageDAO subImageDAO = new SubImageDAO();
		ProductDAO productDAO = new ProductDAO();
		FeatureDAO featureDAO = new FeatureDAO();
		String address = "product-detail.jsp";
		int id = 2;
		List<SubImage> subImages = subImageDAO.getSubImage(id);
		Product product = productDAO.getProductByID(id);
		request.setAttribute("subImages", subImages);
		request.setAttribute("product", product);
		Map<FeatureType, List<Feature>> featureMap = featureDAO.getFeatureMapByProductID(id);
		request.setAttribute("featureMap", featureMap);
		String currentFeature = request.getParameter("currentFeature");
		if(currentFeature==null)
		{
			currentFeature = "basic";
			request.setAttribute("currentFeature", currentFeature);
		}
		else
		{
			Feature feature = featureDAO.getFeatureByName(currentFeature);
			request.setAttribute("currentImage", feature.getImage());
			request.setAttribute("currentFeature", currentFeature);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
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
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		System.out.println("User name: " + username);
		System.out.println("Password: " + password);
		Account account = accountDAO.getAccountByUsername(username);
		String accountUserName = account.getUsername();
		String accountPassword = account.getPassword();
		System.out.println("Account username: " + accountUserName);
		System.out.println("Account password: " + accountPassword);
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

}
