<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Page</title>
        <link rel="stylesheet" href="css/search-page.css"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="search-page.js"></script>
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
        <hr>
        <p class="result__title">Showing results for "${searchKey}" <p style="margin-left: 2.5%">(${count} results)</p>
        <input type="hidden" name="searchKey" value= "${searchKey}">
        <div class="main__container">
            <form id="filterForm" action="${pageContext.request.contextPath}/ProductServlet" class="filter__container">
            	<input type="hidden" name="action" value="filter">
            	<input type="hidden" name="searchKey" value="${searchKey}">
                <p class="filter__header">Color</p>
                <div class="filter__item__container filter__item__container--common">
	                <c:forEach var="color" items="${colors}">
	                    <div class="filter__item__common">
	                    	<c:choose>
	                    		<c:when test="${colorMap[color.name]=='checked'}">
	                    			<input type="checkbox" name="color" value="${color.name}" checked onclick="submitFilterForm()">
	                    		</c:when>
	                    		<c:otherwise>
	                    			<input type="checkbox" name="color" value="${color.name}" onclick="submitFilterForm()">
	                    		</c:otherwise>
	                    	</c:choose>
	                        <label>${color.name}</label><br>
	                    </div>
	                </c:forEach>
                </div>
                <div class="space__line"></div>
                <p class="filter__header">Categories</p>
                <div class="filter__item__container filter__item__container--common">
	                <c:forEach var="category" items="${categories}">
	                    <div class="filter__item__common">
		                    <c:choose>
	                    		<c:when test="${categoryMap[category.name]=='checked'}">
	                    			<input type="checkbox" name="category" value="${category.name}" checked onclick="submitFilterForm()">
	                    		</c:when>
	                    		<c:otherwise>
	                    			<input type="checkbox" name="category" value="${category.name}" onclick="submitFilterForm()">
	                    		</c:otherwise>
	                    	</c:choose>
	                    	<label>${category.name}</label><br>
                    	</div>
	                </c:forEach>
                </div>
                <div class="space__line"></div>
                <p class="filter__header">Collections</p>
                <div class="filter__item__container filter__item__container--common">
	                <c:forEach var="collection" items="${collections}">
	                    <div class="filter__item__common">
	                        <c:choose>
	                    		<c:when test="${collectionMap[collection.name]=='checked'}">
	                    			<input type="checkbox" name="collection" value="${collection.name}" checked onclick="submitFilterForm()">
	                    		</c:when>
	                    		<c:otherwise>
	                    			<input type="checkbox" name="collection" value="${collection.name}" onclick="submitFilterForm()">
	                    		</c:otherwise>
	                    	</c:choose>
	                        <label>${collection.name}</label><br>
	                    </div>
	                </c:forEach>
                </div>
                <div class="space__line"></div>
                <p class="filter__header">Material</p>
                <div class="filter__item__container filter__item__container--common">
                <c:forEach var="material" items="${materials}">
                    <div class="filter__item__common">
                    	<c:choose>
                    		<c:when test="${materialMap[material.name]=='checked'}">
                    			<input type="checkbox" name="materail" value="${material.name}" checked onclick="submitFilterForm()">
                    		</c:when>
                    		<c:otherwise>
                    			<input type="checkbox" name="material" value="${material.name}" onclick="submitFilterForm()">
                    		</c:otherwise>
                    	</c:choose>
                        <label>${material.name}</label><br>
                    </div>
                </c:forEach>
                </div>
            </form>
            <div class="list__container">
	            <c:forEach var= "product" items="${products}">
	                <div class="list__item" style="cursor: pointer;" onclick="location.href='${pageContext.request.contextPath }/ProductDetailServlet?id=${product.id}'">
	                    <div class="list__item__image" style="background-image: url('${pageContext.request.contextPath}${product.image}')"></div>
	                    <div class="list__item__infor__container">
	                        <p class="list__item__infor__name">${product.name}</p>
	                        <c:forEach var= "i" begin="1" end="${product.quantity}" step="1">
		                        <svg xmlns="http://www.w3.org/2000/svg" width="13" height="13" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
		                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
		                        </svg>
	                        </c:forEach>
	                        <p class="list__item__infor__price">${product.price}</p>
	                    </div>
	                </div>
				</c:forEach>        
            </div>
        </div>
    </body>
</html>