<div class="table-responsive">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Общая цена</th>
            <th>Товары</th

        </tr>
        </thead>
        <tbody>
        <#list notActivatedOrders as order>
        <tr>
            <td>${order.name}</td>
            <td>${order.description}</td>
            <td>${order.totalPrice} руб/месяц</td>
            <td>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Название</th>
                        <th>Цена</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if order.orderItems??>
                        <#list order.orderItems as item>
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.price} руб/месяц</td>
                            <td>
                            <button type="button"
                                    class="btn btn-link"
                                    onclick="location.href='/operations/deleteOrderItem?orderId=${order.id}&itemId=${item.id}'">Удалить
                            </button>
                            </td>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </td>
            <td>
                <button type="button"
                        class="btn btn-link"
                        onclick="location.href='/operations/pay/${order.id}'">Оплатить
                </button>
            </td>
            <td>
                <button type="button"
                        class="btn btn-danger"
                        onclick="location.href='/operations/delete/${order.id}'">Удалить
                </button>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>