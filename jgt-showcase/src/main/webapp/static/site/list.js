/**
 * Created with IntelliJ IDEA.
 * User: Koala
 * Date: 14-8-25
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
jQuery(function ($) {

    var accountTable = $('#sitelist').dataTable({
        autoWidth: false,
        processing: true,
        serverSide: true,
        //ordering: false,
        "order": [ 0, 'desc' ],
        pageLength: 20,
        ajax: {
            url: RS_PATH + "site/list",
            type: "POST",
            data: function (d) {
                console.log(d);
                d.keyword = $(':input[type="search"]').val();
            }
        },
        columns: [
            {
                data:'id',
                defaultContent:"",
                visible:false
            },
            {
                data: 'webName',
                defaultContent: "",
                render: function (data, type, row, meta) {
                    return '<a href="' + row.webUrl + '" target="_blank">' + data + '</a>'
                }
            },
            {
                data: 'webUrl',
                defaultContent: ""
            },
            {
                data: 'webType',
                defaultContent: ""
            },
            {
                data: 'filing',
                defaultContent: ""
            },
            {
                data: 'status',
                defaultContent: "",
                render: function (data, type, row, meta) {
                    switch (data) {
                        case 0:
                        {
                            return '<span class="label label-sm label-warning">未验证</span>';
                        }
                        case 1:
                        {
                            return '<span class="label label-sm label-info">审核中</span>';
                        }
                        case 2:
                        {
                            return '<span class="label label-sm label-success">审核通过</span>';
                        }
                    }
                }
            },
            {
                data: 'description',
                defaultContent: ""
            },
            {
                data: 'creationTime',
                defaultContent: ""
            },
            {
                data: 'updateTime',
                defaultContent: ""
            },
            {
                data: '',
                defaultContent: "",
                render: function (data, type, row, meta) {
                    var ps = '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">';
                    if (row.status == 1) {
                        ps += '<a class="green sh" href="' + RS_PATH + 'site/update/' + row.id + '/2">审核'
                            + '</a>';
                    }
                    ps += '<a class="red del" href="' + RS_PATH + 'site/delete/' + row.id + '">删除</a>';
                    ps += '</div>';
                    return ps;
                }
            }
        ],
        language: {
            url: RS_PATH + "assets/local/zh_CN.json"
        }
    });

    /**
     * 审核
     */
    $('#sitelist tbody').on('click', '.sh', function (e) {
        e.preventDefault();
        var me = $(this);
        $.get(me.attr('href'), function (data) {
            var tr = me.closest('tr');
            if (data.success) {
                $(tr).find('.sh').addClass('hide');
                $(tr).find('td:eq(4)').html('<span class="label label-sm label-success">审核通过</span>');
                $.gritter.add({
                    title: '提示',
                    text: '审核成功。',
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
    });

    /**
     * 删除
     */
    $('#sitelist tbody').on('click', '.del', function (e) {
        e.preventDefault();
        var me = $(this);
        $( "#site-del-confirm" ).removeClass('hide').dialog({
            resizable: false,
            modal: true,
            buttons: [
                {
                    html: "确定",
                    class: "btn btn-primary btn-xs",
                    click: function() {
                        $( this ).dialog( "close" );
                        $.get(me.attr('href'), function (data) {
                            if (data.success) {
                                var tr = me.closest('tr');
                                tr.fadeOut(400, function () {
                                    tr.remove();
                                    $.gritter.add({
                                        title: '提示',
                                        text: '删除成功。',
                                        class_name: 'gritter-success'
                                    });
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
                }
                ,
                {
                    html: "取消",
                    class : "btn btn-xs",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                }
            ]
        });
    });

})
