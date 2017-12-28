<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include "../fragment/header.ftl">
<body>
<#include "../fragment/nav.ftl">
<div class="container">
    <h1>Изменить предложение</h1>
    <form name="offer" class="form-horizontal" action="/offers/save" method="post">
        <div class="form-group">
            <input type="hidden" class="form-control" name="id" value="${offer.id}">
        </div>
        <div class="form-group">
            <label for="name">Название</label>
            <input type="text" class="form-control" name="name" value="${offer.name}" placeholder="Введите название">
        </div>
        <div class="form-group">
            <label for="description">Описание</label>
            <input type="text" class="form-control" name="description" value="${offer.description}" placeholder="Введите краткое описание">
        </div>
        <div class="form-group">
            <label for="fullDescription">Полное описание</label>
            <textarea class="form-control" name="fullDescription"  placeholder="Введите описание">${offer.fullDescription}</textarea>
        </div>
        <div class="form-group">
            <label for="availability">Доступность</label><br>
            <label class="radio-inline">
                <input type="radio" name="availability" value="true" checked> <img src="https://png.icons8.com/color/50/000000/checkmark.png" width="20" height="20">
            </label>
            <label class="radio-inline">
                <input type="radio" name="availability" value="false"> <img src="https://png.icons8.com/office/40/000000/minus.png" width="20" height="20">
            </label>
        </div>
        <div class="form-group">
            <label for="price">Цена</label>
            <input type="text" class="form-control" name="price" value="${offer.price}" placeholder="Введите цену">
        </div>
        <div class="form-group">
            <label for="categoryName">Категория
                <select class="form-control" name="categoryName">
                <#list categories as category>
                    <option>${category.name}</option>
                </#list>
                </select>
            </label>
        </div>
        <div class="form-group">
            <label for="imageUrl">Фото</label>
            <input type="text" class="form-control" name="imageUrl" value="${offer.imageUrl}" placeholder="Введите url фото">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-success" value="Сохранить">
        </div>
    </form>
</div>
<#include "../fragment/footer.ftl">
</body>
</html>