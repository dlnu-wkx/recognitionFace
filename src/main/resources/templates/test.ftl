<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 题库 </title>
    <link href="layui/css/demo.css" rel="stylesheet">

<#--  <script type="text/javascript" src="./layui/layui.js ">   </script>-->
    <script  src="jquery/jquery-3.3.1.min.js "></script>
    <script src="jquery/jquery.cookie.js"></script>

</head>
<body class="body">

<div class="top">
    <div class="leftfont"><font size="5" >登陆后界面</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>

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
<div class="right1">
    <button class="button5">举手</button>
</div>

<div class="right2">
    <button class="button5">请假</button>
</div>

<div class="right3">
    <button class="button5">退出系统</button>
</div>


<div class="center" id="qbank" align="center">

</div>
<br><br>


<div class="pages">
    <button class="button3" onclick="lastpage()">上一页</button>
    <button onclick="submit()" class="button6">提交测试</button>
    <button class="button4" onclick="nextpage()">下一页</button>
</div>




</body>

<script>

    var code=0;
    var ananswer=new Array();
    var answer=new Array();
    //记录页数的i值,页数减1
    var i=0;

    /*
    将所有问题都加载，显示一题，隐藏其它
     */
    window.onload = function laod () {

            var str = "";

            //测试cookie
            // alert($.cookie('name'));

            //连接Servlet
            $.ajax({
                type: "post",
                url: "/findallquestion",
                contentType: false,
                processData: false,
                async: false,
                success: function (question) {

                    //循环取出并加载选择题
                    for (var i =0; i<question.cbank.length;i++) {

                        var k=i+1;
                        /*alert(question.cbank.question[0])*/
                        str+="<div class='qbank"+i+"' id='qbank"+i+"'>";

                        str+="<font size='4'>选择题</font>";

                        str += " <div id='question' class='question'><font size='3' >"+k+"." + question.cbank[i].question + "</font></div><br><br><br>";

                        str += " <div class='cbooks'>";
                        str += " <p><input type='checkbox' class='choose' name='message'  value='A' ><font size='4'>A.</font><font size='3' >" + question.cbank[i].answer1 + "</font><br><br><br>";
                        str += " <input type='checkbox' class='choose' name='message'   value='B' ><font size='4'>B.</font><font size='3' >" + question.cbank[i].answer2 + "</font><br><br><br>";
                        str += " <input type='checkbox' class='choose' name='message'   value='C'><font size='4'>C.</font><font size='3' >" + question.cbank[i].answer3 + "</font><br><br><br>";
                        str += " <input type='checkbox'class='choose' name='message'   value='D' ><font size='4'>D.</font><font size='3' >" + question.cbank[i].answer4 + "</font><br><br><br>";
                        str += " </p></div>";

                        str+="</div>";

                    }
                    //循环取出并加载判断题
                    for(var j=0; j<question.jbank.length;j++){
                        var k=j+4;
                        var p=j+3;
                        str+="<div class='qbank"+j+"' id='qbank"+p+"'>";

                        str+="<font size='4'>判断题</font>";

                        str += " <div id='question' class='question'><font size='3' >"+k+"." + question.jbank[j].question + "</font></div><br><br><br>";

                        str += " <div class='cbooks'>";
                        str += " <input type='checkbox' name='message'  class='choose'  value='对' ><font size='4'>对</font><br><br><br>";
                        str += " <input type='checkbox' name='message'  class='choose'  value='错' ><font size='4'>错</font><br><br><br>";

                        str += " </div>";

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

                },
                error: function (error) {
                    alert(JSON.stringify(error))
                }
            });
        }









    function submit() {
    ananswer[i]=$('input:checkbox:checked').val();

    var qbank=$("#qbank");
    var str3="";

    //可循环取出优化
    answer[0]=$.cookie('answer0');
    answer[1]=$.cookie('answer1');
    answer[2]=$.cookie('answer2');
    answer[3]=$.cookie('answer3');
    answer[4]=$.cookie('answer4');



    for (var j=0;j<5;j++){
        if(ananswer[j]==answer[j]){

            code+=20;
        }
        }

        alert(code)
        $("#qbank"+i+"").hide();
        str3+="<br><br><br><br><br>"
        str3+="<div><font size='3' >安全测试评分</font></div>";
        str3+="<div><font size='3' >题目数量：5题</font></div>";
        str3+="<div><font size='3' >每题分数：20分</font></div>";
        str3+="<div><font size='3' >测试总分：100分</font></div>";
        str3+="<div><font size='3' >合格分数：60分</font></div>";
        str3+="<br><br><br>"
    if(code<60){
        str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>需要重新答题</font>";
        str3+="<br><br>"
        str3+=" <button class='button7' onclick='test()'>重新答题</button>"
    }else{
        str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>已经</font><font size='3'color='red'>合格</font>";
        str3+="<br><br>"
        str3+="<font size='5'color='red'>点击左侧任务开始实训</font>";

        //左侧按键颜色变灰
        $('#button1').css('color','green');
        $('#button2').css('color','orage');
        $('#button3').css('color','bule');
        $('#button4').css('color','bule');
        $('#button5').css('color','bule');
        $('#button6').css('color','bule');
        $('#button7').css('color','bule');
        $('#button8').css('color','bule');
        $('#button9').css('color','bule');

    }
    qbank.html(str3);


    }

    //用来测试的方法
function test() {
    alert(1)
}




    //下一页
    function nextpage() {
        //翻页前先存储一下题解
       ananswer[i]=$('input:checkbox:checked').val();

        if(i!=4 ){
           //清除前一个题
           $("#qbank"+i+"").hide();

           //页面翻页
           i++;

           //加载下一页
           $("#qbank"+i+"").show();
       }else if(i==4){
           alert("已经没有下一页了")
       }


    }

    //上一页
    function lastpage(){
    //翻页前先存储一下题解
        ananswer[i]=$('input:checkbox:checked').val();
        if(i!=0){
            //清除前一个题
            $("#qbank"+i+"").hide();

            //页面翻页
            i--;

            //加载上一页
            $("#qbank"+i+"").show();


        }else{
            alert("没有上一页了")
        }


    }

//单选方法
    $(function(){
        $(":checkbox").each(function(){
            $(this).click(function () {
                if ($(this).is(":checked")) {
                    //$('#cb').prop('checked') 一样的效果
                    $(":checkbox").each(function () {
                        $(this).prop("checked", false);
                    });
                    $(this).prop("checked", true);
                }
            });
        });
    });



</script>

</html>