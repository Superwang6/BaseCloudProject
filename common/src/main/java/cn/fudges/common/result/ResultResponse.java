package cn.fudges.common.result;

import java.io.Serializable;

/**
 * @author 王平远
 * @since 2024/8/20
 */

public class ResultResponse<T> implements Serializable {

    private String code = "00000";

    private String message = "success";

    private T data;

    public ResultResponse() {
    }

    public ResultResponse(T data) {
        this.data = data;
    }

    public ResultResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return "00000".equals(this.code);
    }

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(data);
    }

    public ResultResponse<T> fail(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
