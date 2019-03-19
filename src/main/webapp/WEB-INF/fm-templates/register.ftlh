<html>
<body>
<#if errors??>
    <#list errors as error>
        <ul>
            <li>${error}</li>
        </ul>
    </#list>
</#if>
<h1>Register</h1>
<form method="post" action="/register">
    Name: <input type="text" name="name"/><br/><br/>
    Surname: <input type="text" name="surname"/><br/><br/>
    Email: <input type="email" name="email"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    <input type="submit" name="save" value="Register"/><br/><br/>

</form>
</body>
</html>