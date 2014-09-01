/**
 * Created with IntelliJ IDEA.
 * User: Koala
 * Date: 14-8-12
 * Time: 上午9:24
 * To change this template use File | Settings | File Templates.
 */
jQuery(function ($) {
    var action_a;
    /*
     * 调用搜索，搜索参数在fnServerParams中定义
     */
    $("#extendSearch").click( function () {
        extendTable.fnFilter();
    } );

    /*
     * 初始化表格参数
     */
    var extendTable = $('#extendlist').dataTable({
        autoWidth: false,
        processing: true,
        serverSide: true,
        searching:false,
        pageLength: 10,
        ajax: {
            url: RS_PATH+"extend/list",
            type: "POST",
            data: function (d) {
                d.keyword= $(".search-query").val();
            }
        },
        columns : [{
            data : "id",	//列标识，和服务器返回数据中的属性名称对应
            title : "",//列标题
            defaultContent : "", //此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
            searchable: false,
            visible : false //此列不显示
        }, {
            data : "pictureUrl",
            title : "图片",
            defaultContent : "",
            class:"col-lg-1 align-middle",
            searching: false,
            orderable:false,
            render:function(data,type,row,meta){
                 return '<img src="'+data+'" width="80" height="80">';
            }
        }, {
            data : "name",
            title : "名称",
            defaultContent : "",
            class:"col-lg-2 align-middle",
            orderable:false,
            render: function(data,type,row,meta){
                return '<a href="'+row.url+'" target="_blank">'+data+'</a>'
            }
        }, {
            data : "price",
            title : "价格",
            defaultContent : "",
            searching: false,
            class:"col-lg-1 align-middle"
        }, {
            data : "description",
            title : "描述",
            defaultContent : "",
            searching: false,
            class:'align-middle',
            orderable:false
        }, {
            data : "status",
            title : "状态",
            defaultContent : "",
            searching: false,
            class:"col-lg-1 align-middle",
            render: function(data,type,row,meta){
                if(data){
                    return '<span class="label label-sm label-success">已发布</span>';
                }else{
                    return '<span class="label label-sm label-warning">未发布</span>';
                }
            }
        }, {
            data : "rate",
            title : "佣金比率",
            searching: false,
            defaultContent : "",
            class:"col-lg-1 align-middle",
            render: function(data){
                return ((data * 100).toFixed(1)) + '%';
            }
        }, {
            data : "startTime",
            title : "推广时间(开始)",
            searching: false,
            defaultContent : "",
            class:"col-lg-1 align-middle"
        }, {
            data : "endTime",
            title : "推广时间(结束)",
            defaultContent : "",
            searching: false,
            class:"col-lg-1 align-middle"
        },  {
            data : "",
            title : "操作",
            class:"col-lg-1 align-middle",
            defaultContent : "",
            searching: false,
            orderable:false,
            render: function(data,type,row,meta){
                var ps='<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'
                        +'<a class="blue bj" href="#" data="'+row.id+'">编辑</a>';
                if(row.status){
                    ps+='<a class="red cx" href="'+RS_PATH+'extend/update/'+row.id+'/false">撤消发布'
                        +'</a>'
                        +'<a class="green fb hide" href="'+RS_PATH+'extend/update/'+row.id+'/true">发布'
                        +'</a>';
                }else{
                    ps+='<a class="red cx hide" href="'+RS_PATH+'extend/update/'+row.id+'/false">撤消发布'
                        +'</a>'
                        +'<a class="green fb" href="'+RS_PATH+'extend/update/'+row.id+'/true">发布'
                        +'</a>';
                }
                ps+='</div>';
                return ps;
            }
        }],
        language: {
            url: RS_PATH+"assets/local/zh_CN.json"
        }
    });

    $('#startTime').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('#endTime').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    //override dialog's title function to allow for HTML titles
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));


    var extendValidate = $('#extend-form').validate({
        errorElement: 'div',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {
            rate: {
                required: true,
                number: true,
                max: 1
            },
            startTime: {
                required: true,
                dateISO: true
            },
            endTime: {
                required: true,
                dateISO: true
            }
        },

        messages: {
            rate: {
                required: "佣金比率不能为空.",
                number: '必须是数字.',
                max: '最大值不能超过1'
            },
            startTime: {
                required: "开始时间不能为空.",
                dateISO: "日期不合法."
            },
            endTime: {
                required: "结束时间不能为空.",
                dateISO: "日期不合法."
            }
        },

        highlight: function (e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },

        success: function (e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
            $(e).remove();
        }
    });

    $('#extendlist tbody').on('click','.bj', function (e) {
        e.preventDefault();
        action_a = $(this);
        setFormValues(action_a);
        var dialog = $("#extendModal").removeClass('hide').dialog({
            modal: true,
            title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i> 推广设置</h4></div>",
            title_html: true,
            buttons: [
                {
                    text: "保存",
                    "class": "btn btn-primary btn-xs",
                    click: function () {
                        if (!extendValidate.form()) {
                            return;
                        } else {
                            $("#extend-form").submit();
                            $(this).dialog("close");
                        }
                    }
                },
                {
                    text: "取消",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                }
            ]
        });
    });

    $("#extend-form").on("submit", function (e) {
        e.preventDefault();
        var form = $(this);
        $.post(form.attr("action"), form.serialize(), function (data) {
            if (data.success) {
                updateTrValue(data.data.extend);
                $.gritter.add({
                    title: '提示',
                    text: '保存成功。',
                    class_name: 'gritter-success'
                });
            } else {
                $.gritter.add({
                    title: '错误',
                    text: data.msg,
                    class_name: 'gritter-error'
                });
            }
        })
    });

    /**
     * 发布
     */
    $('#extendlist tbody').on('click','.fb', function (e) {
        e.preventDefault();
        var me = $(this);
        if (valid(me)) {
            updateStatus(me, 1);
        } else {
            $.gritter.add({
                title: '警告',
                text: "请设置佣金和推广时间再发布！",
                class_name: 'gritter-warning'
            });
        }
    });

    /**
     * 撤消发布
     */
    $('#extendlist tbody').on('click','.cx', function (e) {
        e.preventDefault();
        var me = $(this);
        updateStatus(me, 2);
    });

    /**
     * 设置form值
     */
    function setFormValues(me) {
        var tr = me.closest('tr');
        $("#id").val(me.attr('data'));
        $("#rate").val(parseFloat($(tr).find('td:eq(5)').text() || 0) / 100);
        $("#startTime").val($(tr).find('td:eq(6)').text());
        $("#endTime").val($(tr).find('td:eq(7)').text());
    }

    /**
     * 清除form
     */
    function updateTrValue(data) {
        var tr = action_a.closest('tr');
        $(tr).find('td:eq(5)').text(((data.rate * 100).toFixed(1)) + '%');
        $(tr).find('td:eq(6)').text(data.startTime);
        $(tr).find('td:eq(7)').text(data.endTime);

    }

    function valid(me) {
        var tr = me.closest('tr');
        var flag = true;
        if (!($(tr).find('td:eq(5)').text())) {
            return false;
        }
        if (!($(tr).find('td:eq(6)').text())) {
            return false;
        }
        if (!($(tr).find('td:eq(7)').text())) {
            return false;
        }
        return flag;
    }

    /**
     * 更新发布状态
     */
    function updateStatus(me, s) {
        $.get(me.attr('href'), function (data) {
            var tr = me.closest('tr');
            if (data.success) {
                var msg;
                var lab;
                switch (s) {
                    case 1:
                        msg = "发布";
                        lab = '<span class="label label-sm label-success">已发布</span>';
                        $(tr).find('.cx').removeClass('hide');
                        $(tr).find('.fb').addClass('hide');
                        break;
                    case 2:
                        msg = "撤消发布";
                        lab = '<span class="label label-sm label-warning">未发布</span>';
                        $(tr).find('.fb').removeClass('hide');
                        $(tr).find('.cx').addClass('hide');
                        break;
                }
                if (tr) {
                    $(tr).find('td:eq(4)').html(lab);
                }
                $.gritter.add({
                    title: '提示',
                    text: msg + '成功。',
                    class_name: 'gritter-success'
                });
            } else {
                $.gritter.add({
                    title: '错误',
                    text: data.msg,
                    class_name: 'gritter-error'
                });
            }
            return false;
        });
    }

})