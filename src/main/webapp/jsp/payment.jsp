<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="author" content="Untree.co">
  <link rel="shortcut icon" href="favicon.png">

  <meta name="description" content="" />
  <meta name="keywords" content="bootstrap, bootstrap4" />
		<link rel="stylesheet" href="css/search-page.css"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="js/search-page.js"></script>
		<!-- Bootstrap CSS -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/tiny-slider.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
				<link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
		
		<title>Thanks </title>
	</head>

	<body>
		<header>
            <div class="search__container">
	            <form action="ProductServlet">
	                <input class="search__input" name="searchKey">
	                <input type="hidden" name="action" value="Search">
	                <button class="search__button">
	                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
	                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
	                      </svg>
	                </button>
	            </form>
            </div>
            <p class="header__title">Harmoni Home</p>
            <ul class="header__container">
                <li class="header__item">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                    </svg>
                    <p onclick="location.href='${pageContext.request.contextPath}/InfoServlet'">Account</p>
                </li>
                <li class="header__item">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                        <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
                    </svg>
                    <p onclick="location.href='${pageContext.request.contextPath}/FavoriteServlet'">Favorite</p>
                </li>
                <li class="header__item">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 16 16">
                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                    </svg>
                    <p onclick="location.href='${pageContext.request.contextPath}/CartServlet'">Cart</p>
                </li>
            </ul>
        </header>
		<!-- Start Header/Navigation 
		<nav class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" aria-label="Furni navigation bar">

			<div class="container">
				<a class="navbar-brand" href="index.html">Harmoni Home<span>.</span></a>

				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarsFurni">
					<ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
						<li class="nav-item ">
							<a class="nav-link" href="index.html">Home</a>
						</li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/shop.jsp">Shop</a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/voucher.jsp">Coupon</a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/HistoryServlet">History</a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/FavoriteServlet">Favorite</a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/contact.jsp">Contact us</a></li>
					</ul>

					<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
						<li><a class="nav-link" href="${pageContext.request.contextPath}/InfoServlet"><img src="${pageContext.request.contextPath}/images/user.svg"></a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/CartServlet"><img src="${pageContext.request.contextPath}/images/cart.svg"></a></li>
					</ul>
				</div>
			</div>
				
		</nav> -->
		<!-- End Header/Navigation -->

		<!-- Start Hero Section 
			<div class="hero">
				<div class="container">
					<div class="row justify-content-between">
						<div class="col-lg-5">
							<div class="intro-excerpt">
								<h1>Checkout</h1>
							</div>
						</div>
						<div class="col-lg-7">
							
						</div>
					</div>
				</div>
			</div> -->
		<!-- End Hero Section -->

		<div class="untree_co-section">
		    <div class="container">
		      
		      <div class="row">
		        <div class="col-md-6 mb-5 mb-md-0">
		          <h2 class="h3 mb-3 text-black">Thông tin chi tiết</h2>
		          <div class="p-3 p-lg-5 border bg-white">
		          
		          <div class="form-group row">
		              <div class="col-md-6">
		                <label for="c_fname" class="text-black">Họ và tên <span class="text-danger">*</span></label>
        				<input type="text" class="form-control" id="username" name="username" value="${account.username}" style="width: 170%;" readonly>
		              </div>

		            </div>
		          
		          
		          
		            <div class="form-group">
		              <label for="c_sex" class="text-black">Giới tính <span class="text-danger">*</span></label>
		              			<select id="gender" name="gender" class="form-control" style="width: 80%;" readonly>
        							<option value="" ${account.gender == "" ? "selected" : ""}>Chọn giới tính</option>
        							<option value="Male" ${account.gender == "Male" ? "selected" : ""}>Male</option>
        							<option value="Female" ${account.gender == "Female" ? "selected" : ""}>Female</option>
    							</select>
		            </div>

		            <div class="form-group row">
		              <div class="col-md-12">
		                <label for="c_bday" class="text-black">Ngày tháng năm sinh </label>
		                <input type="text" class="form-control" id="birthday" name="birthday" style="width: 80%;" value="${account.birthday}" readonly>
		              </div>
		            </div>

		            <div class="form-group row">
		              <div class="col-md-12">
		                <label for="c_address" class="text-black">Địa chỉ <span class="text-danger">*</span></label>
		                <input type="text" class="form-control" id="address" name="address" value="${account.address}" style="width: 80%;" readonly>
		              </div>
		            </div>


		            <div class="form-group row mb-5">
		              <div class="col-md-6">
		                <label for="c_email_address" class="text-black">Email<span class="text-danger">*</span></label>
		                <input type="text" class="form-control" id="email" name="email" value="${account.email}" readonly>
		              </div>
		              <div class="col-md-6">
		                <label for="c_phone" class="text-black">Phone <span class="text-danger">*</span></label>
		                				<input type="text" class="form-control" id="phone" name="phone" value="${account.phone}" readonly>
		              </div>
		            </div>


		            <!--<div class="form-group">
		              <label for="c_order_notes" class="text-black">Ghi chú đơn hàng</label>
		              <textarea name="c_order_notes" id="c_order_notes" cols="30" rows="5" class="form-control" placeholder="Ghi chú vào đây..."></textarea>
		            </div>-->

		          </div>
		        </div>
		        <div class="col-md-6">

		          <!--<div class="row mb-5">
		            <div class="col-md-12">
		              <h2 class="h3 mb-3 text-black">Mã giảm giá</h2>
		              <div class="p-3 p-lg-5 border bg-white">

		                <label for="c_code" class="text-black mb-3">Nhập mã giảm giá nếu có</label>
		                <div class="input-group w-75 couponcode-wrap">
		                  <input type="text" class="form-control me-2" id="c_code" placeholder="Mã giảm giá" aria-label="Coupon Code" aria-describedby="button-addon2">
		                  <div class="input-group-append">
		                    <button class="btn btn-black btn-sm" type="button" id="button-addon2">Apply</button>
		                  </div>
		                </div>
						<div id="coupon-feedback" class="mt-2 text-danger" style="display: none;">Mã giảm giá đã được áp dụng</div>
		              </div>
		            </div>
		          </div>-->
				
				
				<form action="${pageContext.request.contextPath}/PaymentServlet" method="post">
				<input type="hidden" name="action" value="confirmPayment" />
		          <div class="row mb-5">
		            <div class="col-md-12">
		              <h2 class="h3 mb-3 text-black">Your Order</h2>
		              <div class="p-3 p-lg-5 border bg-white">
		                <table class="table site-block-order-table mb-5">
		                  <thead>
		                    <th>Product</th>
		                    <th>Total</th>
		                  </thead>
		                  <tbody>
		                  
						<c:forEach var="product" items="${products}">
    <c:forEach var="favorite" items="${favorites}">
        <c:if test="${product.id == favorite.productId}">
            <tr>
                <td>${product.name} <strong class="mx-2">x</strong> ${favorite.buy}</td>
                <td>$${product.price * favorite.buy}</td>
            </tr>
        </c:if>
    </c:forEach>
