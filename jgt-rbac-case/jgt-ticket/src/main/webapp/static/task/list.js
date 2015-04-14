/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-27
 * Time: 下午1:59
 * To change this template use File | Settings | File Templates.
 */

$(function(){
    /*删除事件*/
    $(".del").on('click',function(e){
        e.preventDefault();
        var me = $(this);
        bootbox.confirm("确定要删除吗?", function(result) {
            if(result) {
               del(me);
            }
        });
    });

    function del(me){
        $.get(me.attr('href'),function(data){
            if(data.result){
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
    }
})