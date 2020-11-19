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


//请假原因框
function showleave() {
    $("#co_leavemes").show();
    $("#leaveclass").css('background-color','#FFC000');
    $("#leaveclass").text('取消弹框');
    $("#leaveclass").attr("onclick","removeleave()");

}


function removeleave() {
    $("#co_leavemes").hide();
    $("#leaveclass").css('background-color','rgba(68,114,196)');
    $("#leaveclass").text('请假');
    $("#leaveclass").attr("onclick","showleave()");
}

function deleteleave() {
    $.ajax({
        type: 'post',
        url: '/deleteleave',
        success: function (i){
            if(i==1){
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
//现场管理
function fieldManagement() {
    location.href="/teachRegister";
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
                    rolling_barrage.show()
                }
            }
            //location.href = "/student_test";
        }
    });

}