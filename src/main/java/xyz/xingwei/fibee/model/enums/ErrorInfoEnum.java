package xyz.xingwei.fibee.model.enums;

import lombok.Getter;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 23:39
 */
@Getter
public enum ErrorInfoEnum {
    SUCCESS(200, "success"),

    UNKNOWN(501, "unknown error"),
    SERVICE_NOT_FOUND(510, "service provider not found"),


    // 100 - 200 : net error
    NET_DEFAULT_ERROR(10, "net default error"),

    NET_CONNECT_ERROR(20, "net connecting error"),

    CHANNEL_STATE_ILLEGAL(21, "channel state illegal"),

    NET_OPERATE_INTERRUPTED(90, "net operate interrupted"),
    ;
    private final int code;

    private final String msg;

    ErrorInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
