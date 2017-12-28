<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">X</button>
                <h4 class="modal-title">Выберите ордер</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/operations/addOrderItem" method="get">
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="offerId" value="${offer.id}">
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="orderName">
                            <#list orders as item>
                                <option>${item.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-success" value="Добавить">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>