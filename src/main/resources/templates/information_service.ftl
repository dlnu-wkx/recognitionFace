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


<!--右侧按键-->
<div class="i_right" align="center">
    <button onclick="fieldManagement()" class="i_field_management">现场管理</button>
    <br><br>
    <button class="i_information_service">信息查询</button>
    <br><br>
    <button onclick="timeStatus()" class="i_time_status">实时状态</button>
    <br><br>
    <button onclick="informationDelivery()"class="i_information_delivery">信息发布</button>
    <br><br>
    <button class="i_exit" id="exit" onclick="outpower()">退出</button>

</div>

<!--中间信息查询-->
<div class="i_center" >
   <div class="i_service">
        <font size="5">人员：</font> &emsp;&emsp;&emsp;&emsp;&emsp; <input type="text"   id="i_name" class="i_text"><br><br><br><br>
        <font size="5">事件：</font> &emsp;&emsp;&emsp;&emsp;&emsp;
        <select class="i_text3" id="selecttype">
           <option value="人脸识别">人脸识别</option>
           <option value ="查岗">查岗</option>
           <option value ="登陆">登陆</option>
           <option value="举手">举手</option>
           <option value="请假/销假">请假/销假</option>
            <option value="测试成绩">测试成绩</option>
        </select><br><br><br><br>
   </div>
    <div class="i_timechose">
       <font size="5" class="i_font">时间：</font> <input class="i_text4" id="startday" type="date" /><input class="i_text5" id="starttime" type="time" />
       <input class="i_text6" id="endday" type="date" /><input class="i_text7" id="endtime" type="time" />
       <br><br><br><br>
       <button class="i_button" onclick="selectmes()">查询</button>
    </div>
</div>

<!--模糊查询框-->
<div id="i_namelike" class="i_namelike" hidden>

</div>

<!--中间表格-->
<div class="i_excel" id="i_excel" hidden>

</div>


<!--弹框-->
<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>

    <button class="p_button2">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="parent" hidden></div>
<div id="showVdieo" style="position: absolute;z-index:10;top: 24%;left: 41%"></div>

</body>

<script>
    function selectmes(){
        //alert(0)
        var i_name=$("#i_name").val()
        var selecttype=$("#selecttype").val()
        var startday=$("#startday").val()
        var endday=$("#endday").val()
        var starttime=$("#starttime").val()
        var endtime=$("#endtime").val()
        var i_excel=$("#i_excel")
        var str=""

        starttime=startday+" "+starttime+":00";
        endtime=endday+" "+endtime+":59";

        //初始值学生数为0
        var studntcount=0;
        $.ajax({
            type: "post",
            url: "/findcountbystudentname",
            data:{"zname":i_name},
            async: false,
            success: function (data){
                studntcount=data
            }
        });

        if(!i_name){
            alert(1)

        }

      else if (selecttype=="人脸识别"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"人脸识别查询.xls\")'>下载</button>&emsp; &emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='4'><font size='5'>"+i_name+"签到信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>签到时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectattandancemes",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime,"zcheck":"人脸识别"},
                async: false,
                success: function (data){
                   // alert(createTime(data[0].mestime))
                    for (var i=0;i<data.length;i++){

                         //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else if(selecttype=="查岗"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"查岗查询.xls\")'>下载</button>&emsp; &emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='4'><font size='5'>"+i_name+"查岗信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>签到时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectattandancemes",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime,"zcheck":"查岗"},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else if(selecttype=="登陆"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"登陆查询.xls\")'>下载</button>&emsp; &emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='6'><font size='5'>"+i_name+"登陆信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>实训设备</th><th class='i_tableth2'>登陆/退出</th><th class='i_tableth2'>签到时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectinandout",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].facilityname+"</th><th class='i_tableth2'>"+data[i].isintype+"</th><th class='i_tableth2'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else if (selecttype=="举手"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"举手查询.xls\")'>下载</button>&emsp; &emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='6'><font size='5'>"+i_name+"举手信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>实训设备</th><th class='i_tableth2'>实时状态</th><th class='i_tableth2'>签到时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectheadsup",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime,"ztype":"举手"},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].facilityname+"</th><th class='i_tableth2'>"+data[i].isintype+"</th><th class='i_tableth2'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else if(selecttype=="请假/销假"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"请假（销假）查询.xls\")'>下载</button>&emsp; &emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='7'><font size='5'>"+i_name+"请假信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>请假原因</th><th class='i_tableth2'>请假状态</th><th class='i_tableth2'>时间</th><th class='i_tableth2'>审批人</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectleave",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].leavereason+"</th><th class='i_tableth2'>"+data[i].isintype+"</th><th class='i_tableth2'>"+createTime(data[i].mestime)+"</th>"

                        $.ajax({
                            type: "post",
                            url: "/selectteachernamebyid",
                            data:{"zid":data[i].approver},
                            async: false,
                            success: function (data2){
                                if (data2)
                                    str+="<th class='i_tableth1'>"+data2+"</th>"
                                else
                                    str+="<th class='i_tableth1'></th>"
                            }
                        })

                        str+="</tr>"
                    }
                }
            });
        }else  if(selecttype=="测试成绩"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"测试成绩查询.xls\")'>下载</button>&emsp; &emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='7'><font size='5'>"+i_name+"测试成绩查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>实训设备</th><th class='i_tableth2'>测试类型</th><th class='i_tableth2'>成绩</th><th class='i_tableth2'>测试时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectscore",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].facilityname+"</th><th class='i_tableth2'>"+data[i].testtype+"</th><th class='i_tableth2'>"+data[i].score+"</th><th class='i_tableth2'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }
        str+="</table>"
        i_excel.html(str)
        i_excel.show()
    }

    function loadexcel(name) {

            //alert(name)
            var exportFileContent = document.getElementById("i_table").outerHTML;

            //使用Blob
            var blob = new Blob([exportFileContent], {type: "text/plain;charset=utf-8"});         //解决中文乱码问题
            blob =  new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
            //设置链接
            var link = window.URL.createObjectURL(blob);
            var a = document.createElement("a");    //创建a标签
            //a.download = "信息查询表格.xls";
             a.download = name;
            // 设置被下载的超链接目标（文件名）
            a.href = link;                            //设置a标签的链接
            document.body.appendChild(a);            //a标签添加到页面
            a.click();                                //设置a标签触发单击事件
            document.body.removeChild(a);            //移除a标签
    }
    
    function hideexcel(){
        $("#i_excel").hide()
    }

