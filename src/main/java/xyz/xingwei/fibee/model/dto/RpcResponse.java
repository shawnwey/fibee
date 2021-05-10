package xyz.xingwei.fibee.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.xingwei.fibee.model.enums.ErrorInfoEnum;

import java.io.Serializable;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 19:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcResponse<T> implements Serializable {
    private static final long serialVersionUID = -6876830479878478305L;

    private String requestId;

    private Integer code;

    private String msg;

    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(ErrorInfoEnum.SUCCESS.getCode());
        response.setMsg(ErrorInfoEnum.SUCCESS.getMsg());
        response.setRequestId(requestId);
        if (null != data) {
            response.setData(data);
        }
        return response;
    }

    public static <T> RpcResponse<T> fail(ErrorInfoEnum errorInfoEnum) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(errorInfoEnum.getCode());
        response.setMsg(errorInfoEnum.getMsg());
        return response;
    }
}
