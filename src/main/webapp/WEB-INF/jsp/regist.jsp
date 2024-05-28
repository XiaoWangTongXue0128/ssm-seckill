<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <%@include file="common/head.jsp" %>

    <script type="application/javascript">

        $(function () {
            $("#regForm").submit(function () {
                // alert("段落被点击了。");

                // 手机号验证
                var phoneText = $("#phoneId").val();
                if (phoneText.length != 11) {
                    // 提示手机号不正确
                    alert("提示手机号不正确");
                    return false;
                }
                // 密码验证
                var pwd1Text = $("#pwd1Id").val();
                var pwd2Text = $("#pwd2Id").val();
                if (pwd1Text != pwd2Text) {
                    // 提示手机号不正确
                    alert("密码2次不一致");
                    return false;
                }

                return true;

            });

        });

    </script>
</head>
<body>


<div class="container">

    <form class="form-signin" action="/seckill/regist" method="post" id="regForm">
        <h2 class="form-signin-heading">用户注册</h2>
        <label for="inputPhone" class="sr-only">手机号</label>
        <input id="phoneId" type="text" id="inputPhone" class="form-control" placeholder="手机号" required autofocus
               name="phone">
        <label for="inputPassword1" class="sr-only">用户密码</label>
        <input id="pwd1Id" type="password" id="inputPassword1" class="form-control" placeholder="用户密码" required
               name="password1">
        <label for="inputPassword2" class="sr-only">确认密码</label>
        <input id="pwd2Id" type="password" id="inputPassword2" class="form-control" placeholder="确认密码" required
               name="password2">

        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
    </form>

</div> <!-- /container -->

</body>
</html>
