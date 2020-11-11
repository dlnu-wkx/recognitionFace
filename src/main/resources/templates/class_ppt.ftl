<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 实训任务 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/class_ppt.css" rel="stylesheet" type="text/css">


    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >实训任务</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>

<!--左侧灰色按键-->
<div class="left" id="leftbutton">

</div>


<!--请假弹框-->
<div class="co_leavemes" hidden id="co_leavemes">
    <font>请假原因</font>
    <input type="text" class="co_mes" id="co_mes">
    <button class="co_button" onclick="common_leave()">确认</button>
</div>



<div class="right">
    <!--右侧按键-->
    <div class="right1">
        <button class="button5" onclick="upheads()" id="upheads">举手</button>
    </div>

    <div class="right2">
        <button class="button5" onclick="showleave()">请假</button>
    </div>

    <div class="right3">
        <button class="button5">退出系统</button>
    </div>
</div>


<!--中间题目主体部分-->
<div class="center" id="cp_content" style="background-color: white">



</div>



<!--翻页-->
<div class="pages" hidden id="pages">
    <button class="button3" id="lastpage" onclick="lastpage()">上一页</button>
    <div class="cp_number" id="cp_number"></div>
    <button class="button4" id="nextpage" onclick="nextpage()">下一页</button>
</div>


<!--滚动弹幕-->
<div  class="rolling_barrage" id="rolling_barrage">

</div>


<br><br>





</body>

<script>

    //默认页面数
    var pages=1;
    var static_taskid=0;

    var last_page=3;


    function loadcontentbypages2(taskid) {

        $("#pages").show()

        pages=1;
        loadcontentbypages(taskid);

        //上一页按键变灰
        $("#lastpage").css("background-color","#A5A5A5");
        //移除上一页方法
        $("#lastpage").removeAttr("onclick");

        //如果这个任务只有一页
        if(last_page==1){
            //下一页按键变灰
            $("#nextpage").css("background-color","#A5A5A5");
            //移除下一页方法
            $("#nextpage").removeAttr("onclick");

        }else {
            $("#nextpage").css("background-color","#4472C4");
            $("#nextpage").attr("onclick","nextpage()");
        }

    }



    //根据页面与任务id加载任务主体内容
    function  loadcontentbypages(taskid){

        static_taskid=taskid;

        var formData = new FormData();
        formData.append("page",pages);
        formData.append("taskid",taskid);

        var cp_content=$("#cp_content");
        var str="";

        var cp_number=$("#cp_number");
        var str2="";

        //主体任务方法
        $.ajax({
            type: "post",
            url: "/selectcontentbypage",
            contentType: false,
            processData: false,
            data:formData,
            async: false,
            success: function (data){

               if(data.ztype=="图片"){
                    str+="<img src='"+data.zcontent+"'  alt='测试用' class='cp_message' />";
                }else if(data.ztype=="视频"){
                    str+="<video src='"+data.zcontent+"' controls='controls' class='cp_message'>您的浏览器不支持 video 标签。</video>"
                }else if(data.ztype=="文字"){
                    str+="<font class='cp_message' size='7'>"+data.zcontent+"</font>"
                }

            }
        });

        cp_content.html(str);
        //当前任务共有几页
        $.ajax({
            type: "post",
            url: "/findendpages",
            contentType: false,
            processData: false,
            data:formData,
            async: false,
            success: function (data){

                last_page=data;
            }
        });

        str2="<font size='5'>"+pages+"页/共"+last_page+"页</font>"
        cp_number.html(str2)

        //更新任务的各种时间与状态
        $.ajax({
            type: "post",
            url: "/updatetasktime",
            contentType: false,
            processData: false,
            data:{"taskid":taskid},
            async: false,
            success: function (data){


            }
        });



    }





    //页面加载前方法
    window.onload =function () {
        $("#lastpage").css("background-color","#A5A5A5");
        getcommand();
        findalltask();

    }

    //获取命令
    function getcommand() {
        var rolling_barrage=$("#rolling_barrage")
        var str=""

        //获取教师命令
        $.ajax({
            type: "post",
            url: "/findcommand",
            contentType: false,
            processData: false,
            async: false,
            success: function (data){

                for(var i=0;i<data.length;i++){
                    if(data[i].ztype =="签到"||data[i].ztype=="查岗"){
                        location.href = "/student";
                    }
                    else if(data[i].ztype == "滚屏信息"){
                        str+=" <marquee><span style=‘font-weight: bolder;font-size: 40px;color: white;’><font size='7'>"+data[i].zcontent+"</font></span></marquee>"
                        rolling_barrage.html(str)
                    }
                }
                //location.href = "/student_test";
            }
        });

    }

//所有任务
    function findalltask() {

        var leftbutton=$("#leftbutton");
        var str="";

        //固定任务按键
        $.ajax({
            type: "post",
            url: "/findallfixedtasks",
            contentType: false,
            processData: false,
            async: false,
            success: function (data){

                for(var i=0;i<data.length;i++){
                    str+="<button onclick='loadcontentbypages2("+data[i].zstudent_scheduleid+")' class='cp_button2' id='"+data[i].zstudent_scheduleid+"'>"+data[i].zname+"</button> <br><br>"

                }

            }
        });


        //临时任务按键
        $.ajax({
            type: "post",
            url: "/findalltemporarytask",
            contentType: false,
            processData: false,
            async: false,
            success: function (data){

                for(var i=0;i<data.length;i++){
                    str+="<button class='cp_button1' onclick='loadcontentbypages2("+data[i].zid+")' id='"+data[i].zid+"'>"+data[i].ztitle+"</button> <br><br>"
                }

            }
        });

        leftbutton.html(str)
    }
    

        //上一页方法
        function lastpage() {

        //如果此时页面值为2
           if(pages==2){
                //上一页按键变灰
                $("#lastpage").css("background-color","#A5A5A5");
                //移除上一页方法
                $("#lastpage").removeAttr("onclick");

            }
            pages--;
            //上一页后变成最后一页的前一页，按键变蓝，并加载下一页方法
            if (pages==last_page-1){
                $("#nextpage").css("background-color","#4472C4");
                $("#nextpage").attr("onclick","nextpage()");
            }


            loadcontentbypages(static_taskid);
        }


        //下一页方法
        function nextpage(){


          if(pages==last_page-1){
                //下一页按键变灰
                $("#nextpage").css("background-color","#A5A5A5");
                //移除下一页方法
                $("#nextpage").removeAttr("onclick");

            }
            pages++;
            //下一页到第二页，上一页按键变蓝，并加载上一页方法
            if(pages==2){
                $("#lastpage").css("background-color","#4472C4");
                $("#lastpage").attr("onclick","lastpage()");
            }


            loadcontentbypages(static_taskid);


        }





</script>

</html>