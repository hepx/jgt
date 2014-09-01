/**
 * Created with IntelliJ IDEA.
 * User: Koala
 * Date: 14-8-6
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
jQuery(function ($) {
    $('#accountlist tbody').on('click','.sp', function (e) {
        e.preventDefault();
        var me=$(this);
        updateStatus(me,1);
    });

    $('#accountlist tbody').on('click','.bh', function (e) {
        e.preventDefault();
        var me=$(this);
        updateStatus(me,2);
    });
    $('#accountlist tbody').on('click','.dj', function (e) {
        e.preventDefault();
        var me=$(this);
        updateStatus(me,3);
    });
    function updateStatus(me,s){
        $.get(me.attr('href'),function(data){
            if(data.success){
                var msg;
                var lab;
                switch(s){
                    case 1:
                        msg="审批";
                        lab='<span class="label label-sm label-success">审核通过</span>';
                        break;
                    case 2:
                        msg="驳回";
                        lab='<span class="label label-sm label-warning">已驳回</span>';
                        break;
                    case 3:
                        msg="冻结";
                        lab='<span class="label label-sm label-danger">已冻结</span>';
                        break;
                }
                var tr = me.closest('tr');
                if(tr){
                    $(tr).find('td:eq(7)').html(lab);
                }
                $.gritter.add({
                    title: '提示',
                    text: msg+'成功。',
                    class_name: 'gritter-success'
                });
            }else{
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
