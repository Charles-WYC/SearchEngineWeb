package searchEngine.model;

import java.util.Map;

/**
 * Created by 王贇超 on 2017/9/12.
 */
//@Entity
//@Table(name = "s")
public class Task{

    private String RequestMethod;
    private Map<String, String> requestHeader;

    private String id;

    private String requestUrl;

//    @Column(name = "parser", nullable = false)
    private String parser;

//    @Column(name = "Time", nullable = false)
    private String createTime;
    private String beginTime;
    private String finishTime;

//    @Column(name = "Creator", nullable = false)
    private String creator;

//    @Column(name ="status", nullable = false)
    private String status;

//    @Column(name ="annotation", nullable = false)
    private String annotation;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getParser() {
        return parser;
    }

    public void setParser(String parser) {
        this.parser = parser;
    }

    public String getRequestMethod() {
        return RequestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        RequestMethod = requestMethod;
    }

    public Map<String, String> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(Map<String, String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
