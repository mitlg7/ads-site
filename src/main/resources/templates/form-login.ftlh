<#import "components/skeleton.ftlh" as component>
<#import "components/navbar.ftlh" as bootstrap>

<@component.skeleton>
<@bootstrap.navbar/>

<div class="container col-4 mt-5">

    <form action="/login" method="post">

        <div class="form-group">
            <label for="username">Логин: </label>
            <input type="text" name="username" class="form-control" id="username"
                   placeholder='' required/>
        </div>
        <div class="form-group">
            <label for="password">Пароль: </label>
            <input type="password" name="password" class="form-control" id="password" placeholder='' required>
        </div>
        <div class="form-check mb-1">
            <input type="checkbox" name="remember-me" class="form-check-input" id="remember">
            <label class="form-check-label" for="remember">Запомнить</label>
        </div>
        <button type="submit" class="btn btn-primary mb-1">Войти</button>
        <p style="color: black;">Или <a class="" href="/registration">Регистрация</a></p>
    </form>
</div>
</@component.skeleton>