<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 机床控制器电源管理 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/js/common.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >机床控制器电源管理</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>


<!--中部电源显示-->
<div class="p_center" id="p_center">

</div>


<!--左侧按键-->
<div class="p_left" align="center" id="p_left">

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
    <button class="p_button4" id="p_button4" onclick="outpower()">退出</button></div>


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
    <button class="p_button2" onclick="overclass()">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="parent" hidden></div>


</body>





</body>

<script>



    var num=0;

    function lockscreen() {
        //蒙版出现
        $("#parent").show();
        //弹框消失
        $("#popup").hide();
        //按键文字改变，颜色改变，方法改变
        $("#p_button4").css('background-color','#FFC000');
        $("#p_button4").text('解锁');
        $("#p_button4").attr("onclick","removescreer()");

    }

    function removescreer() {
        location.href = "/teacher";
    }

    function removemes() {
        $("#inputmes").val("");
    }

    var zlocation="";


    //查到被点击实训室的所有设备
    function findfacbyrid(id) {

        zlocation =id;

        $("#p_left button").css("background-color","#70AD47");

        var str="";
        var p_center=$("#p_center");
        p_center.empty();
        $.ajax({
            type: "post",
            url: "/findfacilitybyrid",
            data:{"zlocation":id},
            success: function (data) {
                    num=data.length;

                $("#"+id+"").css("background-color","#FFC000")

                if(data.length <7){

                    str+="<table class='p_bbbox' id='p_bbox'>"
                    str+=" <tr>";
                    //var类型，不能写成int
                    for(var i=0; i<data.length;i++){

                         if (data[i].zpowerstatus=="已开机"){
                             str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div class='delivery_sbox'><input id='"+data[i].zidentity+"' type='checkbox' class='p_check'></div></th>";
                         }else if (data[i].zpowerstatus=="关机"){
                             str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div class='delivery_unpowerbox'><input id='"+data[i].zidentity+"' type='checkbox' class='p_check'></div></th>";
                         }

                    }
                    str+="</tr>";
                    str+="</table>";
                    str+="<button class='d_button1' onclick='allchose()'>全选</button>"

                }else {
                    var j=0;
                    str+="<table class='p_bbbox' id='p_bbox'>"

                    for (var i=0;i<(data.length/6+1);i++){

                        str+=" <tr>";
                        for(;j<6*(i+1);j++){
                            if(j==data.length){break;}
                            if (data[j].zpowerstatus=="已开机"){
                                str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div class='delivery_sbox'><input id='"+data[j].zidentity+"' type='checkbox' class='p_check'></div></th>";
                            }else if (data[j].zpowerstatus=="关机"){
                                str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div class='delivery_unpowerbox'><input id='"+data[j].zidentity+"' type='checkbox' class='p_check'></div></th>";
                            }

                        }
                        str+="</tr>";
                        //  j+=6;
                    }

                    str+="</table>";
                    str+="<button class='d_button1' onclick='allchose()'>全选</button>"
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
                    str+=" <br><br><button onclick='findfacbyrid("+data[i].zlocation+")' class='p_button2'id='"+data[i].zlocation+"'>"+data[i].zname+"</button>"
                }
                p_left.html(str)

            }
        });

    }

    function allchose() {
        $("#p_center  input[type='checkbox']").attr("checked","true");
    }

    function insertcommand() {
        var inputmes=$("#inputmes").val()
        alert(inputmes)
        if(inputmes !=null|| imputmes!="" || inputmes!="点击输入滚动消息"){
            $.ajax({
                type: "post",
                url: "/insertcommand",
                data:{"zcontent":inputmes,"zlocation":zlocation},
                success: function (data) {
                    if(data>0){
                        alert("发布成功")
                    }

                }
            });
        }
    }

    function overclass() {

        $.ajax({
            type: "post",
            url: "/overclass",
            data:{"num":num},
            success: function (data) {
                if(data!=0){
                    alert("成功下课，等待电源关闭")
                }
            }
        });
    }






</script>

</html>