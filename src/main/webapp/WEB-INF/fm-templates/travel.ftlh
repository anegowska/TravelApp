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

<#if travels??>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Hotel</th>
        <th scope="col">Number of days</th>
        <th scope="col">Price</th>
        <th scope="col">Transport</th>
        <th scope="col">Board</th>
        <th scope="col"></th>
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
            <td>
                <form method="post" action="/travel?action=delete">
                    <input type="hidden" name="tid" value="${t.id}">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</#if>
<br/>
<p>Add new travel:</p>
<form method="post" action="/travel?action=add">
    Number of days: <input type="number" name="numberOfDays"/><br/><br/>
    Price: <input type="number" name="price"/><br/><br/>

    <#list boardTypes>
        <div class="form-group">

            <label form="chooseBoard">Board</label>
            <select name="board" class="form-control" id="chooseBoard">

                <#items as b>
                    <option value="${b}">${b}</option>
                </#items>
            </select>
        </div>
    </#list>

    <#list transportTypes>
        <div class="form-group">

            <label form="chooseTransport">Transport</label>
            <select name="transport" class="form-control" id="chooseTransport">

                <#items as t>
                    <option value="${t}">${t}</option>
                </#items>
            </select>

        </div>
    </#list>

    <#list hotels>
        <div class="form-group">

            <label form="chooseHotel">Hotel</label>
            <select name="hotelId" class="form-control" id="chooseHotel">

                <#items as h>
                    <option value="${h.id}">${h.name}</option>
                </#items>
            </select>

        </div>
    </#list>


    <input type="submit" name="save" value="save"/><br/><br/>
</form>

</body>
</html>