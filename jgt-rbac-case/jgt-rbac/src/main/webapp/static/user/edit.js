/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-26
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */

$(function () {

    $(".chosen-select").chosen();

    //表单验证
    $('#validation-form').validate({
        errorClass: 'help-block inline',
        focusInvalid: false,
        rules: {
            username: {
                required: true
            },
            email: {
                required: true
            },
            password: {
                required: true,
                minlength:6
            },
            organizationId:{
              required:true
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

