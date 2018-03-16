<%-- 
    Document   : search
    Created on : Mar 15, 2018, 5:41:52 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome , ${sessionScope.USERNAME}
        </font>
        <h1>Search Page</h1>
        <form action="search">
            SearchValue<input type="text" name="txtsearchValue" value="" />
            <input type="submit" value="search" name="btAction"/>
        </form><br/>
        <c:set var="searchValue" value="${param.txtsearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <form action="update">
                </form>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="update">
                            <tr>
                                <th>${counter.count}</th>
                                <th>${dto.username}
                                    <input type="hidden" name="txtuserName" value="${dto.username}" />
                                </th>
                                
                                <th>
                                    <input type="text" name="txtpassword" value="${dto.password}" />
                                </th>
                                <th>${dto.lastname}</th>
                                <th>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN" 
                                           <c:if test="${dto.role}">
                                               checked ="checked"
                                               </c:if>
                                           />
                                </th>
                                <td>
                                    <c:url var="delLink" value="delete">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtsearchValue}"/>
                                    </c:url>
                                    <a href="${delLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtsearchValue}" />
                                </td>
                            </tr>
                        </form>
                            
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>
                    <font color="red">
                    No recored is matched!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
    </body>
</html>
