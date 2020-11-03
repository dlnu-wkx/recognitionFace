<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 信息发送 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >信息发送</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>


<!--中部电源显示-->
<div class="p_center">
    <table class="p_bbbox">
        <tr>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
        </tr>
        <tr>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"   align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
                <div class="power_bbox"   align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
        </tr>
        <tr>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="delivery_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
        </tr>
    </table>
    <button class="d_button1">全选</button>
</div>


<!--左侧按键-->
<div class="p_left" align="center">
    <br><br>
    <button class="p_button1" id="button1">数控车实训室</button>
    <br><br>
    <button class="p_button2" id="button2">数控铣实训室</button>
    <br><br>
    <button class="p_button2" id="button3">数控车展示屏</button>
    <br><br>
    <button class="p_button2" id="button4">数控铣展示屏</button>
    <br><br>
</div>
<!--右侧按键-->
<div class="p_right" align="center">
    <button class="p_button3">现场管理</button>
    <br><br>
    <button class="p_button3">信息查询</button>
    <br><br>
    <button class="p_button3">实时状态</button>
    <br><br>
    <button class="p_button4" onclick="outmessage()">信息发布</button>
    <br><br>
    <button class="p_button3">退出</button>
</div>
   
<!--下方按键及内容-->
<div class="p_text" align="center">
    <input type="text" class="d_text" value="点击输入滚动消息">
    <div class="d_font2"><font size="5">选项</font></div>
    <div class="d_choose">
    <div class="d_font">
        <font >显示:</font> <input type="text" value="" size="1"><font>秒</font>
    </div>
</div>
</div>


<!--弹框-->
<div hidden class="d_popup" id="de_popup" align="center">
    <br>
    <button class="p_button2" onclick="fixed_tasks()">固定任务</button><br><br>
    <button class="p_button2" onclick="">临时任务</button><br><br>
    <button class="p_button2" onclick="">信息发送</button>
</div>

<!--蒙版-->
<div id="parent" class="parent" hidden></div>


</body>





</body>

<script>

    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
    }
    
    function outmessage() {
        $("#de_popup").show()
    }
    
    function fixed_tasks() {
        alert(1);

        window.location.href = "/fixed_task";
    }


</script>

</html>