package com.csjbot.robot.biz.tms.model;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpStatus;

public class Result {

    public static Result success() { return new Result(true, OK);}

    public static Result success(String msg) { return new Result(true, OK, msg);}

    public static Result fail(HttpStatus status) { return new Result(false, status);}

    public static Result fail(HttpStatus status, String msg) { return new Result(false, status, msg);}

    public static Result from(PreDefined pre) { return new Result(pre.suc(), pre.status(), pre.msg()); }

    public static Result from(PreDefined pre, String msg) { return new Result(pre.suc(), pre.status(), msg); }

    public static Result from(PreDefined pre, Object detail) {
        return new Result(pre.suc(), pre.status(), pre.msg() + ": " + detail.toString());
    }

    public enum PreDefined {
        EXISTING(false, FORBIDDEN, "存在同名文件(夹)"),
        EXISTING_DIR(false, FORBIDDEN, "存在同名文件夹"),
        EXISTING_FILE(false, FORBIDDEN, "存在同名文件"),
        SERVER_FAIL(false, INTERNAL_SERVER_ERROR, "server操作失败"),
        SERVER_EXCEPTION(false, INTERNAL_SERVER_ERROR, "server操作异常"),
        UNKNOWN(false, BAD_REQUEST, "未知错误");

        private final Result res;

        PreDefined(boolean suc, HttpStatus status, String msg) {
            this.res = new Result(suc, status, msg);
        }

        public boolean suc() {return res.isSuccess();}

        public HttpStatus status() {return res.getStatus();}

        public String msg() {return res.getMessage();}

    }

    private final boolean success;
    private final HttpStatus status;
    private String message;

    public Result(boolean suc, HttpStatus status) {
        this.success = suc;
        this.status = status;
    }

    public Result(boolean suc, HttpStatus status, String msg) {
        this.success = suc;
        this.status = status;
        this.message = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
}
