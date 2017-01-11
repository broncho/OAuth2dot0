<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录认证</title>
</head>
<body>
<form id="login" action="${authorize_url}" method="post">
    <label for="username_input">用户名</label> <input id="username_input" name="username" type="text"/>
    <label for="password_input">用户密码</label> <input id="password_input" name="password" type="password"/>
    <input id="submit_button" type="submit" name="submit" value="提交">
</form>
</body>
</html>