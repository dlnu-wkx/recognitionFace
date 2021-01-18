//举手
function upheads() {

    $.ajax({
        type: "post",
        url: "/insertevent",
        data:{"ztype":"举手"},
        success: function (data){
                if(data==1){
                    layer.msg("已举手，等待老师处理", { icon: 1, offset: "auto", time:1000 });
                }
              $("#upheads").css('background-color','#FFC000');
              $("#upheads").text('取消举手');
              $("#upheads").attr("onclick","removeup()");
        }
    });

}

function leaveclass() {

    //删除临时任务
    $.ajax({
        type: "post",
        url: "/deletemes",
        data:{},
        success: function (data){

        }
    });
    //将继电器6号端口断开
    $.ajax({
        type: "post",
        url: "/usixout",
        data:{},
        success: function (data){

        }
    });

    //学生退出日志  



    location.href="/student"
}


//申请退出系统
function outsystem() {
    //alert(1)
    $.ajax({
        type: "post",
        url: "/outsystem",
        data:{"ztype":"退出系统"},
        success: function (data){
            if(data==1){
                layer.msg("已申请退出系统，等待老师处理", { icon: 1, offset: "auto", time:1000 });
            }
            $("#outsystem").css('background-color','#FFC000');
            $("#outsystem").text('取消申请');
            $("#outsystem").attr("onclick","removeout()");
        }
    });
}

function removeout() {
    $.ajax({
        type: "post",
        url: "/removeout",
        success: function (data){
            if(data!=0){
                layer.msg("已取消申请", { icon: 1, offset: "auto", time:1000 });
            }
            $("#outsystem").css('background-color','rgba(68,114,196)');
            $("#outsystem").text('退出系统');
            $("#outsystem").attr("onclick","outsystem()");
        }
    });
}



//判断字符串长度
function titleLength(str) {
    var strLength = 0;
    var list = str.split("");
    for (var i = 0; i < list.length; i++) {
            strLength = strLength + 1;
    }
    return strLength;
}

//去掉前面空格
function LTrim(str){
    var i;
    for(i=0;i<str.length;i++){
        if(str.charAt(i)!=" ")
            break;
    }
    str = str.substring(i,str.length);
    return str;
}

//去掉后面空格
function RTrim(str){
    var i;
    for(i=str.length-1;i>=0;i--){
        if(str.charAt(i)!=" ")
            break;
    }
    str = str.substring(0,i+1);
    return str;
}

//去掉前后空格
function Trim(str){
    return LTrim(RTrim(str));
}




//请假原因框
function showleave() {
    $("#co_leavemes").show();
    $("#leaveclass").css('background-color','#FFC000');
    $("#leaveclass").text('取消弹框');
    $("#leaveclass").attr("onclick","removeleave()");

}


/*function removeleave() {
    $("#co_leavemes").hide();
    $("#leaveclass").css('background-color','rgba(68,114,196)');
    $("#leaveclass").text('请假');
    $("#leaveclass").attr("onclick","showleave()");
}*/

//销假
function deleteleave() {
    $.ajax({
        type: 'post',
        url: '/deleteleave',
        success: function (data){
           // alert(data)
            if(data!=0){
                layer.msg("已销假", { icon: 1, offset: "auto", time:1000 });
                $("#leave").css('background-color','rgba(68,114,196)');
                $("#leave").attr("onclick","showleave()");
                $("#leave").text('请假');

            }
        }
    });
}


//请假
function common_leave(){
    var co_mes=$("#co_mes").val();
    //去空格
    co_mes=Trim(co_mes);

    //alert(co_mes)
    var length=titleLength(co_mes);

    //数据长度过长
    if (length>100){
        alert("请假原因过长");
    }
    //去空格后是否还有内容
    else if(!co_mes){
        alert("请填入请假原因");
    }else {
        $.ajax({
            type: 'post',
            url: '/insertevent',
            data:  {"ztype": "请假", "zcontent": co_mes} ,
            success: function (i){
                if(i==1){
                    layer.msg("已请假，等待老师审批", { icon: 1, offset: "auto", time:1000 });
                    $("#co_leavemes").hide();

                    $("#leave").css('background-color','#FFC000');
                    $("#leave").attr("onclick","deleteleave()");
                    $("#leave").text('销假');
                }
            }
        });
    }

}

