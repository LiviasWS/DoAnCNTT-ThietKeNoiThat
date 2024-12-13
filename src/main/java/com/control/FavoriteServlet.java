package com.control;

import com.model.Favorite;
import com.model.Product;
import com.dao.ProductDAO;
import com.dao.FavoriteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/FavoriteServlet")
@MultipartConfig
public class FavoriteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private FavoriteDAO favoriteDAO;
    private ProductDAO productDAO;

    public FavoriteServlet() {
    	super();
        favoriteDAO = new FavoriteDAO(); 
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	int accountId = (int) session.getAttribute("accountID");
        
        List<Favorite> favorites = favoriteDAO.getFavoritesByAccount(accountId);
        
        List<Product> products = new ArrayList<Product>();
        for (Favorite favorite : favorites) {
			Product product = productDAO.getProductByID(favorite.getProductId());
			products.add(product);
		}
        request.setAttribute("products", products);
        request.setAttribute("favorites", favorites);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/favo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	int accountId = (int) session.getAttribute("accountID");

        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));

        if ("add".equals(action)) {
            Favorite favorite = new Favorite(accountId, productId);
            favoriteDAO.addFavo(favorite);
        } else if ("remove".equals(action)) {
            favoriteDAO.deleteFavo(accountId, productId);
        }
        else if("update".equals(action)){
            favoriteDAO.updateStatusProduct("added", accountId, productId);
        }

        response.sendRedirect("FavoriteServlet");
    }
}
