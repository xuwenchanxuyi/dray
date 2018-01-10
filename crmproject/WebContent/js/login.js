$(function(){
    $("#loginCon").find(".txt").eq(0).find("input").focus(function () {
            $(this).val("");
    }).blur(function(){
        var val=$("#loginCon").find(".txt").eq(0).find("input").val();
        $(this).val(val);
    })
    $("#loginCon").find(".txt").eq(1).find("input").focus(function () {
        $(this).val("");
    }).blur(function(){
        var val2=$("#loginCon").find(".txt").eq(1).find("input").val();
        $(this).val(val2);
    })
    $(".but").click(function(){
        if($("#loginCon").find(".txt").eq(0).find("input").val()=="请输入用户名"||$("#loginCon").find(".txt").eq(0).find("input").val()=="请输入密码") {
            alert("请填写信息")
        }
    })

})