<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 题库 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script type="text/javascript" src="./layui/js/common.js "></script>
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
    <div class="leftfont"><font size="5" >登陆后界面</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>

<!--左侧灰色按键-->
<div class="left">

</div>

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
        <button class="button5" onclick="outsystem()" id="outsystem">退出系统</button>
    </div>
</div>


<!--中间题目主体部分-->
<div class="center" id="qbank" style="background-color: white">


</div>


<br><br>


<!--翻页-->
<div class="pages">
    <button class="button3" id="lastpage" onclick="lastpage()">上一页</button>
    <button class="button4" id="nextpage" onclick="nextpage()">下一页</button>
</div>


<div class="pagesnumber" id="pagesnumber">

</div>


</body>

<script>

    function loadpagenumber(i) {
        //先清空里面的
        $("#pagesnumber").empty();
        var str=" <font size='5'>"+i+"/"+static_questionnum+"</font>"
        var pagesnumber=$("#pagesnumber")
        pagesnumber.html(str)

    }


    //分数
    var code=0;

    var static_questionnum=0;
    var static_singlecode=0;
    var static_total=0;

    //学生题解
    var ananswer=new Array();
    //正确题解
    var answer=new Array();

    var answercode=new Array();

    var questionid=new Array()


    var static_passingcode=0;

    //记录页数的i值,页数减1
    var i=0;



    //页面加载前方法
    window.onload =function () {
        //通过的分数
        $.ajax({
            type: "post",
            url: "/findpassingcode",
            success: function (data){
                static_passingcode=data;
            }
        });


        var pagesnumber=$("#pagesnumber")
        //题目数量
        $.ajax({
            type: "post",
            url: "/findtestnumber",
            async: false,
            success: function (data){
                static_questionnum=data;
                var str=" <font size='5'>1/"+static_questionnum+"</font>"
                pagesnumber.html(str)
                static_singlecode=100/static_questionnum;
            }
        });

        //总分
        $.ajax({
            type: "post",
            url: "/findtesttotal",
            async: false,
            success: function (data){
                static_total=data;
            }
        });


        load();
    }

    /*
       将所有问题都加载，显示一题，隐藏其它
        */
    function load() {
        //上一页按键变灰
        $("#lastpage").css("background-color","#A5A5A5");
        $("#nextpage").show();
        //上一页展现出来


        //下一页恢复原来的样式与方法
        var button4 =  $("#nextpage")
        button4.attr("onclick","nextpage()");
        button4.text('下一页');

        //去除中间显示的样式
        $("#qbank").removeAttr("align");

        var str = "";
        //测试cookie
        //alert($.cookie('name'));


        //连接Servlet
        $.ajax({
            type: "post",
            url: "/findtestbynumber",
            data:{"number":static_questionnum},
            async: false,
            success: function (question) {
               // alert(question[0].zid)

                //alert("选择题个数"+question.cbank.length)
                //alert("判断题个数"+question.jbank.length)
                //选择题循环取出并加载
               for (var w =0; w<question.cbank.length;w++) {
                    var k=w+1;

                   questionid[w]=question.cbank[w].zid;
                    str+="<div class='qbank"+w+"' id='qbank"+w+"'>";

                    str+="<font size='5' class='title'>安全测试题，"+static_questionnum+"个，共"+static_total+"，"+static_passingcode+"分及格</font>";
                    str+="<font size='5' class='questiontitle'>选择题("+question.cbank.length+"个)</font>";

                    str += " <div id='question' class='question'><font size='5' >"+k+"." + question.cbank[w].ztitlecontent + "</font></div><br><br><br>";

                    str += " <div class='cbooks' id='cbooks"+w+"'>";
                    str += " <p><input type='checkbox' class='choose' name='message' id='A"+w+"' value='A' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>A.</font><font onclick='checkquestion(\"A"+w+"\")' size='5' >" + question.cbank[w].zoptionA + "</font><br><br><br>";
                    str += " <input type='checkbox' class='choose' name='message' id='B"+w+"'    value='B' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>B.</font><font onclick='checkquestion(\"B"+w+"\")' size='5' >" + question.cbank[w].zoptionB + "</font><br><br><br>";
                    str += " <input type='checkbox' class='choose' name='message' id='C"+w+"'   value='C'>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>C.</font><font onclick='checkquestion(\"C"+w+"\")' size='5' >" + question.cbank[w].zoptionC + "</font><br><br><br>";
                    str += " <input type='checkbox'class='choose' name='message' id='D"+w+"'   value='D' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>D.</font><font onclick='checkquestion(\"D"+w+"\")' size='5' >" + question.cbank[w].zoptionD + "</font><br><br><br>";
                    str += " </p></div>";

                    str+="</div>";
                    str+=""
                }

                //判断题循环取出并加载
                for(var j=0; j<question.jbank.length;j++){
                    var k=j+question.cbank.length+1;
                    var p=j+question.cbank.length;

                    questionid[p]=question.jbank[j].zid;
                    str+="<div class='qbank"+p+"' id='qbank"+p+"'>";

                    str+="<font size='5' class='questiontitle'>判断题("+question.jbank.length+"个)</font>";

                    str += " <div id='question' class='question'><font size='5' >"+k+"." + question.jbank[j].ztitlecontent + "</font></div><br><br><br>";

                    str += " <div class='cbooks' id='cbooks"+p+"'>";
                    str += " <p><input type='checkbox' name='message' id='t"+p+"'  class='choose'  value='对' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font onclick='checkquestion(\"t"+p+"\")' size='5'>对</font><br><br><br></p>";
                    str += " <p onclick='checkquestion(\"f"+p+"\")'><input type='checkbox' name='message'  id='f"+p+"'  class='choose'  value='错' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>错</font><br><br><br>";

                    str += " </p></div>";

                    str+="</div>";

                }
                //先清空里面的
                $("#qbank").empty();

                //再将所有html放入
                $("#qbank").html(str);

                for (var c=1;c<question.cbank.length+question.jbank.length;c++) {
                    $("#qbank"+c+"").hide();
                }
                //隐藏其它题

                //获取正确答案
                $.ajax({
                    type: "post",
                    url: "/getsafetestanswer",
                    async: false,
                    success: function (data){
                        for (var c=0;c<data.length;c++) {
                            answer[c]=data[c]
                        }
                       // alert(answer)
                    }
                });


                //单选方法加载
                onechose();

            },
            error: function (error) {
                alert(JSON.stringify(error))
            }
        });
    }

    //点击题目选中选项
   function checkquestion(id){
        //alert(id)
       //alert($("#"+id+"").val());
       if ($("#"+id+"").prop('checked'))
           $("#"+id+"").prop("checked",false);
       else
           $("#"+id+"").prop("checked",true);
   }



   function submit() {

       submit2()
   }


    /**
     * 提交方法
     */

    function submit2() {

        //学生题解记录
        ananswer[i]=$("#cbooks"+i+"").find(':checkbox:checked').val();
        for (var f=0;f<i+1;f++){
            if (!ananswer[f]){
                alert("你还有未做的题，请仔细检查")
                return ;
            }

        }


        //分数信息居中显示
        $("#qbank").attr("align","center");

        $("#lastpage").css("background-color","#A5A5A5");
        $("#nexttpage").css("background-color","#A5A5A5");


        //中间题目获取
        var qbank=$("#qbank");
        var str3="";


        //总分
        for (var j=0;j<static_questionnum;j++){

            //正确答案与学生题解对比
            if(ananswer[j]==answer[j]){
                code+=static_singlecode;
                answercode[j]=static_singlecode;
            }else{
                answercode[j]=0;
            }
        }
        //隐藏题目，并加载分数信息
        $("#qbank"+i+"").hide();
        str3+="<br><br><br><br><br>"
        str3+="<div><font size='3' >安全测试评分</font></div>";
        str3+="<div><font size='3' >题目数量："+static_questionnum+"题</font></div>";
        str3+="<div><font size='3' >每题分数："+static_singlecode+"分</font></div>";
        str3+="<div><font size='3' >测试总分：100分</font></div>";
        str3+="<div><font size='3' >合格分数："+static_passingcode+"分</font></div>";
        str3+="<br><br><br>"
        //如果分数小于60，加载小于60的信息
        if(code<static_passingcode){
            str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>需要重新答题</font>";
            str3+="<br><br>"
            str3+=" <button class='button7' onclick='reanswer()'>重新答题</button>"
        }else{
            //加载大于60分的信息
            str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>已经</font><font size='3'color='red'>合格</font>";
            str3+="<br><br>"
            str3+="  <button class='start_button' onclick='begintrain()'>开始实训</button>";

            //左侧按键颜色改变(可循环优化)

        }
        //放入
        qbank.html(str3);
        //提交按键隐藏（防止分数多次叠加）
        $("#nextpage").hide();


        /*alert(answercode)
        alert(ananswer)
        alert(answer)
        alert(questionid)
        alert(static_questionnum)*/
     //  更改输入
      $.ajax({
            type: "post",
            url: "/updatetestinput",
            data: {"ananswer":ananswer,"answercode":answercode,"questionid":questionid,"number":static_questionnum},
            success: function (data) {

                if(data==static_questionnum){
                    layer.msg("提交成功", { icon: 1, offset: "auto", time:2000 });
                }else{
                    alert("提交出错")
                }

            }
        });
        code=0;


    }

    //用来测试的方法
    function test() {
        alert(1)
    }

    //重新答题方法
    function reanswer() {
        //下一页出现
        $("#nextpage").show();
        //颜色改变
        $("#lastpage").css("background-color","#4472C4");
        $("#nextpage").css("background-color","#4472C4");

        //初始化页面值
        i=0;

        //加载题目
        load();
        loadpagenumber(1);

    }

    //下一页
    function nextpage() {
        //alert(i+1)
        //翻页前先存储一下题解
        ananswer[i]=$("#cbooks"+i+"").find(':checkbox:checked').val();


        if(i!=(static_questionnum-1)){

            //清除前一个题
            $("#qbank"+i+"").hide();

            //页面翻页
            i++;
            //加载下一页
            $("#qbank"+i+"").show();
            if(i==1){
                $("#lastpage").css("background-color","#4472C4");
            }

            if(i==(static_questionnum-1)){
                var button4 =  $("#nextpage")
                button4.attr("onclick","submit()");
                button4.text('交卷');
            }

        }else {

            alert("已经没有下一页了")
        }
        //当前页面单选
        onechose();
        loadpagenumber(i+1)
    }

    //上一页
    function lastpage(){
       // alert(i+1)
        //翻页前先存储一下题解
        ananswer[i]= $("#cbooks"+i+"").find(':checkbox:checked').val();
        if(i!=0){
            //清除前一个题
            $("#qbank"+i+"").hide();

            //页面翻页
            i--;

            //加载上一页
            $("#qbank"+i+"").show();
            //当前页面单选
            onechose();
            if(i==0){
                $("#lastpage").css("background-color","#A5A5A5");
                $("#lastpage").css("daoborder-color","#f8fff9");
            }
            if(i==(static_questionnum-2)){
                var button4 =  $("#nextpage")
                button4.attr("onclick","nextpage()");
                button4.text('下一页');
            }

        }else{

            alert("没有上一页了")
        }
        loadpagenumber(i+1)

    }





    //单选方法
    function onechose(){
        //在当前题解区域查找按键
        $("#cbooks"+i+"").find(":checkbox").each(function(){
            $(this).click(function () {
                if ($(this).is(":checked")) {
                    //将之前确认的选项取消
                    $("#cbooks"+i+"").find(":checkbox").each(function () {
                        $(this).prop("checked", false);
                    });
                    //钩选现在需要确认的选项
                    $(this).prop("checked", true);
                }
            });
        });
    }

function begintrain() {

    location.href = "/class_ppt";
}



</script>

</html>