function removeup() {
    $.ajax({
        type: "post",
        url: "/removeup",
        success: function (data){
            if(data==1){
                layer.msg("已取消举手", { icon: 1, offset: "auto", time:1000 });
            }
            $("#upheads").css('background-color','rgba(68,114,196)');
            $("#upheads").text('举手');
            $("#upheads").attr("onclick","upheads()");
        }
    });

}

function subContent() {
    var formData = new FormData();
    var zcontent =$("#textContent").val();
    var ztype=$("#valueSelect option:selected").val();
    formData.append("ztype",ztype );
    formData.append("zcontent", zcontent);
    $.ajax({
        type:"post",
        url:"/zrecord",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success:function (success) {
        if(success==1){
            alert("提交成功");
        }else{
            alert("提交失败没有提交成功");
        }
        }
    })
}
var filterjieshiLoop=0;
//根据开始按钮或者是结束按钮来帅选签到的人
var timer=null;
function OpenOTimer(a) {
        filterjieshiLoop=1;
        var zcheck="";
        if(a==2){
            zcheck="查岗";
            insertCheckPoint();
            $("#startID2").css('background-color','rgba(237,125,49)')
            $("#endID2").css('background-color','rgba(0,0,255)')
        }else {
            zcheck="人脸识别";
            $("#startID").css('background-color','rgba(237,125,49)')
            $("#endID").css('background-color','rgba(0,0,255)')
        }


        var myDate = new Date();
        var mytime=myDate.getTime();
        timer = setInterval(function(){
            showRecognitionFace(mytime,zcheck,filterjieshiLoop)
        }, 3000)

  /*  alert("执行完了start的功能")
    if (a == 2) {

        //timer=false;
    }*/
}

function CloseTimer(a) {
    if(a==2){
        //清除所有查岗的信息
        delAllCheckPoint();
        $("#endID2").css('background-color','rgba(237,125,49)')
        $("#startID2").css('background-color','rgba(0,0,255)')
    }else {
        $("#endID").css('background-color','rgba(237,125,49)')
        $("#startID").css('background-color','rgba(0,0,255)')
    }

    window.clearInterval(timer);
    filterjieshiLoop=2;
}

