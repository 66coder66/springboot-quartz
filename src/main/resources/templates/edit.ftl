<!DOCTYPE html>
<html lang="en">
<head>
    <title>定时器修改</title>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="job/plugs/layui/css/layui.css" rel="stylesheet" type="text/css">
    <script src="job/plugs/layui/layui.all.js" type="text/javascript"></script>
    <script src="job/plugs/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var pbsBasePath = '${request.contextPath}/';
        var jobData = '${jobJson}';
        var viewType = '${viewType}';
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
<form lay-filter="edit_filter"  name="form_edit" class="layui-form" style="margin-top: 10px;" id="form_edit">
    <input name="ID" id="ID" type="hidden" value="${job.ID}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">名称：</label>
        <div class="layui-input-block half-selem">
            <input type="text" id="JOB_NAME" name="JOB_NAME" value="${job.JOB_NAME}" <#if viewType =='view'>readonly</#if> class="layui-input" required lay-verify="required" placeholder="请输入名称" autocomplete="off">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型：</label>
        <div class="layui-input-block half-selem">
            <select id="JOB_TYPE" name="JOB_TYPE" lay-verify="required" <#if viewType =='view'>disabled</#if>>
                <option value=""></option>
                <option value="JAVA" <#if job.JOB_TYPE =='JAVA'>selected</#if>>JAVA实现</option>
                <option value="SQL" <#if job.JOB_TYPE =='SQL'>selected</#if>>自定义SQL</option>
<#--                <option value="EL" <#if job.JOB_TYPE =='EL'>selected</#if>>EL表达式</option>-->
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否运行：</label>
        <div class="layui-input-block">
            <input name="IS_RUN" type="radio" value="1"  title="是" <#if job.IS_RUN =='true'>checked</#if> <#if viewType =='view'>disabled</#if>/>
            <input name="IS_RUN" type="radio" value="0"  title="否" <#if job.IS_RUN ==false>checked</#if> <#if viewType =='view'>disabled</#if>/>
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
                        <select id="NIAN" name="NIAN" lay-verify="required" <#if viewType =='view'>disabled</#if>>
                            <option value="*" <#if job.NIAN =='*'>selected</#if>>每年</option>
                        </select>
                    </td>
                    <td>
                        <select id="YUE" name="YUE" lay-verify="required" <#if viewType =='view'>disabled</#if>>
                            <option value="*" <#if job.YUE =='*'>selected</#if>>每月</option>
                        </select>
                    </td>
                    <td>
                        <select id="XINGQI" name="XINGQI" lay-verify="required" <#if viewType =='view'>disabled</#if>>
                            <option value="?" <#if job.XINGQI =='?'>selected</#if>>无</option>
                            <option value="MON" <#if job.XINGQI =='MON'>selected</#if>>周一</option>
                            <option value="TUE" <#if job.XINGQI =='TUE'>selected</#if>>周二</option>
                            <option value="WED" <#if job.XINGQI =='WED'>selected</#if>>周三</option>
                            <option value="THU" <#if job.XINGQI =='THU'>selected</#if>>周四</option>
                            <option value="FRI" <#if job.XINGQI =='FRI'>selected</#if>>周五</option>
                            <option value="SAT" <#if job.XINGQI =='SAT'>selected</#if>>周六</option>
                            <option value="SUN" <#if job.XINGQI =='SUN'>selected</#if>>周日</option>
                        </select>
                    </td>
                    <td>
                        <select id="RI" name="RI" lay-verify="required" <#if viewType =='view'>disabled</#if>>
                            <option value="?" <#if job.RI =='?'>selected</#if>>无</option>
                            <option value="*" <#if job.RI =='*'>selected</#if>>每日</option>
                            <option value="L" <#if job.RI =='L'>selected</#if>>每月最后一天</option>
                        </select>
                    </td>
                    <td>
                        <#--                        <div id="SHI_CONTEXT">-->
                        <#--                        </div>-->
                        <select id="SHI" name="SHI" lay-verify="required" <#if viewType =='view'>disabled</#if>>
                            <option value="*" <#if job.SHI =='*'>selected</#if>>每小时</option>
                        </select>
                    </td>
                    <td><select id="FEN" name="FEN" lay-verify="required" <#if viewType =='view'>disabled</#if>>
                            <option value='0/1' <#if job.FEN =='0/1'>selected</#if>>每1分钟</option>
                            <option value='0/5' <#if job.FEN =='0/5'>selected</#if>>每5分钟</option>
                            <option value='0/10' <#if job.FEN =='0/10'>selected</#if>>每10分钟</option>
                            <option value='0/15' <#if job.FEN =='0/15'>selected</#if>>每15分钟</option>
                            <option value='0/20' <#if job.FEN =='0/20'>selected</#if>>每20分钟</option>
                            <option value='0/30' <#if job.FEN =='0/30'>selected</#if>>每30分钟</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">实现内容：</label>
        <div class="layui-input-block">
            <textarea name="JOB_IMP" <#if viewType =='view'>readonly</#if> placeholder="请输入内容" class="layui-textarea prec9-selem" style="height: 240px;">${job.JOB_IMP}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="text-align: center;">
            <button class="<#if viewType =='view'>layui-btn layui-btn-disabled<#else>layui-btn</#if>" <#if viewType =='edit'>lay-submit lay-filter="formSubmit"</#if> <#if viewType =='view'>disabled</#if>>保存</button>
        </div>
    </div>
</form>
<script src="job/portal/js/edit.js" type="text/javascript"></script>
</body>
</html>