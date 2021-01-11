<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 机床控制器电源管理 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/js/common.js"></script>
    <script src="./layui/layui.js"></script>

</head>
<body  class="body" >
<!--警示消息-->
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
    <button onclick="fieldManagement()" class="p_field_management">现场管理</button>
<br><br>
    <button onclick="informationService()" class="p_information_service">信息查询</button>
<br><br>
    <button onclick="timeStatus()" class="p_time_status">实时状态</button>
<br><br>
    <button onclick="informationDelivery()" class="p_information_delivery">信息发布</button>
    <br><br>
    <button class="p_exit" id="p_button4" onclick="outpower()">退出</button>
</div>
<#--<div class="outdiv">
   </div>-->


<!--下方按键及内容-->
<div class="p_text" align="center">
    <br><br>
    <button class="p_button5" onclick="startchose()">开启</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5" onclick="startallfacti()">全部开启</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5" onclick="closechose()">关闭</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5" onclick="endallfacti()">全部关闭</button>
</div>

<#--<!--关闭时间控制&ndash;&gt;
<div class="closetime" align="center">
    <font size="3">关闭电源时作用：</font><br><br>
    <input type="checkbox" name="closetime" id="closetime" class="p_chose1">&emsp;&emsp;&emsp;
    <font size="3">关闭时延时</font><input type="number" class="p_input" id="timenumber"><font size="3">分钟</font>

</div>-->


<!--测试题控制-->
<div class="p_testchose" align="center">
     <font size="3">开启电源时作用:</font><br><br>
    &emsp;&emsp;<input type="checkbox" name="istest" id="istest" hidden class="p_chose1">&emsp;&emsp;&emsp;
    <font>测试合格分数</font>&emsp;&emsp;<input type="number" class="p_input" id="testcode">&emsp;分
</div>



<!--弹框-->
<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>
    <button class="p_button2" onclick="overclass()">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="parent" hidden></div>



</body>



