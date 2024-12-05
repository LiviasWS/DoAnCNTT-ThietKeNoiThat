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

import com.dao.AccountDAO;
import com.dao.FavoriteDAO;
import com.dao.ProductDAO;
import com.dao.SubImageDAO;
import com.model.Account;
import com.model.Favorite;
import com.model.Product;
import com.model.SubImage;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;
    private FavoriteDAO favoriteDAO;
    
	ProductDAO productDAO= new ProductDAO();
	SubImageDAO subImageDAO = new SubImageDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
        accDAO = new AccountDAO(); 
        favoriteDAO = new FavoriteDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountID = 1;
        int productID = 2;
        int count = 1;

        List<Favorite> favorites = favoriteDAO.getAllCartByStatus(accountID);
        List<Product> products = new ArrayList<>();
        float totalPrice = 0;
        float tich = 0;

        for (Favorite favorite : favorites) {
            Product product = productDAO.getProductByID(favorite.getProductId());
            products.add(product);

            float productPrice = Float.parseFloat("100$".replace("$", ""));
            float itemTotal = productPrice * count;
            tich = itemTotal; 
            totalPrice += productPrice * count;
            
        }

        response.getWriter().append("Served at: ").append(request.getContextPath());
        request.setAttribute("count", count);
        request.setAttribute("accountID", accountID);
        request.setAttribute("productID", productID);
        request.setAttribute("price", "100$");

        request.setAttribute("products", products);
        request.setAttribute("favorites", favorites);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("tich", tich);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/cart.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void SessionExample (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "/CartServlet";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	private void ShowProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "product-detail.jsp";
		int id = Integer.parseInt(request.getParameter("id"));
		List<SubImage> subImages = subImageDAO.getSubImage(id);
		Product product = productDAO.getProductByID(id);
		request.setAttribute("subImages", subImages);
		request.setAttribute("product", product);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
