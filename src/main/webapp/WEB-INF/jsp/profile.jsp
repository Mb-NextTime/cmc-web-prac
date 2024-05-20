<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<head>
    <title>Profile</title>
</head>
<body>
    Your name: ${customer.name}
    <br><br>
    Your email: ${customer.email}
    <br><br>
    Bonuses: ${customer.bonuses}
    <br><br>
    Your bookings:
    <br>
    <br>
    <c:forEach items="${customer.bookings}" var="booking">
        <a href="../flight/${booking.flight.id}">
            ${booking.flight.departureCity.name} -> ${booking.flight.arrivalCity.name}
            <br>
            ${booking.seat.row}:${booking.seat.column}, price: $${booking.price}
        </a>
        <br>
        &#10060;
        <br><br>
    </c:forEach>
</body>
