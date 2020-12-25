<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link href="./layui/css/time_status.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">
<div class="layui-layout layui-layout-admin" >
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #C6C6C6">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">登录界面</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div id='welcomeField' class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
        </div>
        <div id='teach' class="layui-col-xs9" align="center" style="width: 74%">
            <div style="margin: 0,auto;margin-top:40px;height:80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
            </div>
            <#--<div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='${path}'style='width: 15rem;height: 16rem;'></div>
            <div style="margin: 0,auto;margin-top:220px;height: 80px;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C"> 进入安浩智能学习工厂</div>-->
        </div>
        <#--二级菜单-->
        <div id='twoMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 88%">
            <div>
                <ul style="margin-top: 10%;width: 80%;left: 40%;margin: 37px auto">
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="faceShow()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">人脸识别</button></li>
                    <li style=" margin: 0 auto;margin-right: 10%"><button onclick="powerController()" style="float:right;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">设备电源</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 30% auto;width: 80% ">
                    <li style="margin-left: 10%"><button id="checkPointColor" onclick="checkPoint()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">查岗</button></li>
                    <li style="margin-right: 10%"><button  id="courseRecordColor" onclick="courseRecord()" style="float:right;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">课堂记录</button></li>
                </ul>
            </div>
        </div>
        <div id="courseRecordContent"style="display: none;width: 50%;height: 300px;position: fixed;background-color: #CDCDCD;border: 1px solid;top:50%;left: 7%;z-index: 3">
            <div style="margin: 5px auto;width: 29%">
                <div style="float: left">课堂记录</div>
                <select id="valueSelect" style="float: right">
                    <option value="正常">正常</option>
                    <option value="事故">事故</option>
                    <option value="冲突">冲突</option>
                    <option value="其他">其他</option>
                </select>
            </div>
            <div style="margin: 29px auto;background-color: #ffffff;width: 90%;height: 200px"><textarea id="textContent" style="width: 100%;height: 100%"></textarea></div>
            <div style="position: absolute;left: 15%"><button onclick="subContent()" style="background-color: #0000FF;height: 30px;width: 81px;border-radius: 10px;color: #ffffff">确定</button></div>
            <div style="position: absolute;left: 60%"><button onclick="cancel()" style="background-color: #0000FF;height: 30px;width: 81px;border-radius: 10px;color: #ffffff">取消</button></div>
        </div>
        <div id="hiddenArea"style="position: absolute;height: 100%;width: 100%;filter: alpha(opacity=60);opacity: 0.6;display: none;z-index: 2">
        </div>
        <#--三级菜单-->
        <div id='threeMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 88%">
            <#--<div>
                <ul style="margin-top: 10%;width: 80%;left: 40%;margin: 37px auto">
                    <li style="margin-left: 10% "><button onclick="studentShow(this.value)" value="1" style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控车讨论区</button></li>
                    <li style="margin-right: 10%"><button onclick="studentShow1(this.value)" value="1" style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控铣讨论区</button></li>
                </ul>
            </div>-->
            <#--<div>
                <ul style="margin: 15% auto;width: 80% ">
                    <li style="margin-left: 10%"><button  style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">东大门入口</button></li>
                    <li style="margin-right: 10%"><button style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">东大门出口</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 25% auto;width: 80% ">
                    <li style="margin-left: 10%"><button style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">西大门入口</button></li>
                    <li style="margin-right: 10%"><button style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">西大门出口</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 35% auto;width: 80% ">
                    <li style="margin-left: 10%"><button style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">北大门入口</button></li>
                    <li style="margin-right: 10%"><button style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">北大门出口</button></li>
                </ul>
            </div>-->
        </div>
        <#--查岗的下级功能表-->
        <div id='checkPointMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 88%">
            <#--<div>
                <ul style="margin-top: 10%;width: 80%;left: 40%;margin: 37px auto">
                    <li style="margin: 0 auto;margin-left: 46% "><button onclick="studentShow(this.value)" value="2" style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控区实训区</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 30% auto;width: 80% ">
                    <li style="margin-left: 46%"><button  onclick="studentShow1(this.value)" value="2" style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控铣实训区</button></li>
                </ul>
            </div>-->
        </div>
        <#--四级菜单-->
        <div id='fourMenu' class="layui-col-xs1" align="center" style="display:none;width: 26%;font-size: 70px">
            <div id="mainDiv"></div>
            <#--这个地方到时候要循环遍历出来拼接字符串-->
            <div id="identifyAreas"style="width: 80%;height:200px;background-color: #ffff;border: 1px solid red;overflow: auto">
                <#--<div style="font-size: 20px;width: 80%;margin-top: 10px">张三  机电19班</div>
                <div style="font-size: 20px;width: 80%;margin-top: 10px">李四  机电19班</div>
                <div style="font-size: 20px;width: 80%;margin-top: 10px">王二  机电19班</div>
                <div style="font-size: 20px;width: 80%;margin-top: 10px">麻子  机电19班</div>
                <div style="font-size: 20px;width: 80%;margin-top: 10px">隔壁老王  机电19班</div>-->
            </div>
        </div>
        <div id='fourMenu1' class="layui-col-xs8" align="center" style="display:none;width: 64%">
            <div style="height: 500px;text-align:center;line-height:40px;font-size: 40px;background-color: #ffff;overflow:scroll;border: 1px solid red">
                <#--这个也要用遍历写出来的，显示最先出现的三个人-->
                <div style="width: 100%;margin-top: 2px;">
                    <div id="left" style="width: 33%;position: absolute;left:1%;background-color: #BDD7EE;color: red;border: 1px solid"></div>
                    <div id="middle" style="width: 27%;position: absolute;left:35%;background-color: #BDD7EE;color: red;border: 1px solid"></div>
                    <div id="right" style="width: 33%;position: absolute;left:65%;background-color: #BDD7EE;color: red;border: 1px solid"></div>
                </div>
                <#--按照顺序排出识别的人脸，顺序是最早的在最下最右边-->
                <div id="mainBody"style="width: 100%;margin: 53px auto">
                    <#--<table>
                        <tr>
                            <th>
                                <div style="background-color: #BDD7EE;width: 20%;border: 1px solid red;position: absolute;left: 1%">乔峰</div>
                            </th>
                            <th>
                                <div style="background-color: #BDD7EE;width: 20%;border: 1px solid red;position: absolute;left: 26%">阿朱</div>
                            </th>
                            <th>
                                <div style="background-color: #BDD7EE;width: 20%;border: 1px solid red;position: absolute;left: 51%">段玉</div>
                            </th>
                            <th>
                                <div style="background-color: #BDD7EE;width: 20%;border: 1px solid red;position: absolute;left: 75%">虚竹</div>
                            </th>
                        </tr>
                    </table>-->
                </div>
            </div>
            <#--人脸识别时显示的开始和结束按钮-->
            <div id="openAndstart"style="display: none">
                <div style="float:left;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 50%">
                    <button id="startID" onclick="OpenOTimer(this.value)" value="1" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">开始</button>
                </div>
                <div style="text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:right;width: 50%">
                    <button id="endID"onclick="CloseTimer(this.value)" value="1" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">结束</button>
                </div>
            </div>
            <#--查岗时候显示的开始和结束按钮-->
            <div id="openAndstart2"style="display: none">
                <div style="float:left;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 50%">
                    <button id="startID2" onclick="OpenOTimer(this.value)" value="2" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">开始</button>
                </div>
                <div style="text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:right;width: 50%">
                    <button id="endID2"onclick="CloseTimer(this.value)" value="2" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">结束</button>
                </div>
            </div>

        </div>
        <#--右边功能按钮-->
        <div class="layui-col-xs1" align="right" style="height:100%;width: 9%;border-left: 1px solid #c2c2c2;">
            <div>
                <button id='colorType'  onclick="fieldManagement()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    现场管理
                </button>
            </div>
            <div>
                <button onclick="informationService()"  style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    信息查询
                </button>
            </div>
            <div>
                <button onclick="timeStatus()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    实时状态
                </button>
            </div>
            <div>
                <button onclick="informationDelivery()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    信息发布
                </button>
            </div>
            <div>
                <button id="exit" onclick="outpower()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:70px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 27px;font-size: 27px;left:20%;position:absolute;z-index:10;">
                    退出系统
                </button>
            </div>
        </div>
    </div>
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


