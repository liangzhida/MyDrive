package com.example.mydrive.bean;

import java.util.List;

public class Message {

    /**
     * reason : 错误的短信模板ID,请通过后台确认!!!
     * result : []
     * error_code : 205402
     */

    private String reason;
    private int error_code;
    private List<?> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }
}
