package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalanceEndpointRouter implements HttpEndpointRouter {

    public static final LoadBalanceEndpointRouter ROUTER = new LoadBalanceEndpointRouter();

    private AtomicInteger count = new AtomicInteger(0);

    private LoadBalanceEndpointRouter() {
    }

    public LoadBalanceEndpointRouter getInstance() {
        return ROUTER;
    }

    @Override
    public String route(List<String> urls) {
        // 负载均衡
        int i = count.getAndIncrement();
        String url = urls.get(i % urls.size());
        System.out.println("count:" + i + ",url:" + url);
        return url;
    }
}
