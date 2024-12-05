package com.control;

import com.model.Account;
import com.model.Favorite;
import com.model.Product;
import com.model.Payment;
import com.dao.AccountDAO;
import com.dao.FavoriteDAO;
import com.dao.ProductDAO;
import com.dao.PaymentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PaymentServlet")
@MultipartConfig
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;
    private FavoriteDAO favoriteDAO;
    private ProductDAO productDAO;
    private PaymentDAO paymentDAO;

    public PaymentServlet() {
        super();
        accDAO = new AccountDAO();
        favoriteDAO = new FavoriteDAO();
        productDAO = new ProductDAO();
        paymentDAO = new PaymentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            default:
                listInfo(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "confirmPayment":
                confirmPayment(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void listInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int accountID = 1;
        int productID = 2;
        int count = 1;
        Account account = accDAO.getAccountById(accountID);
        request.setAttribute("account", account);

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
        request.setAttribute("count", count);
        request.setAttribute("accountID", accountID);
        request.setAttribute("productID", productID);
        request.setAttribute("price", 100);
        request.setAttribute("products", products);
        request.setAttribute("favorites", favorites);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("tich", tich);

        request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);
    }

    private void confirmPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Payment payment = new Payment();
        payment.setAccountId(accountID);
        payment.setTotalAmount(totalPrice);
        payment.setDiscountAmount(0);
        payment.setFinalAmount(totalPrice);
        payment.setPayStatus("completed"); 
        payment.setPayMethod("cash");
        payment.setOrderDate(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));

        paymentDAO.addPayment(payment, products);

        favoriteDAO.updateFavoriteStatus("no", accountID);

        response.sendRedirect(request.getContextPath() + "/jsp/finish.jsp");
    }
}
