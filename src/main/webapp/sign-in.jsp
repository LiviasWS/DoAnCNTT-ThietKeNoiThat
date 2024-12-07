<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign In</title>
        <link rel="stylesheet" href="css/sign-in.css" >
    </head>
    <body>
        <div class="background__image">
            <div class="background__flur">
                <div class="form-container">
                    <h2>Sign in</h2>
                    <form action="${pageContext.request.contextPath}/LogInServlet" method="POST">
                        <!-- Username -->
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" placeholder="Enter your username" required>
                        </div>
                        <!-- Password -->
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" placeholder="Enter your password" required>
                        </div>
                        <!-- Address -->
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" id="address" name="address" placeholder="Enter your address">
                        </div>
                        <!-- Email -->
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" placeholder="Enter your email" required>
                        </div>
                        <!-- Birthday -->
                        <div class="form-group">
                            <label for="birthday">Birthday</label>
                            <input type="date" id="birthday" name="birthday" required>
                        </div>
                        <!-- Gender -->
                        <div class="form-group gender">
                            <label>Gender</label>
                            <label>
                                <input type="radio" name="gender" value="male" required> Male
                            </label>
                            <label>
                                <input type="radio" name="gender" value="female" required> Female
                            </label>
                            <label>
                                <input type="radio" name="gender" value="other" required> Other
                            </label>
                        </div>
                        <!-- Phone -->
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" required>
                        </div>
                        <!-- Submit -->
                        <button type="submit" name="action" value="signIn">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
