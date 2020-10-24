<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">

    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin"  style="background-color: #CDCDCD">
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #CDCDCD">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">登录界面</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div class="layui-col-xs10" align="center" style="left: 150px">
            <div style="margin: 0,auto;margin-top:40px;height: 80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
                ${name}${faceId}
            </div>
            <div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='images/shibie.jpg'style='width: 15rem;height: 16rem;'></div>
            <div style="margin: 0,auto;margin-top:220px;height: 80px;text-align:center;line-height:80px;font-size:20px;color:#0C0C0C"> 进入安浩智能学习工厂</div>
            <div>
               <button style="color:#FFFFFF;height: 75px;display:block;margin:0 auto;margin-top:0px;width:211px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 32px" onclick="test()">
                开始测试
               </button>
            </div>
       </div>
       <div class="layui-col-xs1" align="right" style="height:700px;left: 124px;border-left: 1px solid #c2c2c2;">
          <div>
            <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:10px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 20px">
                举手
            </button>
          </div>
          <div>
            <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:15px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 20px">
                请假
            </button>
          </div>
          <div>
            <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:350px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 27px;font-size: 26px">
                退出系统
            </button>
          </div>
       </div>
</div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    function test() {
        /*ajax不能实现只发送不要数据所以不能用ajax*/
        location.href = "/test";
    }
</script>
</body>
</html>