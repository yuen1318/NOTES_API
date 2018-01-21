package io.toro.NotesAppProject.pojo;

import java.util.Map;

public class ApiError {
    private int status;
    private String error;
    private String message;
    private String timeStamp;
    private String trace;


    public ApiError(int status, Map<String, Object> errrorAttributes) {
        this.status = status;
        this.error = (String) errrorAttributes.get("error");
        this.message = (String) errrorAttributes.get("message");
        this.timeStamp = errrorAttributes.get("timestamp").toString();
        this.trace = (String) errrorAttributes.get("trace");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
