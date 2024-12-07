<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
    <head>
        <title>Main Menu</title>
        <link rel="stylesheet" href="css/sign-on.css" >
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <div class="background__image">
            <div class = "background__flur">
                <div class="login-container">
                    <h2>Sign on</h2>
                    <form action = "${pageContext.request.contextPath}/LogInServlet">
                        <label for="username">User Name</label>
                        <input type="text" id="username" name="username" placeholder="Enter your username">

                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" placeholder="Enter your password">
                        
                        <button type="submit" name="action" value = "signOn">Sign on</button>
                        <button style = "margin-top: 10px;" name="action" value="signInRedirect">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>