package org.hepx.tasksys.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hepx.tasksys.entity.Task;
import org.hepx.tasksys.entity.User;
import org.hepx.tasksys.service.TaskService;
import org.hepx.tasksys.service.UserService;
import org.hepx.tasksys.velocity.Functions;
import org.hepx.tasksys.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {

    private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @RequiresPermissions("task:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("taskList", taskService.findAll());
        return "task/list";
    }

    @RequiresPermissions(value = {"task:create","mytask:create"},logical = Logical.OR)
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(@RequestParam(defaultValue = "1")String type,Model model) {
        model.addAttribute("op", "新增");
        model.addAttribute("type",type);
        return "task/edit";
    }

    @RequiresPermissions(value = {"task:create","mytask:create"},logical = Logical.OR)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Task task, @RequestParam("type")String type, RedirectAttributes redirectAttributes) {
        if("2".equals(type)){
            task.setUserId(Functions.getCurrentUserId());
        }
        taskService.createTask(task);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        if("2".equals(type)){
            return "redirect:/task/my";

        }else{
            return "redirect:/task";
        }
    }

    @RequiresPermissions(value = {"task:update","mytask:update"},logical = Logical.OR)
    @RequestMapping(value = "/{id}/{type}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, @PathVariable("type")String type, Model model) {
        model.addAttribute("task", taskService.findOne(id));
        model.addAttribute("op", "修改");
        model.addAttribute("type",type);
        return "task/edit";
    }

    @RequiresPermissions(value = {"task:update","mytask:update"},logical = Logical.OR)
    @RequestMapping(value = "/{id}/{type}/update", method = RequestMethod.POST)
    public String update(Task task, @PathVariable("type")String type, RedirectAttributes redirectAttributes) {
        taskService.updateTask(task);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        if("1".equals(type)){
            return "redirect:/task";
        }else{
            return "redirect:/task/my";
        }
    }

    @RequiresPermissions("task:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Map delete(@PathVariable("id") Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }
    @RequiresPermissions("task:update")
    @RequestMapping(value = "/{id}/finish", method = RequestMethod.GET)
    @ResponseBody
    public Map finish(@PathVariable("id") Long id) {
        try {
            Task task=taskService.findOne(id);
            task.setStatus(Task.TaskStaus.FINISH);
            taskService.updateTask(task);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }

    @RequiresPermissions("mytask:view")
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String myList(Model model) {
        Long userId = Functions.getCurrentUserId();
        if (userId != null) {
            model.addAttribute("taskList", taskService.findByUserId(userId));
        }
        return "task/mylist";
    }


}
