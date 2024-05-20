<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<head>
    <title>Flight</title>
</head>
<body>
    <div>
        <link href="/index.css" rel="stylesheet" />
        Departure city: ${flight.departureCity.name}
        <br>
        Arrival city: ${flight.arrivalCity.name}
        <br>
        Departure time: ${flight.scheduledDeparture}
        <br>
        Scheduled arrival time: ${flight.scheduledArrival}
        <br>
        with aircraft of company ${flight.company}.
        <br>
        Base ticket price: $${flight.basePrice}
        <br><br>

        Bookings:
        <form action="/booking/submit" method="post">
            <input type="hidden" name="flightId" value="${flight.id}" />
            <table border="1">
                <c:set var="rowIndex" value="${0}" />
                <c:forEach items="${bookings}" var="row">
                    <tr>
                        <c:set var="colIndex" value="${0}" />
                        <c:forEach items="${row}" var="booked">
                            <td>
                                <c:choose>
                                    <c:when test="${booked}">
                                        <input type="checkbox" checked onclick="return false;" style="accent-color: #e74c3c;"/>
                                    </c:when>
                                    <c:when test="${user == null}">
                                        <input type="checkbox" onclick="return false;" style="accent-color: #e74c3c;"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name='${rowIndex}_${colIndex}' style="accent-color: #33ff3f;"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <c:set var="colIndex" value="${colIndex + 1}" />
                        </c:forEach>
                    </tr>
                    <c:set var="rowIndex" value="${rowIndex + 1}" />
                </c:forEach>
            </table>
            <c:if test="${user != null}">
                <button type="submit">
                    Confirm booking
                </button>
            </c:if>
        </form>
    </div>
</body>
