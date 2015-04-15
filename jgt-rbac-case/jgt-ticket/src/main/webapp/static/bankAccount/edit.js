/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-27
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */

$(function () {
    //表单验证
    $('#validation-form').validate({
        errorClass: 'help-block inline',
        focusInvalid: false,
        rules: {
            bankName: {
                required: true
            },
            account: {
                required: true
            }
        },
        highlight: function (e) {
            $(e).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(e).remove();
        }
    });
})