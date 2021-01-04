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
    <script src="/layui/layui.js"></script>
    <script type="text/javascript" src="jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<div>
    <script>
        var layer;
        $(function () {
            layui.use("layer",function () {
                layer =layui.layer;
            });
        })
    </script>

</div>
<!--头部导航条-->
<div class="top">
    <div class="leftfont">实时状态</div>
    <div class="rightfont">安浩智能学习工厂</div>
</div>


<!--中部电源显示-->
<div class="p_center">
</div>



<!--右侧按键-->
<div class="t_right" align="center">
    <button  onclick="fieldManagement()"class="t_field_management">现场管理</button>
    <br><br>
    <button onclick="informationService()" class="t_information_service">信息查询</button>
    <br><br>
    <button class="t_button4">实时状态</button>
    <br><br>
    <button onclick="informationDelivery()" class="t_information_delivery">信息发布</button>
    <br><br>
    <button class="t_exit" id="exit" onclick="outpower()">退出</button>
</div>

<!--机床信息示意-->
<div id="center" class="i_center" >
   <#-- <table class="t_table">
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
    </table>-->
   <#-- <input type="text" class="t_text">-->

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
    <button class="p_button2">下课</button>
</div>

<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>

    <button class="p_button2">下课</button>
</div>
<!--蒙版-->
<div id="parent" class="parent" hidden></div>


</body>

<script>

    //弹框
    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
        $("#popup").hide();
        $("#exit").css('background-color','#FFC000');
        $("#exit").text('解锁');
        $("#exit").attr("onclick","removescreer();");
    }
    function removescreer(){
        $("#parent").hide();
        $("#exit").text('退出系统');
        $("#exit").css('background-color','#4472c4');
        $("#exit").attr("onclick","outpower();");
    }


    window.onload=showStudentStatus();
       var times=setInterval(function(){
        showStudentStatus()
    }, 5000)





    //学生实时视频
    function diagram(id) {

        var str =$("#status"+id).val();
        var raisehand = new RegExp("举手");
        var leave = new RegExp("请假")
        if(raisehand.test(str)){
            //处理学生的举手请求
            cancelRaisehand(id);
        }
        if(leave.test(str)){
            document.getElementById(id).style.display="block";
        }
    //取消举手
    function cancelRaisehand(id) {
        $.ajax({
            type: 'post',
            url: '/cancelRaisehand',
            data:{"id":id},
            success:function (data) {
                if(data == 1){
                    layer.msg("已处理", { icon: 1, offset: "auto", time:1000 });
                }
            }
        })
        }


    }
    //关闭
    function closemessage(id) {
        $("#t_message"+id).hide()
    }
    //现场管理
    function fieldManagement() {
        location.href="/field_management";
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