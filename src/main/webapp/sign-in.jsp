<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <link rel="stylesheet" type="text/css" href="css/sign-in.css">
</head>
<body>
	<div class="background">
		<div class="background-flur">
		    <form class="registration-form" action="#" method="POST" enctype="multipart/form-data">
		        <h2>Register</h2>
		        <div class="form-group">
		            <label for="username">Username</label>
		            <input type="text" id="username" name="username" placeholder="Enter your username" required>
		        </div>
		        <div class="form-group">
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password" placeholder="Enter your password" required>
		        </div>
		        <div class="form-group">
		            <label for="address">Address</label>
		            <input type="text" id="address" name="address" placeholder="Enter your address" required>
		        </div>
		        <div class="form-group">
		            <label for="email">Email</label>
		            <input type="email" id="email" name="email" placeholder="Enter your email" required>
		        </div>
		        <div class="form-group">
		            <label for="birthday">Birthday</label>
		            <input type="date" id="birthday" name="birthday" required>
		        </div>
		        <div class="form-group">
		            <label for="gender">Gender</label>
		            <select id="gender" name="gender" required>
		                <option value="" disabled selected>Select your gender</option>
		                <option value="male">Male</option>
		                <option value="female">Female</option>
		                <option value="other">Other</option>
		            </select>
		        </div>
		        <div class="form-group">
		            <label for="phone">Phone</label>
		            <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" required>
		        </div>
		        <div class="form-group">
		            <label for="image">Profile Picture</label>
		            <input type="file" id="image" name="image" accept="image/*" required>
		        </div>
		        <div class="form-actions">
		            <button type="submit">Register</button>
		        </div>
		    </form>
	    </div>
    </div>
</body>
</html>
