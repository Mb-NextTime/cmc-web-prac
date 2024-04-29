<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <head>
        <title>Flight</title>
    </head>
    <body>
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
        <table border="1">
            <c:forEach items="${bookings}" var="row">
                <tr>
                    <c:forEach items="${row}" var="booked">
                        <td> ${booked} </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>

    </body>
