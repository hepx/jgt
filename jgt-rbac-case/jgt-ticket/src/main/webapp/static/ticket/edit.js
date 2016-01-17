/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-27
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
$(function () {

    var in_ticket_table = "#in-ticket-table";
    var out_ticket_table = "#out-ticket-table";
    var formatter = {
        number: {decimalSeparator: ".", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0.00'}
    };
    /*进票table*/
    var in_lastsel;
    $(in_ticket_table).jqGrid({
        datatype: "local",
        colNames: ['进票日期', '到期日期', '票号', '票面金额',  '进票点', '票面零头',  '其它费用', '进票实际金额'],
        colModel: [
            {name: 'inDate', index: 'inDate', width: 90, editable: true, sortable: false},
            {name: 'expireDate', index: 'expireDate', width: 90, editable: true, sortable: false},
            {name: 'ticketNo', index: 'ticketNo', width: 100, editable: true, sortable: false, editrules: {required: true, number: true, custom: true, custom_func: verifyTicketNo}},
            {name: 'ticketMoney', index: 'ticketMoney', width: 100, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'inPoint', index: 'inPoint', width: 60, editable: true, sortable: false, formatter: 'currency',
                formatoptions: {decimalSeparator: ".", decimalPlaces: 2, defaultValue: '0.00', suffix: '%'}},
            {name: 'ticketOdd', index: 'ticketOdd', width: 90, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'otherFee', index: 'otherFee', width: 90, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'inTicketSurplus', index: 'inTicketSurplus', width: 100, editable: false, sortable: false, formatter: 'currency', formatoptions: formatter.number}
        ],
        viewrecords: true,
        autowidth: true,
        //forceFit: true,
        height: 120,
        scroll: true,
        cellEdit: true,
        rownumbers: true,
        rownumWidth: 50,
        cellsubmit: 'clientArray',
        footerrow: true,
        gridComplete: completeInTicket,
        caption: '<strong>进票</strong>&nbsp;&nbsp;<a id="in_add" class="btn btn-xs btn-success">增加进票</a>' +
                 '&nbsp;<a id="in_del" class="btn btn-xs btn-danger">删除进票</a>',
        pager: '#in-grid-pager',
        afterEditCell: function (id, name, val, iRow, iCol) {
            $('#' + iRow + '_' + name).select();
            if (name === 'expireDate') {
                $("#" + iRow + "_expireDate").datepicker({
                    language: 'cn',
                    format: "yyyy-mm-dd",
                    todayBtn: "linked",
                    autoclose: true
                });
            } else if (name === 'inDate') {
                $("#" + iRow + "_inDate").datepicker({
                    language: 'cn',
                    format: "yyyy-mm-dd",
                    todayBtn: "linked",
                    autoclose: true
                });
            }
        },
        afterSaveCell: function (rowid, name, val, iRow, iCol) {
            if (name === 'inDate') {
                $(in_ticket_table).jqGrid('setRowData', rowid, {inDate: moment(val).format('YYYY-MM-DD')});
            } else if (name === 'expireDate') {
                $(in_ticket_table).jqGrid('setRowData', rowid, {expireDate: moment(val).format('YYYY-MM-DD')});
            } else if ($.inArray(name, ['ticketMoney', 'ticketOdd', 'inPoint', 'otherFee']) != -1) {
                computeInTicketSurplus(rowid, iCol);
                completeInTicket();
            }
        }
    });
    $(in_ticket_table).navGrid('#in-grid-pager', {edit: false, add: false, del: false, search: false});

    /*出票table*/
    $(out_ticket_table).jqGrid({
        datatype: "local",
        colNames: ['票号', '票面金额', '出票日期', '出票点', '贴现金额','出票实际金额', 'ID'],
        colModel: [
            {name: 'ticketNo', index: 'ticketNo', width: 100, sortable: false,
                editable: true },
            {name: 'ticketMoney', index: 'ticketMoney', width: 100,
                editable: false, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'outDate', index: 'outDate', width: 90, editable: true, sortable: false},
            {name: 'outPoint', index: 'outPoint', width: 60,
                editable: true, sortable: false, formatter: 'currency',
                formatoptions: {decimalSeparator: ".", decimalPlaces: 2, defaultValue: '0.00', suffix: '%'}},
            {name: 'discountAmount', index: 'discountAmount', width: 100,
                editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'outTicketSurplus', index: 'outTicketSurplus', width: 100,
                editable: false, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'id', index: 'id', sortable: false, hidden: true}
        ],
        viewrecords: true,
        autowidth: true,
        forceFit: true,
        height: 120,
        scroll: true,
        cellEdit: true,
        rownumbers: true,
        rownumWidth: 50,
        cellsubmit: 'clientArray',
        footerrow: true,
        gridComplete: completeOutTicket,
        pager: '#out-grid-pager',
        caption: '<strong>出票</strong>&nbsp;&nbsp;<a id="out_add" class="btn btn-xs btn-success">增加出票</a>' +
                '&nbsp;<a id="out_del" class="btn btn-xs btn-danger">删除出票</a>',
        afterEditCell: function (id, name, val, iRow, iCol) {
            $('#' + iRow + '_' + name).select();
            if (name === 'ticketNo') {
                $('#' + iRow + '_ticketNo').autocomplete({
                    minLength: 2,
                    source: RS_PATH + 'ticket/getTickets',
                    focus: function (event, ui) {
                        $('#' + iRow + '_ticketNo').val(ui.item.value);
                        return false;
                    },
                    select: function (event, ui) {
                        $(out_ticket_table).jqGrid('setRowData', iRow, {id: ui.item.id, ticketMoney: ui.item.ticketMoney})
                        return false;
                    }
                });
            } else if (name === 'outDate') {
                $("#" + iRow + "_outDate").datepicker({
                    language: 'cn',
                    format: "yyyy-mm-dd",
                    todayBtn: "linked",
                    autoclose: true
                });
            }
        },
        afterSaveCell: function (rowid, name, val, iRow, iCol) {
            if (name === 'outDate') {
                $(out_ticket_table).jqGrid('setRowData', rowid, {outDate: moment(val).format('YYYY-MM-DD')});
            } else if ($.inArray(name, ['ticketNo', 'outPoint','discountAmount']) != -1) {
                computeOutTicketSurplus(rowid, iCol);
                completeOutTicket()
            }
        }
    });
    $(out_ticket_table).navGrid('#out-grid-pager', {edit: false, add: false, del: false, search: false});

    $('#name').autocomplete({
        minLength: 1,
        source: RS_PATH + "customer/getCustomers",
        focus: function (event, ui) {
            $("#name").val(ui.item.value);
            return false;
        },
        select: function (event, ui) {
            $("#telphone").val(ui.item.telphone);
            $("#idCard").val(ui.item.idCard);
            return false;
        }
    });

    //增加进票
    $('#in_add').on('click', function (e) {
        e.preventDefault();
        in_add();
    });
    //删除进票
    $('#in_del').on('click', function (e) {
        e.preventDefault();
        var id=  $(in_ticket_table).jqGrid('getGridParam', 'selrow');
        if(!id){
            showErrors("没有选择进票记录。");
        }else{
            //var rowData = $(in_ticket_table).jqGrid('getRowData', id);
            //showInfo(rowData);
            var rs = $(in_ticket_table).jqGrid('delRowData', id);
            if(rs){
                completeInTicket();
            }
        }
    });
    //增加出票
    $('#out_add').on('click', function (e) {
        e.preventDefault();
        out_add();
    });
    //删除出票
    $('#out_del').on('click', function (e) {
        e.preventDefault();
        var id=  $(out_ticket_table).jqGrid('getGridParam', 'selrow');
        if(!id){
             showErrors("没有选择出票记录。");
        }else{
            /*var rowData = $(out_ticket_table).jqGrid('getRowData', id);
             showInfo(rowData);*/
            var rs = $(out_ticket_table).jqGrid('delRowData', id);
            if(rs){
                completeOutTicket();
            }
        }
    });
    //增加支付
    $('#addPayment').on('click', function (e) {
        e.preventDefault();
        addPayment();
    });

    //提交交易
    $('#submit').on('click', function (e) {
        e.preventDefault();
        if (!verifyTicketHeader()) {
            return;
        }
        //表单数据
        var formData = $('#validation-form').serializeArray().reduce(function (obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});
        //进票数据
        var in_datas = $(in_ticket_table).jqGrid("getRowData");
        //出票数据
        var out_datas = $(out_ticket_table).jqGrid("getRowData");
        //出票和进票必须有一项
        if (in_datas.length == 0 && out_datas.length == 0) {
            showErrors("请添加进票或出票，二者必须填写一项。");
            return
        }
        if (!verifyInTicketData(in_datas)) {
            return;
        }
        if (!verifyOutTicketData(out_datas)) {
            return;
        }
        //支付总额
        var paymentTotal = 0;
        //支付内容
        var payments = new Array();
        $('.form-inline').each(function (i) {
            var formValue = $(this).serializeObject();
            if (formValue.payMoney) {
                payments.push(formValue);
                if (formValue.payMode === 'PAY') {//支付
                    paymentTotal -= parseFloat(formValue.payMoney)
                } else if (formValue.payMode === 'COLLECT'){ //收取
                    paymentTotal += parseFloat(formValue.payMoney);
                }
            }
        });

        if (parseFloat(paymentTotal) != parseFloat($('#profit').html())) {
            showErrors("填写的支付金额与票据合计不符,请认真检查!");
            return;
        }
        var data = "{\"trade\":" + JSON.stringify(formData) + ",\"inTickets\":" + JSON.stringify(in_datas) +
            ",\"outTickets\":" + JSON.stringify(out_datas) + ",\"payments\":" + JSON.stringify(payments) + "}";

        $.ajax({
            url: RS_PATH + 'ticket/create',
            type: "POST",
            contentType: 'application/json;charset=utf-8',
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.result) {
                    showInfo("票据保存成功。");
                    setTimeout(function () {
                        window.location.href = RS_PATH + 'ticket/create';
                    }, 3000);
                } else {
                    showErrors("发生未知异常。");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                showErrors(errorThrown);
            }
        })
    });

    function verifyTicketHeader() {
        if (!$('#name').val()) {
            showErrors("请填写客户名。");
            return false;
        }
        if (!$('#telphone').val()) {
            showErrors("请填写客户电话。");
            return false;
        }
        return true;
    }

    function verifyTicketNo(value, colname) {
        if (value.length != 8) {
            return [false, "长度必须为8位"];
        }
        return [true, ""];
    }

    function verifyInTicketData(datas, title) {
        var flag = true;
        $.each(datas, function (i, data) {
            if (data.ticketNo.length != 8) {
                showErrors("进票记录中有无效票号。");
                flag = false;
                return false;
            }
            if (!data.expireDate) {
                showErrors("进票记录中有到期日期没有填写。");
                flag = false;
                return false;
            }
            if (!data.inDate) {
                showErrors("进票记录中有进票日期没有填写。");
                flag = false;
                return false;
            }
        });
        return flag;
    }

    function verifyOutTicketData(datas) {
        var flag = true;
        $.each(datas, function (i, data) {
            if (data.ticketNo.length != 8) {
                showErrors("出票记录中有无效票号。");
                flag = false;
                return false;
            }
            if (!data.outDate) {
                showErrors("出票记录中有出票日期没有填写。");
                flag = false;
                return false;
            }
        });
        return flag;
    }

    function showErrors(msg) {
        $.gritter.add({
            title: '警告',
            text: msg,
            class_name: 'gritter-error'
        });
    }

    function showInfo(msg) {
        $.gritter.add({
            title: '提示',
            text: msg,
            class_name: 'gritter-success'
        });
    }

    function in_add() {
        var rowNum = $(in_ticket_table).jqGrid('getGridParam', 'records') + 1;
        $(in_ticket_table).jqGrid('addRowData', rowNum, {inDate: moment().format('YYYY-MM-DD')}, 'last');
        $(in_ticket_table).jqGrid('editCell', rowNum, 1, true);
    }

    function out_add() {
        var rowNum = $(out_ticket_table).jqGrid('getGridParam', 'records') + 1;
        $(out_ticket_table).jqGrid('addRowData', rowNum, {outDate: moment().format('YYYY-MM-DD')}, 'last');
        $(out_ticket_table).jqGrid('editCell', rowNum, 1, true);
    }

    //统计进票
    function completeInTicket() {
        var sum_ticketMoney = $(in_ticket_table).getCol('ticketMoney', false, 'sum');
        var sum_inTicketSurplus = $(in_ticket_table).getCol('inTicketSurplus', false, 'sum');
        $(in_ticket_table).footerData('set', { ticketNo: '合计:', ticketMoney: sum_ticketMoney, inTicketSurplus: sum_inTicketSurplus });
        completeProfit();
    }

    //统计出票
    function completeOutTicket() {
        var sum_ticketMoney = $(out_ticket_table).getCol('ticketMoney', false, 'sum');
        var sum_outTicketSurplus = $(out_ticket_table).getCol('outTicketSurplus', false, 'sum');
        $(out_ticket_table).footerData('set', { ticketNo: '合计:', ticketMoney: sum_ticketMoney, outTicketSurplus: sum_outTicketSurplus });
        completeProfit();
    }

    /*
     * 统计利润
     * 合计金额=出票实际金额总额-进票实际金额总额；
     * 当合计数值为正时，需要向客户收取款项，对话框中显示“收取”；
     * 当合计数值为负时，需要向客户支付款项，对话框中显示“支付”
     * */
    function completeProfit() {
        var profit = $(out_ticket_table).getCol('outTicketSurplus', false, 'sum') - $(in_ticket_table).getCol('inTicketSurplus', false, 'sum');
        var lable = "合计";
        if (profit > 0) {
            lable = "合计(收取)";
            $('#flag').attr('class', 'green');
            $('#profit').attr('class', 'green');
        } else if (profit < 0) {
            lable = "合计(支付)";
            $('#flag').attr('class', 'red');
            $('#profit').attr('class', 'red');
        }
        $('#flag').html('<b>' + lable + '</b>');
        $('#profit').html(profit.toFixed(2));

    }

    //计算进票的票面实际金额
    function computeInTicketSurplus(rowid, iCol) {
        var ticketMoney = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 4));
        var inPoint = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 5)) / 100;
        var ticketOdd = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 6));
        var otherFee = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 7));
        //计算公式=（票面金额-票面零头）*（1-点数）-其他费用
        var ticketSurplus = (ticketMoney - ticketOdd) * (1 - inPoint) - otherFee;
        $(in_ticket_table).jqGrid('setRowData', rowid, {inTicketSurplus: parseFloat(ticketSurplus)});
    }

    //计算出票的票面实际金额
    function computeOutTicketSurplus(rowid, iCol) {
        var ticketMoney = parseFloat($(out_ticket_table).jqGrid('getCell', rowid, 2));
        var outPoint = parseFloat($(out_ticket_table).jqGrid('getCell', rowid, 4)) / 100;
        var discountAmount = parseFloat($(out_ticket_table).jqGrid('getCell', rowid, 5));
        //计算公式= 票面金额*（1-出票点数）- 贴现金额
        var ticketSurplus = ticketMoney * (1 - outPoint) - discountAmount;
        $(out_ticket_table).jqGrid('setRowData', rowid, {outTicketSurplus: parseFloat(ticketSurplus)});
    }

    function addPayment() {
        $('#payments').append(
            '<div class="space-2"></div>'
            + '<form class="form-inline">'
            + '<div class="form-group">'
            + '<select name="payMode" class="form-control">'
            + '<option value="PAY">支付</option>'
            + '<option value="COLLECT">收取</option>'
            + '</select>'
            + '</div>'
            + ' <div class="form-group">'
            + '<input type="text" name="payMoney" class="form-control input-small" placeholder="金额">'
            + '</div>'
            + '</form>'
        );
    }

