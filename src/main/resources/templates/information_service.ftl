<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 信息查询（智能搜索） </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
           <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">

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
<button>测试</button>


<!--右侧按键-->
<div class="p_right" align="center">
    <button class="p_button3">现场管理</button>
    <br><br>
    <button class="p_button4">信息查询</button>
    <br><br>
    <button class="p_button3">实时状态</button>
    <br><br>
    <button class="p_button3">信息发布</button>
    <br><br>
    <button class="p_button3" onclick="outpower()">退出</button>
</div>

<!--中间信息查询-->
<div class="i_center" >
   <div class="i_service">
        <font size="5">人员：</font> &emsp;&emsp;&emsp;&emsp;&emsp; <input type="text" class="i_text"><br><br>
        <font size="5">事件：</font> &emsp;&emsp;&emsp;&emsp;&emsp;
        <select class="i_text3">
           <option value="">全部</option>
           <option value ="">成绩</option>
           <option value ="">签到情况</option>
           <option value="">课堂表现</option>
           <option value="">其它</option>
        </select><br><br>
       <font size="5">时间：</font> &emsp;&emsp;&emsp;&emsp;&emsp; <input class="i_text1" type="date" />&emsp;&emsp;&emsp;&emsp;&emsp; &emsp;&emsp; <input class="i_text2" type="time" />
       &emsp;&emsp;&emsp;  &emsp;&emsp;<font size="5">----</font> <input class="i_text1" type="date" />&emsp;&emsp;&emsp;&emsp;&emsp; &emsp;&emsp; <input class="i_text2" type="time" />
       <br><br>
   </div>
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