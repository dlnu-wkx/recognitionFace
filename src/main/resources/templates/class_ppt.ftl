<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 题库 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">

    <#--  <script type="text/javascript" src="./layui/layui.js ">   </script>-->
    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >登陆后界面</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>

<!--左侧灰色按键-->
<div class="left">
    <button class="button8" id="button1">固定任务</button>
    <br><br>
    <button class="button9" id="button2">临时任务</button>
    <br><br>
    <button class="button2" id="button3">临时任务</button>
    <br><br>
    <button class="button2" id="button4">任务名称</button>
    <br><br>
    <button class="button2" id="button5">任务名称</button>
    <br><br>
    <button class="button2" id="button6">任务名称</button>
    <br><br>
    <button class="button2" id="button7">任务名称</button>
    <br><br>
    <button class="button2" id="button8">任务名称</button>
    <br><br>
    <button class="button2" id="button9">任务名称</button>
    <br><br>
</div>

<div class="right">
    <!--右侧按键-->
    <div class="right1">
        <button class="button5">举手</button>
    </div>

    <div class="right2">
        <button class="button5">请假</button>
    </div>

    <div class="right3">
        <button class="button5">退出系统</button>
    </div>
</div>


<!--中间题目主体部分-->
<div class="center" id="qbank" style="background-color: white">
    <img src='C:\Users\Administrator\Desktop\fkq\test.jpg'>

</div>


<br><br>


<!--翻页-->
<div class="pages">
    <button class="button3" id="lastpage" onclick="lastpage()">上一页</button>
    <button class="button4" id="nextpage" onclick="nextpage()">下一页</button>
</div>





</body>

<script>

    //页面加载前方法
    window.onload =function () {
       $("#lastpage").css("background-color","#A5A5A5");
    }


</script>

</html>