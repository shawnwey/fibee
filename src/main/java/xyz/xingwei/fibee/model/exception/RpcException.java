package xyz.xingwei.fibee.model.exception;

import xyz.xingwei.fibee.model.enums.ErrorInfoEnum;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 23:36
 */
public class RpcException extends AbsRpcException {
    private ErrorInfoEnum errorInfo;


    public RpcException(ErrorInfoEnum errorInfo) {
        super(errorInfo);
    }
}
