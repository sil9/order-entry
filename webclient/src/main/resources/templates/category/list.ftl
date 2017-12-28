<!DOCTYPE html>
<html lang="en">
<#include "../fragment/header.ftl">
<body>
<#include "../fragment/nav.ftl">
<div class="container">
    <h2>Категории</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>#ID</th>
                <th>Название</th>
                <th colspan="2">Действие</th>
            </tr>
            </thead>
            <tbody>
            <#list categories as category>
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>
                    <button type="button"
                            class="btn btn-link"
                            onclick="location.href='update/${category.id}'">Изменить
                    </button>
                </td>
                <td>
                    <button type="button"
                            class="btn btn-danger"
                            onclick="location.href='delete/${category.id}'">Удалить
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