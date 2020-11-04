<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 机床控制器电源管理 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >机床控制器电源管理</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>


<!--中部电源显示-->
<div class="p_center">
    <table class="p_bbbox">


        <tr>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
            <div class="power_bbox"  align="center">
                <font size="3">名称1</font>
                <div class="power_sbox">
                    <input type="checkbox" class="p_check">
                </div>
        </th>    <th>
            <div class="power_bbox"  align="center">
                <font size="3">名称1</font>
                <div class="power_sbox">
                    <input type="checkbox" class="p_check">
                </div>
        </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
        </tr>
        <tr>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"   align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
            <div class="power_bbox"  align="center">
                <font size="3">名称1</font>
                <div class="power_sbox">
                    <input type="checkbox" class="p_check">
                </div>
        </th>    <th>
            <div class="power_bbox"   align="center">
                <font size="3">名称1</font>
                <div class="power_sbox">
                    <input type="checkbox" class="p_check">
                </div>
        </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
        </tr>
        <tr>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>    <th>
            <div class="power_bbox"  align="center">
                <font size="3">名称1</font>
                <div class="power_sbox">
                    <input type="checkbox" class="p_check">
                </div>
        </th>    <th>
            <div class="power_bbox"  align="center">
                <font size="3">名称1</font>
                <div class="power_sbox">
                    <input type="checkbox" class="p_check">
                </div>
        </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
            <th>
                <div class="power_bbox"  align="center">
                    <font size="3">名称1</font>
                    <div class="power_sbox">
                        <input type="checkbox" class="p_check">
                    </div>
            </th>
        </tr>
    </table>
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
    <button onclick="fieldManagement()" class="p_button3">现场管理</button>
<br><br>
    <button onclick="informationService()" class="p_button3">信息查询</button>
<br><br>
    <button onclick="timeStatus()" class="p_button3">实时状态</button>
<br><br>
    <button onclick="informationDelivery()" class="p_button3">信息发布</button>
    <br><br>
    <button class="p_button4" onclick="outpower()">退出</button>
</div>

<!--下方按键及内容-->
<div class="p_text" align="center">
    <br><br>
    <button class="p_button5">开启</button>&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5">关闭</button>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5">全部开启</button>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5">全部关闭</button><br><br>
    <input type="checkbox"><font size="3">关闭时延时</font><input type="text" size="1"><font size="3">分钟</font>
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





</body>

<script>

    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
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
</script>

</html>