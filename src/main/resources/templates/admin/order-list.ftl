<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-L-admin1.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="../../static/css/font.css">
    <link rel="stylesheet" href="../../static/css/xadmin.css">
    <script src="../../static/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页
        </a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
    <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
        <#--<input class="layui-input" placeholder="开始日" name="start" id="start">-->
        <#--<input class="layui-input" placeholder="截止日" name="end" id="end">-->
            <#if tableName??>
                            <input type="text" name="tableName" placeholder="请输入表名" autocomplete="off" class="layui-input" value="${tableName}">
            <#else>
                                        <input type="text" name="tableName" placeholder="请输入表名" autocomplete="off" class="layui-input" >

            </#if>

            <button class="layui-btn" lay-filter="sreach" id="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <#--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>-->
        <#--<button class="layui-btn" onclick="x_admin_show('添加用户','./order-add.html')"><i class="layui-icon"></i>添加-->
        <#--</button>-->
        <span class="x-right" style="line-height:40px">共有数据：${size} 张表</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>表名</th>
            <th>表解释</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#list tableNameList as table>
            <tr>
   <td>
       <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i>
       </div>
   </td>
                <td>    ${table.tableName}</td>
                <td>    ${table.tabelComment}</td>

                <td class="td-manage">
                <a title="查看" onclick="x_admin_show('编辑','order-view.html')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
                </a>
                <a title="生成表" onclick="member_del(this,'${table.tableName}')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
                </a>
                </td>
            </#list>

        </tr>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <a class="num" href="">1</a>
            <span class="current">2</span>
            <a class="num" href="">3</a>
            <a class="num" href="">489</a>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 5, time: 1000});

            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 5, time: 1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认生成这张表的数据吗？', function (index) {
            //发异步删除数据
console.log(id);

            $.ajax({
                url: "http://localhost:8008/generate/generateTemplate?tableName="+id,
            }).done(function() {
                $(this).addClass("done");
            });


            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？' + data, function () {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});


        });
    }


    $("#search").click(function () {
        var tableName = $(" input[ name='tableName' ] ").val();

        window.location = 'http://localhost:8008/freemarker/admin/order-list?tableName='+tableName;

    });

</script>
</body>

</html>