<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="en">
    <head>
        <title>Avia Booking</title>
        <meta property="og:title" content="Avia Booking" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta charset="utf-8" />
        <meta property="twitter:card" content="summary_large_image" />
        <style data-tag="reset-style-sheet">
            html {
                line-height: 1.15;
            }
            body {
                margin: 0;
            }
            * {
                box-sizing: border-box;
                border-width: 0;
                border-style: solid;
            }
            p,
            li,
            ul,
            pre,
            div,
            h1,
            h2,
            h3,
            h4,
            h5,
            h6,
            figure,
            blockquote,
            figcaption {
                margin: 0;
                padding: 0;
            }
            button {
                background-color: transparent;
            }
            button,
            input,
            optgroup,
            select,
            textarea {
                font-family: inherit;
                font-size: 100%;
                line-height: 1.15;
                margin: 0;
            }
            button,
            select {
                text-transform: none;
            }
            button,
            [type="button"],
            [type="reset"],
            [type="submit"] {
                -webkit-appearance: button;
            }
            button::-moz-focus-inner,
            [type="button"]::-moz-focus-inner,
            [type="reset"]::-moz-focus-inner,
            [type="submit"]::-moz-focus-inner {
                border-style: none;
                padding: 0;
            }
            button:-moz-focus,
            [type="button"]:-moz-focus,
            [type="reset"]:-moz-focus,
            [type="submit"]:-moz-focus {
                outline: 1px dotted ButtonText;
            }
            a {
                color: inherit;
                text-decoration: inherit;
            }
            input {
                padding: 2px 4px;
            }
            img {
                display: block;
            }
            html {
                scroll-behavior: smooth;
            }
        </style>
        <style data-tag="default-style-sheet">
            html {
                font-family: Karla;
                font-size: 16px;
            }

            body {
                font-weight: 400;
                font-style: normal;
                text-decoration: none;
                text-transform: none;
                letter-spacing: normal;
                line-height: 1.15;
                color: var(--dl-color-gray-black);
                background-color: var(--dl-color-gray-white);
            }
        </style>
        <link rel="stylesheet" href="https://unpkg.com/animate.css@4.1.1/animate.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Karla:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,200;1,300;1,400;1,500;1,600;1,700;1,800&amp;display=swap" data-tag="font" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Cormorant+Infant:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&amp;display=swap" data-tag="font" />
        <link rel="stylesheet" href="https://unpkg.com/@teleporthq/teleport-custom-scripts/dist/style.css" />
        <!--This is the head section-->
        <!-- <style> ... </style> -->
    </head>
    <body>
        <link rel="stylesheet" href="./style.css" />
        <div>
            <link href="./index.css" rel="stylesheet" />
            <div class="home-container">
                <section class="home-hero">
                    <div class="home-main">
                        <!-- Background Image -->
                        <div class="home-video">
                            <div class="home-tint"></div>
                        </div>
                        <div class="home-content">
                            <header data-thq="thq-navbar" class="home-navbar">
                                <!-- Logo -->
                                <img alt="logo" src="public/logo.svg" class="home-logo" />
                                <!-- Navigation bar -->
                                <div data-thq="thq-burger-menu" class="home-menu">
                                    <div class="home-links">
                                        <c:choose>
                                            <c:when test="${user == null}">
                                                <a href="/authentication/login"><span class="home-text link">Login</span></a>
                                                <a href="/authentication/register"><span class="home-text link">Register</span></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="/profile"><span class="home-text link">Profile</span></a>
                                                <a href="/authentication/logout"><span class="home-text link">Logout</span></a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </header>
                            <div class="home-center">
                                <!-- Title and slogan -->
                                <div class="home-heading">
                                    <h1 class="home-header">Book flight tickets online</h1>
                                    <p class="home-caption">Find the best option for the lowest prices!</p>
                                </div>
                                <!-- Filter -->
                                <form action="/search/submit" method="post">
                                    <div class="home-border">
                                        <div class="home-filter">
                                            <img alt="image" src="public/Icons/location.svg" class="home-image" />
                                            <input type="text" name="dCity" placeholder="Departure City" class="home-textinput input" />
                                            <input type="text" name="aCity" placeholder="Arrival City" class="home-textinput input" />

                                            <input type="date" name="dDate" placeholder="Departure Date" class="home-textinput1 input" />
                                            <input type="date" name="aDate" placeholder="Arrival Date" class="home-textinput1 input" />

                                            <!-- input type="text" name="company" placeholder="Company" class="home-textinput input" /-->

                                            <!-- input type="number" placeholder="Number of seats" class="home-textinput2 input" /-->
                                            <button type="submit">
                                                <div class="home-search"><img alt="image" src="public/Icons/search.svg" class="home-icon10" /></div>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
                <section class="home-footer">
                    <div class="home-content1">
                        <div class="home-main1">
                            <div class="home-branding">
                                <div class="home-heading1">
                                    <img alt="image" src="public/logo.svg" class="home-image1" />
                                    <p class="home-caption1">Book cheapest and best tickets for plane flights online
                                        with our best amazing and very-very cheap looking website!</p>
                                </div>
                            </div>
                            <span class="home-copyright">© 2024 Character. All Rights Reserved.</span>
                        </div>
                    </div>
                </section>
                <div>
                    <div class="home-container4">
                        <script>
                            function initAccordion() {
                              /*
                              Accordion - Code Embed
                              */
                              const accordionContainers = document.querySelectorAll(
                                '[data-role='accordion-container']'
                              ); // All accordion containers
                              const accordionContents = document.querySelectorAll(
                                '[data-role='accordion-content']'
                              ); // All accordion content
                              const accordionIconsClosed = document.querySelectorAll(
                                '[data-role='accordion-icon-closed']'
                              ); // All accordion closed icons
                              const accordionIconsOpen = document.querySelectorAll(
                                '[data-role='accordion-icon-open']'
                              ); // All accordion open icons

                              accordionContents.forEach((accordionContent) => {
                                accordionContent.style.display = 'none'; //Hides all accordion contents
                              });

                              accordionIconsClosed.forEach((icon) => {
                                icon.style.display = 'flex';
                              });

                              accordionIconsOpen.forEach((icon) => {
                                icon.style.display = 'none';
                              });

                              accordionContainers.forEach((accordionContainer, index) => {
                                if (accordionContainer.classList.contains('initialised')) {
                                  return;
                                }

                                accordionContainer.classList.add('initiased');

                                accordionContainer.addEventListener('click', () => {
                                  if (accordionContents[index].style.display === 'flex') {
                                    // If the accordion is already open, close it
                                    accordionContents[index].style.display = 'none';
                                    accordionIconsClosed[index].style.display = 'flex';
                                    accordionIconsOpen[index].style.display = 'none';
                                  } else {
                                    // If the accordion is closed, open it
                                    accordionContents.forEach((accordionContent) => {
                                      accordionContent.style.display = 'none'; //Hides all accordion contents
                                    });

                                    accordionIconsClosed.forEach((accordionIcon) => {
                                      accordionIcon.style.display = 'flex'; // Resets all icon transforms to 0deg (default)
                                    });

                                    accordionIconsOpen.forEach((accordionIcon) => {
                                      accordionIcon.style.display = 'none';
                                    });

                                    accordionContents[index].style.display = 'flex'; // Shows accordion content
                                    accordionIconsClosed[index].style.display = 'none'; // Rotates accordion icon 180deg
                                    accordionIconsOpen[index].style.display = 'flex';
                                  }
                                });
                              });
                            }

                            initAccordion();
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <script defer="" src="https://unpkg.com/@teleporthq/teleport-custom-scripts"></script>
    </body>
</html>