//从数据库中找出教室的所有设备
function showStudentStatus(){
    var str="";
    var center=$("#center");

    $.ajax({
        type: "post",
        url: "/findfacilitybytrainingroom",
        success: function (data) {
            if(data.length <7){
                center.empty();
                str+="<table class='t_table' id='p_bbox'>"
                str+=" <tr>";
                //var类型，不能写成int
                for(var i=0; i<data.length;i++){
                    str+=   "<th>";
                    str+=   "<div class='t_button1' id='"+data[i].zid+"' onclick=''>";
                    //str+="<th><button class='t_button1'onclick='diagram(\""+data[i].zid+"\")' >"+data[i].zidentity+"</button></th>";
                    str+="<div class='t_message'  align='center' id='t_message"+data[i].zid+"'>";
                    str+=  "<button class='t_button2' id='button"+data[i].zid+"'></button>";
                    str+= "<div class='t_id' align='center' id='machineNumber"+data[i].zid+"'>"+data[i].zidentity+"</div>";
                    str+= "<div class='t_name' align='center' id='name"+data[i].zid+"'></div>";
                    str+=   "</div>";
                    str+=   "<div class='t_student1'id='student"+data[i].zid+"'>";
                    str+=   "</div>";
                    //str+=  "<div class='t_student' align='center'><font size='3'>电脑屏幕</font></div>";
                    //str+= "<div class='t_computer' align='center'><font size='3'>摄像头</font></div>";
                    //str+= "<input type='text' class='t_progress' id='progress"+data[i].zid+"' value='当前进度:'>";
                    str+= "<div><input type='text' class='t_staets' id='status"+data[i].zid+"' value='状态信息 :'></div>";
                    //str+= "<button class='t_button3' onclick='closemessage(\""+data[i].zid+"\")'>关闭</button>";
                    //举手请假的部分
                    str+="<div class='t_event' id='"+data[i].zid+"'>"
                    str+=   "<div class='t_even_message'><div class='t_even_id'align='center' >请假理由：</div><div class='t_even_name' align='center'id='even"+data[i].zid+"'></div></div>";
                    str+=   "<div class='t_even_staets'><button class='even_button1' align='center' onclick='agree(\""+data[i].zid+"\")'>同意</button><button class='even_button2' align='center'onclick='disagree(\""+data[i].zid+"\")'>不同意</button></div>";
                    str+=   "</div>";
                    str+=   "</div>";
                    str+=   "</th>";
                    findStudentName(data[i].zid);


                    //后面使用
                   /* str+="<th><button class='t_button1'onclick='diagram(\""+data[i].zid+"\")' >"+data[i].zidentity+"</button></th>";
                    str+="<div class='t_message' hidden align='center' id='t_message\""+data[i].zid+"\"'>";
                    str+=  "<button class='t_button2' id='button\""+data[i].zid+"\"'></button>";
                    str+= "<div class='t_id' align='center' id='machineNumber"+data[i].zid+"'><font size='3'></font></div>";
                    str+= "<div class='t_name' align='center' id='name"+data[i].zid+"'><font size='3'>当前人员：</font></div>";
                    str+=  "<div class='t_student' align='center'><font size='3'>电脑屏幕</font></div>";
                    str+= "<div class='t_computer' align='center'><font size='3'>摄像头</font></div>";
                    str+= "<input type='text' class='t_progress' id='progress"+data[i].zid+"' value='当前进度:'>";
                    str+= "<input type='text' class='t_staets' id='status"+data[i].zid+"' value='状态信息 :'>";
                    str+= "<button class='t_button3' onclick='closemessage(\""+data[i].zid+"\")'>关闭</button>";
                    str+=   "</div>";*/

                }
                str+="</tr>";
                str+="</table>";
                center.html(str)
            }else {
                center.empty();
                var j=0;
                str+="<table class='t_table' id='p_bbox'>"

                for (var i=0;i<(data.length/8+1);i++){
                                /*传值uuid时js需要转换下\""+data[j].zid+"\"*/
                    str+=" <tr>";
                    for(;j<7*(i+1);j++){
                        if(j==data.length){break;}
                        str+=   "<th>";
                        str+=   "<div class='t_button1' onclick='diagram(\""+data[j].zid+"\")'>";
                        //str+="<th><button class='t_button1' >"+data[j].zidentity+"</button></th>";
                        str+="<div class='t_message'  align='center' id='t_message"+data[j].zid+"'>";
                        str+=  "<button class='t_button2' id='button"+data[j].zid+"'></button>";
                        str+= "<div class='t_id' align='center' id='machineNumber"+data[j].zid+"'>"+data[j].zidentity+"</div>";
                        str+= "<div class='t_name' align='center' id='name"+data[j].zid+"'></div>";
                        str+=   "</div>";
                        str+=   "<div class='t_student1' id='student"+data[j].zid+"'>";
                        str+=   "</div>";
                        //str+=  "<div class='t_student' align='center'><font size='3'>电脑屏幕</font></div>";
                        //str+= "<div class='t_computer' align='center'><font size='3'>摄像头</font></div>";
                        //str+= "<input type='text' class='t_progress' id='progress"+data[j].zid+"' value='当前进度:'>";
                        str+= "<div><input type='text' class='t_staets' id='status"+data[j].zid+"' value='状态信息 :'></div>";
                        //str+= "<button class='t_button3' onclick='closemessage(\""+data[j].zid+"\")'>关闭</button>";
                        //举手请假的部分
                        str+="<div class='t_event' id='"+data[j].zid+"'>"
                        str+=   "<div class='t_even_message'><div class='t_even_id'align='center' >请假理由：</div><div class='t_even_name' align='center'id='even"+data[j].zid+"'></div></div>";
                        str+=   "<div class='t_even_staets'><button class='even_button1' align='center' onclick='agree(\""+data[j].zid+"\")'>同意</button><button class='even_button2' align='center'onclick='disagree(\""+data[j].zid+"\")'>不同意</button></div>";
                        str+=   "</div>";
                        str+=   "</div>";
                        str+=   "</th>";
                        findStudentName(data[j].zid);
                    }
                    str+="</tr>";
                }

                str+="</table>";
                center.html(str)
            }

            //center.html(str)

        }
    });

}
//任课教师同意学生的批准请假
function agree(zid){
    var formData = new FormData();
    formData.append("zid",zid );
    formData.append("zstatus", "同意");
    $.ajax({
        type: 'post',
        url: '/agree',
        data:  {"zid":zid,"content":"同意"} ,
        success:function (data) {
        if(data==1){
            layer.msg("已批准", { icon: 1, offset: "auto", time:1000 });
            document.getElementById(zid).style.display="none";
        }
        }

    })
}

