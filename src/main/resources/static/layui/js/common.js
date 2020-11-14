//举手
function upheads() {

    $.ajax({
        type: "post",
        url: "/insertevent",
        data:{"ztype":"举手"},
        success: function (data){
                if(data==1){
                    alert("已举手，等待老师处理")
                }
              $("#upheads").css('background-color','#FFC000');
              $("#upheads").text('取消举手');
              $("#upheads").attr("onclick","removeup()");

        }
    });

}


//请假原因框
function showleave() {
    $("#co_leavemes").show()
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
                 alert("已请假，等待老师审批")
                 $("#co_leavemes").hide();
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
                alert("已取消举手")
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
//退出
function outpower(){
    $("#popup").show()
}