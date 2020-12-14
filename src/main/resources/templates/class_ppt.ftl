<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 实训任务 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/class_ppt.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/fixed_task.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
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
    <div class="leftfont"><font size="5" >实训任务</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>
<#--隐藏区域是为了解决摄像头显示其他不能点击的问题-->

<div id="hiddenArea"style="position: absolute;height: 200%;width: 200%;filter: alpha(opacity=60);opacity: 0.6;display: none;z-index: 2"></div>
<!--左侧灰色按键-->
<div class="left" id="leftbutton">

</div>
<div id="regcoDiv"></div>
<div id="chagangID" style="display: none"></div>
<div id="gundongID" style="display: none"></div>

<!--请假弹框-->
<div class="co_leavemes" hidden id="co_leavemes" align="center">
    <font size="5">请假原因</font>
    <textarea type="text" class="co_mes" id="co_mes"></textarea>
    <button class="co_button" onclick="common_leave()">确认</button>
</div>



<div class="right">
    <!--右侧按键-->
    <div class="right1">
        <button class="button5" onclick="upheads()" id="upheads">举手</button>
    </div>

    <div class="right2">
        <button class="button5" onclick="showleave()"  id="leave">请假</button>
    </div>

    <div class="right3">
        <button class="button5">退出系统</button>
    </div>
</div>


<!--中间题目主体部分-->
<div class="center" id="cp_content" style="background-color: white;z-index:-1;">
        <div>
            <font size="7">请点击左侧实训标题，此处将显示实训内容</font>
        </div>
</div>



<!--翻页-->
<div class="pages" hidden id="pages">
    <button class="button3" id="lastpage" onclick="lastpage()">上一页</button>
    <div class="cp_number" id="cp_number"></div>
    <button class="button4" id="nextpage" onclick="nextpage()">下一页</button>
</div>


<!--滚动弹幕-->
<div  class="rolling_barrage" id="rolling_barrage" hidden >


</div>


<br><br>





</body>

