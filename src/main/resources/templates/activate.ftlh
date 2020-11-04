<#import "components/skeleton.ftlh" as component>
<#import "components/navbar.ftlh" as bootstrap>

<@component.skeleton>
<@bootstrap.navbar/>
<div class="container col-4 mt-5">
<h3></h3>
<form action="/activate" method="get">
    <div class="form-group">
        <label for="code">:</label>
        <input type="text" name="code" class="form-control" id="code" >
    </div>
    <button type="submit" class="btn btn-warning mb-1"></button>
</form>
</div>
</@component.skeleton>