package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Order;
import com.model.Payment;
import com.model.Product;
import com.util.DBUtil;

public class PaymentDAO {
    private Connection connection;
    private ProductDAO productDAO;

    public PaymentDAO() {
        connection = DBUtil.getConnection();
        productDAO = new ProductDAO();
    }

    public void addPayment(Payment pay, List<Product> products) {
        try {
            String query = "INSERT INTO payment (ACCOUNT_ID, TOTAL_AMOUNT, FINAL_AMOUNT, PAY_STATUS, PAY_METHOD, ORDER_DATE) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pay.getAccountId());
            stmt.setFloat(2, pay.getTotalAmount());
            stmt.setFloat(3, pay.getFinalAmount());
            stmt.setString(4, pay.getPayStatus());
            stmt.setString(5, pay.getPayMethod());
            stmt.setString(6, pay.getOrderDate());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int paymentId = 0;
            if (rs.next()) {
                paymentId = rs.getInt(1);
            }

            for (Product product : products) {
                String productQuery = "INSERT INTO payment_items (PAYMENT_ID, PRODUCT_ID, QUANTITY, UNIT_PRICE, TOTAL_PRICE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement productStmt = connection.prepareStatement(productQuery);
                productStmt.setInt(1, paymentId);
                productStmt.setInt(2, product.getId());
                productStmt.setFloat(4, Float.parseFloat(product.getPrice()));  
                productStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getAllPaymentsByAccount(int accountId) {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payment WHERE ACCOUNT_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int paymentId = rs.getInt("PAYMENT_ID");
                int productId = rs.getInt("PRODUCT_ID");
                float totalAmount = rs.getFloat("TOTAL_AMOUNT");
                float discountAmount = rs.getFloat("DISCOUNT_AMOUNT");
                float finalAmount = rs.getFloat("FINAL_AMOUNT");
                String payStatus = rs.getString("PAY_STATUS");
                String payMethod = rs.getString("PAY_METHOD");
                String orderDate = rs.getString("ORDER_DATE");

                Payment payment = new Payment();
                payment.setPaymentId(paymentId);
                payment.setAccountId(accountId);
                payment.setProductId(productId);
                payment.setTotalAmount(totalAmount);
                payment.setDiscountAmount(discountAmount);
                payment.setFinalAmount(finalAmount);
                payment.setPayStatus(payStatus);
                payment.setPayMethod(payMethod);
                payment.setOrderDate(orderDate);
                
                Product product = productDAO.getProductByID2(productId);
                if (product != null) {
                    payment.setProduct(product);  
                }

                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
    
    public List<Payment> getPaymentsWithProducts(int accountId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        Map<Integer, Payment> paymentMap = new HashMap<>();

        String sql = "SELECT p.PAYMENT_ID, p.TOTAL_AMOUNT, p.DISCOUNT_AMOUNT, p.FINAL_AMOUNT, p.ORDER_DATE, " +
                     "pi.PRODUCT_ID, pr.NAME AS PRODUCT_NAME, pr.PRICE, pr.BUY " +
                     "FROM payment p " +
                     "JOIN payment_items pi ON p.PAYMENT_ID = pi.PAYMENT_ID " +
                     "JOIN product pr ON pi.PRODUCT_ID = pr.ID " +
                     "WHERE p.ACCOUNT_ID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int paymentId = rs.getInt("PAYMENT_ID");
                
                // Kiểm tra nếu Payment chưa tồn tại trong Map
                Payment payment = paymentMap.get(paymentId);
                if (payment == null) {
                    payment = new Payment();
                    payment.setPaymentId(paymentId);
                    payment.setTotalAmount(rs.getFloat("TOTAL_AMOUNT"));
                    payment.setDiscountAmount(rs.getFloat("DISCOUNT_AMOUNT"));
                    payment.setFinalAmount(rs.getFloat("FINAL_AMOUNT"));
                    payment.setOrderDate(rs.getString("ORDER_DATE"));
                    payment.setProductList(new ArrayList<>()); // Tạo danh sách sản phẩm
                    paymentMap.put(paymentId, payment);
                }
                
                // Tạo Product và thêm vào Payment
                Product product = new Product();
                product.setId(rs.getInt("PRODUCT_ID"));
                product.setName(rs.getString("PRODUCT_NAME"));
                product.setPrice(String.valueOf(rs.getFloat("PRICE")));
                float totalPrice = rs.getFloat("PRICE") * rs.getInt("BUY");
                payment.getProductList().add(product);
            }
        }
        
        // Chuyển từ Map sang List
        payments.addAll(paymentMap.values());
        return payments;
    }



}
