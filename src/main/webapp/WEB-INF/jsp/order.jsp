<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>订单确认</title>

    <%--    把公用的内容单独翻入到head中去--%>
    <%@include file="common/head.jsp" %>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        #time {
            background-color: #D00414;
            line-height: 120px;
            margin: auto auto;
            text-align: center;
        }

        .zz {
            color: #D00414;
            border-radius: 5px;
            border: 1px solid #440106;
            font-size: 45px;
            background-color: #440106;
        }

        .st {
            font-size: 18px;
            color: white;
        }

    </style>

    <script type="application/javascript">

        function countDownPrint(htmlId, val) {
            // 规范时间格式 00 01 ... 09 10 11
            if (val < 10) {
                val = "0" + val;
            }

            $("#" + htmlId).text(val);
        }

        var orderTime = ${orderTime.time};
        // 支付超时时间5分钟
        var temp   ;
        // 超时时间 = 订单生成时间 + 5分钟
        var orderExpire = orderTime + 15 * 1000;
        // var orderExpire = orderTime + 5 * 60 * 1000;


        // 浏览器加载 HTML 就执行
        // 每个1秒 执行 function方法的内容
        var intervalID = window.setInterval(function () {

            // 当前时间用客户端时间
            var current = new Date().getTime();

            temp = orderExpire - current;

            if (temp <= 0) {
                // 支付超时
                $('#payBtn').removeClass("btn-success");
                $('#payBtn').addClass("disabled");
                $('#payBtn').addClass("btn-default");
                $("#payBtn").removeAttr("type");
                $("#payTextId").text('订单超时，请重新下单！');
                return;
            }

            console.log("temp = " + temp);

            // 定义 天、时、分、秒
            var m, s = 0;

            // 分
            m = Math.floor(temp / 1000 / 60 % 60);
            // 秒
            s = Math.floor(temp / 1000 % 60);

            // 更新时间
            countDownPrint("sec_min", m);
            countDownPrint("sec_sec", s);

            // 基于服务器的时间，手动更新1秒
            temp = temp - 1000;

        }, 1000);
    </script>
</head>
<body>
<div class="container">

    <div class="panel panel-danger">
        <!-- Default panel contents -->
        <div class="panel-heading">${item.name}</div>
        <div class="alert alert-success" role="alert">秒杀价：${item.price}</div>
        <div class="alert alert-success" role="alert">订单号：${seckillOrder.orderCode}</div>

        <h1 class="text-center" id="payTextId">请尽快支付，完成订单确认~！</h1>
        <%--        当前场次 00 天 00 时 00 分 00 后结束抢购--%>
        <div id="seckillBox">
            <div id="time">
                <span class="st" id="text1Id">支付剩余时间</span>

                <span id="sec_min" class="zz">00</span>
                <span>分</span>
                <span id="sec_sec" class="zz">00</span>

            </div>
        </div>
        <form action="/seckill/pay" method="get">
            <input type="hidden" name="orderCode" value="${seckillOrder.orderCode}">
            <button id="payBtn" class="btn btn-success " type="submit">支付</button>
        </form>
    </div>
</div>

</body>
</html>
