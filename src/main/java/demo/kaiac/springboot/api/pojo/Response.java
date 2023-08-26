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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getResponse() {
        return response;
    }

    public void setResponse(List response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response [status=" + status + ", message=" + message + "]";
    }

}