//任课教师不同意学生请假
function disagree(zid) {
    var formData = new FormData();
    formData.append("zid",zid );
    formData.append("zstatus", "不同意");
    $.ajax({
        type: 'post',
        url: '/disagree',
        data:  {"zid":zid,"content":"不同意"},
        success:function (data) {
        if(data==1){
            layer.msg("已否决", { icon: 1, offset: "auto", time:1000 });
            document.getElementById(zid).style.display="none";
        }
        }
    })
}

//从数据库中找出举手的同学
function findRaiseHand(zid) {

    $.ajax({
        type: 'post',
        url: '/findRaiseHand',
        data:  {"zid": zid} ,
        success: function (data){
            if(data!=""){
                $("#button"+zid).css('background-color','rgba(237,125,49)');
                $("#status"+zid).val("状态信息："+data.ztype);
                if(data.ztype=="请假"){
                    $("#even"+zid).append(data.zcontent);
                }
               /* $(function(){
                    $("#student"+zid).on('click',function(){
                        $(this).css("background-color","green");
                    });

                });*/

            }/*else{
                    //查看电源是否打开
                //findOpenPower(zid);

            }*/

        }
    });
}
//查看电源情况
function findOpenPower(zid){

    $.ajax({
        type: 'post',
        url: '/findOpenPower',
        data:  {"zid": zid} ,
        success: function (i){
            if(i!=""){
                $("#button"+zid).css('background-color','rgba(11,255,10)');
            }else{
                $("#button"+zid).css('background-color','rgba(128,128,128)');

            }

        }
    });

}
//查看使用该机床学生的姓名
function findStudentName(zid){
    $.ajax({
        type: 'post',
        url: '/findStudentName',
        data:  {"zid": zid} ,
        success: function (name){
            if(name!=""){
                $("#name"+zid).empty();
                $("#name"+zid).append(name);
                $("#button"+zid).css('background-color','rgba(11,255,10)');
                findRaiseHand(zid);
                presentProgess(zid)
            }else{
                $("#button"+zid).css('background-color','rgba(128,128,128)');
            }

        }
    });

}

//查看学生当前进度
function presentProgess(zid){
    $.ajax({
        type: 'post',
        url: '/presentProgess',
        data:  {"zid":zid} ,
        success: function (data){
            if(data!=""){
                $("#student"+zid).css("background-color","rgba(11,255,10)");
                $("#student"+zid).append("实训中")
            }else{
                $("#student"+zid).css("background-color","rgba(237,125,49)");
                $("#student"+zid).append("测试中")
            }
        }
    });
}





