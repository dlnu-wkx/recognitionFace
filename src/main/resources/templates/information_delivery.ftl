<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 信息发送 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/layui.js"></script>
    <script src="./layui/js/common.js"></script>
</head>
<body  class="body_delivery" >

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
    <div class="leftfont">信息发送</div>
    <div class="rightfont">安浩智能学习工厂</div>
</div>


<!--中部电源显示-->
<div class="p_center3" id="p_center">


</div>


<!--左侧按键-->
<div class="p_left" align="center" id="p_left">

</div>
<!--右侧按键-->
<div class="d_right" align="center">
    <button  onclick="fieldManagement()" class="f_field_management">现场管理</button>
    <button onclick="informationService()" class="f_field_service">信息查询</button>
    <button onclick="timeStatus()"class="f_field_status">实时状态</button>
    <button class="f_field_delivery" id="deliveryid" onclick="informationDelivery()">信息发布</button>
    <button onclick="outpower()" id="exit" class="p_exit">退出系统</button>
</div>

<!--下方按键及内容-->
<div class="p_text2" align="center">
    <textarea  type="text" class="d_text"  onclick="removemes()" id="inputmes">点击输入滚动消息</textarea>
    <div class="d_font2"><button class="button7" onclick="insertcommand()">确认</button>
        <#-- <br><br><button class="button7" onclick="insertcommandbychose()">发送到勾选</button>-->
    </div>
    <div class="d_choose">
        <div class="d_font">
            <font >显示:</font>&emsp;&emsp;&emsp;<input id="i_time" class="i_time" type="number" value="" size="4">&emsp;&emsp;&emsp;<font>秒</font>
        </div>
    </div>
</div>


<!--弹框-->
<div  class="d_popup" id="de_popup" align="center">
    <br>
    <button class="p_button2" onclick="fixed_task()">固定任务</button><br><br>
    <button class="p_button2" onclick="temporary_task()">临时任务</button><br><br>
    <button class="p_button2" onclick="">信息发送</button>
</div>

<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>

    <button class="p_button2" onclick="overclass()" >下课</button>
</div>
<!--蒙版-->
<div id="parent" class="parent" hidden></div>
<div id="showVdieo" style="position: absolute;z-index:10;top: 24%;left: 41%"></div>

</body>





</body>

