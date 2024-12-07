<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chọn số lượng sản phẩm</title>
    <style>
        button {
            margin: 5px;
            padding: 10px;
        }
    </style>
</head>
<body>

    <h2>Chọn số lượng sản phẩm</h2>

    <!-- Hiển thị số lượng -->
    <label for="quantity">Số lượng: </label>
    <span id="quantity">1</span>

    <!-- Nút tăng giảm -->
    <button onclick="decreaseQuantity()">Giảm</button>
    <button onclick="increaseQuantity()">Tăng</button>

    <!-- Nút thêm vào giỏ hàng -->
    <form id="addToCartForm" action="AddToCartServlet" method="post">
        <input type="hidden" id="cartQuantity" name="quantity" value="1">
        <button type="submit">Add to Cart</button>
    </form>

    <script>
        // Hàm giảm số lượng
        function decreaseQuantity() {
            const quantity = document.getElementById("quantity");
            let quantityValue = parseInt(quantity.textContent);
            if (quantityValue > 1) {
                quantity.textContent = quantityValue - 1;
                document.getElementById("cartQuantity").value = quantity.textContent;
            }
        }

        // Hàm tăng số lượng
        function increaseQuantity() {
            const quantity = document.getElementById("quantity");
            let quantityValue = parseInt(quantity.textContent);
            quantity.textContent = quantityValue + 1;
            document.getElementById("cartQuantity").value = quantity.textContent;
        }
    </script>

</body>
</html>
