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
import javax.servlet.http.HttpSession;

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
    	HttpSession session = request.getSession(false);
    	int accountId = (int) session.getAttribute("accountID");
        Account account = accDAO.getAccountById(accountId);
        request.setAttribute("account", account);

        List<Favorite> favorites = favoriteDAO.getAllCartByStatus(accountId);
        List<Product> products = new ArrayList<>();
        float totalPrice = 0; 

        for (Favorite favorite : favorites) {
            Product product = productDAO.getProductByID(favorite.getProductId());
            if (product != null) {
                products.add(product);
                try {
                    String priceStr = product.getPrice();
                    if (priceStr != null && !priceStr.trim().isEmpty()) {
                        float productPrice = Float.parseFloat(priceStr);
                        int productQuantity = favorite.getBuy();
                        totalPrice += productPrice * productQuantity;
                    }
                } catch (NumberFormatException e) {
                    totalPrice += 0;
                }
            }
        }

        request.setAttribute("products", products);
        request.setAttribute("favorites", favorites);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);
    }

    private void confirmPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	int accountId = (int) session.getAttribute("accountID"); 

        String totalPriceParam = request.getParameter("totalPrice");
        float totalAmount = 0;
        if (totalPriceParam != null && !totalPriceParam.trim().isEmpty()) {
            try {
                totalAmount = Float.parseFloat(totalPriceParam);
            } catch (NumberFormatException e) {
                totalAmount = 0;
            }
        }

        float discountAmount = 0; 
        float finalAmount = totalAmount - discountAmount;

        List<Favorite> favorites = favoriteDAO.getAllCartByStatus(accountId);
        List<Product> products = new ArrayList<>();
        for (Favorite favorite : favorites) {
            Product product = productDAO.getProductByID(favorite.getProductId());
            if (product != null) {
                products.add(product);
            }
        }

        Payment payment = new Payment();
        payment.setAccountId(accountId);
        payment.setTotalAmount(totalAmount);
        payment.setDiscountAmount(discountAmount);
        payment.setFinalAmount(finalAmount);
        payment.setPayStatus("completed"); 
        payment.setPayMethod("cash");
        payment.setOrderDate(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));

        paymentDAO.addPayment(payment, products);

        favoriteDAO.updateFavoriteStatus("no",1, accountId);

        response.sendRedirect(request.getContextPath() + "/jsp/finish.jsp");
    }
}
