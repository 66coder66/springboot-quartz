<!DOCTYPE html>
<html lang="en">
<head>
    <title>定时器新增</title>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="job/plugs/layui/css/layui.css" rel="stylesheet" type="text/css">
    <script src="job/plugs/layui/layui.all.js" type="text/javascript"></script>
    <script src="job/plugs/jquery-3.4.1.min.js" type="text/javascript"></script>
<#--    <script src="pbs/plugs/xm-select.js"></script>-->
    <script type="text/javascript">
        var pbsBasePath = "${request.contextPath}/";
    </script>
    <style>
        .half-selem{
            width:50%
        }
        .prec9-selem{
            width:95%
        }
    </style>
</head>
<body>
<form lay-filter="create_filter"  name="form_create" class="layui-form" style="margin-top: 10px;" id="form_create">
<#--    <input name="SHI" id="SHI" type="hidden" />-->
    <div class="layui-form-item">
        <label class="layui-form-label">名称：</label>
        <div class="layui-input-block half-selem">
            <input type="text" id="JOB_NAME" name="JOB_NAME"  class="layui-input" required lay-verify="required" placeholder="请输入名称" autocomplete="off">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型：</label>
        <div class="layui-input-block half-selem">
            <select id="JOB_TYPE" name="JOB_TYPE" lay-verify="required" >
                <option value=""></option>
                <option value="JAVA">JAVA实现</option>
                <option value="SQL">自定义SQL</option>
<#--                <option value="EL">EL表达式</option>-->
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否运行：</label>
        <div class="layui-input-block">
            <input name="IS_RUN" type="radio" value="1"  title="是"/>
            <input name="IS_RUN" type="radio" value="0"  title="否" checked/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">Cron规则：</label>
        <div class="layui-input-block">
            <table class="layui-table prec9-selem">
                <colgroup>
                    <col width="20">
                    <col width="20">
                    <col width="20">
                    <col width="20">
                    <col width="10">
                    <col width="20">
                </colgroup>
                <tr>
                    <td>年</td>
                    <td>月</td>
                    <td>星期</td>
                    <td>日</td>
                    <td>时</td>
                    <td>分</td>
                </tr>
                <tr>
                    <td>
                        <select id="NIAN" name="NIAN" lay-verify="required">
                            <option value="*">每年</option>
                        </select>
                    </td>
                    <td>
                        <select id="YUE" name="YUE" lay-verify="required">
                            <option value="*" >每月</option>
                        </select>
                    </td>
                    <td>
                        <select id="XINGQI" name="XINGQI" lay-verify="required">
                            <option value="?" >无</option>
                            <option value="MON" >周一</option>
                            <option value="TUE" >周二</option>
                            <option value="WED" >周三</option>
                            <option value="THU" >周四</option>
                            <option value="FRI" >周五</option>
                            <option value="SAT" >周六</option>
                            <option value="SUN" >周日</option>
                        </select>
                    </td>
                    <td>
                        <select id="RI" name="RI" lay-verify="required">
                            <option value="?" selected>无</option>
                            <option value="*" >每日</option>
                            <option value="L" >每月最后一天</option>
                        </select>
                    </td>
                    <td>
<#--                        <div id="SHI_CONTEXT">-->
<#--                        </div>-->
                        <select id="SHI" name="SHI" lay-verify="required">
                            <option value="*" >每小时</option>
                        </select>
                    </td>
                    <td><select id="FEN" name="FEN" lay-verify="required">
                            <option value='0/1'>每1分钟</option>
                            <option value='0/5'>每5分钟</option>
                            <option value='0/10'>每10分钟</option>
                            <option value='0/15'>每15分钟</option>
                            <option value='0/20'>每20分钟</option>
                            <option value='0/30'>每30分钟</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">实现内容：</label>
        <div class="layui-input-block">
            <textarea name="JOB_IMP" placeholder="请输入内容" class="layui-textarea prec9-selem" style="height: 240px;"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="text-align: center;">
            <button class="layui-btn" lay-submit lay-filter="formSubmit">保存</button>
        </div>
    </div>
</form>
<script src="job/portal/js/create.js" type="text/javascript"></script>
</body>
</html>