<script>
    window.onLoad=aaa();
    function aaa(){
        var servicebutton = document.getElementById("deliveryid");
        servicebutton.style.backgroundColor="#ED7D31"
    }
    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
        $("#popup").hide();
        $("#exit").css('background-color','#FFC000');
        $("#exit").text('解锁');
        $("#exit").attr("onclick","removescreer();");
    }
    function removescreer(){
        /* $("#parent").hide();
         $("#exit").text('退出系统');
         $("#exit").css('background-color','#4472c4');
         $("#exit").attr("onclick","outpower();");*/
        getMedia1()
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
            data:{"id":id},
            async: false,
            success: function (data) {


                $("#"+id+"").css("background-color","#FFC000")

                if(data.length <7){
                    str+="<table class='p_bbbox2' id='p_bbox'>"
                    str+="<tr style='height:0%;'><th style='width:50px '></th><th style='width:50px '></th><th style='width:50px '></th><th style='width:50px '></th><th style='width:50px '></th><th style='width:50px '></th></tr>";
                    str+=" <tr style='height: 40%'>";

                    //var类型，不能写成int
                    for(var i=0; i<data.length;i++){

                        if (data[i].zpowerstatus=="已开机"){
                            $.ajax({
                                type: "post",
                                url: "/findstunamebyfacid",
                                data:{"zid":data[i].zid},
                                async: false,
                                success: function (data2) {
                                    if (data2){
                                        str+="<th ><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_sbox'>"+data2+"<input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                    }else{
                                        str+="<th ><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_sbox'><input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                    }
                                }
                            })
                            //   findHaveStudent(data[i].zid)
                        }else if (data[i].zpowerstatus=="未开机"){
                            str+="<th ><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_unpowerbox'><input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox' class='p_check'/></div></th>";
                            //  findHaveStudent(data[i].zid)
                        }
                    }
                    str+="</tr>";
                    str+="<tr></tr>";
                    str+="</table>";
                    //str+="<button class='d_button1' onclick='allchose()'>全选</button>"
                    str+="<div class='d_button1'><font size='5'>全选：</font><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"
                }else {
                    var j=0;
                    str+="<table class='p_bbbox2' id='p_bbox' >"
                    for (var i=0;i<(data.length/6+1);i++){
                        str+=" <tr>";
                        for(;j<6*(i+1);j++){
                            if(j==data.length){break;}
                            if (data[j].zpowerstatus=="已开机"){
                                $.ajax({
                                    type: "post",
                                    url: "/findstunamebyfacid",
                                    data:{"zid":data[j].zid},
                                    async: false,
                                    success: function (data2) {
                                        if (data2){
                                            str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_sbox'>"+data2+"<input name='check' id='"+data[j].zid+"' value='"+data[j].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                        }else{
                                            str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_sbox'><input name='check' id='"+data[j].zid+"' value='"+data[j].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                        }
                                    }
                                })
                                // str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_sbox'><input name='check' id='"+data[j].zid+"' value='"+data[i].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                //  findHaveStudent(data[j].zid)
                            }else if (data[j].zpowerstatus=="未开机"){
                                str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_unpowerbox'><input name='check' id='"+data[j].zid+"' value='"+data[i].zid+"' type='checkbox' onclick='addchoice(this)' class='p_check'/></div></th>";
                                // findHaveStudent(data[j].zid)
                            }
                        }
                        str+="</tr>";
                        //  j+=6;
                    }
                    str+="</table>";
                    //str+="<button  id='quanxuan' class='d_button1' onclick='allchose()'>全选</button>"
                    str+="<div class='d_button1' ><font size='5'>全选</font><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"
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

        //alert(11)
        var i_time=$("#i_time").val()

        var inputmes=$("#inputmes").val()
        //alert(inputmes)
        if(inputmes && inputmes !="点击输入滚动消息"){
            $.ajax({
                type: "post",
                url: "/insertcommand",
                data:{"zcontent":inputmes,"time":i_time},
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






    
    function outmessage() {
        //$("#de_popup").show()
    }

    function temporary_task() {

        location.href = "/temporary_task";
    }

    function fixed_task() {
        location.href = "/fixed_task";
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
        location.href="/student_status";
    }
    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }


    var mediaStreamTrack;
    var time=null;
    function getMedia1() {
        $("#showVdieo").empty();
        let videoComp = "<video  muted id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
        $("#showVdieo").append(videoComp);

        let constraints = {
            video: {width: 500, height: 500},
            audio: true
        };
        //获得video摄像头区域
        let video = document.getElementById("video");
        //这里介绍新的方法，返回一个 Promise对象
        // 这个Promise对象返回成功后的回调函数带一个 MediaStream 对象作为其参数
        // then()是Promise对象里的方法
        // then()方法是异步执行，当then()前的方法执行完后再执行then()内部的程序
        // 避免数据没有获取到
        let promise = navigator.mediaDevices.getUserMedia(constraints);
        promise.then(function (stream) {
            mediaStreamTrack = typeof stream.stop === 'function' ? stream : stream.getTracks()[1];
            video.srcObject = stream;
            video.play();
        });

        // var t1 = window.setTimeout(function() {
        //     takePhoto();
        // },2000)
        time= window.setInterval(function () {//每隔几秒查询对比一次结果，循环对比
            chooseFileChangeCompI_D()
        }, 5000);

    }

    function chooseFileChangeCompI_D() {

        /* var ip=returnCitySN["cip"];*/


        let showVdieo = $("#showVdieo");
        if (showVdieo.has('video').length) {
            let video = document.getElementById("video");
            let canvas = document.getElementById("canvas");
            let ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0, 500, 500);
            var base64File = canvas.toDataURL();
            var formData = new FormData();
            formData.append("groupId", "101")
            formData.append("file", base64File);

            //var data=getOsInfo();

            //操作系统

            //ip地址
            formData.append("ip",1);

            $.ajax({
                type: "post",
                url: "/faceTeacherSearch",
                data: formData,
                contentType: false,
                processData: false,
                async: false,
                success: function (text) {
                    var res = JSON.stringify(text)
                    if (text.code == 0) {
                        var name = text.data.name;
                        $("#nameDiv").html("姓名：" + name);
                        var similar = text.data.similarValue;
                        $("#similarDiv").html("相似度：" + similar + "%");
                        var age = text.data.age;
                        $("#ageDiv").html("年龄：" + age);
                        var gender = text.data.gender;
                        $("#genderDiv").html("性别：" + gender);
                        mediaStreamTrack.stop();
                        $("#showVdieo").hide();
                        $("#parent").hide();
                        $("#exit").text('退出系统');
                        $("#exit").css('background-color','#4472c4');
                        $("#exit").attr("onclick","outpower();");
                        clearInterval(time);
                    } else {
                        $("#nameDiv").html("");
                        $("#similarDiv").html("");
                        $("#ageDiv").html("");
                        $("#genderDiv").html("");

                        showTips(text.message);
                    }

                },
                error: function (error) {
                    $("#nameDiv").html("");
                    $("#similarDiv").html("");
                    $("#ageDiv").html("");
                    $("#genderDiv").html("");
                    alert(JSON.stringify(error))
                }
            });
        }
    }



</script>

</html>