<script>
    //备用参数，方便处理逻辑
    var j=0;

    //默认页面数
    var pages=1;
    //training_taskid
    var static_taskid=0;

    //共有几页，最后一页
    var last_page=0;
    //training_task_contentid
    var task_contentid=0;
    //zassign_scheduleid
    var zassign_scheduleid=0;

    //任务类型：1为固定任务，2为临时任务
    var static_kindid=0;

    //当前任务内容的类型（文字，图片，测量数据，视频）
    var static_ztype="";

    //测量数据类任务的参数
    //学生的自检值，填入文本类的值
    var zselfcheck=new Array();
    //实训任务评分表id，每条测量数据都有一个id
    var ztrainingtaskassessID=new Array();
    //需要测量的总数据条数
    var static_assessnum=0;

    //加载页面前页码及按键逻辑处理（任务表id,任务类型，固定任务Id）
    function loadcontentbypages2(taskid,kindid,assid) {
        //每次点击一个新任务都将交卷更改为下一页并变更方法
        $("#nextpage").attr("onclick","nextpage()");
        $("#nextpage").text('下一页');

        //页码代码
        $("#pages").show()
        //当前页为1
        pages=1;
        loadcontentbypages(taskid,kindid,assid);

        //上一页按键变灰
        $("#lastpage").css("background-color","#A5A5A5");
        //移除上一页方法
        $("#lastpage").removeAttr("onclick");

        //如果这个任务只有一页
        if(last_page==1){
            //下一页按键变提交按键，并加载提交按键方法
            $("#nextpage").attr("onclick","sumbmitpages()");
            $("#nextpage").text('交卷');

        }else {
            //除了上述特殊情况外，正常加载下一页方法及颜色
            $("#nextpage").css("background-color","#4472C4");
            $("#nextpage").attr("onclick","nextpage()");
        }

    }



    //根据页面与任务id加载任务主体内容
    //任务表id,任务类型（1为固定任务,2为临时任务），固定任务id
    function  loadcontentbypages(taskid,kindid,assid){

       //将传进来的三个值附值给全局变量
        static_kindid=kindid;
        static_taskid=taskid;
        zassign_scheduleid=assid;

        //返回值data类，确定有多个变量用
        var formData = new FormData();
        formData.append("page",pages);
        formData.append("taskid",taskid);

        //任务主体及其拼接类
        var cp_content=$("#cp_content");
        var str="";
        //页码部分及其拼接类
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
                //将取出来的值附值给全局变量
                task_contentid=data.zid;
                static_ztype=data.ztype;

                //判断主体内容，图片，视频，文字，数据测量各不相同
                //任务有title的情况
                if (data.ztitle){
                    str+="<div class='mes_title'><font size='5'>"+data.ztitle+"</font></div>"
                    if(data.ztype=="图片"){
                        //截取图片获取相对路径
                        var zcontent = data.zcontent;
                        var content=zcontent.substr(43);
                        str+="<img src='"+content+"'  alt='测试用' class='cp_message2' />";
                    }else if(data.ztype=="视频"){
                        var zcontent = data.zcontent;
                        var content=zcontent.substr(43);
                        str+="<video src='"+content+"' controls='controls' class='cp_message2'>您的浏览器不支持 video 标签。</video>"
                    }else if(data.ztype=="文字"){
                        str+="<font class='cp_message2' size='3'>"+data.zcontent+"</font>"
                        //数据测量需要额外加载右侧测量值的Id
                    }else if(data.ztype=="数据测量"){
                        var zcontent = data.zcontent;
                        var content=zcontent.substr(43);
                        str+=" <div class='left_table2'><img src='"+content+"'  alt='测试用' class='right_message' /></div>"
                        str+=" <div class='reight_mes2'>"
                        str+=" <table class='r_table2'>";
                        str+="<tr><th>测量值</th><th>序列</th></tr>"

                        //加载测量值的个数及id
                        $.ajax({
                            type: "post",
                            url: "/findtaskassessbytrainingid",
                            async: false,
                            data:{"ztraining_taskID":static_taskid},
                            success: function (data) {
                                static_assessnum=data.length;
                                for (var i=0;i<data.length;i++){
                                    ztrainingtaskassessID[i]=data[i].zid;
                                    j=i+1;
                                    str+=" <tr><th><input class='rmes_input' type='input' id='"+data[i].zid+"'></th><th>"+j+"</th></tr>"
                                }
                            }
                        });
                        }
                        str+="</table>";
                        str+="</div>"

                    //任务没有title的情况
                }else{
                    if(data.ztype=="图片"){
                        var zcontent = data.zcontent;
                        var content=zcontent.substr(43);

                        str+="<img src='"+content+"'  alt='测试用' class='cp_message' />";
                    }else if(data.ztype=="视频"){
                        var zcontent = data.zcontent;
                        var content=zcontent.substr(43);
                        str+="<video src='"+content+"' controls='controls' class='cp_message'>您的浏览器不支持 video 标签。</video>"
                    }else if(data.ztype=="文字"){
                        str+="<font class='cp_message' size='3'>"+data.zcontent+"</font>"
                    }else if(data.ztype=="数据测量"){
                        var zcontent = data.zcontent;
                        var content=zcontent.substr(43);
                        str+=" <div class='left_table'><img src='"+content+"'  alt='测试用' class='right_message' /></div>"
                        str+=" <div class='reight_mes'>"
                        str+=" <table class='r_table2'>";
                        str+="<tr><th>测量值</th><th>序列</th></tr>"


                        $.ajax({
                            type: "post",
                            url: "/findtaskassessbytrainingid",
                            async: false,
                            data:{"ztraining_taskID":static_taskid},
                            success: function (data) {
                                static_assessnum=data.length;
                                for (var i=0;i<data.length;i++){
                                    ztrainingtaskassessID[i]=data[i].zid;
                                    j=i+1;
                                    str+=" <tr><th><input class='rmes_input' type='input' id='"+data[i].zid+"'></th><th>"+j+"</th></tr>"
                                }
                            }
                        });

                        str+="</table>";
                        str+="</div>"
                    }
                }
            }
        });

        cp_content.html(str);
        //加载任务的页码
        $.ajax({
            type: "post",
            url: "/findendpages",
            contentType: false,
            processData: false,
            data:formData,
            async: false,
            success: function (data){
               //将其赋值给全局变量
                last_page=data;
            }
        });
        //页码前端加载
        str2="<font size='5'>"+pages+"页/共"+last_page+"页</font>"
        cp_number.html(str2)

        //如果是固定任务就更新任务的日志表
        if(static_kindid==1) {
            //更新固定任务的各种时间及状态
            $.ajax({
                type: "post",
                url: "/updatetasktime",
                data: {"taskid": taskid, "zassign_scheduleid": zassign_scheduleid, "task_contentid": task_contentid},
                success: function (data) {
                    // alert(data)
                }
            });
        }

    }


    //页面加载前方法
    window.onload =function () {
        welcome();
        $("#lastpage").css("background-color","#A5A5A5");
        findalltask();
        /*getcommand();*/
        //循环查找老师的命令
        window.setInterval(function () {
            getcommand();
        }, 8000);
    }

    function welcome() {

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
                    str+="<button onclick='loadcontentbypages2(\""+data[i].zstudent_scheduleid+"\",1,\""+data[i].zassign_scheduleid+"\")' class='cp_button2' id='\""+data[i].zstudent_scheduleid+"\"'>"+data[i].zname+"</button> <br><br>"

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
                    str+="<button class='cp_button1' onclick='loadcontentbypages2(\""+data[i].zcontentID+"\",2)' id='\""+data[i].zid+"\"'>"+data[i].ztitle+"</button> <br><br>"
                }

            }
        });

        leftbutton.html(str)
    }
    

        //上一页方法
        function lastpage() {
        //如果是数据测量的表，将插入学生输入的测量数据
        if(static_ztype=="数据测量"){

        for(var i=0;i<static_assessnum;i++){
            //对每个传过来的字符串去空格
            var removenull=Trim($("#"+ztrainingtaskassessID[i]+"").val());
            //空字符串与空格判断
            if(!removenull){
                alert("你还有未输入的测量数据！")
                return;
            }
            //长度长于数据库字体长度，直接返回
            else if(titleLength(removenull)>20){
                alert("你输入的长度过长！")
                return;
            }
            //长度与空格检查都通过，将值附给自检值
            else{
                zselfcheck[i]=removenull
            }
        }

            $.ajax({
                type: "post",
                url: "/inserttaskinput",
                async: false,
                data:{"zassign_scheduleid":zassign_scheduleid,"zselfcheck":zselfcheck,"ztrainingtaskassessID":ztrainingtaskassessID},
                success: function (data) {
                    if(data>0)
                    layer.msg("成功提交测量数据", { icon: 1, offset: "auto", time:1000 });
                }
            });


        }


        //固定任务更新content_log时间
        if(static_kindid==1){
            $.ajax({
                type: "post",
                url: "/updatetaskendtime",
                data:{"zassign_scheduleid":zassign_scheduleid,"task_contentid":task_contentid},
                success: function (data){
                    // alert(data)
                }
            });
        }



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
                $("#nextpage").attr("onclick","nextpage()");
                $("#nextpage").text('下一页');
            }


            //加载上一页
            loadcontentbypages(static_taskid,static_kindid,zassign_scheduleid);

            if(static_ztype=="数据测量" && zselfcheck){
                //alert(1)
                for (var i=0;i<zselfcheck.length;i++) {
                    $("#"+ztrainingtaskassessID[i]+"").val(zselfcheck[i]);
                }

            }
        }


        //下一页方法
        function nextpage(){
            if(static_ztype=="数据测量"){

                for(var i=0;i<static_assessnum;i++){
                    var removenull=Trim($("#"+ztrainingtaskassessID[i]+"").val())
                    if(!removenull){
                        alert("你还有未输入的测量数据！")
                        return;
                    }else if(titleLength(removenull)>20){
                        alert("你输入的长度过长！")
                        return;
                    }else{
                        zselfcheck[i]=removenull
                    }
                }

                $.ajax({
                    type: "post",
                    url: "/inserttaskinput",
                    async: false,
                    data:{"zassign_scheduleid":zassign_scheduleid,"zselfcheck":zselfcheck,"ztrainingtaskassessID":ztrainingtaskassessID},
                    success: function (data) {
                        if(data>0)
                            layer.msg("成功提交测量数据", { icon: 1, offset: "auto", time:1000 });
                    }
                });
            }

            //固定任务更新content_log时间
            if(static_kindid==1){
                $.ajax({
                    type: "post",
                    url: "/updatetaskendtime",
                    data:{"zassign_scheduleid":zassign_scheduleid,"task_contentid":task_contentid},
                    success: function (data){
                        // alert(data)
                    }
                });
            }

          if(pages==last_page-1){
              $("#nextpage").attr("onclick","sumbmitpages()");
              $("#nextpage").text('交卷');
            }
            pages++;
            //下一页到第二页，上一页按键变蓝，并加载上一页方法
            if(pages==2){
                $("#lastpage").css("background-color","#4472C4");
                $("#lastpage").attr("onclick","lastpage()");
            }


            loadcontentbypages(static_taskid,static_kindid,zassign_scheduleid);
            if(static_ztype=="数据测量" && zselfcheck){
                //alert(1)
                for (var i=0;i<zselfcheck.length;i++) {
                    $("#"+ztrainingtaskassessID[i]+"").val(zselfcheck[i]);
                }

            }


        }


        function sumbmitpages() {
            //alert("进入")

            if(static_kindid==1){
                $.ajax({
                    type: "post",
                    url: "/updatealltaskend",
                    data:{"zassign_scheduleid":zassign_scheduleid,"ztrainingtaskID":static_taskid,"task_contentid":task_contentid},
                    success: function (data){
                        if(data>0){
                            layer.msg("已提交成功", { icon: 1, offset: "auto", time:1000 });
                            last_page=0;

                        }else{
                            alert("出错，请吉时联系老师")
                        }
                    }
                });
            }else {
                layer.msg("已提交成功", { icon: 1, offset: "auto", time:1000 });
            }

        }



</script>

</html>