<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Chọn số lượng sản phẩm</title>
	    <link rel="stylesheet" href="css/product-detail.css">
	    <script src="product-detail.js" defer></script>
	</head>
	<body>
		
	    <div class="infor__sub__container">
	        <div class="quantity-container">
	            <button class="decrease" onclick="decreaseQuantity()">-</button>
	            <input type="number" id="quantity" name="quantity" value="1" readonly>
	            <button class="increase" onclick="increaseQuantity()">+</button>
	        </div>
	    </div>
	</body>
</html>
