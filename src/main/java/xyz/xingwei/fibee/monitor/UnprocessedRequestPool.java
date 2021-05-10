package xyz.xingwei.fibee.monitor;

import lombok.extern.slf4j.Slf4j;
import xyz.xingwei.fibee.model.dto.RpcResponse;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xingwei.xyz
 * @date 2021/5/4 15:58
 */
@Slf4j
public class UnprocessedRequestPool {
    private static final UnprocessedRequestPool unprocessedRequestPool = new UnprocessedRequestPool();

    private UnprocessedRequestPool() {
        futureMap = new ConcurrentHashMap<>();
    }

    private final Map<String, CompletableFuture<RpcResponse<?>>> futureMap;

    public CompletableFuture<RpcResponse<?>> put(String requestId, CompletableFuture<RpcResponse<?>> completableFuture) {
        return futureMap.put(requestId, completableFuture);
    }

    public Boolean process(RpcResponse<?> response) {
        CompletableFuture<RpcResponse<?>> completableFuture = futureMap.remove(response.getRequestId());
        if (completableFuture == null) {
            String requestId = Optional.ofNullable(response.getRequestId()).orElse("null!");
            log.error("requestId[{}] can not be processed, it's completableFuture is null", requestId);
            throw new IllegalStateException();
        }
        return completableFuture.complete(response);
    }
}
