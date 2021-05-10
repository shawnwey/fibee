package xyz.xingwei.fibee;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.xingwei.fibee.model.dto.RpcRequest;
import xyz.xingwei.fibee.proxy.IProxyFactory;
import xyz.xingwei.fibee.proxy.ProxyFactory;
import xyz.xingwei.fibee.test.IPriceService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 17:20
 */
public class Fibee {
    private static Logger log = LoggerFactory.getLogger(Fibee.class);

    public static void main(String[] args) {
        log.info("============ fibee start ============");
        rpcTest();
    }

    private static void rpcTest() {
        IProxyFactory proxyFactory = new ProxyFactory();
        // 获取 client stub
        IPriceService priceService = proxyFactory.getProxy(IPriceService.class);

        Double price = priceService.getPrice(100d, 0.8f);

        System.out.println(price);
    }

    public static void deserialize () {
        RpcRequest rpcRequest = RpcRequest.builder().interfaceName("Go").methodName("go").build();
        Kryo kryo = new Kryo();
        kryo.register(RpcRequest.class);
        kryo.register(String[].class);
        kryo.setRegistrationRequired(true);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Output output = new Output(outputStream);

        kryo.writeObject(output, rpcRequest);
        byte[] bytes = output.toBytes();
        System.out.println(bytes);

        System.out.println("=============== deserialize ==============");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(inputStream);

        RpcRequest deRpcReq = kryo.readObject(input, RpcRequest.class);
        System.out.println(deRpcReq);
    }
}
