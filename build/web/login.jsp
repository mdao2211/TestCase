<%-- 
    Document   : login2
    Created on : Jul 3, 2023, 5:27:10 PM
    Author     : Twna21
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Volunteer</title>
        <link rel="stylesheet" href="./css/loginCSS.css">

    </head>

    <body>

        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="signup" method="post">
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/CommunityUnity/LoginGoogleController&response_type=code&client_id=552963219492-6a880it69r6hmnahrj8c69f328id25m6.apps.googleusercontent.com&approval_prompt=force" class="social">
                            <img src="./images/OIP.jpg" style="height: 100%;" alt="Image Description"><i
                                class="fab fa-google"></i></a>



                    </div>
                    <span>or registration by Account</span>
                    <input type="text" placeholder="username" name="su_username" />

                    <input type="password" name="su_password" placeholder="Password" />
                    <input type="password" name="repass" placeholder="Repeat Password" />
                    <button class="btn btn-dark btn-lg btn-block" type="submit">SIGN UP</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="login" method ="POST">
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/MilkTeaProjectPRJ301/LoginGoogleController&response_type=code&client_id=552963219492-6a880it69r6hmnahrj8c69f328id25m6.apps.googleusercontent.com&approval_prompt=force" class="social">
                            <img src="./images/OIP.jpg" style="height: 100%;" alt="Image Description">
                            <i class="fab fa-google"></i></a>

                    </div>
                    
                    <c:if test="${not empty requestScope.ERROR_MASSEGE}">
                        <!-- Error MSG -->
                        <div class="alert alert-danger" role="alert" style="color: red">
                            ${requestScope.ERROR_MASSEGE}
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.MSG_SUCCESS}">
                        <!-- Error MSG -->
                        <div class="alert alert-success" role="alert">
                            ${requestScope.MSG_SUCCESS}
                        </div>
                    </c:if>
                    <span>or use your account</span>
                    <input type="text" placeholder="username" name="username" />
                    <input type="password" placeholder="Password" name="password" />
                    <a href="foget.jsp">Forgot your password?</a>
                    <button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>

                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>

        <footer>    
        </footer>

        <script src="./js/login.js" type="text/javascript"></script>
    </body>

</html>