//Timestamp转字符串
    function createTime(v){
        var now = new Date(v);
        var yy = now.getFullYear();      //年
        var mm = now.getMonth() + 1;     //月
        var dd = now.getDate();          //日
        var hh = now.getHours();         //时
        var ii = now.getMinutes();       //分
        var ss = now.getSeconds();       //秒
        var clock = yy + "-";
        if(mm < 10) clock += "0";
        clock += mm + "-";
        if(dd < 10) clock += "0";
        clock += dd + " ";
        if(hh < 10) clock += "0";
        clock += hh + ":";
        if (ii < 10) clock += '0';
        clock += ii + ":";
        if (ss < 10) clock += '0';
        clock += ss;
        return clock;
    }



    //输入姓名发生改变
    $('#i_name').bind('input propertychange', function () {
        var i_namelike=$("#i_namelike")
        var str="";
        var i_name=$("#i_name").val();
        str+="<ul align='center'>"
        $.ajax({
            type: "post",
            url: "/findgradeandnamelike",
            data:{"zname":i_name},
            async: false,
            success: function (data){
                for (var i=0;i<data.length;i++){
                    str+="<li onclick='i_inputname(\""+data[i]+"\")'>"+data[i]+"</li>"
                }
            }
        });
        str+="</ul>"
        i_namelike.html(str)
        i_namelike.show();
    });
    //点击补全姓名
    function i_inputname(name) {
        $("#i_name").val(name);
        $("#i_namelike").hide();
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
        /*$("#parent").hide();
        $("#exit").text('退出系统');
        $("#exit").css('background-color','#4472c4');
        $("#exit").attr("onclick","outpower();");*/
        getMedia1();
    }

    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //实时状态
    function timeStatus() {
        location.href="/time_status";
    }
    //现场管理
    function fieldManagement() {
        location.href="/field_management";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }


    var mediaStreamTrack;
    var time=null;
    function getMedia1() {
        $("#showVdieo").empty();
        let videoComp = "<video id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
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
            chooseFileChangeCompI_S()
        }, 5000);

    }

    function chooseFileChangeCompI_S() {

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