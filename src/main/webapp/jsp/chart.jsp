<%@ page import="java.util.*,com.model.Cart, com.model.Payment, com.model.PaymentItem" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>THỐNG KÊ</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap2.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/datepicker3.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
	
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	
	
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">Username</div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>

		
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">THỐNG KÊ</h1>
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<div class="quantitychart" id="quantitychart-teal"><span class="order">${totalOrders}</span></div>
						<p>NEW ORDER</p>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<div class="quantitychart" id="quantitychart-blue" ><span class="order">${totalQuantity}</span></div>
						<p>PURCHASED PRODUCTS</p>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<div class="quantitychart" id="quantitychart-orange"><span class="order">${favoQuantity}</span></div>
						<p>LIKED PRODUCTS</p>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<div class="quantitychart" id="quantitychart-red"><span class="order">${totalSpent}</span></div>
						<p>TOTAL</p>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						Thống kê chi tiêu của bạn
						<ul class="pull-right panel-settings panel-button-tab-right">
							<li class="dropdown"><a class="pull-right dropdown-toggle" data-toggle="dropdown" href="#">
								<em class="fa fa-cogs"></em>
							</a>
								<ul class="dropdown-menu dropdown-menu-right">
									<li>
										<ul class="dropdown-settings">
											<li><a href="#">
												<em class="fa fa-cog"></em> Settings 1
											</a></li>
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> Settings 2
											</a></li>
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> Settings 3
											</a></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
						<span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="main-chart" id="line-chart" height="200" width="600"></canvas>
							<script type="text/javascript">
    // Lấy dữ liệu từ JSP và chuyển thành mảng JavaScript
    var paymentDates = <%= request.getAttribute("paymentDates") %>;
    var paymentAmounts = <%= request.getAttribute("paymentAmounts") %>;

    console.log(paymentDates);
    console.log(paymentAmounts);

    var lineChartData = {
        labels: ["03/12/2024","05/12/2024","09/12/2024","11/12/2024","12/12/2024","13/12/2024","14/12/2024"],
        datasets: [
            {
                label: "Chi tiêu theo ngày",
                fillColor: "rgba(48, 164, 255, 0.2)",
                strokeColor: "rgba(48, 164, 255, 1)",
                pointColor: "rgba(48, 164, 255, 1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(48, 164, 255, 1)",
                data: paymentAmounts
            }
        ]
    };

    var ctx = document.getElementById("line-chart").getContext("2d");
    var lineChart = new Chart(ctx).Line(lineChartData);
</script>
							
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		
		
			<div class="col-sm-12">
				<p class="back-link">Liên hệ thêm ở <a href="https://www.facebook.com/phucphan.156">Phuc</a></p>
			</div>
		</div><!--/.row-->
	  

	<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chart.min.js"></script>
	<!--<script src="${pageContext.request.contextPath}/js/chart-data.js"></script>-->
	<script src="${pageContext.request.contextPath}/js/easypiechart.js"></script>
	<script src="${pageContext.request.contextPath}/js/easypiechart-data.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom2.js"></script>
	<script>
	window.onload = function () {
	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
	responsive: true,
	scaleLineColor: "rgba(0,0,0,.2)",
	scaleGridLineColor: "rgba(0,0,0,.05)",
	scaleFontColor: "#c5c7cc"
	});
	var chart2 = document.getElementById("bar-chart").getContext("2d");
	window.myBar = new Chart(chart2).Bar(barChartData, {
	responsive: true,
	scaleLineColor: "rgba(0,0,0,.2)",
	scaleGridLineColor: "rgba(0,0,0,.05)",
	scaleFontColor: "#c5c7cc"
	});
	var chart3 = document.getElementById("doughnut-chart").getContext("2d");
	window.myDoughnut = new Chart(chart3).Doughnut(doughnutData, {
	responsive: true,
	segmentShowStroke: false
	});
	var chart4 = document.getElementById("pie-chart").getContext("2d");
	window.myPie = new Chart(chart4).Pie(pieData, {
	responsive: true,
	segmentShowStroke: false
	});

};
	</script>	
</body>
</html>