<!DOCTYPE html>
<html lang="en">
<#include "../fragment/header.ftl">
<body>
<#include "../fragment/nav.ftl">
<#include "offerModalForm.ftl">
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 align="middle">${offer.name}</h2>
        </div>
        <div class="col-md-4">
            <img src="${offer.imageUrl}">
            <h2 align="center">${offer.price} руб/месяц</h2>
            <button class="btn btn-info" data-toggle="modal" data-target="#myModal">Добавить &raquo;</button>
        </div>
        <div align="left" class="col-md-8">
            <br><br><br><br>
            <p style="background-color: aqua"><big>${offer.fullDescription}</big></p>
        </div>
        <div class="col-md-12">
            <h4>Основные характеристики:</h4><p id="offerDescription">${offer.description}</p>
        </div>
    </div>
    <h4>Возможно вас заинтересует:</h4>
<div style="background-color: cadetblue">
    <#list offers as offer>

            <div class="col-md-2" onclick="location.href='/offers/${offer.id}'">
                <img src="${offer.imageUrl}" height="150" width="100" >
                <p><h4>${offer.name}</h4></p>
            </div>

    </#list>
</div>
</div>
<#include "../fragment/footer.ftl">
</body>
</html>