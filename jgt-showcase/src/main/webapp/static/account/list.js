/**
 * Created with IntelliJ IDEA.
 * User: Koala
 * Date: 14-8-1
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
jQuery(function ($) {

    var accountTable = $('#accountlist').dataTable({
        autoWidth: false,
        processing: true,
        serverSide: true,
        ordering:false,
        pageLength: 20,
        ajax: {
            url: RS_PATH + "account/list",
            type: "POST",
            data: function (d) {
                d.status = status;
                d.keyword = $(':input[type="search"]').val();
            }
        },
        columns: [
            {
                data: 'realName',
                defaultContent : "",
                render: function(data,type,row,meta){
                    return '<a href="'+RS_PATH+'account/'+row.id+'">'+data+'</a>'
                }
            },
            {
                data: 'idCard',
                defaultContent : ""
            },
            {
                data: 'mobilePhone'
            },
            {
                data: 'email'
            },
            {
                data: 'taxType',
                defaultContent : ""
            },
            {
                data: 'taxNo',
                defaultContent : ""
            },
            {
                data: 'taxRate',
                defaultContent : ""
            },
            {
                data: 'status',
                defaultContent : "",
                render:function(data,type,row,meta){
                    switch (data){
                        case 0: {
                            return '<span class="label label-sm label-warning">未审核</span>';
                        }
                        case 1:{
                            return '<span class="label label-sm label-success">审核通过</span>';
                        }
                        case 2:{
                            return '<span class="label label-sm label-warning">已驳回</span>';
                        }
                        case 3:{
                            return '<span class="label label-sm label-danger">已冻结</span>';
                        }
                    }
                }
            },
            {
                data: 'createTime',
                defaultContent : ""
            },
            {
                data: 'updateTime',
                defaultContent : ""
            },
            {
                data: '',
                defaultContent : "",
                render: function(data,type,row,mate){
                    return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'
                            +'<a class="blue sp" href="'+RS_PATH+'account/update/'+row.id+'/1" title="审核">'
                            +'<i class="icon-ok bigger-130"></i>'
                            +'</a>'
                            +'<a class="grey bh" href="'+RS_PATH+'account/update/'+row.id+'/2" title="驳回">'
                            +'<i class="icon-undo bigger-130"></i>'
                            +'</a>'
                            +'<a class="red dj" href="'+RS_PATH+'account/update/'+row.id+'/3" title="冻结">'
                            +'<i class="icon-trash bigger-130"></i>'
                            +'</a>'
                            +'</div>';
                }
            }
        ],
        language: {
            url: RS_PATH + "assets/local/zh_CN.json"
        }
    });

})
