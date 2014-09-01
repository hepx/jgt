/**
 * Created with IntelliJ IDEA.
 * User: Koala
 * Date: 14-8-4
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
jQuery(function ($) {
    var rateTable = $('#ratelist').dataTable({
        ordering: false,
        language: {
            url: RS_PATH+"assets/local/zh_CN.json"
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

    /*删除*/
    $('.red').on('click', function (e) {
        e.preventDefault();
        var me=$(this);
        $.get(me.attr('href'),function(data){
            if(data.success){
                var tr = me.closest('tr');
                tr.fadeOut(400, function(){
                    tr.remove();
                    $.gritter.add({
                        title: '提示',
                        text: '删除成功。',
                        class_name: 'gritter-success'
                    });
                });
            }else{
                $.gritter.add({
                    title: '错误',
                    text: '发生未知异常。',
                    class_name: 'gritter-error'
                });
            }
            return false;
        });
    });
})

