<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 信息发送 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/layui.js"></script>
    <script src="./layui/js/common.js"></script>

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
    <div class="leftfont"><font size="5" >信息发送</font></div>
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
    <textarea  type="text" class="d_text"  onclick="removemes()" id="inputmes">点击输入滚动消息</textarea>
    <div class="d_font2"><button class="button7" onclick="insertcommand()">确认</button>
   <#-- <br><br><button class="button7" onclick="insertcommandbychose()">发送到勾选</button>-->
    </div>
    <div class="d_choose">
    <div class="d_font">
        <font >显示:</font> <input id="i_time" class="i_time" type="number" value="" size="4"><font>秒</font>
    </div>
</div>
</div>


<!--弹框-->
<div hidden class="d_popup" id="de_popup" align="center">
    <br>
    <button class="p_button2" onclick="fixed_task()">固定任务</button><br><br>
    <button class="p_button2" onclick="temporary_task()">临时任务</button><br><br>
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
            data:{"id":id},
            success: function (data) {


                $("#"+id+"").css("background-color","#FFC000")

                if(data.length <7){

                 str+="<table class='p_bbbox' id='p_bbox'>"
                  str+=" <tr>";
                  //var类型，不能写成int
                    for(var i=0; i<data.length;i++){

                        if (data[i].zpowerstatus=="已开机"){
                            str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_sbox'><input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox' class='p_check'/></div></th>";
                            findHaveStudent(data[i].zid)
                        }else if (data[i].zpowerstatus=="未开机"){
                            str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_unpowerbox'><input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox' class='p_check'/></div></th>";
                            findHaveStudent(data[i].zid)
                        }
                   }
                   str+="</tr>";
                   str+="</table>";
                   //str+="<button class='d_button1' onclick='allchose()'>全选</button>"
                   str+="<div class='d_button1'><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"
              }else {
                 var j=0;
                 str+="<table class='p_bbbox' id='p_bbox'>"
                 for (var i=0;i<(data.length/6+1);i++){

                     str+=" <tr>";
                     for(;j<6*(i+1);j++){
                        if(j==data.length){break;}
                         if (data[j].zpowerstatus=="已开机"){
                             str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_sbox'><input name='check' id='"+data[j].zid+"' value='"+data[i].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                             findHaveStudent(data[j].zid)
                         }else if (data[j].zpowerstatus=="未开机"){
                             str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_unpowerbox'><input name='check' id='"+data[j].zid+"' value='"+data[i].zid+"' type='checkbox' onclick='addchoice(this)' class='p_check'/></div></th>";
                             findHaveStudent(data[j].zid)
                         }

                     }
                     str+="</tr>";
                   //  j+=6;
                 }

                 str+="</table>";
                 //str+="<button  id='quanxuan' class='d_button1' onclick='allchose()'>全选</button>"
                 str+="<div class='d_button1' ><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"

             }

                p_center.html(str)

            }
        });
    }

    window.onload =function () {
        onloadallroom();
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
function addchoice(checkbox) {
        if(checkbox.checked==true){
            $(checkbox).prop("checked", true)
        }else{
            $(checkbox).prop("checked", false)
        }

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

    function allchose() {
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

        /*$("#p_center  input[type='checkbox']").attr("checked","true");*/
    }



    /*function insertcommandbychose() {

        var startchose =[];
        $("input[name='zid']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            startchose[i] =$(this).val();
        });

        var i_time=$("#i_time").val()

        var inputmes=$("#inputmes").val()
        //alert(inputmes)
        if(inputmes && inputmes !="点击输入滚动消息"){
            $.ajax({
                type: "post",
                url: "/insertcommandbychose",
                data:{"zcontent":inputmes,"zid":startchose},
                success: function (data) {
                    if(data>0){
                        layer.msg("成功发布滚屏信息", { icon: 1, offset: "auto", time:2000 });
                    }
                }
            });
        }

        setTimeout(function (){
            $.ajax({
                type: "post",
                url: "/upadtestates",
                success: function (data) {
                    if(data>0){
                        layer.msg("滚屏消息已失效", { icon: 1, offset: "auto", time:2000 });
                    }
                }
            });
        }, 1000*i_time+5000);
    }
*/





    function insertcommand() {

        var i_time=$("#i_time").val()

        var inputmes=$("#inputmes").val()
        //alert(inputmes)
        if(inputmes && inputmes !="点击输入滚动消息"){
            $.ajax({
                type: "post",
                url: "/insertcommand",
                data:{"zcontent":inputmes},
                success: function (data) {
                    if(data>0){
                        layer.msg("成功发布滚屏信息", { icon: 1, offset: "auto", time:2000 });
                    }
                }
            });
        }

        setTimeout(function (){
            $.ajax({
                type: "post",
                url: "/upadtestates",
                success: function (data) {
                    if(data>0){
                        layer.msg("滚屏消息已失效", { icon: 1, offset: "auto", time:2000 });
                    }
                }
            });


        }, 1000*i_time+5000);


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

    function temporary_task() {

        location.href = "/temporary_task";
    }

    function fixed_task() {

       location.href = "/fixed_task";
    }


</script>

</html>