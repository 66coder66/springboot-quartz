//判断字符是否为空
function isNull(data){
    if(null == data || "" == data || typeof (data) == "undefined" || data.length == 0 || "null" == data){
        return false;
    }
    return true;
}
$(function($){
    var thisyear=parseInt(new Date().getFullYear());
    for(var i=thisyear;i<thisyear+4;i++){
        $("#NIAN").append("<option value='"+i+"'>"+i+"</option>");
    }
    for(var i=1;i<13;i++){
        $("#YUE").append("<option value='"+i+"'>"+i+"</option>");
    }
    for(var i=1;i<32;i++){
        $("#RI").append("<option value='"+i+"'>"+i+"</option>");
    }
    for(var i=0;i<24;i++){
        $("#SHI").append("<option value='"+i+"'>"+i+"</option>");
    }
    for(var i=0;i<60;i++){
        $("#FEN").append("<option value='"+i+"'>"+i+"</option>");
    }
    layui.use(['form', 'layer'], function () {
        var form = layui.form,layer = layui.layer;
        form.render();
        //form.val("edit_filter",jobData);
        if('edit' ==viewType){
            //监听提交
            form.on('submit(formSubmit)', function (data) {
                //debugger
                //var fdata = form.val("create_filter");
                var df = data.field;
                $.ajax({
                    type: 'POST',
                    contentType: "application/json;charset=UTF-8",
                    url: pbsBasePath + "update",
                    data: JSON.stringify(df),
                    success: function(res){
                        if(res.code!=1&&res.code!=200){
                            layer.alert(res.message);
                        }else {
                            layer.msg('保存成功！');
                            setTimeout(function(){
                                closeWin();
                                parent.listReload(); }, 2000)
                        }
                        return false;
                    },
                    error: function(res){
                        layer.msg('网络错误，请稍后重试！');
                    }
                    // ,complete: function(res){
                    //     setTimeout(function(){
                    //         closeWin();
                    //         parent.listReload(); }, 2000)
                    // }
                });
                return false;
            });
        }
    });
});
// 关闭窗口
function closeWin() {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
}