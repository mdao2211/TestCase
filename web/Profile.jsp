<%-- 
    Document   : Profile
    Created on : Mar 17, 2023, 1:59:39 PM
    Author     : DELL
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="entity.Account" %>
<%@ page import="dao.AccountDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/profile.css">

        <style>
            .photo-frame {
                border: 2px;
                padding: 10px;
                width: 150px;
                height: 150px;
                text-align: center;
            }

            .photo-frame img {
                max-width: 80%;
                max-height: 80%;
            }
        </style>
    </head>
    <body>

        <%
          String name = ((Account) session.getAttribute("LOGIN_USER")).getUserName();
            Account a = new Account();
            AccountDAO dao = new AccountDAO();

            a = dao.getAccount_BYUSER(name);

        %>
        <form action="" method="post" class="form1">

            <c:set var="p" value=""/>

            <div class="edit-profile">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <h5>MY PROFILE</h5>
         
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-6">
                        <table>
                            <tr>
                                <th>Photo:</th>

                                <td>

                                    <div class="photo-frame">
                                        <img src="${sessionScope.LOGIN_USER.photo}">
                                        <c:if test="${sessionScope.LOGIN_USER.photo == null  || sessionScope.LOGIN_USER.photo eq ''}">
                                            <img src="./images/uer.png" alt="images/uer.png">
                                        </c:if> 
                                    </div>
                                </td>





                            </tr>
                            <tr>
                                <th>Tên đăng nhập:</th>
                                <td>${sessionScope.LOGIN_USER.userName}</td>
                            </tr>
                            <tr>
                                <th>Name:</th>
                                <td>
                                    <%=a.getFullName()%>
                                </td>
                            </tr>
                            <tr>
                                <th>Phone</th>
                                <td>
                                    <p style="padding-right: 10px;" id=""> <%=a.getPhone()%></p>
                                </td>
                            </tr>
                            <tr>
                                <th>Address</th>
                                <td>
                                    <p style="padding-right: 10px;" id=""> <%=a.getAddress()%></p>
                                </td>
                            </tr>
                   
                     
                            <tr>
                                <th>Email</th>
                                <td>
                                     <%=a.getEmail()%>
                                </td>
                            </tr>

                        </table>
                        </form>
                        <div>
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">SAVE</button>
                            <a href="home" class="btn btn-primary" data-bs-whatever="@getbootstrap"> BACK HOME </a>
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Cập Nhập Thông Tin</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="ProfileControl" method="post"> 
                                            <div class="modal-body">

                                                <div class="mb-3">
                                                    <label for="recipient-name" class="col-form-label">Username: </label>
                                                    <input type="text" class="form-control" id="recipient-name" value="${sessionScope.LOGIN_USER.userName}" name="username" readonly>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="recipient-name" class="col-form-label">Nqme</label>
                                                    <input type="text" class="form-control" id="recipient-name" value="${sessionScope.LOGIN_USER.fullName}" name="name">
                                                </div>
<!--                                                <div class="mb-3">
                                                    <label for="recipient-name" class="col-form-label">Birth Day: </label>
                                                    <input type="text" class="form-control" id="recipient-name" value="${sessionScope.LOGIN_USER.fullName}" placeholder="yyyy-mm-dd" name="birthday">
                                                </div>-->
                                                <div class="mb-3">
                                                    <label for="recipient-name" class="col-form-label">Phone:</label>
                                                    <input type="number" class="form-control" id="recipient-name" value="${sessionScope.LOGIN_USER.phone}" name="phone">
                                                </div>

                                                <div class="mb-3">
                                                    <label for="message-text" class="col-form-label">Address</label>
                                                    <input type="email" class="form-control" id="message-text" value="${sessionScope.LOGIN_USER.address}" name="address">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="message-text" class="col-form-label">Email:</label>
                                                    <input type="email" class="form-control" id="message-text" value="${sessionScope.LOGIN_USER.email}" name="email">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="message-text" class="col-form-label">New password:</label>
                                                    <input type="password" class="form-control" id="message-text" value="${sessionScope.LOGIN_USER.password}" name="password">
                                                </div>

                                            </div>
                                            <div class="modal-footer">

                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <input type="text" class="form-control" id="recipient-name" value="${sessionScope.LOGIN_USER.userName}" name="username" hidden="">
                                                <input type="submit" class="btn btn-primary" value="Update"> 
                                            </div>


                                        </form>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </body>
</html>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<!-- <script src="profile/"></script> -->