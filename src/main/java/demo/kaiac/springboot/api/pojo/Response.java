package demo.kaiac.springboot.api.pojo;

import java.util.*;

public class Response {

    private boolean status;
    private String message;
    private List response;
    

    public Response() {
    }

    public Response(boolean status, String message, List response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response [status=" + status + ", message=" + message + "]";
    }

}