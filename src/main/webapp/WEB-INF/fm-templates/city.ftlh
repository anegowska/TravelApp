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

<#if cities??>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col"></th>

    </tr>
    </thead>
    <tbody>
        <#list cities as c>
        <tr>
            <td>${c.name}</td>
            <td>
                <form method="post" action="/city?action=delete">
                    <input type="hidden" name="cid" value="${c.id}">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</#if>
<br/>
<p>Add new city:</p>
<form method="post" action="/city?action=add">
    Name: <input type="text" name="name"/><br/><br/>
    <input type="submit" name="save" value="save"/><br/><br/>

</form>
</body>
</html>