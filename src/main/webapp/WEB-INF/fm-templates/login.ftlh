<html>
<body>
<#if errors??>
    <#list errors as error>
        <ul>
            <li style="color: #ff0000; font-size: 14px;">${error}</li>
        </ul>
    </#list>
</#if>
<h1>Login</h1>
<form method="post" action="/login">
    Email: <input type="email" name="email"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    <input type="submit" name="save" value="Login"/><br/><br/>
    <a href="/register">Register</a>

</form>
</body>
</html>