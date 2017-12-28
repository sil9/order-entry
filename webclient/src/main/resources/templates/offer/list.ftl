<!DOCTYPE html>
<html lang="en">
<#include "../fragment/header.ftl">
<body>
<#include "../fragment/nav.ftl">
<div class="container container-fluid">
    <h2>Предложения</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>#ID</th>
                <th>Название</th>
                <th>Доступность</th>
                <th>Цена</th>
                <th>Категория</th>
                <th>URL фото</th>
                <th colspan="2">Действие</th>
            </tr>
            </thead>
            <tbody>
            <#list offers as offer>
            <tr>
                <td>${offer.id}</td>
                <td>${offer.name}</td>
                <#if offer.isAvailability()== true>
                <td><img src="https://png.icons8.com/color/50/000000/checkmark.png" width="30" height="30"></td>
                 <#else>
                <td><img src="https://png.icons8.com/office/40/000000/minus.png" width="30" height="30"></td>
                </#if>
                <td>${offer.price}</td>
                <td>${offer.categoryName}</td>
                <td>${offer.imageUrl}</td>
                <td>
                    <button type="button"
                            class="btn btn-link"
                            onclick="location.href='update/${offer.id}'">Изменить
                    </button>
                </td>
                <td>
                    <button type="button"
                            class="btn btn-danger"
                            onclick="location.href='delete/${offer.id}'">Удалить
                    </button>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
<#include "../fragment/footer.ftl">
</body>
</html>