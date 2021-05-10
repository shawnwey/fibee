package xyz.xingwei.fibee.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 17:45
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 6881191619651716477L;

    private String id;

    private String interfaceName;

    private String methodName;

    private Class<?> returnType;

    private Class<?>[] argTypes;

    private Object[] args;

    private String version;
}
