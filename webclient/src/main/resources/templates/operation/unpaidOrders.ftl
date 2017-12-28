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
        <#list unpaidOrders as order>
        <tr>
            <td>${order.name}</td>
            <td>${order.description}<br><br>Дата окончания: ${order.activeDate.format()}</td>
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
                        <#list order.orderItems as item>
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.price} руб/месяц</td>
                        </tr>
                        </#list>
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