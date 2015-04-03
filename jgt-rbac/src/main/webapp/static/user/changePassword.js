/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-27
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */

$(function(){

    $('#validation-form').validate({
        errorClass:"text-danger",
        focusInvalid: false,
        rules: {
            newPassword: {
                required: true,
                minlength:6
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