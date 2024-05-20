<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<head>
    <title>Register</title>
</head>
<body>
    <form action="/authentication/submit-register" method="post">
        <input type="text" name="name" placeholder="Your name" />
        <input type="text" name="email" placeholder="Your email" />
        <input type="password" name="password" placeholder="Your password" />

        <button type="submit">
            Submit
        </button>
    </form>
</body>
