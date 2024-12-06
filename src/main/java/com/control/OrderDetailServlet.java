package com.control;

import com.model.Account;
import com.model.Favorite;
import com.model.Product;
import com.model.Payment;
import com.model.PaymentItem;
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

@WebServlet("/OrderDetailServlet")
@MultipartConfig
public class OrderDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;
    private FavoriteDAO favoriteDAO;
    private ProductDAO productDAO;
    private PaymentDAO paymentDAO;

    public OrderDetailServlet() {
        super();
        accDAO = new AccountDAO();
        favoriteDAO = new FavoriteDAO();
        productDAO = new ProductDAO();
        paymentDAO = new PaymentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String paymentIdParam = request.getParameter("payment_id");
        int accountId = 1; 
        Account account = accDAO.getAccountById(accountId);
        request.setAttribute("account", account);
        if (paymentIdParam != null) {
            try {
                int paymentId = Integer.parseInt(paymentIdParam);

                Payment payment = paymentDAO.getPaymentByIdAndAccountId(paymentId, accountId);
                List<PaymentItem> paymentItems = new ArrayList<>(); 

                paymentItems = paymentDAO.getPaymentItemsByPaymentId(paymentId);
                
                for (PaymentItem item : paymentItems) {
                    Product product = productDAO.getProductByID(item.getProductId());
                    item.setProductName(product.getName()); 
                }
                request.setAttribute("payment", payment);
                request.setAttribute("paymentItems", paymentItems);
                request.getRequestDispatcher("/jsp/order-detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
            	System.out.println("Không hiện ");
            }
        } else {
        	System.out.println("Không hiện " );
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {

            default:
                doGet(request, response);
                break;
        }
    }

    private void listInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String paymentIdParam = request.getParameter("payment_id");
        System.out.println("Received payment_id: " + paymentIdParam); 
        if (paymentIdParam != null) {
            try {
                int paymentId = Integer.parseInt(paymentIdParam);  
                System.out.println("Parsed payment_id: " + paymentId); 
                List<PaymentItem> paymentItems = paymentDAO.getPaymentItemsByPaymentId(paymentId);
                System.out.println("Payment items: " + paymentItems); 
                request.setAttribute("paymentItems", paymentItems);
            } catch (NumberFormatException e) {
                System.out.println("Invalid payment_id format");
                request.setAttribute("error", "ID thanh toán không hợp lệ.");
            }
        } else {
            System.out.println("Payment ID is missing");
            request.setAttribute("error", "Không có ID thanh toán.");
        }

        int accountId = 1; 
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
        List<Payment> payments = paymentDAO.getAllPaymentsByAccount(accountId); 
        request.setAttribute("payments", payments);
        request.setAttribute("products", products);
        request.setAttribute("favorites", favorites);
        request.setAttribute("totalPrice", totalPrice);

        request.getRequestDispatcher("/jsp/order-detail.jsp").forward(request, response);
    }
}
