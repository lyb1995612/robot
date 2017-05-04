package com.csjbot.robot.biz.tms.model.result;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpStatus;

public class FileResult {

    public static FileResult success() { return new FileResult(true, OK);}

    public static FileResult success(String msg) { return new FileResult(true, OK, msg);}

    public static FileResult fail(HttpStatus status) { return new FileResult(false, status);}

    public static FileResult fail(HttpStatus status, String msg) { return new FileResult(false, status, msg);}

    public static FileResult from(PreDefined pre) { return new FileResult(pre.suc(), pre.status(), pre.msg()); }

    public static FileResult from(PreDefined pre, String msg) { return new FileResult(pre.suc(), pre.status(), msg); }

    public static FileResult from(PreDefined pre, Object detail) {
        return new FileResult(pre.suc(), pre.status(), pre.msg() + ": " + detail.toString());
    }

    public enum PreDefined {
        EXISTING(false, FORBIDDEN, "存在同名文件(夹)"),
        EXISTING_DIR(false, FORBIDDEN, "存在同名文件夹"),
        EXISTING_FILE(false, FORBIDDEN, "存在同名文件"),
        SERVER_FAIL(false, INTERNAL_SERVER_ERROR, "server操作失败"),
        SERVER_EXCEPTION(false, INTERNAL_SERVER_ERROR, "server操作异常"),
        UNKNOWN(false, BAD_REQUEST, "未知错误");

        private final FileResult res;

        PreDefined(boolean suc, HttpStatus status, String msg) {
            this.res = new FileResult(suc, status, msg);
        }

        public boolean suc() {return res.isSuccess();}

        public HttpStatus status() {return res.getStatus();}

        public String msg() {return res.getMessage();}

    }

    private final boolean success;
    private final HttpStatus status;
    private String message;

    public FileResult(boolean suc, HttpStatus status) {
        this.success = suc;
        this.status = status;
    }

    public FileResult(boolean suc, HttpStatus status, String msg) {
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
