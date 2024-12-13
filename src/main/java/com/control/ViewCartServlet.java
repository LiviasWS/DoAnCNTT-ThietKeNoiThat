package com.control;
import com.dao.FavoriteDAO;
import com.dao.ProductDAO;
import com.model.Product;
import com.model.Favorite;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CartServlet")
public class ViewCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private FavoriteDAO favoriteDAO;
    private ProductDAO productDAO;

    
    public ViewCartServlet() {
    	super();
    	favoriteDAO = new FavoriteDAO();
        productDAO = new ProductDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	
    	int accountId = (int) session.getAttribute("accountID");
    	
        List<Favorite> favorites = favoriteDAO.getAllCartByStatus(accountId);
        List<Product> products = new ArrayList<>();
        float totalPrice = 0;

        for (Favorite favorite : favorites) {
            Product product = productDAO.getProductByID(favorite.getProductId());
            products.add(product);

            float productPrice = 0;
            try {
                productPrice = Float.parseFloat(product.getPrice());
            } catch (NumberFormatException e) {
                System.err.println("Invalid price format for product ID: " + product.getId());
            }

            int productQuantity = favorite.getBuy();
            System.out.println("Product ID: " + product.getId() + ", Buy quantity: " + productQuantity);

            float itemTotal = productPrice * productQuantity;
            favorite.setTotalPrice(itemTotal);

            totalPrice += itemTotal;
        }

        request.setAttribute("products", products);
        request.setAttribute("favorites", favorites);
        request.setAttribute("totalPrice", totalPrice); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/cart.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    

}

    
