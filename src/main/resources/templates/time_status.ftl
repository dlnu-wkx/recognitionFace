<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 实时状态 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/time_status.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >实时状态</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>


<!--中部电源显示-->
<div class="p_center">
</div>



<!--右侧按键-->
<div class="p_right" align="center">
    <button  onclick="fieldManagement()"class="p_button3">现场管理</button>
    <br><br>
    <button onclick="informationService()" class="p_button3">信息查询</button>
    <br><br>
    <button class="p_button4">实时状态</button>
    <br><br>
    <button onclick="informationDelivery()" class="p_button3">信息发布</button>
    <br><br>
    <button class="p_button3" onclick="powerController()">退出</button>
</div>

<!--机床信息示意-->
<div id="center" class="i_center" >
    <table class="t_table">
        <tr>
            <th>
               <button class="t_button1" id="1" onclick="diagram(1)">机床示意</button>
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
<div class ="t_hiddenArea" id="hiddenArea">
</div>
<!--学生视频展示-->
<#--<div class="t_message" hidden align="center" id="t_message">


    <button class="t_button2"></button>
    <div class="t_id" align="center"><font size="3">F01</font></div>
    <div class="t_name" align="center"><font size="3">姓名：张三</font></div>


    <div class="t_student" align="center"><font size="3">电脑屏幕</font></div>
    <div class="t_computer" align="center"><font size="3">摄像头</font></div>

    <input type="text" class="t_progress" value="当前进度:">
    <input type="text" class="t_staets" value="状态信息">

    <button class="t_button3" onclick="closemessage()">关闭</button>
</div>-->


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
    //弹框
    window.onload=showStudentStatus();




    function outpower(){
        $("#popup").show()
    }

    //锁屏
    function lockscreen() {
        $("#parent").show()
    }

    //学生实时视频
    function diagram(id) {
        alert(id)
        findRaiseHand(id);
        findStudentName(id);
        presentProgess(id);
        $("#t_message"+id).show()

    }
    //关闭
    function closemessage(id) {
        $("#t_message"+id).hide()
    }
    //现场管理
    function fieldManagement() {
        location.href="/teachRegister";
    }
    //信息查询
    function informationService() {
        location.href="/information_service";
    }
    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }
</script>

</html>