<script>

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
        $("#exit").css('background-color','#FFC000');
        $("#exit").text('解锁');
        $("#exit").attr("onclick","removescreer();");

    }

    function removescreer(){
        $("#parent").hide();
        $("#exit").text('退出系统');
        $("#exit").css('background-color','#4472c4');
        $("#exit").attr("onclick","outpower();");
    }

    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
   window.onload=show();
    //展示现场管理的二级菜单
    function show() {
        document.getElementById("colorType").style.backgroundColor="#ED7D31";
        document.getElementById("welcomeField").style.display="none";
        document.getElementById("teach").style.display="none";
        document.getElementById("twoMenu").style.display="block";
    }
    //展示
    function faceShow() {
        document.getElementById("twoMenu").style.display="none";
        //遍历所有的实训室的摄像装备

        findAllCameras();


        document.getElementById("threeMenu").style.display="block";
    }
    //数控车讨论区显示每台机的人脸识别情况
    function studentShow(e){

       /* var b =$("#zcameraIP"+e).val();
        alert(b)*/

        document.getElementById("threeMenu").style.display="none";
        //摄像头下面的显示的五个人
        document.getElementById("fourMenu").style.display="block";
        //显示开始和结束按钮
        document.getElementById("fourMenu1").style.display="block";
        document.getElementById("openAndstart").style.display="block";
        getMedia();

        $("#left").hide();
        $("#middle").hide();
        $("#right").hide();
    }
    //数控铣讨论区显示每台机的人脸识别情况
    function studentShow1(e){
        document.getElementById("checkPointMenu").style.display="none";
        //摄像头下面显示的五个人
        document.getElementById("fourMenu").style.display="block";
        //显示开始结束按钮
        document.getElementById("fourMenu1").style.display="block";
        document.getElementById("openAndstart2").style.display="block";

        getMedia();
        $("#left").hide();
        $("#middle").hide();
        $("#right").hide();
    }
    //获取摄像头
    /*function getMedia(){

    }*/

   function getMedia() {
        $("#mainDiv").empty();
        let videoComp = " <video id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
        $("#mainDiv").append(videoComp);

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
        promise.then(function (MediaStream) {
            video.srcObject = MediaStream;
            video.play();
        });

        // var t1 = window.setTimeout(function() {
        //     takePhoto();
        // },2000)
    }
    //查岗功能
    function checkPoint() {
        document.getElementById("checkPointColor").style.backgroundColor="#ED7D31";
        document.getElementById("twoMenu").style.display="none";
        //找出所有实训室所有的种类的摄像头
        CheckPointFindAllCameras();
        document.getElementById("checkPointMenu").style.display="block";

    }

    //课堂记录
    function courseRecord() {
        document.getElementById("courseRecordColor").style.backgroundColor="#ED7D31";
        document.getElementById("hiddenArea").style.display="block";
        document.getElementById("courseRecordContent").style.display="block";


    }
    //课程记录中的取消按钮
    function cancel() {
        document.getElementById("hiddenArea").style.display="none";
        document.getElementById("courseRecordContent").style.display="none";
        document.getElementById("courseRecordColor").style.backgroundColor="#71B863";
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
        location.href="/field_management";
    }


</script>
</body>
</html>