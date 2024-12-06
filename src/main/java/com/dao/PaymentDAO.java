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
            // Tính tổng giá trị cho các sản phẩm trong payment_items
            float totalAmount = 0;

            List<Favorite> favorites = favoriteDAO.getAllCartByStatus(pay.getAccountId());

            String query = "INSERT INTO payment (ACCOUNT_ID, PAY_STATUS, PAY_METHOD, ORDER_DATE) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pay.getAccountId());
            stmt.setString(2, pay.getPayStatus());
            stmt.setString(3, pay.getPayMethod());
            stmt.setString(4, pay.getOrderDate());
            stmt.executeUpdate();

            // Lấy ID của payment đã chèn
            ResultSet rs = stmt.getGeneratedKeys();
            int paymentId = 0;
            if (rs.next()) {
                paymentId = rs.getInt(1);
            }

            // Chèn sản phẩm vào payment_items và tính tổng giá trị
            for (Favorite favorite : favorites) {
                for (Product product : products) {
                    if (product.getId() == favorite.getProductId()) {
                        // Tính total_price cho mỗi sản phẩm
                        float totalPrice = favorite.getBuy() * Float.parseFloat(product.getPrice());
                        totalAmount += totalPrice;  // Cộng dồn vào totalAmount


                        String productQuery = "INSERT INTO payment_items (PAYMENT_ID, PRODUCT_ID, QUANTITY, UNIT_PRICE, TOTAL_PRICE) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement productStmt = connection.prepareStatement(productQuery);
                        productStmt.setInt(1, paymentId);
                        productStmt.setInt(2, product.getId());
                        productStmt.setInt(3, favorite.getBuy());  // Số lượng
                        productStmt.setFloat(4, Float.parseFloat(product.getPrice()));  // Giá sản phẩm
                        productStmt.setFloat(5, totalPrice);  // Tổng giá trị cho sản phẩm
                        productStmt.executeUpdate();  // Thực thi câu lệnh chèn
                    }
                }
            }

            // Cập nhật lại total_amount trong bảng payment sau khi chèn payment_items
            String updateQuery = "UPDATE payment SET TOTAL_AMOUNT = ? WHERE PAYMENT_ID = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                updateStmt.setFloat(1, totalAmount);  // Cập nhật total_amount
                updateStmt.setInt(2, paymentId);  // ID của payment
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

                // Lấy giá trị quantity (buy) từ bảng payment_items
                int buyQuantity = rs.getInt("BUY");

                // Gán buyQuantity vào Favorite hoặc xử lý logic của bạn ở đây

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


}
