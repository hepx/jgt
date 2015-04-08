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
            role: {
                required: true
            },
            description: {
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
    var url= RS_PATH+"resource/json";
    if(resourceIds){
        url+="?resourceIds="+resourceIds;
    }
    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": "", "N": "" }
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        async: {
            enable: true,
            contentType: "application/json",
            url: url
        },
        callback: {
            onCheck: onCheck
        }
    };

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            nodes = zTree.getCheckedNodes(true),
            id = "",
            name = "";
        nodes.sort(function compare(a, b) {
            return a.id - b.id;
        });
        for (var i = 0, l = nodes.length; i < l; i++) {
            id += nodes[i].id + ",";
            name += nodes[i].name + ",";
        }
        if (id.length > 0) id = id.substring(0, id.length - 1);
        if (name.length > 0) name = name.substring(0, name.length - 1);
        $("#resourceIds").val(id);
        $("#resourceName").val(name);
    }

    function showMenu() {
        var cityObj = $("#resourceName");
        var cityOffset = $("#resourceName").offset();
        $("#menuContent").css({left: cityOffset.left + "px", top: cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }

    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
            hideMenu();
        }
    }

    $.fn.zTree.init($("#tree"), setting);
    $("#menuBtn").click(showMenu);

})