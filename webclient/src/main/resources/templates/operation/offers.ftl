<div class="container">

    <form class="form-inline" action="/operations" method="get">
        <div class="form-group">
            <label for="categoryName">Категория</label>
            <select class="form-control" name="categoryName">
                <#if categoryName??>
                    <option selected hidden>${categoryName}</option>
                </#if>
                    <option>--</option>
                <#list categories as category>
                    <option>${category.name}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <label for="minPrice">Минимальная цена</label>
            <input type="text" class="form-control" name="minPrice" value="<#if minPrice??>${minPrice}</#if>">
        </div>
        <div class="form-group">
            <label for="maxPrice">Максимальная цена</label>
            <input type="text" class="form-control" name="maxPrice" value="<#if maxPrice??>${maxPrice}</#if>">
        </div>
        <div class="form-group">
            <button type="submit"><img src="https://icons8.com/iconizer/files/Bunch_of_Bluish_Icons/orig/search.png" width="30" height="30"></button>
        </div>
    </form><br>

    <div class="row row-offcanvas row-offcanvas-right">
        <div class="row">
        <#list offers as offer>
            <div id="item_order" class="col-md-4">
                <h2>${offer.name}</h2>
                <img src="${offer.imageUrl}" height="150" width="150">
                <p style="font-weight: 700; font-size: large">${offer.price} руб/месяц</p>
                <p id="item_order_description">${offer.description}</p>
                <a class="btn btn-default" href="offers/${offer.id}" role="button">Подробнее &raquo;</a>
            </div>
        </#list>
        </div>
    </div>
</div>