//从数据库中显示已经检测到的人脸信息
//从数据中找到签到的学生
function showRecognitionFace(mytime,zcheck,filterjieshiLoop) {
    console.log(filterjieshiLoop)
    var formData = new FormData();
    formData.append("zcheck",zcheck);
    formData.append("mytime",mytime );
    if(filterjieshiLoop==1){
    $.ajax({
        type:"post",
        url:"/InspectSitStudent",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success:function (data) {
            if(data!=null){
                /*if(data.length>5){
                    $("#identifyAreas").empty();
                    for(var i=0;i<5;i++){
                        content =" <div style='font-size: 20px;width: 80%;margin-top: 10px'>"+data[i].zstudentName+data[i].zgradeName+"</div>";
                        $("#identifyAreas").append(content);
                    }
                }else {
                    $("#identifyAreas").empty();
                    //alert("data :")
                    for(var i=0;i<data.length;i++){
                        content =" <div style='font-size: 20px;width: 80%;margin-top: 10px'>"+data[i].zstudentName+data[i].zgradeName+"</div>";
                        $("#identifyAreas").append(content);
                    }
                }*/
                //直接在左侧下方显示已经识别的人
                /*var data1 =data
                data1.sort(function(a,b){
                    return b.zrecognizetime-a.zrecognizetime
                })
                findAllLoginpeople(mytime);
                $("#identifyAreas").empty();
                //alert("data :")
                for(var i=0;i<data1.length;i++){
                    content =" <div style='font-size: 20px;width: 80%;margin-top: 10px'>"+data1[i].zstudentName+data1[i].zgradeName+"</div>";
                    $("#identifyAreas").append(content);
                }*/
                findAllLoginpeople(mytime,zcheck);
                 if(data.length==0){
                     $("#left").hide();
                     $("#middle").hide();
                     $("#right").hide();
                 }
                 if(data.length==1){
                     $("#left").empty();
                     $("#middle").hide();
                     $("#right").hide();
                     var  zName =data[0].zname;
                     var data1 = formatterDatetimeLocalToApprication(data[0].zrecognizetime);
                     $("#left").append(zName+"("+data1+")");
                     //$("#mainBody").hide()
                     $("#left").show();
                 }
                 if(data.length==2){
                     $("#left").empty();
                     $("#middle").empty();
                     $("#right").hide();
                     var  zName1 =data[1].zname;
                     var data1 = formatterDatetimeLocalToApprication(data[1].zrecognizetime);
                     $("#left").append(zName1+"("+data1+")");
                     var  zName2 =data[0].zname;
                     var data2 = formatterDatetimeLocalToApprication(data[0].zrecognizetime);
                     $("#middle").append(zName2+"("+data2+")");
                     //$("#mainBody").hide();
                     $("#left").show();
                     $("#middle").show();
                 }
                if(data.length==3){
                    $("#left").empty();
                    $("#middle").empty();
                    $("#right").empty();
                    var  zName1 =data[2].zname;
                    var data1 = formatterDatetimeLocalToApprication(data[2].zrecognizetime);
                    $("#left").append(zName1+"("+data1+")");
                    var  zName2 =data[1].zname;
                    var data2 = formatterDatetimeLocalToApprication(data[1].zrecognizetime);
                    $("#middle").append(zName2+"("+data2+")");
                    var  zName3 =data[0].zname;
                    var data3 = formatterDatetimeLocalToApprication(data[0].zrecognizetime)
                    $("#right").append(zName3+"("+data3+")");
                    //$("#mainBody").hide();
                    $("#left").show();
                    $("#middle").show();
                    $("#right").show();
                }
                 if(data.length>3){
                     $("#left").empty();
                     $("#middle").empty();
                     $("#right").empty();
                     var  zName1 =data[data.length-1].zname;
                     var data1 = formatterDatetimeLocalToApprication(data[data.length-1].zrecognizetime);

                     $("#left").append(zName1+"("+data1+")");
                     var  zName2 =data[data.length-2].zname;
                     var data2 = formatterDatetimeLocalToApprication(data[data.length-2].zrecognizetime);
                     $("#middle").append(zName2+"("+data2+")");
                     var  zName3 =data[data.length-3].zname;
                     var data3 = formatterDatetimeLocalToApprication(data[data.length-3].zrecognizetime)
                     $("#right").append(zName3+"("+data3+")")
                     //$("#mainBody").hide();
                     $("#left").show();
                     $("#middle").show();
                     $("#right").show();
                 }


             if(data.length>0){//不大于三个则显示现有的个数
                 var str="";
                 var center=$("#mainBody");
                 center.empty();
                 var j=0;
                 str+="<table class='fm_table1' id='p_bbox'>";
                 var arr =data
                 //var arr=data.slice(3,data.length);
                 for (var i=0;i<(arr.length/4+1);i++) {

                     str += " <tr>";
                     for (; j < 4 * (i + 1); j++) {
                         if (j == arr.length) {break;}
                        var data1 = formatterDatetimeLocalToApprication(arr[j].zrecognizetime)
                         str += "<th><div class='ff_button1'>" + arr[j].zname +"("+ data1 + ")"+"</div></th>";
                     }

                 }
                 str += "</tr>";
                 str+="</table>";
                 center.html(str)
             }else {
                 var center=$("#mainBody");
                 center.empty();
             }
            }else{
                alert("没有学生签到成功");
            }
        }
    })
    }
}
//查找所有的人包括学生和教师
function findAllLoginpeople(mytime,zcheck) {
    var formData = new FormData();
    formData.append("zcheck",zcheck)
    formData.append("mytime",mytime );
    $.ajax({
        type:"post",
        url:"/InspectSitStudentandTeacher",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success:function (data) {
            if(""!=data){
                $("#identifyAreas").empty();
                //显示最后一个识别成功的人的图片
                console.log(data)
                if(""!=data[data.length-1].originalPictureUrl){
                    var str =data[data.length-1].originalPictureUrl;
                    var path =str.substring(36);
                    document.getElementById("img").src=path;
                }
                for(var i=0;i<data.length;i++){
                    content =" <div style='font-size: 20px;width: 80%;margin-top: 10px'>"+data[i].zname+data[i].zgradeName+"</div>";
                    $("#identifyAreas").append(content);
                }
            }else{//如果在识别时间段内一个都没识别成功则为空
                $("#identifyAreas").empty();
            }
        }

    })

}

