<#import "components/skeleton.ftlh" as component>
<#import "components/navbar.ftlh" as bootstrap>

<@component.skeleton>
<@bootstrap.navbar/>
<div class="container">
    <form class="mt-5 mb-5" action="/ad" method="post" >
        <h3>Создание объявления</h3>

        <div class="form-group">
            <label for="adType">Тип объявления:</label>
            <select class="form-control" name="adType" id="adType">
                <option disabled selected>Тип</option>
                <option value="BUY">Куплю</option>
                <option value="SELL">Продам</option>
            </select>
        </div>


        <div class="form-group">
            <label for="name">Название</label>
            <input type="text" name="name" class="form-control" placeholder=""/>
        </div>
        <div class="form-group">
            <label for="description">Описание</label>
            <textarea type="text" name="description" class="form-control" ></textarea>
        </div>
        <div class="form-group">
            <label for="address">Адресс:</label>
            <input type="text" name="address" class="form-control" placeholder=""/>
        </div>
        <div class="form-group">
            <label for="cost">Цена</label>
            <input type="number" min="0" max="999999999" name="cost" class="form-control" placeholder=""/>
        </div>

        <div class="form-group">
            <label for="adCategory">Категория:</label>
            <select class="form-control" name="adCategory" id="category">
                <option disabled selected>Категория</option>
                <option value="PERSONALS">Персональные вещи</option>
                <option value="TRANSPORT">Транспорт</option>
                <option value="PROPERTY">Недвижимость</option>
            </select>
        </div>
        <div class="mx-auto">
            <button type="submit" class="btn btn-primary ">Создать</button>
        </div>
    </form>


</div>
</@component.skeleton>