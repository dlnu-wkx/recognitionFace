<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 题库 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">

<#--  <script type="text/javascript" src="./layui/layui.js ">   </script>-->
    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>

</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >登陆后界面</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>

<!--左侧灰色按键-->
<div class="left">
    <button class="button8" id="button1">固定任务</button>
    <br><br>
    <button class="button9" id="button2">临时任务</button>
    <br><br>
    <button class="button2" id="button3">临时任务</button>
    <br><br>
    <button class="button2" id="button4">任务名称</button>
    <br><br>
    <button class="button2" id="button5">任务名称</button>
    <br><br>
    <button class="button2" id="button6">任务名称</button>
    <br><br>
    <button class="button2" id="button7">任务名称</button>
    <br><br>
    <button class="button2" id="button8">任务名称</button>
    <br><br>
    <button class="button2" id="button9">任务名称</button>
    <br><br>
</div>

<div class="right">
<!--右侧按键-->
    <div class="right1">
        <button class="button5">举手</button>
    </div>

    <div class="right2">
        <button class="button5">请假</button>
    </div>

    <div class="right3">
        <button class="button5">退出系统</button>
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





</body>

<script>
    //分数
    var code=0;

    //学生题解
    var ananswer=new Array();
    //正确题解
    var answer=new Array();

    //记录页数的i值,页数减1
    var i=0;



    //页面加载前方法
    window.onload =function () {
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
            url: "/findallquestion",
            contentType: false,
            processData: false,
            async: false,
            success: function (question) {

                //选择题循环取出并加载
                for (var w =0; w<question.cbank.length;w++) {
                    var k=w+1;

                    str+="<div class='qbank"+w+"' id='qbank"+w+"'>";

                    str+="<font size='5' class='title'>安全测试题,每题20分，60分及格</font>";
                    str+="<font size='5' class='questiontitle'>选择题</font>";

                    str += " <div id='question' class='question'><font size='5' >"+k+"." + question.cbank[w].ztitlecontent + "</font></div><br><br><br>";

                    str += " <div class='cbooks' id='cbooks"+w+"'>";
                    str += " <p><input type='checkbox' class='choose' name='message' id='A"+w+"' value='A' ><font size='5'>A.</font><font size='5' >" + question.cbank[i].zoptionA + "</font><br><br><br>";
                    str += " <input type='checkbox' class='choose' name='message'id='B"+w+"'    value='B' ><font size='5'>B.</font><font size='5' >" + question.cbank[i].zoptionB + "</font><br><br><br>";
                    str += " <input type='checkbox' class='choose' name='message' id='C"+w+"'   value='C'><font size='5'>C.</font><font size='5' >" + question.cbank[i].zoptionC + "</font><br><br><br>";
                    str += " <input type='checkbox'class='choose' name='message' id='D"+w+"'   value='D' ><font size='5'>D.</font><font size='5' >" + question.cbank[i].zoptionD + "</font><br><br><br>";
                    str += " </p></div>";

                    str+="</div>";

                }

                //判断题循环取出并加载
                for(var j=0; j<question.jbank.length;j++){
                    var k=j+4;
                    var p=j+3;
                    str+="<div class='qbank"+p+"' id='qbank"+p+"'>";

                    str+="<font size='5' class='questiontitle'>判断题</font>";

                    str += " <div id='question' class='question'><font size='5' >"+k+"." + question.jbank[j].ztitlecontent + "</font></div><br><br><br>";

                    str += " <div class='cbooks' id='cbooks"+p+"'>";
                    str += " <p><input type='checkbox' name='message'  class='choose'  value='对' ><font size='5'>对</font><br><br><br>";
                    str += " <input type='checkbox' name='message'   class='choose'  value='错' ><font size='5'>错</font><br><br><br>";

                    str += " </p></div>";

                    str+="</div>";

                }
                //先清空里面的
                $("#qbank").empty();

                //再将所有html放入
                $("#qbank").html(str);

                //隐藏其它题
                $("#qbank1").hide();
                $("#qbank2").hide();

                $("#qbank3").hide();
                $("#qbank4").hide();

                //单选方法加载
                onechose();

            },
            error: function (error) {
                alert(JSON.stringify(error))
            }
        });
    }


    /**
     * 提交方法
     */

    function submit() {
        //分数信息居中显示
        $("#qbank").attr("align","center");

        $("#lastpage").css("background-color","#A5A5A5");
        $("#nexttpage").css("background-color","#A5A5A5");


        //学生题解记录
        ananswer[i]=$("#cbooks"+i+"").find(':checkbox:checked').val();

        //中间题目获取
        var qbank=$("#qbank");
        var str3="";

        //从cookie中拿出正确答案（可循环取出优化）
        answer[0]=$.cookie('answer0');
        answer[1]=$.cookie('answer1');
        answer[2]=$.cookie('answer2');
        answer[3]=$.cookie('answer3');
        answer[4]=$.cookie('answer4');

        //每答对一题加20分
        for (var j=0;j<5;j++){

            //正确答案与学生题解对比
            if(ananswer[j]==answer[j]){
                code+=20;
            }
        }
        //隐藏题目，并加载分数信息
        $("#qbank"+i+"").hide();
        str3+="<br><br><br><br><br>"
        str3+="<div><font size='3' >安全测试评分</font></div>";
        str3+="<div><font size='3' >题目数量：5题</font></div>";
        str3+="<div><font size='3' >每题分数：20分</font></div>";
        str3+="<div><font size='3' >测试总分：100分</font></div>";
        str3+="<div><font size='3' >合格分数：60分</font></div>";
        str3+="<br><br><br>"
        //如果分数小于60，加载小于60的信息
        if(code<20){
            str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>需要重新答题</font>";
            str3+="<br><br>"
            str3+=" <button class='button7' onclick='reanswer()'>重新答题</button>"
        }else{
            //加载大于60分的信息
            str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>已经</font><font size='3'color='red'>合格</font>";
            str3+="<br><br>"
            str3+="<font size='5'color='red'>点击左侧任务开始实训</font>";

            //左侧按键颜色改变(可循环优化)
            $('#button1').css('background-color','#70AD47');
            $('#button2').css('background-color','#FFC000');
            $('#button3').css('background-color','rgba(68,114,196)');
            $('#button4').css('background-color','rgba(68,114,196)');
            $('#button5').css('background-color','rgba(68,114,196)');
            $('#button6').css('background-color','rgba(68,114,196)');
            $('#button7').css('background-color','rgba(68,114,196)');
            $('#button8').css('background-color','rgba(68,114,196)');
            $('#button9').css('background-color','rgba(68,114,196)');

        }
        //放入
        qbank.html(str3);
        //提交按键隐藏（防止分数多次叠加）
        $("#nextpage").hide();

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

    }

    //下一页
    function nextpage() {
        //翻页前先存储一下题解
        ananswer[i]=$("#cbooks"+i+"").find(':checkbox:checked').val();

        if(i!=4){


            //清除前一个题
            $("#qbank"+i+"").hide();

            //页面翻页
            i++;
            //加载下一页
            $("#qbank"+i+"").show();
            if(i==1){
                $("#lastpage").css("background-color","#4472C4");
            }

            if(i==4){
                var button4 =  $("#nextpage")
                button4.attr("onclick","submit()");
                button4.text('交卷');
            }

        }else {

            alert("已经没有下一页了")
        }
        //当前页面单选
        onechose();

    }

    //上一页
    function lastpage(){

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
            if(i==3){
                var button4 =  $("#nextpage")
                button4.attr("onclick","nextpage()");
                button4.text('下一页');
            }

        }else{

            alert("没有上一页了")
        }


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


    };



</script>

</html>