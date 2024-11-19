package com.control;

import com.model.Account;


import com.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/PaymentServlet")
@MultipartConfig
public class PaymentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;

    public PaymentServlet() {
        super();
        accDAO = new AccountDAO();
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
        default:
            doGet(request, response);
            break;
        }

    }
    
    private void listInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int accountId = 1; 
        Account account = accDAO.getAccountById(accountId);
        request.setAttribute("account", account);
        request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);
    }

}
