<!DOCTYPE html>
<html lang="en">
<#include "../fragment/header.ftl">
<body>
<#include "../fragment/nav.ftl">
<div class="container">
    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#aaa" role="tab" data-toggle="tab">Поиск</a></li>
        <li><a href="#bbb" role="tab" data-toggle="tab">Действущие заказы</a></li>
        <li><a href="#ccc" role="tab" data-toggle="tab">Неоплаченные заказы</a></li>
        <li><a href="#eee" role="tab" data-toggle="tab">Неактивированные</a></li>
        <li><a href="#ddd" role="tab" data-toggle="tab">Удаленные</a></li>
        <li><a href="#fff" role="tab" data-toggle="tab">Создать заказ</a></li>
    </ul><br>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane fade in active" id="aaa">
            <#include "offers.ftl">
        </div>
        <div role="tabpanel" class="tab-pane fade" id="bbb">
            <#include "paidOrders.ftl">
        </div>
        <div role="tabpanel" class="tab-pane fade" id="ccc">
            <#include "unpaidOrders.ftl">
        </div>
        <div role="tabpanel" class="tab-pane fade" id="eee">
            <#include "notActivatedOrders.ftl">
        </div>
        <div role="tabpanel" class="tab-pane fade" id="ddd">
        <#include "remoteOrders.ftl">
        </div>
        <div role="tabpanel" class="tab-pane fade" id="fff">
            <#include "createOrderForm.ftl">
        </div>
    </div>
</div>
<#include "../fragment/footer.ftl">
</body>
</html>