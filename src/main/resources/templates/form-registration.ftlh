<#import "components/skeleton.ftlh" as component>
<#import "components/navbar.ftlh" as bootstrap>

<@component.skeleton>
<@bootstrap.navbar/>
<form class="mt-5 mb-5" action="/registration" method="post">
    <div class="form-group">
        <label for="username">Логин</label>
        <input type="text" name="username" class="form-control" id="username"/>
    </div>
    <div class="form-group">
        <label for="password">Пароль</label>
        <input type="text" name="password" class="form-control" id="password"/>
    </div>
    <div class="form-group">
        <label for="email">Почта</label>
        <input type="text" name="email" class="form-control" id="email"/>
    </div>
    <div class="form-group">
        <label for="firstName">Имя</label>
        <input type="text" name="firstName" class="form-control" id="firstName"/>
    </div>
    <div class="form-group">
        <label for="secondName">Фамилия</label>
        <input type="text" name="secondName" class="form-control" id="secondName"/>
    </div>
    <div class="form-group">
        <label for="patronymic">Отчество</label>
        <input type="text" name="patronymic" class="form-control" id="patronymic"/>
    </div>
    <div class="form-group">
        <label for="date">Отчество</label>
        <input type="date" name="birthday" class="form-control" id="date"/>
    </div>
    <button type="submit" class="btn btn-warning">Зарегистрироваться</button>

</form>


</@component.skeleton>