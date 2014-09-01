/**
 * Created with IntelliJ IDEA.
 * User: Koala
 * Date: 14-8-5
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
jQuery(function ($) {

    $('#validation-form').validate({
        errorElement: 'div',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {
            category: {
                required: true
            },
            rate: {
                required: true,
                number: true,
                max: 1
            }
        },

        messages: {
            category: {
                required: "佣金类型不能为空."
            },
            rate: {
                required: "佣金比率不能为空.",
                number: '必须是数字.',
                max: '最大值不能超过1'
            }
        },

        highlight: function (e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },

        success: function (e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
            $(e).remove();
        }

/*        submitHandler: function (form) {
        },
        invalidHandler: function (form) {
        }*/
    });

})