<%@ page import="java.util.*,com.model.Favorite, com.model.Product" %>
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="author" content="Untree.co">
  <link rel="shortcut icon" href="favicon.png">

  <meta name="description" content="" />
  <meta name="keywords" content="bootstrap, bootstrap4" />

		<!-- Bootstrap CSS -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/tiny-slider.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
		<title>FAVO</title>
	</head>

	<body>

		<!-- Start Header/Navigation -->
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
				
		</nav>
		<!-- End Header/Navigation -->

		<!-- Start Hero Section -->
			<div class="hero">
				<div class="container">
					<div class="row justify-content-between">
						<div class="col-lg-5">
							<div class="intro-excerpt">
								<h1>Favorite</h1>
								
							</div>
						</div>
						<div class="col-lg-7">
							<div class="hero-img-wrap">
								<img src="${pageContext.request.contextPath}/images/couch.png" class="img-fluid">
							</div>
						</div>
						</div>
					</div>
				</div>
		<!-- End Hero Section -->
		
				<%
        // Lấy danh sách các sản phẩm yêu thích từ request
        List<Favorite> favorites = (List<Favorite>) request.getAttribute("favorites");
		List<Product> products = (List<Product>) request.getAttribute("products");
        if (favorites != null && !favorites.isEmpty()) {
        	for (Favorite favorite : favorites) {
    	%>
	
		<div class="untree_co-section before-footer-section">
            <div class="container">
              <div class="row mb-5">
                <form class="col-md-12" method="post">
                  <div class="site-blocks-table">
                    <table class="table">
                      <thead>
                        <tr>
                          <th class="product-thumbnail">Hình ảnh</th>
                          <th class="product-name">Sản phẩm</th>
                          <th class="product-price">Giá tiền</th>
                          <th class="product-quantity">Stock</th>
                          <th class="product-total"></th>
                          <th class="product-remove"><img src="${pageContext.request.contextPath}/images/delete.png" alt="Image" class="img-remove"></th>
                        </tr>
                      </thead>
                      <tbody>
                      	<%
                                    for (Product product : products) {
                                        int productId = product.getId();
                                        
                                        // Lấy thông tin sản phẩm từ cơ sở dữ liệu, ví dụ: tên sản phẩm, giá, tình trạng tồn kho
                                        String productName = product.getName(); // Thay thế bằng thông tin thực từ database
                                        String productPrice = product.getPrice();  // Cập nhật thông tin giá thực từ cơ sở dữ liệu
                                        String stockStatus = "In Stock"; // Thay đổi theo tình trạng thực tế
                                %>
                        <tr>
                          <td class="product-thumbnail">
                            <img src="${pageContext.request.contextPath}/images/product-1.png" alt="Image" class="img-fluid">
                          </td>
                          <td class="product-name">
                            <h2 class="h5 text-black"><%= productName %></h2>
                          </td>
                          <td><%= productPrice %></td>
                          <td>
                            In Stock
        
                          </td>
                          <td>
        					<form action="FavoriteServlet" method="post">
    							<input type="hidden" name="productId" value="<%= productId %>">
    							<input type="hidden" name="action" value="update">
    							<button type="submit" class="btn-add-to-cart">Add to Cart</button>
							</form>

    						</td>                   
                          <td>
                                        <form action="FavoriteServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="productId" value="<%= productId %>">
                                            <input type="hidden" name="action" value="remove">
                                            <button type="submit" class="btn-unlike">
                                                <img src="${pageContext.request.contextPath}/images/unlike.png" alt="Remove">
                                            </button>
                                        </form>
                                    </td>
                        </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%
        }
    } else {
%>
        <p>Danh sách yêu thích của bạn trống!</p>
<%
    }
%>
		

		<!-- Start Footer Section -->
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
		</footer>
		<!-- End Footer Section -->	


		<script src="js/bootstrap.bundle.min.js"></script>
		<script src="js/tiny-slider.js"></script>
		<script src="js/custom.js"></script>
	</body>

</html>