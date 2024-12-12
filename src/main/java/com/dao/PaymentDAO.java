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
import com.model.Favorite;
import com.model.PaymentItem;
public class PaymentDAO {
    private Connection connection;
    private ProductDAO productDAO;
    private FavoriteDAO favoriteDAO;
    
    public PaymentDAO() {
        connection = DBUtil.getConnection();
        productDAO = new ProductDAO();
        favoriteDAO = new FavoriteDAO();
    }
    public void addPayment(Payment pay, List<Product> products) {
        try {
            float totalAmount = 0;

            List<Favorite> favorites = favoriteDAO.getAllCartByStatus(pay.getAccountId());

            String query = "INSERT INTO payment (ACCOUNT_ID, PAY_STATUS, PAY_METHOD, ORDER_DATE) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pay.getAccountId());
            stmt.setString(2, pay.getPayStatus());
            stmt.setString(3, pay.getPayMethod());
            stmt.setString(4, pay.getOrderDate());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int paymentId = 0;
            if (rs.next()) {
                paymentId = rs.getInt(1);
            }

            for (Favorite favorite : favorites) {
                for (Product product : products) {
                    if (product.getId() == favorite.getProductId()) {
                        float totalPrice = favorite.getBuy() * Float.parseFloat(product.getPrice());
                        totalAmount += totalPrice; 


                        String productQuery = "INSERT INTO payment_items (PAYMENT_ID, PRODUCT_ID, QUANTITY, UNIT_PRICE, TOTAL_PRICE) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement productStmt = connection.prepareStatement(productQuery);
                        productStmt.setInt(1, paymentId);
                        productStmt.setInt(2, product.getId());
                        productStmt.setInt(3, favorite.getBuy());  
                        productStmt.setFloat(4, Float.parseFloat(product.getPrice()));  
                        productStmt.setFloat(5, totalPrice);  
                        productStmt.executeUpdate();  
                    }
                }
            }

            String updateQuery = "UPDATE payment SET TOTAL_AMOUNT = ? WHERE PAYMENT_ID = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                updateStmt.setFloat(1, totalAmount);  
                updateStmt.setInt(2, paymentId);  
                updateStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getListAllPayment(int accountId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        Map<Integer, Payment> paymentMap = new HashMap<>();

        String sql = "SELECT p.PAYMENT_ID, p.TOTAL_AMOUNT, p.DISCOUNT_AMOUNT, p.FINAL_AMOUNT, p.ORDER_DATE, "
                     + "pi.PRODUCT_ID, pr.NAME AS PRODUCT_NAME, pr.PRICE, pi.QUANTITY AS BUY "
                     + "FROM payment p "
                     + "JOIN payment_items pi ON p.PAYMENT_ID = pi.PAYMENT_ID "
                     + "JOIN product pr ON pi.PRODUCT_ID = pr.ID "
                     + "WHERE p.ACCOUNT_ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int paymentId = rs.getInt("PAYMENT_ID");

                Payment payment = paymentMap.get(paymentId);
                if (payment == null) {
                    payment = new Payment();
                    payment.setPaymentId(paymentId);
                    payment.setTotalAmount(rs.getFloat("TOTAL_AMOUNT"));
                    payment.setDiscountAmount(rs.getFloat("DISCOUNT_AMOUNT"));
                    payment.setFinalAmount(rs.getFloat("FINAL_AMOUNT"));
                    payment.setOrderDate(rs.getString("ORDER_DATE"));
                    payment.setProductList(new ArrayList<>());
                    paymentMap.put(paymentId, payment);
                }

                Product product = new Product();
                product.setId(rs.getInt("PRODUCT_ID"));
                product.setName(rs.getString("PRODUCT_NAME"));
                product.setPrice(String.valueOf(rs.getFloat("PRICE")));

                int buyQuantity = rs.getInt("BUY");


                payment.getProductList().add(product);
            }
        }
        return payments;
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
                
                Product product = productDAO.getProductByID(productId);
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

        String sql = "SELECT p.PAYMENT_ID, p.TOTAL_AMOUNT, p.DISCOUNT_AMOUNT, p.FINAL_AMOUNT, p.ORDER_DATE, pi.PRODUCT_ID, pr.NAME AS PRODUCT_NAME, pr.PRICE, f.BUY FROM payment p JOIN payment_items pi ON p.PAYMENT_ID = pi.PAYMENT_ID JOIN product pr ON pi.PRODUCT_ID = pr.ID JOIN favorite f ON f.PRODUCT_ID = pi.PRODUCT_ID AND f.ACCOUNT_ID = p.ACCOUNT_ID WHERE p.ACCOUNT_ID = ? ";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int paymentId = rs.getInt("PAYMENT_ID");
                
                Payment payment = paymentMap.get(paymentId);
                if (payment == null) {
                    payment = new Payment();
                    payment.setPaymentId(paymentId);
                    payment.setTotalAmount(rs.getFloat("TOTAL_AMOUNT"));
                    payment.setDiscountAmount(rs.getFloat("DISCOUNT_AMOUNT"));
                    payment.setFinalAmount(rs.getFloat("FINAL_AMOUNT"));
                    payment.setOrderDate(rs.getString("ORDER_DATE"));
                    payment.setProductList(new ArrayList<>()); 
                    paymentMap.put(paymentId, payment);
                }
                
                Product product = new Product();
                product.setId(rs.getInt("PRODUCT_ID"));
                product.setName(rs.getString("PRODUCT_NAME"));
                product.setPrice(String.valueOf(rs.getFloat("PRICE")));
                
                int buyQuantity = rs.getInt("BUY"); 
                float productPrice = rs.getFloat("PRICE");
                float totalPrice = productPrice * buyQuantity;
                
                Favorite favorite = new Favorite(accountId, rs.getInt("PRODUCT_ID"));
                favorite.setBuy(buyQuantity);
                favorite.setTotalPrice(totalPrice); 
                
                payment.getProductList().add(product);
            }
        }
        
        payments.addAll(paymentMap.values());
        return payments;
    }
    //public List<PaymentItem> getPaymentItemsByPaymentId(int paymentId) {
        //List<PaymentItem> paymentItems = new ArrayList<>();
        //String query = "SELECT product_id, quantity, total_price FROM payment_items WHERE payment_id = ?";

        //try (PreparedStatement stmt = connection.prepareStatement(query)) {
            //stmt.setInt(1, paymentId); 
            //ResultSet rs = stmt.executeQuery();

            //while (rs.next()) {
                //int productId = rs.getInt("product_id");
                //int quantity = rs.getInt("quantity");
                //float totalPrice = rs.getFloat("total_price");

                //PaymentItem item = new PaymentItem(productId, quantity, totalPrice);
                //paymentItems.add(item);
            ////}
        //} catch (SQLException e) {
            //e.printStackTrace();
        //}

        //return paymentItems;
    //}

    public Payment getPaymentByIdAndAccountId(int paymentId, int accountId) {
        String query = "SELECT * FROM payment WHERE PAYMENT_ID = ? AND ACCOUNT_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, paymentId);
            stmt.setInt(2, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentId(rs.getInt("PAYMENT_ID"));
                    payment.setAccountId(rs.getInt("ACCOUNT_ID"));
                    payment.setTotalAmount(rs.getFloat("TOTAL_AMOUNT"));
                    payment.setDiscountAmount(rs.getFloat("DISCOUNT_AMOUNT"));
                    payment.setFinalAmount(rs.getFloat("FINAL_AMOUNT"));
                    payment.setPayStatus(rs.getString("PAY_STATUS"));
                    payment.setPayMethod(rs.getString("PAY_METHOD"));
                    payment.setOrderDate(rs.getString("ORDER_DATE"));
                    return payment;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<PaymentItem> getPaymentItemsByPaymentId(int paymentId) {
        List<PaymentItem> items = new ArrayList<>();
        String query = "SELECT * FROM payment_items WHERE PAYMENT_ID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, paymentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("PRODUCT_ID");
                    int quantity = rs.getInt("QUANTITY");
                    float totalPrice = rs.getFloat("TOTAL_PRICE");
                    System.out.println("Retrieved: Product ID = " + productId + ", Quantity = " + quantity + ", Total Price = " + totalPrice);
                    
                    PaymentItem item = new PaymentItem();
                    item.setPaymentItemId(rs.getInt("PAYMENT_ITEM_ID"));
                    item.setPaymentId(rs.getInt("PAYMENT_ID"));
                    item.setProductId(productId);
                    item.setQuantity(quantity);
                    item.setTotalPrice(totalPrice);
                    
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    public int getTotalOrdersByAccountId(int accountId) {
        int totalOrders = 0;
        String sql = "SELECT COUNT(PAYMENT_ID) AS TOTAL_ORDERS FROM payment WHERE ACCOUNT_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalOrders = rs.getInt("TOTAL_ORDERS");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalOrders;
    }
    
    public int getTotalQuantityByAccountId(int accountId) {
        int totalQuantity  = 0;
        String sql = "SELECT SUM(pi.QUANTITY) AS total_quantity FROM payment_items pi JOIN payment p ON pi.PAYMENT_ID = p.PAYMENT_ID WHERE p.ACCOUNT_ID = ? GROUP BY p.ACCOUNT_ID";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	totalQuantity  = rs.getInt("total_quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalQuantity;
    }
    public float getTotalSpentByAccountId(int accountId) {
        float totalSpent = 0;
        String sql = "SELECT SUM(FINAL_AMOUNT) AS total_spent FROM payment WHERE ACCOUNT_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalSpent = rs.getFloat("total_spent");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalSpent;
    }
    public List<Payment> getPaymentDataByAccountId(int accountId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT ORDER_DATE, SUM(FINAL_AMOUNT) AS total_amount FROM payment WHERE ACCOUNT_ID = ? GROUP BY ORDER_DATE";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setOrderDate(rs.getString("ORDER_DATE"));
                    payment.setFinalAmount(rs.getFloat("total_amount"));
                    payments.add(payment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return payments;
    }
    public float getTotalAmountByAccountAndDate(int accountId, String orderDate) throws SQLException {
        String sql = "SELECT SUM(FINAL_AMOUNT) AS total_amount FROM payment WHERE account_id = ? AND order_date = ?";
        float totalAmount = 0.0f;
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setString(2, orderDate);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalAmount = rs.getFloat("total_amount");
                    if (rs.wasNull()) {
                        totalAmount = 0.0f;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalAmount;
    }
}