</c:forEach>


		                  
		                 
		                  
		                    
		                    <tr>
		                      <td class="text-black font-weight-bold"><strong>Tổng giá trị giỏ hàng</strong></td>
		                      <td class="text-black">$${totalPrice}</td>
		                    </tr>
		                    <tr>
  								<td class="text-black font-weight-bold"><strong>Giá trị được khấu trừ</strong></td>
  								<td class="text-black strike-orange">$0.00</td>
							</tr>

		                    <tr>
		                      <td class="text-black font-weight-bold"><strong>Tổng giá trị giỏ hàng</strong></td>
		                      <td class="text-black font-weight-bold"><strong>$${totalPrice}</strong></td>
		                    </tr>
		                  </tbody>
		                </table>

		                <div class="border p-3 mb-3">
		                  <h3 class="h6 mb-0"><a class="d-block" data-bs-toggle="collapse" href="#collapsebank" role="button" aria-expanded="false" aria-controls="collapsebank">Chuyển khoản trực tiếp</a></h3>

		                  <div class="collapse" id="collapsebank">
		                    <div class="py-2">
		                      <p class="mb-0">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
		                    </div>
		                  </div>
		                </div>

		                <div class="border p-3 mb-3">
		                  <h3 class="h6 mb-0"><a class="d-block" data-bs-toggle="collapse" href="#collapsecheque" role="button" aria-expanded="false" aria-controls="collapsecheque">Cheque Payment</a></h3>

		                  <div class="collapse" id="collapsecheque">
		                    <div class="py-2">
		                      <p class="mb-0">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
		                    </div>
		                  </div>
		                </div>

		                <div class="border p-3 mb-5">
		                  <h3 class="h6 mb-0"><a class="d-block" data-bs-toggle="collapse" href="#collapsepaypal" role="button" aria-expanded="false" aria-controls="collapsepaypal">Paypal</a></h3>

		                  <div class="collapse" id="collapsepaypal">
		                    <div class="py-2">
		                      <p class="mb-0">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
		                    </div>
		                  </div>
		                </div>

		                <div class="form-group">
		                  <button class="btn btn-black btn-lg py-3 btn-block" type="submit">Confirm</button>
		                </div>

		              </div>
		            </div>
		          </div>
				</form>
		        </div>
		      </div>
		      <!-- </form> -->
		    </div>
		  </div>

		<!-- Start Footer Section
		<footer class="footer-section">
			<div class="container relative">

				<div class="sofa-img">
					<img src="${pageContext.request.contextPath}/images/product-2.png" alt="Image" class="img-fluid">
				</div>

				<div class="row">
					<div class="col-lg-8">
						<div class="subscription-form">
							<h3 class="d-flex align-items-center"><span class="me-1"><img src="${pageContext.request.contextPath}/images/envelope-outline.svg" alt="Image" class="img-fluid"></span><span>Đăng kí để nhận thông báo từ chúng tôi</span></h3>

							<form action="#" class="row g-3">
								<div class="col-auto">
									<input type="text" class="form-control" placeholder="Nhập họ tên">
								</div>
								<div class="col-auto">
									<input type="email" class="form-control" placeholder="Nhập địa chỉ email">
								</div>
								<div class="col-auto">
									<button class="btn btn-primary">
										<span class="fa fa-paper-plane"></span>
									</button>
								</div>
							</form>

						</div>
					</div>
				</div>

				<div class="row g-5 mb-5">
					<div class="col-lg-4">
						<div class="mb-4 footer-logo-wrap"><a href="#" class="footer-logo">Harmoni Home<span>.</span></a></div>
						<p class="mb-4">Tạo nên những không gian sống độc đáo, phản ánh cá tính của bạn. Chúng tôi tin rằng ngôi nhà là nơi để bạn thể hiện bản thân, và chúng tôi sẽ giúp bạn biến giấc mơ đó thành hiện thực.</p>

						<ul class="list-unstyled custom-social">
							<li><a href="#"><span class="fa fa-brands fa-facebook-f"></span></a></li>
							<li><a href="#"><span class="fa fa-brands fa-twitter"></span></a></li>
							<li><a href="#"><span class="fa fa-brands fa-instagram"></span></a></li>
							<li><a href="#"><span class="fa fa-brands fa-linkedin"></span></a></li>
						</ul>
					</div>

					<div class="col-lg-8">
						<div class="row links-wrap">
							<div class="col-6 col-sm-6 col-md-3">
								<ul class="list-unstyled">
									<li><a href="#">About us</a></li>
									<li><a href="#">Services</a></li>
									<li><a href="#">Blog</a></li>
									<li><a href="#">Contact us</a></li>
								</ul>
							</div>

							<div class="col-6 col-sm-6 col-md-3">
								<ul class="list-unstyled">
									<li><a href="#">Support</a></li>
									<li><a href="#">Knowledge base</a></li>
									<li><a href="#">Live chat</a></li>
								</ul>
							</div>

							<div class="col-6 col-sm-6 col-md-3">
								<ul class="list-unstyled">
									<li><a href="#">Jobs</a></li>
									<li><a href="#">Our team</a></li>
									<li><a href="#">Leadership</a></li>
									<li><a href="#">Privacy Policy</a></li>
								</ul>
							</div>

							<div class="col-6 col-sm-6 col-md-3">
								<ul class="list-unstyled">
									<li><a href="#">Nordic Chair</a></li>
									<li><a href="#">Kruzo Aero</a></li>
									<li><a href="#">Ergonomic Chair</a></li>
								</ul>
							</div>
						</div>
					</div>

				</div>

				<div class="border-top copyright">
					<div class="row pt-4">
						<div class="col-lg-6">
							<p class="mb-2 text-center text-lg-start">Copyright &copy;<script>document.write(new Date().getFullYear());</script>. All Rights Reserved. &mdash; Designed by Double Phuc
            </p>
						</div>

						<div class="col-lg-6 text-center text-lg-end">
							<ul class="list-unstyled d-inline-flex ms-auto">
								<li class="me-4"><a href="#">Terms &amp; Conditions</a></li>
								<li><a href="#">Privacy Policy</a></li>
							</ul>
						</div>

					</div>
				</div>

			</div>
		</footer> -->
		<!-- End Footer Section -->	

		<script>
  document.getElementById('button-addon2').addEventListener('click', function() {
    const feedback = document.getElementById('coupon-feedback');
    feedback.style.display = 'block'; 
  });
</script>

<script>
document.getElementById('c_code').addEventListener('input', function() {
	  document.getElementById('coupon-feedback').style.display = 'none';
	});
</script>

		<script src="js/bootstrap.bundle.min.js"></script>
		<script src="js/tiny-slider.js"></script>
		<script src="js/custom.js"></script>
	</body>

</html>