//对TimeStamp时间格式进行处理
function formatterDatetimeLocalToApprication(date){
    //把Timestamp转换成data
    var date1 = new Date(date);
    var date2 =date1.Format("hh:mm")
    return date2;
}
//把Timestamp格式化成为想要的形式
Date.prototype.Format = function (fmt) { //
    var o = {
        "M+": this.getMonth() + 1, //Month
        "d+": this.getDate(), //Day
        "h+": this.getHours(), //Hour
        "m+": this.getMinutes(), //Minute
        "s+": this.getSeconds(), //Second
        "q+": Math.floor((this.getMonth() + 3) / 3), //Season
        "S": this.getMilliseconds() //millesecond
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() +

        "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1,

            (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

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
    location.href="/time_status";
}
//信息发布
function informationDelivery() {
    location.href="/information_delivery";
}


function temporary_task() {

    window.location.href = "/temporary_task";
}

function fixed_task() {

    window.location.href = "/fixed_task";
}

function powerController() {

    window.location.href = "/power_controller";
}

function getcommand2() {

    $.ajax({
        type: "post",
        url: "/findscreencommand",
        data: {},
        async: false,
        success: function (data){
            var comscreen= $("#com_screen")
            var screentype=data.zcontent.split(";");
            alert(screentype[0])
            if (screentype[0].equals("2")){
                alert(1)
                var commandscreenid1=screentype.split(",")[0];
                var commandscreenid2=screentype.split(",")[1];
                alert(commandscreenid1)
                $.ajax({
                    type: "post",
                    url: "/findscreencontenbyid",
                    data: {"id":commandscreenid1},
                    async: false,
                    success: function (data2){
                        alert(data2.zcontent)
                        var ztype=data2.ztype;
                        var str1=""
                        var com_screen21=$("#com_screen21")
                        if (ztype=="图片"){
                            var time2=zcontent.split(";");
                            var time=time2[0];
                            var screenimage=time2[1];

                            var index2=new Array();
                            var screenimagec=new Array();
                            for(var i=0;i<screenimage.length;i++){
                                index2[i]=screenimage[i].lastIndexOf("\/");
                                screenimagec[i]=screenimage[i].substring(index+1,screenimage[0]);
                                str1+="<img class='img-slide img"+(i + 1)+"' src='"+screenimagec[i]+"'>"
                            }
                            alert(screenimagec)
                            com_screen21.html(str1)
                            setInterval(ChangeImg,time*1000)
                        }
                    }
                })

                $("#com_screen").show()

            }
        }
    })

}



//获取命令
function getcommand() {
    //alert(1)
    var rolling_barrage=$("#rolling_barrage");
    var chagangID =document.getElementById("chagangID").innerHTML;
    var gundongID =document.getElementById("gundongID").innerHTML;
    var formData = new FormData();
    formData.append("chagangID", chagangID);
    formData.append("gundongID", gundongID);
    var str="";

    //获取教师命令
    $.ajax({
        type: "post",
        url: "/findcommand",
        data: formData,
        contentType: false,
        processData: false,
        async: false,
        success: function (data) {
            if ("" != data) {

                for (var i = 0; i < data.length; i++) {
                    if (data[i].ztype == "查岗") {//data[i].ztype =="签到"||
                        document.getElementById("chagangID").innerHTML = data[i].zid;
                        getMedia2();

                    } else if (data[i].ztype == "滚屏信息") {
                        str += " <marquee  id='marquee'><span style='font-weight: bolder;font-size: 40px;color: white;'><font size='7'>" + data[i].zcontent + "</font></span></marquee>"
                        rolling_barrage.html(str)
                        rolling_barrage.show()
                        document.getElementById("gundongID").innerHTML = data[i].zid;
                    }
                }
            }
        }
    });

    $.ajax({
        type: "post",
        url: "/findcommand",
        data: {},
        async: false,
        success: function (data){
           // alert(data)
            if (data==0){
                $("#rolling_barrage").hide()
            }
        }
    })



}

var index=0;
//改变图片
function ChangeImg() {
    index++;
    var a=document.getElementsByClassName("img-slide");
    if(index>=a.length) index=0;
    for(var i=0;i<a.length;i++){
        a[i].style.display='none';
    }
    a[index].style.display='block';
}





//在数据库中插入查岗信息
function insertCheckPoint(){
    var formData = new FormData();
    formData.append("ztype", "查岗");
    formData.append("zcontent", "查岗");
    $.ajax({
        type: "post",
        url: "/addCheckPoint",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success: function (data){
        if(data!=1){
            alert("插入查岗失败")
        }

        }
    });
}

function  delAllCheckPoint() {

    $.ajax({
        type: "post",
        url: "/delCheckPoint",
        contentType: false,
        processData: false,
        async: false,
        success: function (data){


        }
    });
}

var mediaStreamTrack;

function getMedia2() {
    $("#regcoDiv").empty();
    let vedioComp = "<video muted id='video2' width='1000px' height='500px' autoplay='autoplay' style='z-index:1000;margin-left: 5%;margin-top: 4%' ></video><canvas id='canvas2' width='1000px' height='500px' style='display: none'></canvas>";
    $("#regcoDiv").append(vedioComp);
    let constraints = {
        video: {width: 1000, height: 800},
        audio: true
    };
    //获得video摄像头区域
    let video = document.getElementById("video2");
    //这里介绍新的方法，返回一个 Promise对象
    // 这个Promise对象返回成功后的回调函数带一个 MediaStream 对象作为其参数
    // then()是Promise对象里的方法
    // then()方法是异步执行，当then()前的方法执行完后再执行then()内部的程序
    // 避免数据没有获取到

    let promise = navigator.mediaDevices.getUserMedia(constraints);
    promise.then(function (stream) {
        mediaStreamTrack = typeof stream.stop === 'function' ? stream : stream.getTracks()[1];
        //video.src = (window.webkitURL).createObjectURL(stream);
        video.srcObject = stream;
        video.play()
    });
    window.setInterval(function () {//每隔几秒查询对比一次结果，循环对比
        chooseFileChangeComp()
    }, 5000);
    //chooseFileChangeComp()

}
function chooseFileChangeComp() {
    let regcoDivComp = $("#regcoDiv");
    if (regcoDivComp.has('video').length) {
        let video = document.getElementById("video2");
        let canvas = document.getElementById("canvas2");
        document.getElementById("hiddenArea").style.display="block";
        let ctx = canvas.getContext('2d');
        ctx.drawImage(video, 0, 0, 500, 500);
        var base64File = canvas.toDataURL();
        var formData = new FormData();
        formData.append("groupId", "101")
        formData.append("file", base64File);
        $.ajax({
            type: "post",
            url: "/faceFind",
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
                    document.getElementById("hiddenArea").style.display="none";
                    //关闭摄像头
                    mediaStreamTrack.stop();
                    $("#regcoDiv").empty();

                } else {
                    $("#nameDiv").html("");
                    $("#similarDiv").html("");
                    $("#ageDiv").html("");
                    $("#genderDiv").html("");
                    //自定义提示框
                    showTips("人脸不匹配");
                   // mediaStreamTrack.stop();
                    //location.href = "/student";
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
function showTips(content) {
    //窗口的宽度
    height = 200;
    var windowWidth = $(window).width();
    var tipsDiv = '<div class="tipsClass">' + content + '</div>';
    $('body').append(tipsDiv);
    $('div.tipsClass').css({//(windowWidth / 2) - 350 / 2 + 'px'
        'top': height + 'px',
        'left':  '650px',
        'position': 'absolute',
        'padding': '3px 5px',
        'background': '#fff',
        'font-size': 20 + 'px',
        'margin': '0 auto',
        'text-align': 'center',
        'width': '350px',
        'height': 'auto',
        'color': '#fc2b2e',
        'opacity': '1.0',
        'z-index':'1000'
    }).show();
    setTimeout(function () {
        $('div.tipsClass').fadeOut();
    }, 2000);
}

function CheckPointFindAllCameras() {
    var checkPointMenu =$("#checkPointMenu");
    checkPointMenu.empty();
    var formData = new FormData();
    formData.append("type", "查岗")
    $.ajax({
        type: "post",
        url: "/findAllCameras",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success: function (data) {
            if(data!=""){
                if(data.length<=2){
                    var str ="<div>";
                    str+=" <ul style='margin-top: 10%;width: 80%;left: 40%;margin: 37px auto'>"
                    for(var i=0;i<data.length;i++){
                        if (i == data.length) {break;}
                        str+="<li style='margin-left: 10% '><button onclick='studentShow1(this.value)' value='' style='float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px'>"+(data[i].ztrainingroomID)+"</button></li>"
                    }
                    str+="</ul>";
                    str+="</div>";
                    checkPointMenu.append(str)
                }else {
                    var j=0;
                    var str ="";
                    for(var i=0;i<(data.length/2);i++){
                        str+="<div>";
                        str+="<ul style='margin:"+(10*i)+"% auto;width: 80%;'>"
                        for(;j<2 * (i + 1);j++){
                            if (j == data.length) {break;}
                            if(j%2==0){
                                str+="<li style='margin-left: 30%'><button  onclick='studentShow1(this.value)' value='2' style='float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px'>"+(data[j].ztrainingroomID)+"</button>"
                                str+="<button id='zcameraIP' style='display: none' value='"+(data[j].zcameraIP)+"'></button>";
                                str+="<button id='zwebaddress'style='display: none' value='"+(data[j].zwebaddress)+"'></button> </li>";
                            }
                            if(j%2==1){
                                str+="<li style='margin-left: 60%'><button  onclick='studentShow1(this.value)' value='2' style='float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px'>"+(data[j].ztrainingroomID)+"</button>"
                                str+="<button id='zcameraIP' style='display: none' value='"+(data[j].zcameraIP)+"'></button>";
                                str+="<button id='zwebaddress'style='display: none' value='"+(data[j].zwebaddress)+"'></button></li>";
                            }
                        }
                        str+="</ul>";
                        str+="</div>";
                    }
                    checkPointMenu.append(str)
                }

            }



        }

    })

}

function findAllCameras() {

    var threeMenu =$("#threeMenu");
    threeMenu.empty();
    var formData = new FormData();
    formData.append("type", "签到")
    $.ajax({
        type: "post",
        url: "/findAllCameras",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success: function (data) {
            if(data!=""){
                if(data.length<=2){
                    var str ="<div>";
                    str+=" <ul style='margin-top: 10%;width: 80%;left: 40%;margin: 37px auto'>"
                  for(var i=0;i<data.length;i++){
                      if (i == data.length) {break;}
                     str+="<li style='margin-left: 20%;float:left '><button onclick='studentShow(this.value)' value='"+data[i].zid+"' id='"+data[i].zid+"' style='float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px'>"+data[i].ztrainingroomID+"</button>"
                      str+="<button id='zcameraIP"+data[i].zid+"' style='display: none' value='"+(data[i].zcameraIP)+"'></button>";
                      str+="<button id='zwebaddress"+data[i].zid+"'style='display: none' value='"+(data[i].zwebaddress)+"'></button></li>";

                  }
                    str+="</ul>";
                    str+="</div>";
                    threeMenu.append(str)
                }else {
                    var j=0;
                    var str ="";
                    for(var i=0;i<(data.length/2);i++){

                        str+="<div>";
                        str+="<ul style='margin:"+(10*i)+"% auto;width: 80%;'>"
                        for(;j<2 * (i + 1);j++){
                            if (j == data.length) {break;}
                            if(j%2==0){
                                str+="<li style='margin-left: 30%'><button  onclick='studentShow(this.value)' value='"+data[j].zid+"' style='float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px'>"+(data[j].ztrainingroomID)+"</button>"
                                str+="<button id='zcameraIP"+data[j].zid+"' style='display: none' value='"+(data[j].zcameraIP)+"'></button>";
                                str+="<button id='zwebaddress"+data[j].zid+"'style='display: none' value='"+(data[j].zwebaddress)+"'></button></li>";
                            }
                            if(j%2==1){
                                str+="<li style='margin-left: 60%'><button  onclick='studentShow(this.value)' value='1' style='float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px'>"+(data[j].ztrainingroomID)+"</button>"
                                str+="<button id='zcameraIP"+data[j].zid+"' style='display: none' value='"+(data[j].zcameraIP)+"'></button>";
                                str+="<button id='zwebaddress"+data[j].zid+"'style='display: none' value='"+(data[j].zwebaddress)+"'></button></li>";
                            }
                        }
                        str+="</ul>";
                        str+="</div>";
                    }
                    threeMenu.append(str)
                }

            }



        }

    })

}

/*
function findZtrainingroomName(zid) {
    var formData = new FormData();
    formData.append("cameraID", zid)
    $.ajax({
        type: "post",
        url: "/findztrainingroomName",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success: function (data) {
            if(null!=data){
                alert(data)
                document.getElementById( zid).innerHTML=data;
            }

        }

    })
}*/
