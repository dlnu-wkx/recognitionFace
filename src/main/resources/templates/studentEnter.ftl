<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">


    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">
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



<!--请假弹框-->
<div class="co_leavemes" hidden id="co_leavemes">
    <font>请假原因</font>
    <input type="text" class="co_mes" id="co_mes">
    <button class="co_button" onclick="common_leave()">确认</button>
</div>



<div class="layui-layout layui-layout-admin" >
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #C6C6C6">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">登录界面</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
            欢迎
        </div>
        <div class="layui-col-xs9" align="center" style="width: 69%">
            <div style="margin: 0,auto;margin-top:40px;height: 80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
                ${name}
            </div>
            <div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='${path}'style='width: 15rem;height: 16rem;'></div>
            <div style="margin: 0,auto;margin-top:220px;height: 80px;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C"> 进入安浩智能学习工厂</div>
            <div>

                    <button style="color:#FFFFFF;height: 75px;display:block;margin:0 auto;margin-top:0px;width:211px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 32px" onclick="test()" id="e_test">
                        开始测试    
                    </button>


            </div>
        </div>
        <div class="layui-col-xs1" align="right" style="right:-3%;left:80px;height:100%;width: 10%;border-left: 1px solid #c2c2c2;">
            <div>
                <button onclick="upheads()"  id="upheads" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    举手
                </button>
            </div>
            <div>
                <button onclick="showleave()" id="leave" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    请假
                </button>
            </div>

            <div>
                <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:300px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 27px;font-size: 27px">
                    退出系统
                </button>
            </div>
        </div>
    </div>
</div>
<script>


    window.onload = function(){

    var zselecttest=$.cookie('zselecttest');

    //如果cookie中不需要安全测试，更改按键的值以及方法
    if(zselecttest!="是"){
        $("#e_test").attr('onclick', 'simulationtasks()')

        $("#e_test").text("实训任务");
    }

    }


    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    
    //跳安全测试的方法
    function test() {
        /*ajax不能实现只发送不要数据所以不能用ajax*/
       // location.href = "/student_test";
     //跳转开始测试的controller
      $.ajax({
            type: "post",
            url: "/starttest",
            contentType: false,
            processData: false,
            async: false,
            success: function (){
                location.href = "/student_test";
            }
        });

        }
    //跳实训任务的方法  
    function simulationtasks() {
        location.href = "/class_ppt";
    }    
    
        
    function show() {
        alert("进入到show中")
    }
</script>
</body>
</html>