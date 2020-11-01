<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 信息查询（智能搜索） </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/time_status.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >信息查询（智能搜索）</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>


<!--中部电源显示-->
<div class="p_center">
</div>



<!--右侧按键-->
<div class="p_right" align="center">
    <button class="p_button3">现场管理</button>
    <br><br>
    <button class="p_button3">信息查询</button>
    <br><br>
    <button class="p_button4">实时状态</button>
    <br><br>
    <button class="p_button3">信息发布</button>
    <br><br>
    <button class="p_button3" onclick="outpower()">退出</button>
</div>

<!--机床信息示意-->
<div class="i_center" >
    <table class="t_table">
        <tr>
            <th>
               <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
        </tr>
        <tr>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
        </tr>
        <tr>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
        </tr>
        <tr>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
            <th>
                <button class="t_button1">机床示意</button>
            </th>
        </tr>
    </table>
    <input type="text" class="t_text">

</div>


<!--弹框-->
<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>
    <font size="3">弹窗</font><br>
    <button class="p_button2">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="parent" hidden></div>


</body>

<script>
    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
    }

</script>

</html>