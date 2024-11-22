package com.control;

import com.model.Account;


import com.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;




@WebServlet("/InfoServlet")
@MultipartConfig
public class InfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;
    
    public InfoServlet() {
        super();
        accDAO = new AccountDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
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
        case "update":
            updateAccount(request, response);
            break;
        default:
            doGet(request, response);
            break;
        }

    }
    
    private void listInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int accountId = 2; 
        Account account = accDAO.getAccountById(accountId);
        request.setAttribute("account", account);
        request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int accountId = 2; // Sử dụng cùng một ID hoặc lấy từ session
        Account account = accDAO.getAccountById(accountId);
        request.setAttribute("account", account);
        request.getRequestDispatcher("/jsp/info-edit.jsp").forward(request, response);
    }
    
    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");

        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("/images");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        Account acc = new Account();
        acc.setId(id);  // Chỉ cần set id để update đúng account
        acc.setUsername(username);
        acc.setAddress(address);
        acc.setEmail(email);
        acc.setPhone(phone);
        acc.setBirthday(birthday);
        acc.setGender(gender);
        acc.setImage(fileName);

        accDAO.updateAccount(acc);
        response.sendRedirect("InfoServlet?action=list");
    }

}
