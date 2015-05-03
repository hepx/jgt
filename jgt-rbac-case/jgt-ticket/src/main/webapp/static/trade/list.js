/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-27
 * Time: 下午1:59
 * To change this template use File | Settings | File Templates.
 */

$(function(){
    //日期
    $('#startTime').datepicker({
        language: 'cn',
        format: "yyyy-mm-dd"
    });
    $('#endTime').datepicker({
        language: 'cn',
        format: "yyyy-mm-dd"
    });
    //浮动窗口
    $('[data-rel=popover]').popover({
        html:true,
        trigger:'hover'
    });

/*    $("#tradelist").dataTable({
        ordering:false,
        pageLength:20,
        lengthMenu: [[20, 40, 80,100, -1], [20, 40, 80,100, "全部"]]
    });*/
    /*删除事件*/
    $(".check").on('click',function(e){
        e.preventDefault();
        var me = $(this);
        bootbox.confirm("确定要完成核对吗?", function(result) {
            if(result) {
               check(me);
            }
        });
    });

    function check(me){
        $.get(me.attr('href'),function(data){
            if(data.result){
                var tr = me.closest('tr');
                $(tr).children('td').eq(8).html('<span class="label label-success">已核对</span>');
                $(tr).children('td').eq(9).html('');
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