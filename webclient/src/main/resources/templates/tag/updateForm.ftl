<!DOCTYPE html>
<html lang="en">
<#include "../fragment/header.ftl">
<body>
<#include "../fragment/nav.ftl">
<div class="container">
    <h1>Изменить тег</h1>
    <form name="category" class="form-horizontal" action="/tags/save" method="post">
        <div class="form-group">
            <input type="hidden" class="form-control" name="id" value="${tag.id}">
        </div>
        <div class="form-group">
            <label for="name">Название</label>
            <input type="text" class="form-control" name="sign" value="${tag.id}">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-success" value="Сохранить">
        </div>
    </form>
</div>
<#include "../fragment/footer.ftl">
</body>
</html>