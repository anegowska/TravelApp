<html>
<body>
<h2>Customers list</h2>
<br>
<#list customers as c>
        <ul>
            <li>Name: ${c.name}</li>
            <li>Surname: ${c.surname}</li>
            <li>Pesel: ${c.pesel}</li>
            <li>Phone: ${c.phone}</li>
        </ul>
</#list>
</body>
</html>
