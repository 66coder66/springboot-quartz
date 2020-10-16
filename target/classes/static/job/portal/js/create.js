// var SHI_H = xmSelect.render({
//     // 这里绑定css选择器
//     el: '#SHI_CONTEXT',
//     //name: 'SHI_REQ',
//     //layVerify: 'required',
//     //layVerType: 'msg',
//     style: {
//         width: '100px',
//     },
//     filterable: true,//搜索
//     delay: 500,//输入停止后延迟0.5秒搜索
//     // 渲染的数据
//     data: [
//         {name: '每小时', value: '*'},{name: '00', value: '00'},{name: '01', value: '01'},{name: '02', value: '02'},
//         {name: '03', value: '03'},{name: '04', value: '04'},{name: '05', value: '05'},{name: '06', value: '06'},
//         {name: '07', value: '07'},{name: '08', value: '08'},{name: '09', value: '09'},{name: '10', value: '10'},
//         {name: '11', value: '11'},{name: '12', value: '12'},{name: '13', value: '13'},{name: '14', value: '14'},
//         {name: '15', value: '15'},{name: '16', value: '16'},{name: '17', value: '17'},{name: '18', value: '18'},
//         {name: '19', value: '19'},{name: '20', value: '20'},{name: '21', value: '21'},{name: '22', value: '22'},{name: '23', value: '23'},
//     ]
//     ,toolbar: {
//         show: true,
//         list: ['CLEAR', 'REVERSE' ]
//     },
// })
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

        //监听提交
        form.on('submit(formSubmit)', function (data) {
            //debugger
            //var fdata = form.val("create_filter");
            var df = data.field;
            $.ajax({
                type: 'POST',
                contentType: "application/json;charset=UTF-8",
                url: pbsBasePath + "save",
                data: JSON.stringify(df),
                success: function(res){
                    if(res.code!=1&&res.code!=200){
                        layer.alert(res.message);
                    }else {
                        layer.msg('创建成功！');
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
    });
});
// 关闭窗口
function closeWin() {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
}