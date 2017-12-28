<nav class="navbar-inverse navbar-fixed-top">
    <div>
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapse"
                        data-toggle="collapse" data-targer="#navbar" aria-expanded="false"
                        aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Life:)</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown"><a href="" class="dropdown-toggle"
                                            data-toggle="dropdown" role="button" aria-haspopup="true"
                                            aria-expanded="false">Категории <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/categories/list">Список категорий</a></li>
                            <li><a href="/categories/add">Добавить категорию</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="" class="dropdown-toggle"
                                            data-toggle="dropdown" role="button" aria-haspopup="true"
                                            aria-expanded="false">Теги <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/tags/list">Список тэгов</a></li>
                            <li><a href="/tags/add">Создать тэг</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="" class="dropdown-toggle"
                                            data-toggle="dropdown" role="button" aria-haspopup="true"
                                            aria-expanded="false">Предложения <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/offers/list">Список предложений</a></li>
                            <li><a href="/offers/add">Создать предложение</a></li>
                        </ul>
                    </li>
                    <li class=""><a href="/operations">Операции</a></li>
                </ul>
                <#if Session.mail??>
                    <form class="navbar-form navbar-right" role="form" method="post" action="/logout">
                        <div class="form-group">
                            <input type="hidden" class="form-control" name="mail" value="${Session.mail}">
                            <label for="mail" style="color: cyan">${Session.mail}</label>
                        </div>
                        <button type="submit" class="btn btn-success">Выйти</button>
                    </form>
                <#else>
                    <form class="navbar-form navbar-right" role="form" method="post" action="/login">
                        <div class="form-group">
                            <input type="email" name="mail" placeholder="Email" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Войти</button>
                    </form>
                </#if>
            </div>
        </div>
    </div>
</nav>
<br><br><br>
