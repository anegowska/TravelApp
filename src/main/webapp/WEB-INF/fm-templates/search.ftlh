<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
          crossorigin="anonymous">

</head>
<body>

<form method="post" action="/search">
    Customer id: <input type="number" name="cid"/>
    <input type="submit" name="save" value="search"/><br/><br/>
</form>

<#if travels??>
<h3>Travels purchased by ${name} ${surname}:</h3>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Hotel</th>
        <th scope="col">Number of days</th>
        <th scope="col">Price</th>
        <th scope="col">Transport</th>
        <th scope="col">Board</th>
    </tr>
    </thead>
    <tbody>
        <#list travels as t>
        <tr>
            <td>${t.hotel.name}</td>
            <td>${t.numberOfDays}</td>
            <td>${t.price}</td>
            <td>${t.transport}</td>
            <td>${t.board}</td>
        </tr>
        </#list>
    </tbody>
</table>
</#if>
</body>
</html>