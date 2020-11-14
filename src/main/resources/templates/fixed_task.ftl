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

<!--左侧人员信息-->
<div class="f_left">
    <font size="3" class="f_font1">设备列表</font>
    <button class="f_button1" onclick="allchose()">全选</button>

    <div class="f_leftmes" id="f_leftmes">

    </div>
</div>

<!--右侧课件列表-->
<div class="f_rmessage">
    <font class="f_font2">课件列表</font>
    <input class="f_input1" type="text" id="f_input1">
    <button class="f_button2" onclick="tasklike()">搜索</button>

   <div class="f_table2mes" id="f_table2mes">

   </div>
</div>


<!--弹框-->
<div hidden class="d_popup" id="de_popup" align="center">
    <br>
    <button class="p_button2" onclick="">固定任务</button><br><br>
    <button class="p_button2" onclick="">临时任务</button><br><br>
    <button class="p_button2" onclick="">信息发送</button>
</div>

<div class="f_bottom">
    <button class="p_button2" onclick="informationDelivery()">信息广播</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button2">临时任务</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button2">任务发送</button>
</div>




</body>

<script>

    function informationDelivery() {
        location.href="/information_delivery"
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


    function allchose() {

        //全选
        $("#f_leftmes  input[type='checkbox']").attr("checked","true");
    }


    window.onload =function () {
        load();
    }

    function load() {

        var str=""
        var f_leftmes=$("#f_leftmes")

        var str2=""
        var f_table2mes=$("#f_table2mes")

        $.ajax({
            type: "post",
            url: "/findallnandf",
            success: function (data) {

                str+="  <table class='f_table1' align='center' id='f_table'>"
                str+="<tr> <th> 当前人员 </th> <th>机床号 </th> <th> 选项 </th> </tr>"
                for (var i=0; i<data.length;i++) {
                    str+=" <tr> <th> "+data[i].zname+" </th> <th>"+data[i].zidentity+" </th> <th> <input id='"+data[i].zid+"' type='checkbox'> </th> </tr> "
                }
                str+=" </table>"
                f_leftmes.html(str)
            }
        });


        $.ajax({
            type: "post",
            url: "/findalltask",
            success: function (data) {

                str2+=" <table class='f_table2'>";
                str2+="<tr><th>课件名称</th><th>选项</th></tr>"
                for (var i=0;i<data.length;i++){
                    str2+=" <tr><th>"+data[i].zname+"</th><th><input type='checkbox' id='"+data[i].zid+"'></th></tr>"
                }
                str2+="</table>";

                f_table2mes.html(str2)
            }
        });

    }


    function tasklike() {
        var str2=""
        var f_table2mes=$("#f_table2mes")
        var zname=$("#f_input1").val()

        $.ajax({
            type: "post",
            url: "/findtasklikename",
            data:{"zname":zname},
            success: function (data) {
                str2+=" <table class='f_table2'>";
                str2+="<tr><th>课件名称</th><th>选项</th></tr>"
                for (var i=0;i<data.length;i++){
                    str2+=" <tr><th>"+data[i].zname+"</th><th><input type='checkbox'></th></tr>"
                }
                str2+="</table>";

                f_table2mes.empty();
                f_table2mes.html(str2)
            }
        })
        }







</script>

</html>