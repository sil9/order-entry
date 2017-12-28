<!DOCTYPE html>
<html lang="en">
<#include "../fragment/header.ftl">
<body>
<#include "../fragment/nav.ftl">
<div class="container">
    <h2>Теги</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>#ID</th>
                <th>Ключ</th>
                <th colspan="2">Действие</th>
            </tr>
            </thead>
            <tbody>
            <#list tags as tag>
            <tr>
                <td>${tag.id}</td>
                <td>${tag.sign}</td>
                <td>
                    <button type="button"
                            class="btn btn-link"
                            onclick="location.href='update/${tag.id}'">Изменить
                    </button>
                </td>
                <td>
                    <button type="button"
                            class="btn btn-danger"
                            onclick="location.href='delete/${tag.id}'">Удалить
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