<script>
    //全部开启
    function startallfacti() {
       //alert(1)
        if($('#istest').prop('checked')){
            var zpassingscore=$("#testcode").val()
            $.ajax({
                type: "post",
                url: "/updatetestbyscheduleid",
                data:{"zpassingscore":zpassingscore},
                async: false,
                success: function (data) {
                    if(data>0){
                        layer.msg("已全部开启安全测试", { icon: 1, offset: "auto", time:1000 });
                    }
                }
            });

        }else{
            $.ajax({
                type: "post",
                url: "/updatenotestbyscheduleid",
                async: false,
                success: function (data) {
                    //alert(data)
                    if(data>0){
                        layer.msg("不开启安全测试", { icon: 1, offset: "auto", time:1000 });
                    }
                }
            });
        }

        $.ajax({
            type: "post",
            url: "/updateallfacility",
            data:{"ztrainroomid":ztrainroomid,"zpowerstatus":"已开机"},
            async: false,
            success: function (data) {
                if(data>0){
                    layer.msg("已全部开启，等待电源开启", { icon: 1, offset: "auto", time:1000 });
                }else{
                    alert("出错")
                }
            }
        });
        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);
    }

    //全部关闭
    function endallfacti() {
       // alert(2)
        //延时按键有被选中，加载延时关闭选项
       /* if($('#closetime').prop('checked')){

            var timenumber=$("#timenumber").val();
            setTimeout(function (){

                $.ajax({
                    type: "post",
                    url: "/updateallfacility",
                    data:{"ztrainroomid":ztrainroomid,"zpowerstatus":"未开机"},
                    async: false,
                    success: function (data) {
                        if(data>0){
                            layer.msg("已接受关机命令，请等待"+timenumber+"分钟后关机", { icon: 1, offset: "auto", time:1000 });
                        }else{
                            alert("出错")
                        }
                    }
                });

            }, 60000*timenumber);
        }//延时按键没有被选中，加载直接关闭方法
        else{*/
            $.ajax({
                type: "post",
                url: "/updateallfacility",
                data:{"ztrainroomid":ztrainroomid,"zpowerstatus":"未开机"},
                async: false,
                success: function (data) {
                    if(data>0){
                        layer.msg("已全部关闭，等待设备关闭", { icon: 1, offset: "auto", time:1000 });
                    }else{
                        alert("出错")
                    }
                }
            });
      //  }
        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);

    }

    //开启选中的
    function startchose() {
        var j=0;
        var startchose =[];
        $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            startchose[i] =$(this).val();
        });
          //  alert(startchose[i]);
            $.ajax({
                type: "post",
                url: "/updateallfacilitybyzid",
                data:{"zid":startchose,"zpowerstatus":"已开机","kind":"开启"},
                async: false,
                success: function (data) {
                    if(data>0){
                        layer.msg("已开启选中，等待电源开启", { icon: 1, offset: "auto", time:1000 });
                    }else{
                        alert("出错")
                    }
                }
            });

            //同时开启安全测试
          /*  var zpassingscore=$("#testcode").val()
            $.ajax({
                type: "post",
                url: "/updatetestbychose",
                data:{"zid":startchose,"zpassingscore":zpassingscore},
                async: false,
                success: function (data) {
                    if(data>0){
                        layer.msg("已开启安全测试", { icon: 1, offset: "auto", time:1000 });
                    }
                }
            });*/

        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);
    }


    //关闭选中
    function closechose() {

        var j=0;
        var closechose =[];
        $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            closechose[i] =$(this).val();
        });
               alert(closechose);
                $.ajax({
                    type: "post",
                    url: "/updateallfacilitybyzid",
                    data: {"zid": closechose, "zpowerstatus": "未开机","kind":"关闭"},
                    async: false,
                    success: function (data) {
                        if(data>0){
                            layer.msg("已关闭选中，等待电源关闭", { icon: 1, offset: "auto", time:1000 });
                        }else{
                            alert("出错")
                        }
                    }
                });
        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);
    }




    //退出
    function outpower(){
        $("#popup").show()
    }



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
        $("#parent").hide();
        $("#p_button4").text('退出系统');
        $("#p_button4").css('background-color','#4472c4');
        $("#p_button4").attr("onclick","outpower();");
    }

    function removemes() {
        $("#inputmes").val("");
    }

    var ztrainroomid="";

    var static_teststate=new Array();

    //查到被点击实训室的所有设备
    function findfacbyrid(id) {
        ztrainroomid =id;

        $("#p_left button").css("background-color","#70AD47");

        var str="";
        var p_center=$("#p_center");
        p_center.empty();
        $.ajax({
            type: "post",
            url: "/findfacilitybyrid",
            data:{"id":id},
            async: false,
            success: function (data) {

                $("#"+id+"").css("background-color","#FFC000")

                if(data.length <7){

                    str+="<table class='p_bbbox' id='p_bbox'>"
                    str+=" <tr>";
                    for(var i=0; i<data.length;i++){
                        str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_unpowerbox'><input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox' class='p_check'/></div></th>";
                        static_teststate[j]=data[i].zid
                    }
                    str+="</tr>";
                    str+="</table>";
                    str+="<div class='d_button1'><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"

                }else {
                    var j=0;
                    str+="<table class='p_bbbox' id='p_bbox'>"
                    for (var i=0;i<(data.length/6+1);i++){

                        str+=" <tr>";
                        for(;j<6*(i+1);j++){
                            if(j==data.length){break;}
                            else {
                                str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_unpowerbox'><input name='check' id='"+data[j].zid+"' value='"+data[i].zid+"' type='checkbox' onclick='addchoice(this)' class='p_check'/></div></th>";

                                static_teststate[j]=data[j].zid
                            }
                        }
                        str+="</tr>";
                        //  j+=6;
                    }

                    str+="</table>";
                    //str+="<button class='d_button1' onclick='allchose()'>全选</button>"
                    str+="<div class='d_button1' ><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"
                }

                p_center.html(str)

                for (var i=0;i<static_teststate.length;i++){
                    $.ajax({
                        type: "post",
                        url: "/findteststatebyfid",
                        data:{"id":static_teststate[i]},
                        async: false,
                        success: function (data) {
                            if(data=="0"){
                                $("#div"+static_teststate[i]+"").css('background-color','rgba(112,167,71)');
                            }
                        }
                    });


                }


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
                    str+=" <br><br><button onclick='findfacbyrid(\""+data[i].zid+"\")' class='p_button2'id='"+data[i].zid+"'>"+data[i].zname+"</button>"
                }
                p_left.html(str)

            }
        });

    }

    function  findHaveStudent(zid){
        $.ajax({
            type: 'post',
            url: '/findStudentName',
            data:  {"zid": zid} ,
            success: function (name){
                if(name!=""){
                    $("#div"+zid).css('background-color','rgba(112,167,71)');
                }else{
                    $("#div"+zid).css('background-color','rgba(128,128,128)');
                }

            }
        });
    }

    function allchose() {
        /*$("#p_center  input[type='checkbox']").attr("checked","true");*/

        if($("input[name='checkall']").is(':checked')){
            /*$("[name='checkall']:checkbox").attr('checked', true);*/
            $("[type='checkbox']:checkbox").prop("checked", true);
            //$("#p_center  input[type='checkbox']").attr("checked","")
        }
        else{
            /*$("[name='checkall']:checkbox").attr('checked', false);*/
            $("[type='checkbox']:checkbox").prop('checked', false);
            /*$("#p_center  input[type='checkbox']").attr("checked",false)*/
        }
    }


    /**
     * 下课
     */
   /* function overclass() {
        $.ajax({
            type: "post",
            url: "/overclass",
            data:{"ztrainroomid":ztrainroomid},
            success: function (data) {
                if(data!=0){
                    layer.msg("已下课，等待电源关闭", { icon: 1, offset: "auto", time:2000 });
                }else{
                    alert("出错")
                }
            }
        });
    }*/

</script>

</html>