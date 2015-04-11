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
        bootbox.confirm("确定要删除吗？删除的数据将不能还原。", function(result) {
            if(result) {
               del(me);
            }
        });
    });
    /*完成事件*/
    $(".finish").on('click',function(e){
        e.preventDefault();
        var me = $(this);
        bootbox.confirm("确定要完成吗？完成后的任务将不能进行任何操作。", function(result) {
            if(result) {
                finish(me);
            }
        });
    });

    //删除
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

    //完成
    function finish(me){
        $.get(me.attr('href'),function(data){
            if(data.result){
                var tr = me.closest('tr');
                $(tr).children('td').eq(4).html('<span class="label label-success">已完成</span>');
                $(tr).children('td').eq(5).html('');
                $.gritter.add({
                    title: '提示',
                    text: '操作成功。',
                    class_name: 'gritter-success'
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