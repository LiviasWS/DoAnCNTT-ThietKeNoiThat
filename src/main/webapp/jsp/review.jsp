<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- /*
* Bootstrap 5
* Template Name: Furni
* Template Author: Untree.co
* Template URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
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
		
		<title>Coupon</title>
	</head>
<body>


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
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/history.jsp">History</a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/favo.jsp">Favorite</a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/contact.jsp">Contact us</a></li>
					</ul>

					<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/info.jsp"><img src="${pageContext.request.contextPath}/images/user.svg"></a></li>
						<li><a class="nav-link" href="${pageContext.request.contextPath}/jsp/cart.jsp"><img src="${pageContext.request.contextPath}/images/cart.svg"></a></li>
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
								<h1>Review</h1>
							</div>
						</div>
						<div class="col-lg-7">
							
						</div>
					</div>
				</div>
			</div> -->
		<!-- End Hero Section -->



	<div class="review-wrapper">
        <div class="review-header">
            <a href="${pageContext.request.contextPath}/HistoryServlet" class="review-back-button">←</a>
            <h1>Đánh giá đơn hàng</h1>
        </div>

        <div class="rating-container">
            <div class="rating-stars">
                <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
            </div>
        </div>
        <div class="media-upload">
            <p>Thêm ít nhất 1 hình ảnh/video về sản phẩm</p>
            <div class="upload-buttons">
                <button class="media-button">
                <span><img src="${pageContext.request.contextPath}/images/camera.png" alt="Product"></span>
                <p>Hình ảnh</p>
                </button>
                <button class="media-button"><span><img src="${pageContext.request.contextPath}/images/video-camera.png" alt="Product"></span>
                <p>Video</p></button>
            </div>
        </div>
        <form class="feedback-form">
            <p>Viết đánh giá từ 50 ký tự</p>
            <textarea placeholder="Hãy nêu cảm nghĩ của bạn về sản phẩm của chúng tôi"></textarea>
            <button type="submit" class="submit-review-button">Gửi</button>
        </form>

    </div>
</body>
</html>