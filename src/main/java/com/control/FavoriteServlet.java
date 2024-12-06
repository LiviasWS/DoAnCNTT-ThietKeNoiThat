package com.control;

import com.model.Favorite;
import com.model.Product;
import com.dao.ProductDAO;
import com.dao.FavoriteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Override
    public void init() throws ServletException {
        favoriteDAO = new FavoriteDAO(); 
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = 1;
        
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
        int accountId = 1;

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
