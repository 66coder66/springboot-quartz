<!DOCTYPE html>
<html lang="en">
<head>
    <title>定时器列表</title>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="job/plugs/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="job/plugs/layui/css/admin.css" rel="stylesheet" type="text/css">
    <link href="job/portal/css/reset_css.css" rel="stylesheet" type="text/css">
    <link href="job/portal/css/form.css" rel="stylesheet" type="text/css">
    <link href="job/portal/css/form_edit_0702.css" rel="stylesheet" />
    <script src="job/plugs/layui/layui.all.js" type="text/javascript"></script>
    <script src="job/plugs/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var pbsBasePath = "${request.contextPath}/";
    </script>
</head>
<body>
<div class="workbench_box">
    <!--查询盒子-->
    <div class="workbench_top_box">
            <span class="workbench_left">
                <span class="workbench_input_search totable">
                    <input type="text" id="jobName" name="jobName" placeholder="请输入查询条件" class="workbench_input" />
                    <span class="workbench_search_btn"><img src="job/portal/img/workbench_search.png" class="workbench_search"></span>
                </span>
            </span>
    </div>
    <!--table-->
    <div class="form_table" style="margin-top: 10px;">
        <button type="button" class="layui-btn layui-btn-sm" onclick="createJob()"><i class="layui-icon layui-icon-addition"></i></button><#--新增-->
        <#--<button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon">&#xe642;</i></button>修改-->
        <button type="button" class="layui-btn layui-btn-sm" onclick="delJobs()"><i class="layui-icon"></i></button><#--删除-->
        <table id="timer_list" lay-filter="timer_list_event"></table>
    </div>
</div>
<script type="text/html" id="toolBtn">
        <a  title="编辑" lay-event="edit"
            style="cursor:pointer;width: 40px;"><i class="layui-icon layui-icon-edit"></i>
        </a>
</script>
<script src="job/portal/js/list.js" type="text/javascript"></script>
</body>
</html>