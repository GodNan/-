package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.entity.bdpfnew.Process;
import com.css.bdpfnew.service.ProcessService;
import com.css.bdpfnew.service.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lvmn
 * @Date 2018/11/8 15:05
 * @Description
 *  工作流程Controller
 */
@RestController
@RequestMapping(value = "/processes")
@Api(value = "工作流程", produces = "produces是什么", description = "description这个又是什么")
public class ProcessController {

    @Autowired
    private ProcessService processService;
    @Autowired
    private StateService stateService;

    @ApiOperation(value="已发证查询", notes="notes表示的什么意思", httpMethod = "get")
    @RequestMapping(method = RequestMethod.GET)
    public Message findAll(){
        List<Process> processes = processService.findAll();
        return Message.success("成功",processes);
    }

    @ApiOperation(value="获取所有任务名称", notes="所有任务名称按照processId升序排序")
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public Message findNames(){
        List<Process> processes = processService.findAll();
        List<String> names = new ArrayList<String>();
        for (Process process : processes) {
            names.add(process.getName());
        }
        return Message.success("成功",names);
    }

    @ApiOperation(value = "获取全部子流程名称",
            notes = "通过流程id获取该流程下所有子流程名称数组集合，默认查询processId=1的")
    @RequestMapping(value = "/flows/{processId}", method = RequestMethod.GET)
    public Message getFlows(@PathVariable Integer processId){
        if(processId == null){
            processId = 1;
        }
        String[] states = stateService.findStatesByProcess(processId);
        return Message.success("成功", states);
    }
}
