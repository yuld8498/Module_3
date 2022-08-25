<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<style>
    td{
        margin-right: 30px;
        height: 30px;
        width: 200px;
        margin-bottom: 10px;
    }
    input{
        width: 100%;
        height: 80%;
    }
</style>
<body>
<div class="container">
    <div class="header">
        <h1>Product discount calculator</h1>
    </div>
    <div class="flex-content">
        <form  method="post" action="/Discount">
            <table>
                <thead>
                <th colspan="2">Discount service</th>
                </thead>
                <tbody>
                <tr>
                    <td>Product Description </td>
                    <td><input type="text" name="description" placeholder="input description of product"></td>
                </tr>
                <tr>
                    <td>List Price </td>
                    <td><input type="text" name="list-price" placeholder="input price of product"></td>
                </tr>
                <tr>
                    <td>Number Product </td>
                    <td><input type="number" name="number-product" placeholder="input number product"></td>
                </tr>
                <tr>
                    <td>Discount Percent</td>
                    <td><input type="number" name="discount-percent" placeholder="input discount percent(Number)"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="submit"></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>