package org.hepx.tasksys.web.controller;

import org.hepx.tasksys.entity.Reply;
import org.hepx.tasksys.entity.Task;
import org.hepx.tasksys.service.ReplyService;
import org.hepx.tasksys.service.TaskService;
import org.hepx.tasksys.velocity.Functions;
import org.hepx.tasksys.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Map;

/**
 * User: hepanxi
 * Date: 15-4-11
 * Time: 上午9:48
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {

    private static Logger logger  = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private ReplyService replyService;


    @RequestMapping(value = "/{taskId}",method = RequestMethod.GET)
    public String list(@PathVariable("taskId")Long taskId,Model model){
        model.addAttribute("task",taskService.findOne(taskId));
        model.addAttribute("replys",replyService.findByTaskId(taskId));
        return "task/reply";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String create(Reply reply,@RequestParam("status")Task.TaskStaus status, RedirectAttributes redirectAttributes){
        reply.setCreateTime(new Date());
        reply.setUserId(Functions.getCurrentUserId());
        replyService.createReply(reply);
        //回复后任务状态变为时行中。
        if(status.equals(Task.TaskStaus.CREATED)){
            Task t =taskService.findOne(reply.getTaskId());
            t.setStatus(Task.TaskStaus.DOING);
            taskService.updateTask(t);
        }
        redirectAttributes.addFlashAttribute("msg", "回复成功");
        return "redirect:/reply/"+reply.getTaskId();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(@PathVariable("replyId")Long replyId){
        try {
            replyService.deleteReply(replyId);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }
}
