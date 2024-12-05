<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CSS -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/tiny-slider.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
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
								<h1>Information</h1>
								
							</div>
						</div>

						</div>
					</div>
				</div>

		<!-- End Hero Section -->




			<!-- Start Why Choose Us Section -->
		<div class="why-choose-section">
			<div class="container">
			<form action="InfoServlet?action=update" method="post" enctype="multipart/form-data">
    			<input type="hidden" name="action" value="update">
    			<input type="hidden" name="id" value="${account.id}">
				<div class="row justify-content-between align-items-center">
					<div class="col-lg-6">
						<h2 class="section-title2">Xin chào, ${account.username}</h2>
						
					
					
					
						<div class="row my-5">
							<div class="col-6 col-md-6">
								<div class="feature">

									<div class="form-group row">
    									<div class="col-md-12">
        									<label for="c_fname" class="text-black">Họ và tên <span class="text-danger">*</span></label>
        									<input type="text" class="form-control" id="username" name="username" value="${account.username}" required>
    									</div>
									</div>

								</div>
							</div>

							<div class="col-6 col-md-6">
								<div class="feature">
									<label for="c_sex" class="text-black">Giới tính <span class="text-danger">*</span></label>
		              			<select id="gender" name="gender" class="form-control" style="width: 80%;" required>
        							<option value="" ${account.gender == "" ? "selected" : ""}>Chọn giới tính</option>
        							<option value="Male" ${account.gender == "Male" ? "selected" : ""}>Male</option>
        							<option value="Female" ${account.gender == "Female" ? "selected" : ""}>Female</option>
    							</select>

								</div>
							</div>
							
							<div class="col-6 col-md-6">
								<div class="feature">
									<div class="col-md-12">
		                				<label for="c_bday" class="text-black">Ngày tháng năm sinh </label>
		                				<input type="text" class="form-control" id="birthday" name="birthday" style="width: 80%;" value="${account.birthday}">

		              				</div>
								</div>
							</div>
							
							


							<div class="col-6 col-md-6">
								<div class="feature">
									<div class="col-md-12">
		                				<label for="c_phone" class="text-black">Phone <span class="text-danger">*</span></label>
		                				<input type="text" class="form-control" id="phone" name="phone" value="${account.phone}" required>
		              				</div>
								</div>
							</div>
							
							<div class="col-6 col-md-6">
								<div class="feature">
									<div class="col-md-12">
		                				<label for="c_email_address" class="text-black">Email<span class="text-danger">*</span></label>
		                				<input type="text" class="form-control" id="email" name="email" value="${account.email}" required>
		              				</div>
								</div>
							</div>
							<div class="col-6 col-md-6">
								<div class="feature">
									<div class="col-md-12">
		                				<label for="c_address" class="text-black">Địa chỉ <span class="text-danger">*</span></label>
		                				<input type="text" class="form-control" id="address" name="address" value="${account.address}" required>
		              				</div>
								</div>
							</div>

						</div>
					</div>

					<div class="col-lg-5">
    				<div class="img-wrap">
        <!-- Show a placeholder or the currently saved image -->
        				<img id="preview-image" src="${pageContext.request.contextPath}/images/${account.image}" alt="Image" class="img-ava">
    				</div>
    			<button class="btn-change" type="button" id="button-change" onclick="document.getElementById('image-upload').click()">Change</button>
    			<input type="file" id="image-upload" name="image" style="display: none;" accept="image/*" onchange="previewImage(event)">
			</div>

					
					<div class="col-6 col-md-6">
								<div class="feature">
									<button class="btn-update" type="submit" id="button-update">Update</button>
								</div>
							</div>
					</div>
					</form>
				</div>
			</div>
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
		<script>
function previewImage(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('preview-image').src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
}
</script>
		
		<script src="js/bootstrap.bundle.min.js"></script>
		<script src="js/tiny-slider.js"></script>
		<script src="js/custom.js"></script>
</body>
</html>