/*    $('#payments').on('change', '.payType', function (e) {
        var form = $(this).parents('form');
        var form_group = $(this).parents('.form-group');
        if (this.value === '') {
            removePayMoneyElement(form);
        } else if (this.value === 'CASH' || this.value === 'TALLY') {
            addPayMoneyElement(form, form_group);
        } else {
            addTransferElement(form, form_group);
        }
    });

    function addTransferElement(form, e) {
        removePayMoneyElement(form);
        e.after(
            ' <div class="form-group">'
                + '<select name="transferType" class="form-control">'
                + '<option value="">请选择</option>'
                + '<option value="EBANK">网银</option>'
                + '<option value="POS">刷卡机</option>'
                + '</select>'
                + '</div>'
                + ' <div class="form-group">'
                + '<input type="text" name="payMoney" class="form-control input-small" placeholder="金额">'
                + '</div>'
        );
    }

    function addPayMoneyElement(form, e) {
        removePayMoneyElement(form);
        e.after(
            ' <div class="form-group">'
                + '<input type="text" name="payMoney" class="form-control input-small" placeholder="金额">'
                + '</div>')
    }

    function removePayMoneyElement(form) {
        $(form).find('select[name="transferType"]').parents('.form-group').remove();
        $(form).find('input[name="payMoney"]').parents('.form-group').remove();
    }

    function addPayment() {
        $('#payments').append(
            '<form class="form-inline">'
                + '<div class="form-group">'
                + '<select name="payMode" class="form-control">'
                + '<option value="PAY">支付</option>'
                + '<option value="COLLECT">收取</option>'
                + '</select>'
                + '</div>'
                + ' <div class="form-group">'
                + $('select[name="payType"]:first').parents('.form-group').html()
                + '</div>'
                + '</form>'
        );
    }*/
});