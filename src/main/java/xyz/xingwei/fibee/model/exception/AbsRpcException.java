package xyz.xingwei.fibee.model.exception;

import xyz.xingwei.fibee.model.enums.ErrorInfoEnum;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 23:46
 */
public abstract class AbsRpcException extends RuntimeException {
    private ErrorInfoEnum errorInfo;

    public AbsRpcException(ErrorInfoEnum errorInfo) {
        super("errorCode:[" + errorInfo.getCode() + "] " + errorInfo.getMsg());
        this.errorInfo = errorInfo;
    }

    public int getErrorCode() {
        return errorInfo.getCode();
    }

    public String getErrorMsg() {
        return errorInfo.getMsg();
    }
}
