<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 任务发布 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/fixed_task.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >任务发布</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>




<!--右侧按键-->
<div class="p_right" align="center">
    <button onclick="fieldManagement()" class="p_button3">现场管理</button>
    <br><br>
    <button onclick="informationService()" class="p_button3">信息查询</button>
    <br><br>
    <button onclick="timeStatus()" class="p_button3">实时状态</button>
    <br><br>
    <button onclick="informationDelivery()" class="p_button4" onclick="outmessage()">信息发布</button>
    <br><br>
    <button onclick="powerController()" class="p_button3">退出</button>
</div>

<!--左侧人员信息-->
<div class="f_left">
    <font size="3" class="f_font1">设备列表</font>
    <button class="f_button1" onclick="allchose()">全选</button>

        <table class="f_table1" align="center" id="f_table">
            <tr>

                <th>
                    当前人员
                </th>
                <th>
                    机床号
                </th>
                <th>
                    选项
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr><tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr><tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr><tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
            <tr>

                <th>
                    张三
                </th>
                <th>
                    F01
                </th>
                <th>
                    <input type="checkbox">
                </th>
            </tr>
        </table>
</div>

<!--右侧课件列表-->
<div class="f_rmessage">
    <font class="f_font2">课件列表</font>
    <input class="f_input1" type="text" >
    <button class="f_button2">搜索</button>

    <table class="f_table2">
        <tr>
            <th>课件名称</th>
            <th>
                选项
            </th>
        </tr>
        <tr>
            <th>课件名称1</th>
            <th>
                <input type="checkbox">
            </th>
        </tr>

    </table>
</div>


<!--弹框-->
<div hidden class="d_popup" id="de_popup" align="center">
    <br>
    <button class="p_button2" onclick="">固定任务</button><br><br>
    <button class="p_button2" onclick="">临时任务</button><br><br>
    <button class="p_button2" onclick="">信息发送</button>
</div>

<div class="f_bottom">
    <button class="p_button2">信息广播</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button2">临时任务</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button2">任务发送</button>
</div>




</body>

<script>

    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
    }

    function outmessage() {
        alert(1)
        $("#de_popup").show()
    }


    function allchose() {

        //全选
        $("#f_table  input[type='checkbox']").attr("checked","true");
    }
    //现场管理
    function fieldManagement() {
        location.href="/field_management";
    }
    //信息查询
    function informationService() {
        location.href="/information_service";
    }
    //实时状态
    function timeStatus() {
        location.href="/time_status";
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