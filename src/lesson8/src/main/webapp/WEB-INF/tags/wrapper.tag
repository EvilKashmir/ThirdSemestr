<%@tag description="Wrapper Tag" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Ференец II Ракоци</title>
    <style>
        <%@include file='../../css/main.css'%>
    </style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<header>
    <div class="head-nav">
        <nav>
            <img id="main-icon" src="../../asserts/main_icon.svg">
            <span id="name-span"><b>NewWiki</b></span>
            <input id="search-line" type="search" title="Поиск" placeholder="Искать на сайте" size="50">
        </nav>
    </div>
</header>
<jsp:doBody/>
<footer class="end">
    <hr>
    <b>Copyrighted ©EvilKashmir</b>
</footer>
</body>
</html>