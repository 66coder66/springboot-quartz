$(function(){
    // table
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#timer_list',
            url: pbsBasePath +'getListJson' ,//数据接口
            request: {
                jobName: $("#jobName").val()
            },
            cols: [[ //表头
                {type:'checkbox'},
                {field: 'ID', hide:true},
                {field: 'JOB_NAME', title: '名称',width:200,event: 'viewForm', style:'cursor: pointer;',
                    // 行内编辑table
                    templet:function(res){
                        return '<div style="color: #1E9FFF;">'+res.JOB_NAME+'</div>';
                    }},
                {field: 'IS_RUN', title: '是否运行',width:50,
                    templet:function(res){
                        var str = res.IS_RUN == true?'是':'否';
                        return '<div>'+str+'</div>';
                    }
                },
                {field: 'JOB_TYPE', title: '类型',width:100,templet:function(res){
                        var str = res.JOB_TYPE == 'JAVA'?'JAVA实现':'自定义SQL';
                        return '<div>'+str+'</div>';
                    }},
                {field: 'CREATE_DATE', title: '创建日期',templet:'<div>{{ layui.util.toDateString(d.CREATE_DATE, "yyyy-MM-dd HH:mm:ss") }}</div>',width:200,required:true},
                {title:'操作', width:40, align:'left', toolbar: '#toolBtn',fixed: 'right'}
            ]],
            id:'timer_list_id',
            even:true,
            height:'full-76',
            page:true,
            done:function(){

            }
        });
    })
    //监听单元格事件
    var objNewTaskWin;
    layui.table.on('tool(timer_list_event)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;
        if(layEvent === 'viewForm'){//查看
            var url = pbsBasePath +'edit?id='+data.ID+"&type="+'view';
            layui.layer.open({
                type: 2,
                title: "定时器详情",
                shade: [0],
                area: ['1100px', '650px'],
                offset: 'auto', //默认坐标，即垂直水平居中
                anim: 2,
                content: [encodeURI(url), 'no']
            });
        }
        if(layEvent === 'edit'){
            var url = pbsBasePath +'edit?id='+data.ID+"&type="+'edit';
            layui.layer.open({
                type: 2,
                title: "编辑定时器",
                shade: [0],
                area: ['1100px', '650px'],
                offset: 'auto', //默认坐标，即垂直水平居中
                anim: 2,
                content: [encodeURI(url), 'no']
            });
        }
    });
    $('.totable .workbench_search_btn').on('click', function(){
        listReload();
    });
    $('#jobName').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            listReload();
        }
    });
})
//TODO
//重新加载列表数据
function listReload(){
    layui.table.reload('timer_list_id', {//todo_Reload是组件名称,表格名
        page: {//page参数是指重新刷新后从第几页开始
            curr: 1 //重新从第 1 页开始
        }
        ,where: {//where是指你要往后台发的参数的键值对,可以不加key{}
            jobName: $('#jobName').val()
        }
    }, 'data');//data是默认值
}
//新建数据
function createJob() {
    var url = pbsBasePath +'create?';
    layui.layer.open({
        type: 2,
        title: "新增定时器",
        shade: [0],
        area: ['1100px', '650px'],
        offset: 'auto', //默认坐标，即垂直水平居中
        anim: 2,
        content: [encodeURI(url), 'no']
    });
}
//删除数据
function delJobs(){
    var checkStatus = layui.table.checkStatus('timer_list_id');
    if(checkStatus.data.length == 0){
        layui.layer.msg('请选择要删除的数据！');
        return false;
    }
    layui.layer.confirm('是否删除选中数据?', {icon: 3, title:'提示'}, function(index){
        maskOn();
        var rows = checkStatus.data;
        var arr = [];
        for(var i=0;i<rows.length;i++){
            arr.push(rows[i].ID);
        }
        //var datas = arr.toString();
        $.ajax({
            type: 'POST',
            url: pbsBasePath + "delDate",
            data: {ids:arr},
            traditional:true,
            success: function(res){
                var code = res.code;
                if(code!='1' && code!='200'){
                    layui.layer.alert(res.message);
                }else {
                    layui.layer.msg('删除成功！');
                    listReload();
                }
                return false;
            },
            error: function(res){
                layui.layer.msg('网络错误，请稍后重试！');
            },
            complete: function(res){
                maskOff();
            }
        });
        return false;
    });
}

//遮罩
function maskOn(){
    layui.layer.load(1,{
        shade:[0.3,'#000000']
    })
}
//关闭遮罩
function maskOff(){
    layui.layer.closeAll('loading');
}