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

<#if hotels??>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Number of stars</th>
        <th scope="col">City</th>
        <th scope="col">Country</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <#list hotels as h>
        <tr>
            <td>${h.name}</td>
            <td>${h.stars}</td>
            <td>${h.city.name}</td>
            <td>${h.country.name}</td>
            <td>
                <form method="post" action="/hotel?action=delete">
                    <input type="hidden" name="hid" value="${h.id}">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</#if>
<br/>
<p>Add new hotel:</p>
<form method="post" action="/hotel?action=add">
    Name: <input type="text" name="name"/><br/><br/>
    Number of stars: <input type="number" name="stars"/><br/><br/>
    <#list cities>
        <div class="form-group">

            <label form="chooseCity">City</label>
            <select name="cityId" class="form-control" id="chooseCity">

                <#items as c>
                <option value="${c.id}">${c.name}</option>
                </#items>
            </select>

        </div>
    </#list>

    <#list countries>
        <div class="form-group">

            <label form="chooseCountry">Country</label>
            <select name="countryId" class="form-control" id="chooseCountry">

                <#items as c>
                    <option value="${c.id}">${c.name}</option>
                </#items>
            </select>

        </div>
    </#list>
    <input type="submit" name="save" value="save"/><br/><br/>
</form>

</body>
</html>