/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-27
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */

$(function () {

/*    var grid_data =
        [
            {id: "1", ticketNo: "12345678", ticketMoney: "10000", expireDate: "2007-12-03", certifyFee: 0, ticketOdd: "0", inPoint: "0", otherFee: "0", ticketSurplus: "10000"},
            {id: "2", ticketNo: "54545478", ticketMoney: "10000", expireDate: "2007-12-03", certifyFee: 0, ticketOdd: "0", inPoint: "0", otherFee: "500", ticketSurplus: "9500"}
        ];*/

    var in_ticket_table = "#in-ticket-table";
    var out_ticket_table = "#out-ticket-table";
    var formatter = {
        number: {decimalSeparator: ".", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0.00'}
    };
    /*进票table*/
    $(in_ticket_table).jqGrid({
        //data: grid_data,
        datatype: "local",
        colNames: ['票号', '票面金额', '到期日期', '证明费', '票面零头', '进票点', '其它费用', '票面实际金额'],
        colModel: [
            {name: 'ticketNo', index: 'ticketNo', width: 100, editable: true, sortable: false,editrules:{required:true,number:true} },
            {name: 'ticketMoney', index: 'ticketMoney', width: 100, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'expireDate', index: 'expireDate', width: 90, editable: true, sortable: false},
            {name: 'certifyFee', index: 'certifyFee', width: 90, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'ticketOdd', index: 'ticketOdd', width: 90, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'inPoint', index: 'inPoint', width: 60, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'otherFee', index: 'otherFee', width: 90, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'inTicketSurplus', index: 'inTicketSurplus', width: 100, editable: false, sortable: false, formatter: 'currency', formatoptions: formatter.number}
        ],
        viewrecords: true,
        autowidth: true,
        //forceFit: true,
        height: 150,
        scroll: true,
        cellEdit: true,
        rownumbers: true,
        rownumWidth: 50,
        cellsubmit: 'clientArray',
        footerrow: true,
        gridComplete: completeInTicket,
        caption:'进票',
        pager: '#in-grid-pager',
        afterEditCell: function (id, name, val, iRow, iCol) {
            if (name == 'expireDate') {
                $("#" + iRow + "_expireDate").datepicker({language:'cn',format: "yyyy-mm-dd"});
            }
        },
        afterSaveCell: function (rowid, name, val, iRow, iCol) {
            if($.inArray(name,['ticketMoney','certifyFee','ticketOdd','inPoint','otherFee'])!=-1){
                computeInTicketSurplus(rowid,iCol);
                completeInTicket();
            }
        }
    });
    $(in_ticket_table).navGrid('#in-grid-pager',{edit:false,add:false,del:false,search:false})
        .navButtonAdd('#in-grid-pager',{
            caption:"增加进票",
            buttonicon:"ui-icon-add",
            onClickButton: function(){
                in_add();
            },
            position:"last"
        })
        .navButtonAdd('#in-grid-pager',{
            caption:"删除",
            buttonicon:"ui-icon-del",
            onClickButton: function(){
                //alert("Deleting Row");
            },
            position:"last"
        });

    /*出票table*/
    $(out_ticket_table).jqGrid({
        datatype: "local",
        colNames: ['票号', '票面金额', '出票点', '出票实际金额'],
        colModel: [
            {name: 'ticketNo', index: 'ticketNo', width: 100, editable: true, sortable: false,editrules:{required:true,number:true} },
            {name: 'ticketMoney', index: 'ticketMoney', width: 100, editable: false, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'outPoint', index: 'outPoint', width: 60, editable: true, sortable: false, formatter: 'currency', formatoptions: formatter.number},
            {name: 'outTicketSurplus', index: 'outTicketSurplus', width: 100, editable: false, sortable: false, formatter: 'currency', formatoptions: formatter.number}
        ],
        viewrecords: true,
        autowidth: true,
        forceFit: true,
        height: 150,
        scroll: true,
        cellEdit: true,
        rownumbers: true,
        rownumWidth: 50,
        cellsubmit: 'clientArray',
        footerrow: true,
        gridComplete: completeOutTicket,
        pager: '#out-grid-pager',
        caption:'出票',
        afterSaveCell: function (rowid, name, val, iRow, iCol) {
            if($.inArray(name,['ticketNo','outPoint'])!=-1){
                computeOutTicketSurplus(rowid,iCol);
                completeOutTicket()
            }
        }
    });
    $(out_ticket_table).navGrid('#out-grid-pager',{edit:false,add:false,del:false,search:false})
        .navButtonAdd('#out-grid-pager',{
            caption:"增加出票",
            buttonicon:"ui-icon-add",
            onClickButton: function(){
                out_add();
            },
            position:"last"
        })
        .navButtonAdd('#out-grid-pager',{
            caption:"删除",
            buttonicon:"ui-icon-del",
            onClickButton: function(){
                //alert("Deleting Row");
            },
            position:"last"
        });

    $('#submit').on('click',function(e){
        e.preventDefault();
        if(!verifyTicketHeader()){
           return ;
        }
        //表单数据
        var formData=$('#validation-form').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});
        //进票数据
        var in_datas=$(in_ticket_table).jqGrid("getRowData");
        if(in_datas.length==0){
            showErrors("请添加进票。");
            return
        }
        //出票数据
        var out_datas=$(out_ticket_table).jqGrid("getRowData");
        var data = "{\"trade\":" + JSON.stringify(formData) + ",\"inTickets\":" + JSON.stringify(in_datas) + ",\"outTickets\":" + JSON.stringify(out_datas) + "}";
        $.ajax({
            url:RS_PATH+'ticket/create',
            type: "POST",
            contentType: 'application/json;charset=utf-8',
            data: data,
            dataType: "json",
            success: function (data) {
                if(data.result){
                    showInfo("票据保存成功。");
                    setTimeout(function(){
                        window.location.href = RS_PATH+'ticket/create';
                    },3000);
                }else{
                    showErrors("发生未知异常。");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                showErrors(errorThrown);
            }
        })
    });

    function verifyTicketHeader(){
        if(!$('#name').val()){
            showErrors("请填写客户名。");
            return false;
        }
        if(!$('#telphone').val()){
            showErrors("请填写客户电话。");
            return false;
        }
        return true;
    }

    function showErrors(msg){
        $.gritter.add({
            title: '警告',
            text: msg,
            class_name: 'gritter-error'
        });
    }
    function showInfo(msg){
        $.gritter.add({
            title: '提示',
            text: msg,
            class_name: 'gritter-success'
        });
    }
    function in_add(){
        var rowNum=$(in_ticket_table).jqGrid('getGridParam','records')+1;
        $(in_ticket_table).jqGrid('addRowData',rowNum,{},'last');
        $(in_ticket_table).jqGrid('editCell',rowNum,1,true);
    }

    function out_add(){
        var rowNum=$(out_ticket_table).jqGrid('getGridParam','records')+1;
        $(out_ticket_table).jqGrid('addRowData',rowNum,{ticketMoney:100},'last');
        $(out_ticket_table).jqGrid('editCell',rowNum,1,true);
    }

    //统计进票
    function completeInTicket(){
        var sum_ticketMoney=$(in_ticket_table).getCol('ticketMoney',false,'sum');
        var sum_inTicketSurplus=$(in_ticket_table).getCol('inTicketSurplus',false,'sum');
        $(in_ticket_table).footerData('set', { ticketNo: '合计:', ticketMoney: sum_ticketMoney, inTicketSurplus: sum_inTicketSurplus });
    }

    //统计出票
    function completeOutTicket(){
        var sum_ticketMoney=$(out_ticket_table).getCol('ticketMoney',false,'sum');
        var sum_outTicketSurplus=$(out_ticket_table).getCol('outTicketSurplus',false,'sum');
        $(out_ticket_table).footerData('set', { ticketNo: '合计:', ticketMoney: sum_ticketMoney, outTicketSurplus: sum_outTicketSurplus });
    }

    //计算进票的票面实际金额
    function computeInTicketSurplus(rowid, iCol) {
        var ticketMoney = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 2));
        var certifyFee = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 4));
        var ticketOdd = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 5));
        var inPoint = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 6));
        var otherFee = parseFloat($(in_ticket_table).jqGrid('getCell', rowid, 7));
        //计算公式=（票面金额-票面零头）*（1-点数）-证明费-其他
        var ticketSurplus = (ticketMoney - ticketOdd) * (1 - inPoint) - certifyFee - otherFee;
        $(in_ticket_table).jqGrid('setRowData', rowid, {inTicketSurplus: parseFloat(ticketSurplus)});
    }

    //计算进票的票面实际金额
    function computeOutTicketSurplus(rowid, iCol) {
        var ticketMoney = parseFloat($(out_ticket_table).jqGrid('getCell', rowid, 2));
        var outPoint = parseFloat($(out_ticket_table).jqGrid('getCell', rowid, 3));
        //计算公式= 票面金额*（1-出票点数）
        var ticketSurplus = ticketMoney * (1 - outPoint);
        $(out_ticket_table).jqGrid('setRowData', rowid, {outTicketSurplus: parseFloat(ticketSurplus)});
    }
})