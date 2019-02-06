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
<h2>Customers list</h2>
<br>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Pesel</th>
        <th scope="col">Phone</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <#list customers as c>
        <tr>
            <td>${c.name}</td>
            <td>${c.surname}</td>
            <td>${c.pesel}</td>
            <td>${c.phone}</td>
            <td>
                <form method="post" action="/customer?action=delete">
                    <input type="hidden" name="cid" value="${c.id}">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
<br/>
<a href="/customer">Back to <strong>Add customer</strong></a>
</body>
</html>
