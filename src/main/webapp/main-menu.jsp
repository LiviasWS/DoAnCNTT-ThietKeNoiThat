<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Main Menu</title>
        <link rel="stylesheet" type="text/css" href="css/main-menu.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="js/main-menu.js"></script>
    </head>
    <body>
        <div class="background">
            <div class="background__flur"></div>
        </div>
        <div class="header">
            <ul class="header__container">
                <li class="header__item header__item--logo">HarmoniHome</li>
            </ul>
            <ul class="header__container header__container--title">
                <li class="header__item header__item--title">Main Menu</li>
                <li class="header__item header__item--title">Services</li>
                <li class="header__item header__item--title">Projects</li>
                <li class="header__item header__item--title">Contact</li>
            </ul>
        </div>
        <div class="background__title">
            <p class="background__title__text">HarmoniHome Interior Services<br>Building your dream home with you</p>
        </div>
        <c:forEach var="collection" items="${collectionBestSellerMap}">
            <div class="mainContent">
                <div class="mainContent__container"></div>
                <div class="mainContent__image" style="background-image: url('${pageContext.request.contextPath}${collection.key.image}');"></div>
                <p class="mainContent__title">${collection.key.title}</p>
                <div class="mainContent__content">
                    <p>${collection.key.description}</p>
                    <hr>
                </div>
                <a href="${pageContext.request.contextPath }/ProductServlet?collection=${collection.key.id}&action=ListProductByCollection">
	                <button class="mainContent__directionContainer" type="submit">
	                    <p style="margin-top: 10px; margin-left: 10px">Inspiration gallery</p>
	                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
	                        <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8"/>
	                    </svg>
	                </button>
                </a>
                <div class="mainContent__bestSeller__title">
                	<hr style="border: 2px solid black !important; width: 80%; margin-left: 10%;">
                	<p>Best Seller</p>
            	</div>
            	<div class="mainContent__bestSeller__container">
		            <c:forEach var= "bestSeller" items="${collection.value}">
			                <div class="mainContent__bestSeller__item">
			                    <div class="mainContent__bestSeller__image" style="background-image: url('${pageContext.request.contextPath}${bestSeller.image}');"></div>
			                    <div class="mainContent__bestSeller__item__infor">
			                        <p class="mainContent__bestSeller__item__name">${bestSeller.name}</p>
			                        <c:forEach var= "i" begin="1" end="${bestSeller.quantity}" step="1">
			                        	<svg xmlns="http://www.w3.org/2000/svg" width="13" height="13" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
			                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
			                        </svg>
			                        </c:forEach>
			                        <p class="mainContent__bestSeller__item__price">${bestSeller.price}</p>
			                    </div>
			                </div>
					</c:forEach>
				</div>
				<hr style="border: 2px solid black !important; width: 80%; margin-left: 10%; position: absolute; top: 1230px;">
			</div>
        </c:forEach>
    </body>
</html>
