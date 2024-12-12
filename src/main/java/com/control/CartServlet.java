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
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FavoriteDAO favoriteDAO;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
    	favoriteDAO = new FavoriteDAO();
        productDAO = new ProductDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	
    	int accountId = (int) session.getAttribute("accountID");
    	String productIdParam = request.getParameter("productID");
        String quantityParam = request.getParameter("quantity");
    	System.out.println("Account ID: " + accountId);
    	System.out.println("Product ID: " + productIdParam);
    	System.out.println("Quantity: " + quantityParam);
    	
    	try {
            int productId = Integer.parseInt(productIdParam);
            int quantity = Integer.parseInt(quantityParam);

            Favorite favorite = new Favorite();
            favorite.setAccountId(accountId);
            favorite.setProductId(productId);
            favorite.setBuy(quantity);
            favoriteDAO.addFavo(favorite);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid productId or quantity");
            return; 
        }
    	
    	String action = request.getParameter("action");
    	if ("viewCart".equals(action)) {
            viewCart(request, accountId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/cart.jsp");
            dispatcher.forward(request, response);
        }
    	
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
    private void viewCart(HttpServletRequest request, int accountId) {
        List<Favorite> favorites = favoriteDAO.getAllCartByStatus(accountId);
        List<Product> products = new ArrayList<>();
        float totalPrice = 0;

        for (Favorite favorite : favorites) {
            Product product = productDAO.getProductByID(favorite.getProductId());
            products.add(product);

            float productPrice = Float.parseFloat(product.getPrice());
            int productQuantity = favorite.getBuy();
            float itemTotal = productPrice * productQuantity;
            favorite.setTotalPrice(itemTotal);

            totalPrice += itemTotal;
        }

        request.setAttribute("products", products);
        request.setAttribute("favorites", favorites);
        request.setAttribute("totalPrice", totalPrice);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    

}
