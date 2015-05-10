/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-5-7
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */

$(function () {
    var t_money =0;

    $('#analysislist tBody tr').each(function(i){
        totalC(this);
    });
    function totalC(tr){
        var ticketMoeny =parseFloat($(tr).children('td').eq(1).text()) || 0;
        t_money += ticketMoeny;
    }
    $("#total_ticketMoney").html(t_money.toFixed(2));

/*    $("#analysislist").dataTable({
        dom: 'l<"toolbar">rtip',
        ordering: false,
        pageLength: 20,
        lengthMenu: [
            [20, 40, 80, 100, -1],
            [20, 40, 80, 100, "全部"]
        ],
        footerCallback: function (row, data, start, end, display) {
            var api = this.api(), data;
            // Remove the formatting to get integer data for summation
            var floatVal = function (i) {
                return isNaN(i) ? 0 : parseFloat(i);
            };
            // Total over all pages
            total = api
                .column(1)
                .data()
                .reduce(function (a, b) {
                    return floatVal(a) + floatVal(b);
                });
            // Total over this page
            pageTotal = api
                .column(1, { page: 'current'})
                .data()
                .reduce(function (a, b) {
                    return floatVal(a) + floatVal(b);
                }, 0);
            // Update footer
            $(api.column(1).footer()).html(
                pageTotal.toFixed(2) + ' ( ' + total.toFixed(2) + ' 总计)'
            );
        }
    });
    $("div.toolbar").html('<' +
        'div class="pull-right">' +
        '<form id="s_form" class="form-inline" method="GET" action="">' +
        '<input id="ticket_filter" name="ticketNo" data-index="0" class="input-small" type="text" placeholder="票号">' +
        '&nbsp;<select id="status_filter" name="ticketStatus" data-index="3">' +
        '<option value="">请选择</option>' +
        '<option value="在库">在库</option>' +
        '<option value="已售出">已售出</option>' +
        '</select>' +
        '&nbsp;<button id="a_search" class="btn btn-primary btn-sm">查询</button>' +
        '</form>' +
        '</div>');

    $('#a_search').on('click',function(e){
        e.preventDefault();
        $('#s_form').submit();
    });*/

/*    $('#ticket_filter').on( 'keyup click', function () {
        filterColumn($(this).attr('data-index'), $(this).val());
    });

    $('#status_filter').on( 'change', function () {
        filterColumn($(this).attr('data-index'), $(this).val());
    });

    function filterColumn ( i,val ) {
        $('#analysislist').DataTable().column( i ).search(val).draw();
    }*/
})