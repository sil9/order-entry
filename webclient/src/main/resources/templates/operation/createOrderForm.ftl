<form name="order" class="form-horizontal" action="/operations/save" method="post">
    <div class="form-group">
        <input type="hidden" class="form-control" name="id">
    </div>
    <div class="form-group">
        <label for="name">Название</label>
        <input type="text" class="form-control" name="name" placeholder="Введите название">
    </div>
    <div class="form-group">
        <label for="description">Описание</label>
        <input type="text" class="form-control" name="description" placeholder="Введите описание">
    </div>
    <div class="form-group">
        <input type="hidden" class="form-control" name="customerEmail" value="${Session.mail}">
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-success" value="Сохранить">
    </div>
</form>