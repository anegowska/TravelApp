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

<#if purchases??>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Hotel</th>
        <th scope="col">Number of days</th>
        <th scope="col">Price</th>
        <th scope="col">Transport</th>
        <th scope="col">Board</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <#list purchases as p>
        <tr>
            <td>${p.customer.name}</td>
            <td>${p.customer.surname}</td>
            <td>${p.travel.hotel.name}</td>
            <td>${p.travel.numberOfDays}</td>
            <td>${p.travel.price}</td>
            <td>${p.travel.transport}</td>
            <td>${p.travel.board}</td>
            <td>
                <form method="post" action="/purchase?action=delete">
                    <input type="hidden" name="pid" value="${p.id}">
                    <button type="submit" class="btn btn-danger">Resign</button>
                </form>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</#if>
<br/>
<p>Add new purchase:</p>
<form method="post" action="/purchase?action=add">

    <#list customers>
        <div class="form-group">

            <label form="chooseCustomer">Customer</label>
            <select name="cid" class="form-control" id="chooseCustomer">

                <#items as c>
                    <option value="${c.id}">${c.name} ${c.surname}, pesel: ${c.pesel}</option>
                </#items>
            </select>

        </div>
    </#list>

    <#list travels>
        <div class="form-group">

            <label form="chooseTravel">Travel</label>
            <select name="tid" class="form-control" id="chooseTravel">

                <#items as t>
                    <option value="${t.id}">${t.hotel.name}, number of days: ${t.numberOfDays}, price: ${t.price}</option>
                </#items>
            </select>

        </div>
    </#list>
    <input type="submit" name="save" value="save"/><br/><br/>
</form>

</body>
</html>
