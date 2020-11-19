<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="./jquery/jquery-3.3.1.min.js"></script>
    <script src="./layui/layui.js"></script>
    <script src="./jquery/jquery.cookie.js"></script>
</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">



<div class="layui-layout layui-layout-admin" >
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #C6C6C6">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">教师端登陆</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
            欢迎
        </div>
        <div class="layui-col-xs9" align="center" style="width: 69%">
            <div style="margin: 0,auto;margin-top:40px;height: 80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
                ${name}
            </div>
            <div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='${path}'style='width: 15rem;height: 16rem;'></div>

            <div>

            </div>
        </div>
        <!--右侧按键-->
        <div class="p_right" align="center">
            <button onclick="fieldManagement()" class="p_button3">现场管理</button>
            <br><br>
            <button onclick="informationService()" class="p_button3">信息查询</button>
            <br><br>
            <button onclick="timeStatus()" class="p_button3">实时状态</button>
            <br><br>
            <button onclick="informationDelivery()" class="p_button3">信息发布</button>
            <br><br>
        </div>
        <div class="outdiv">
            <button class="p_button3" id="p_button3" onclick="powerController()">退出</button></div>
    </div>
</div>
<script>


    window.onload = function(){

        var zselecttest=$.cookie('zselecttest');


    }


    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });

    //跳安全测试的方法
    function test() {


    }

</script>
</body>
</html>