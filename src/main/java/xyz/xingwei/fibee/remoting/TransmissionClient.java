package xyz.xingwei.fibee.remoting;


/**
 * @author xingwei.xyz
 * @date 2021/5/2 19:54
 */
public abstract class TransmissionClient<T, R>{

    public abstract R sendMessage(T request);
}
