<#macro navbar>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
<a class="navbar-brand mr-4" href="#">ALITO</a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="#">Куплю </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Продам </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/ad/create">Создать объявление</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/login">Войти</a>
        </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" placeholder="Поиск" aria-label="Search">
        <button class="btn btn-dark btn-outline-primary my-2 my-sm-0" type="submit">Поиск</button>
    </form>
</div>
</nav>
</#macro>