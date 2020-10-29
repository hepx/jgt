package org.hepx.jgt.common.web.resolver;

import org.apache.commons.lang3.StringUtils;
import org.hepx.jgt.common.datatable.Column;
import org.hepx.jgt.common.datatable.Order;
import org.hepx.jgt.common.datatable.RequestParams;
import org.hepx.jgt.common.datatable.Search;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 专门用来解析，jquery datatable与后台交互传参接收转换
 * 前台传参格式
 * draw:1
 * columns[0][data]:webName
 * columns[0][name]:
 * columns[0][searchable]:true
 * columns[0][orderable]:true
 * columns[0][search][value]:
 * columns[0][search][regex]:false
 * columns[1][data]:webUrl
 * columns[1][name]:
 * columns[1][searchable]:true
 * columns[1][orderable]:true
 * columns[1][search][value]:
 * columns[1][search][regex]:false
 * columns[2][data]:webType
 * columns[2][name]:
 * columns[2][searchable]:true
 * columns[2][orderable]:true
 * columns[2][search][value]:
 * columns[2][search][regex]:false
 * columns[3][data]:filing
 * columns[3][name]:
 * columns[3][searchable]:true
 * columns[3][orderable]:true
 * columns[3][search][value]:
 * columns[3][search][regex]:false
 * order[0][column]:0
 * order[0][dir]:asc
 * start:0
 * length:20
 * search[value]:
 * search[regex]:false
 *
 * @author: Koala
 * @Date: 14-8-28 下午5:07
 * @Version: 1.0
 */
public class DataTableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return RequestParams.class.equals(parameter.getParameterType());
    }

    @Override
    public RequestParams resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        RequestParams requestParams =new RequestParams();

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Integer draw=Integer.parseInt(request.getParameter("draw"));
        requestParams.setDraw(draw);
        Integer start=Integer.parseInt(request.getParameter("start"));
        requestParams.setStart(start);
        Integer length=Integer.parseInt(request.getParameter("length"));
        requestParams.setLength(length);
        requestParams.setSearch(parseSearch(request));
        requestParams.setOrders(parseOrders(request));
        requestParams.setColumns(parseColumns(request));
        requestParams.setPage(start/length);
        return requestParams;
    }

    /**
     * 解析 查询参数
     * @param request
     * @return
     */
    private Search parseSearch(HttpServletRequest request){
        Map<String,Object> search_map= WebUtils.getParametersStartingWith(request,"search");
        if(search_map!=null){
            Search s=new Search();
            s.setRegex(Boolean.parseBoolean((String) search_map.get("[regex]")));
            s.setValue((String) search_map.get("[value]"));
            return s;
        }else{
            return null;
        }
    }

    /**
     * 解析列参数
     * @param request
     * @return
     */
    private List<Column> parseColumns(HttpServletRequest request){
        List<Column> columns =null;
        String param= "columns";
        Map<String,Object> column_map=WebUtils.getParametersStartingWith(request,param);
        if(column_map!=null && column_map.size()>0){
            columns=new ArrayList<Column>();
            Set<String> keys=column_map.keySet();
            int index=getMaxIndex(keys);
            for(int i=0;i<=index;i++){
                Map<String,Object> index_map=WebUtils.getParametersStartingWith(request,param+"["+i+"]");
                if(index_map!=null){
                    Column c=new Column();
                    c.setName((String)index_map.get("[name]"));
                    c.setData((String) index_map.get("[data]"));
                    c.setOrderable(Boolean.parseBoolean((String) index_map.get("[orderable]")));
                    c.setSearchable(Boolean.parseBoolean((String) index_map.get("[searchable]")));
                    columns.add(c);
                }
            }
        }
        return columns;
    }

    /**
     * 解析排序参数
     * @param request
     * @return
     */
    private List<Order> parseOrders(HttpServletRequest request){
        List<Order> orders=null;
        String param="order";
        Map<String,Object> order_map=WebUtils.getParametersStartingWith(request,param);
        if(order_map!=null && order_map.size()>0){
            orders=new ArrayList<Order>();
            Set<String> keys=order_map.keySet();
            int index=getMaxIndex(keys);
            for(int i=0;i<=index;i++){
                Map<String,Object> index_map=WebUtils.getParametersStartingWith(request,param+"["+i+"]");
                if(index_map!=null){
                    Order o=new Order();
                    o.setColumn(Integer.parseInt((String) index_map.get("[column]")));
                    o.setDir((String) index_map.get("[dir]"));
                    orders.add(o);
                }
            }
        }
        return orders;
    }

    /**
     * 获得参数列表中的最大值
     * @param keys
     * @return
     */
    private int getMaxIndex(Set<String>keys){
        Pattern pattern = Pattern.compile("\\[(\\d+?)\\]");
        List<Integer>arrs=new ArrayList<Integer>();
        for(String s:keys){
            Matcher matcher = pattern.matcher(s);
            matcher.find();
            String v=matcher.group(1);
            if(StringUtils.isNotEmpty(v)){
                arrs.add(Integer.parseInt(v));
            }
        }
        return Collections.max(arrs);
    }

}
