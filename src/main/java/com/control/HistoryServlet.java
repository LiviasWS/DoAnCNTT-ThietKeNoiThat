package com.control;
import com.model.Order;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HistoryServlet")
@MultipartConfig
public class HistoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;
    private FavoriteDAO favoriteDAO;
    private ProductDAO productDAO;
    private PaymentDAO paymentDAO;

    public HistoryServlet() {
        super();
        accDAO = new AccountDAO();
        favoriteDAO = new FavoriteDAO();
        productDAO = new ProductDAO();
        paymentDAO = new PaymentDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int accountId = 1; // Lấy ID tài khoản
        PaymentDAO paymentDAO = new PaymentDAO();
        try {
            List<Payment> payments = paymentDAO.getPaymentsWithProducts(accountId);
            List<Favorite> favorites = favoriteDAO.getAllPay(accountId);
            request.setAttribute("payments", payments);  // payment chứa danh sách productList
            request.setAttribute("favorites", favorites);
            request.getRequestDispatcher("/jsp/history.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lấy thông tin đơn hàng.");
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
    
   

    
}
