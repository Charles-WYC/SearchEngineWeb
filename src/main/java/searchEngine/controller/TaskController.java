package searchEngine.controller;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import searchEngine.Service.TaskService;
import searchEngine.model.Response;
import searchEngine.model.Task;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 王贇超 on 2017/9/15.
 */

@RequestMapping(value = "/Task")
@Scope("session")
@Controller
@Component
public class TaskController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody
    public Response createTask(HttpServletRequest request){
        logger.info("in create task");
        String requestUrl = request.getParameter("requestUrl");
        logger.info("request url: "+requestUrl);
        String requestMethod = request.getParameter("requestMethod");
        String referer = request.getParameter("baseSite");
        String parser = request.getParameter("parser");
        String creator = request.getParameter("creator");
        String annotation = request.getParameter("annotation");
        String status = "waiting";
        String id = UUID.randomUUID().toString();
        logger.info("task id: "+id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime= df.format(new Date());

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        requestHeader.put("Accept","application/json, text/plain, */*");
        requestHeader.put("Accept-Encoding","gzip,deflate");
        requestHeader.put("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6");
        requestHeader.put("Connection","keep-alive");
        requestHeader.put("Content-Type","application/json;charset=UTF-8");
        requestHeader.put("Referer",referer);

        Task tempTask = new Task();
        tempTask.setRequestUrl(requestUrl);
        tempTask.setAnnotation(annotation);
        tempTask.setCreateTime(createTime);
        tempTask.setCreator(creator);
        tempTask.setId(id);
        tempTask.setRequestMethod(requestMethod);
        tempTask.setRequestHeader(requestHeader);
        tempTask.setParser(parser);
        tempTask.setStatus(status);

        logger.info("begin save task");
        taskService.save(tempTask);
        JSONObject jsonTempTask = new JSONObject(tempTask);

        logger.info("begin kafka send task");
        String stringTempTask = jsonTempTask.toString();
        kafkaTemplate.send("createTask",stringTempTask);
        kafkaTemplate.flush();

        Response response = new Response();
        response.setRequestType("createTask");
        return response;
    }
}
