package org.hepx.ticket.web;



import org.hepx.jgt.common.json.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * web层使用的Json结果集
 *
 * @author: hepx
 * @Date: 14-8-21 下午6:23
 * @Version: 1.0
 */
public class ResponseResult implements Serializable {

    public final static int RESULT_SUCCESS = 1; //成功
    public final static int RESULT_FAil = 0; //失败
    public final static String UN_KNOW_EXCEPTION = "服务器出现未知异常";
    public final static String NOT_FIND_RESOURCE = "访问的资源不存在";
    public final static String MISS_PARAM_RESOURCE = "请求参数异常";
    public final static String SUCCESS_MSG = "系统处理成功";

    //日志输出模板
    public final static String LOGGER_TEMPLATE ="error:{},params:{}。";

    private final String KEY_RESULT = "result";
    private final String KEY_MSG = "msg";

    /**
     * 数据
     */
    private Map<String, Object> dataMap;

    private ResponseResult() {
        dataMap = new HashMap<String, Object>();
    }

    /**
     * 构造成功的响应信息
     *
     * @param msg
     */
    private ResponseResult(String msg) {
        dataMap = new HashMap<String, Object>();
        setResult(RESULT_SUCCESS);
        setMsg(msg);
    }

    /**
     * 构造指定结果状态响应信息
     *
     * @param result
     */
    private ResponseResult(int result) {
        dataMap = new HashMap<String, Object>();
        setResult(result);
    }

    /**
     * 以消息和响应码来构造信息
     *
     * @param msg
     */
    private ResponseResult(int result, String msg) {
        dataMap = new HashMap<String, Object>();
        setResult(result);
        setMsg(msg);
    }

    /**
     * 返回json对象
     *
     * @return
     */
    public String toJson() {
        return JsonUtil.objectToJson(this.dataMap);
    }

    /**
     * 直接写入客户端
     *
     * @param response
     * @throws java.io.IOException
     */
    public void toJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(toJson());
    }

    /**
     * 返回MAP数据对象
     *
     * @return
     */
    public Map<String, Object> toMap() {
        return this.dataMap;
    }

    /**
     * 加入数据项到MAP
     *
     * @param name
     * @param value
     */
    public void addData(String name, Object value) {
        dataMap.put(name, value);
    }

/*    *//**
     * 添加分页数据
     * @param page
     *//*
    public void addPageResult(Page page){
        if(page!=null){
            dataMap.put("pageCount",page.getPageCount());
            dataMap.put("resultCount",page.getResultCount());
            dataMap.put("list",page.getData());
        }
    }*/

    /**
     * 合并MAP中的数据
     * @param map
     */
    public void mergerMap(Map<String,Object> map){
        dataMap.putAll(map);
    }

    /**
     * 设置默认的失败标志
     */
    public void setDefFailMsg(){
        setResult(RESULT_FAil);
        setMsg(UN_KNOW_EXCEPTION);
    }

    /**
     * 设置异常信息
     * @param e
     */
    public void setExceptionMsg(Exception e){
        setResult(RESULT_FAil);
        setMsg(e.getMessage());
    }

    /**
     * 设置默认的成功标志
     */
    public void setDefSucMsg(){
        setResult(RESULT_SUCCESS);
        setMsg(SUCCESS_MSG);
    }

    public void setResult(int result) {
        if(result==RESULT_FAil){//如果是失败的结果，清除之前加入MAP中的数据
            dataMap.clear();
        }
        dataMap.put(KEY_RESULT, result);
    }

    public void setMsg(String msg) {
        dataMap.put(KEY_MSG, msg);
    }

    public static ResponseResult buildSuccessResult(){
        ResponseResult result = new ResponseResult();
        result.setDefSucMsg();
        return  result;
    }

    public static ResponseResult buildFailResult(){
        ResponseResult result = new ResponseResult();
        result.setDefFailMsg();
        return result;
    }
}
