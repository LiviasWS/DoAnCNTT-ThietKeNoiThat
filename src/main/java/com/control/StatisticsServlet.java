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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet("/StatisticsServlet")
@MultipartConfig
public class StatisticsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;
    private FavoriteDAO favoriteDAO;
    private ProductDAO productDAO;
    private PaymentDAO paymentDAO;
    
    public StatisticsServlet() {
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
            	showOrderStatistics(request, response);
                break;
        }
    }
    
    
    private void showOrderStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = 1;

        int totalOrders = paymentDAO.getTotalOrdersByAccountId(accountId);
        int totalQuantity = paymentDAO.getTotalQuantityByAccountId(accountId);
        int favoQuantity = favoriteDAO.getFavoQuantityByAccountId(accountId);
        float totalSpent = paymentDAO.getTotalSpentByAccountId(accountId);
        
        float totalChart = 0.0f;
        float totalChart1 = 0.0f;
        float totalChart2 = 0.0f;
        float totalChart3 = 0.0f;
        float totalChart4 = 0.0f;
        float totalChart5 = 0.0f;
        float totalChart6 = 0.0f;
        
        try {
        	totalChart = paymentDAO.getTotalAmountByAccountAndDate(accountId, "03/12/2024");
            totalChart1 = paymentDAO.getTotalAmountByAccountAndDate(accountId, "05/12/2024");
            totalChart2 = paymentDAO.getTotalAmountByAccountAndDate(accountId, "09/12/2024");
            totalChart3 = paymentDAO.getTotalAmountByAccountAndDate(accountId, "11/12/2024");
            totalChart4 = paymentDAO.getTotalAmountByAccountAndDate(accountId, "12/12/2024");
            totalChart5 = paymentDAO.getTotalAmountByAccountAndDate(accountId, "13/12/2024");
            totalChart6 = paymentDAO.getTotalAmountByAccountAndDate(accountId, "14/12/2024");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Payment> payments = paymentDAO.getPaymentDataByAccountId(accountId);

        request.setAttribute("totalQuantity", totalQuantity);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("favoQuantity", favoQuantity);
        request.setAttribute("totalSpent", totalSpent);
        request.setAttribute("payments", payments);
        request.setAttribute("totalChart1", totalChart1); 
        
        
        List<String> dates = Arrays.asList("03/12/2024","05/12/2024","09/12/2024","11/12/2024","12/12/2024","13/12/2024","14/12/2024");
        List<Float> amounts = Arrays.asList(totalChart, totalChart1, totalChart2, totalChart3, totalChart4, totalChart5, totalChart6);

        // Truyền dữ liệu vào JSP thông qua request
        request.setAttribute("paymentDates", dates);
        request.setAttribute("paymentAmounts", amounts);
        
        
        request.getRequestDispatcher("/jsp/chart.jsp").forward(request, response);

    }

}
