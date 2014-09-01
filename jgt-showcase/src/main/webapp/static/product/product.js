/**
 * Created with IntelliJ IDEA.
 * User: Koala
 * Date: 14-8-8
 * Time: 下午5:49
 * To change this template use File | Settings | File Templates.
 */
jQuery(function ($) {
    var productTable = $('#productlist').dataTable({
        "aoColumns": [
            { "bSortable": false },
            null,
            null,
            null,
            null,
            null,
            { "bSortable": false }
        ],
        "language": {
            "url": RS_PATH+"assets/local/zh_CN.json"
        }
    });

    $('table th input:checkbox').on('click', function () {
        var that = this;
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });
    });

    /*加入推广*/
    $('a[class="green"]').on('click', function (e) {
        e.preventDefault();
        var me=$(this);
        $.get(me.attr('href'),function(data){
            if(data.success){
                $.gritter.add({
                    title: '提示',
                    text: '添加成功。',
                    class_name: 'gritter-success'
                });
            }else{
                $.gritter.add({
                    title: '错误',
                    text: data.msg,
                    class_name: 'gritter-error'
                });
            }
        });
    });
})

