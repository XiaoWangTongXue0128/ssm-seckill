<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>商品秒杀</title>

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

        // 规范时间格式 00 01 ... 09 10 11
        function countDownPrint(htmlId, val) {

            if (val < 10) {
                val = "0" + val;
            }

            $("#" + htmlId).text(val);
        }




        // JavaScript 日志 初始化  new Date(long) = time;
        // item.endTime.time --> xxxx long
        var startTime = new Date(${item.startTime.time});
        var endTime = new Date(${item.endTime.time});
        var serverNowLong;

        //  请求服务器获取当前事件
        $.get("/seckill/now", {}, function (result) {
            // result json
            // {"success":true,"data":1595944752610,"message":"now long"}

            // 获取服务器返回的当前时间，对应你的一次请求
            serverNowLong = result['data'];

            // 如果服务器返回的当前时间大于endTime结束时间，意味着活动已经结束了，没有必要执行后续的逻辑了。
            var endLong = serverNowLong - endTime.getTime();
            if (endLong >= 0) {

                // 秒杀活动已经结束
                seckillOver();

                return;
            }

            // 浏览器加载 HTML 就执行
            // 每个1秒 执行 function方法的内容
            var intervalID = window.setInterval(function () {

                // 用当前时间和结束对比 相减
                // long - long =  时间之差
                // 毫秒数 分别 获取 天/时/分/秒
                // 1天 = 24小时 = 24 * 60 * 1000 * 60
                // var temp = endTime.getTime() - new Date().getTime();
                var temp = startTime.getTime() - serverNowLong;

                // 判断temp 是否小于0 小于0 就说明秒杀活动开始
                if (temp <= 0) {
                    // 结束掉周期函数
                    clearInterval(intervalID);
                    // 秒杀活动开始，显示秒杀按钮，获取秒杀URL
                    startSeckill();
                    return;
                }

                console.log("temp = " + temp);

                // 定义 天、时、分、秒
                var d, h, m, s = 0;

                // 天
                d = Math.floor(temp / 1000 / 60 / 60 / 24);
                // 时
                h = Math.floor(temp / 1000 / 60 / 60 % 24);
                // 分
                m = Math.floor(temp / 1000 / 60 % 60);
                // 秒
                s = Math.floor(temp / 1000 % 60);

                // 更新时间
                countDownPrint("sec_day", d);
                countDownPrint("sec_hour", h);
                countDownPrint("sec_min", m);
                countDownPrint("sec_sec", s);

                // 基于服务器的时间，手动更新1秒
                serverNowLong = serverNowLong + 1000;

            }, 1000);
        })

        //秒杀结束文本设置
        function seckillOver() {
            $('#seckillBtn').removeClass("btn-default");
            $('#seckillBtn').addClass("btn-warning");
            $('#seckillBtn').text("抢购结束");
            $("#seckillTextId").text('秒杀活动已经结束');
        }

        // function countdownComponent(temp) {
        //     // 定义 天、时、分、秒
        //     var d, h, m, s = 0;
        //
        //     // 天
        //     d = Math.floor(temp / 1000 / 60 / 60 / 24);
        //     // 时
        //     h = Math.floor(temp / 1000 / 60 / 60 % 24);
        //     // 分
        //     m = Math.floor(temp / 1000 / 60 % 60);
        //     // 秒
        //     s = Math.floor(temp / 1000 % 60);
        //
        //     // 更新时间
        //     countDownPrint("sec_day", d);
        //     countDownPrint("sec_hour", h);
        //     countDownPrint("sec_min", m);
        //     countDownPrint("sec_sec", s);
        // }

        function startSeckill() {
            // alert("可以开始秒杀商品");
            $("#seckillTextId").text('秒杀活动正在进行中');

            // 修改计时器，变更成还有多久结束
            $("#text1Id").text('距离活动结束还有');
            $("#text2Id").text('错过又要等一年！');
            var intervalID = window.setInterval(function () {

                // 用当前时间和结束对比 相减
                // long - long =  时间之差
                // 毫秒数 分别 获取 天/时/分/秒
                // 1天 = 24小时 = 24 * 60 * 1000 * 60
                // var temp = endTime.getTime() - new Date().getTime();
                var temp = endTime.getTime() - serverNowLong;

                // 判断temp 是否小于0 小于0 就说明秒杀活动结束
                if (temp <= 0) {
                    // 结束掉周期函数
                    clearInterval(intervalID);

                    seckillOver();

                    return;
                }

                console.log("temp = " + temp);

                // 定义 天、时、分、秒
                var d, h, m, s = 0;

                // 天
                d = Math.floor(temp / 1000 / 60 / 60 / 24);
                // 时
                h = Math.floor(temp / 1000 / 60 / 60 % 24);
                // 分
                m = Math.floor(temp / 1000 / 60 % 60);
                // 秒
                s = Math.floor(temp / 1000 % 60);

                // 更新时间
                countDownPrint("sec_day", d);
                countDownPrint("sec_hour", h);
                countDownPrint("sec_min", m);
                countDownPrint("sec_sec", s);

                // 基于服务器的时间，手动更新1秒
                serverNowLong = serverNowLong + 1000;

            }, 1000);


            // 获取服务器的秒杀地址
            var seckillId = ${item.id};
            $.post("/seckill/getSeckillUrl/" + seckillId, {}, function (result) {
                if (result && result['success']) {
                    // 正常返回结果
                    var seckillUrl = result['data'];
                    // 判断当前是否在活动期间
                    if (seckillUrl['enable']) {

                        // 调整按钮样式
                        $('#seckillBtn').removeClass("btn-default");
                        $('#seckillBtn').removeClass("disabled");
                        $('#seckillBtn').addClass("btn-success");
                        // 给下单按钮添加一次点击事件
                        $('#seckillBtn').one('click', function () {
                            // alert('one click');
                            // 设置下单按钮不可用
                            $(this).addClass('disabled');

                            // 触发下单请求
                            // /com.duyi.seckill/execute/{seckillId}/{md5}
                            var url = "/seckill/execute/" + seckillId + "/" + seckillUrl['md5'];
                            // $.post(url, {}, function (result) {
                            //
                            //     alert("秒杀结果：" + result['message'] + ", success = " + result['success']);
                            //
                            // });
                            window.location.href = url;


                        });

                    } else {
                        // 活动结束
                        seckillOver();

                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="container">

    <div class="panel panel-danger">
        <!-- Default panel contents -->
        <div class="panel-heading">${item.name}</div>
        <div class="alert alert-success" role="alert">秒杀价：${item.price}，数量：${item.number}</div>

        <h1 class="text-center" id="seckillTextId">秒杀活动即将开始</h1>
        <%--        当前场次 00 天 00 时 00 分 00 后结束抢购--%>
        <div id="seckillBox">
            <div id="time">
                <span class="st" id="text1Id">当前场次</span>

                <span id="sec_day" class="zz">00</span>
                <span>天</span>
                <span id="sec_hour" class="zz">00</span>
                <span>时</span>
                <span id="sec_min" class="zz">00</span>
                <span>分</span>
                <span id="sec_sec" class="zz">00</span>

                <span class="st" id="text2Id">开始抢购</span>

            </div>
        </div>
        <button id="seckillBtn" class="btn btn-default disabled">开始抢购</button>

    </div>
</div>

</body>
</html>
