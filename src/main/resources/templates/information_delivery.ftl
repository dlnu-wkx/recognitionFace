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
<div class="p_center" id="p_center">


</div>


<!--左侧按键-->
<div class="p_left" align="center" id="p_left">
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
    <button  onclick="fieldManagement()" class="p_button3">现场管理</button>
    <br><br>
    <button onclick="informationService()" class="p_button3">信息查询</button>
    <br><br>
    <button onclick="timeStatus()"class="p_button3">实时状态</button>
    <br><br>
    <button class="p_button4" onclick="outmessage()">信息发布</button>
    <br><br>
    <button onclick="powerController()"class="p_button3">退出</button>
</div>
   
<!--下方按键及内容-->
<div class="p_text" align="center">
    <input type="text" class="d_text" value="点击输入滚动消息" onclick="removemes()" id="inputmes">
    <div class="d_font2"><button class="button7">确认</button></div>
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

    function removemes() {
        $("#inputmes").val("");
    }


    //查到该次上课的所有人的姓名
    function findfacbyrid(id) {


        var str="";
        var p_center=$("#p_center");

        $.ajax({
            type: "post",
            url: "/findfacilitybyrid",
            data:{"ztrainroomid":id},
            success: function (data) {


             if(data.length <7){

                 str+="<table class='p_bbbox' id='p_bbox'>"
                  str+=" <tr>";
                  //var类型，不能写成int
                    for(var i=0; i<data.length;i++){
                       str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div class='delivery_sbox'><input id='"+data[i].zid+"' type='checkbox' class='p_check'></div></th>";
                   }
                   str+="</tr>";
                   str+="</table>";
                   str+="<button class='d_button1' onclick=''>全选</button>"

              }else {
                 var j=0;
                 str+="<table class='p_bbbox' id='p_bbox'>"

                 for (var i=0;i<(data.length/6+1);i++){

                     str+=" <tr>";
                     for(;j<6*(i+1);j++){
                        if(j==data.length){break;}
                         str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div class='delivery_sbox'><input type='checkbox' id='"+data[i].zid+"' class='p_check'></div></th>";

                     }
                     str+="</tr>";
                   //  j+=6;
                 }

                 str+="</table>";
                 str+="<button class='d_button1' onclick=''>全选</button>"
             }

                p_center.html(str)

            }
        });
    }

    window.onload =function () {
        onloadallroom();
    }

    function onloadallroom(){
      var p_left=$("#p_left");
      var str=""

        $.ajax({
            type: "post",
            url: "/findalltrainroom",
            success: function (data) {
                    for(var i =0; i<data.length;i++){
                        if(i==0){
                            str+=" <br><br><button onclick='findfacbyrid("+data[i].zid+")' class='p_button1'id='"+data[i].zid+"'>"+data[i].zname+"</button>"
                        }else {
                            str+=" <br><br><button onclick='findfacbyrid("+data[i].zid+")' class='p_button2'id='"+data[i].zid+"'>"+data[i].zname+"</button>"
                        }

                    }
                    p_left.html(str)

            }
        });

    }


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

        window.location.href = "/fixed_task";
    }

    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //信息查询
    function informationService() {
        location.href="/information_service";
    }
    //实时状态
    function timeStatus() {
        location.href="/time_status";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }
    function fieldManagement() {
        location.href="/teachRegister";
    }
</script>

</html>