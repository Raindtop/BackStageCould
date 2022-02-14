package org.raindrop.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
public class RequestBodyFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerRequest serverRequest = new DefaultServerRequest(exchange);
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).map(s -> {
            return s;
        });

        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());

        headers.remove(HttpHeaders.CONTENT_LENGTH);

        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        outputMessage.getBody().switchIfEmpty(Flux.empty());
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public HttpHeaders getHeaders() {
                    long contentLength = headers.getContentLength();
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.putAll(super.getHeaders());
                    if (contentLength > 0) {
                        httpHeaders.setContentLength(contentLength);
                    } else {
                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                    }
                    return httpHeaders;
                }

                @Override
                public Flux<DataBuffer> getBody() {
                    return outputMessage.getBody();
                }
            };
            return chain.filter(exchange.mutate().request(decorator).build());
        }));
    }

//    /**
//     * 因为是WebFlux框架，如果不对body进行处理
//     *
//     * @param exchange
//     * @param chain
//     * @return
//     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        Flux<DataBuffer> dataBufferFlux = exchange.getRequest().getBody();
//        return dataBufferFlux.hasElements().flatMap(hasBody -> {
//            if (hasBody) {
//                return DataBufferUtils.join(dataBufferFlux).flatMap(dataBuffer -> {
//                    log.info("111");
//                    DataBufferUtils.retain(dataBuffer);
//                    Flux<DataBuffer> cachedFlux = Flux.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
//                    ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
//                        @Override
//                        public Flux<DataBuffer> getBody() {
//                            return cachedFlux;
//                        }
//                    };
//                    log.info("222");
//                    StringBuilder body = new StringBuilder();
//                    mutatedRequest.getBody().subscribe(dataBuffer1 -> {
//                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
//                        dataBuffer1.read(bytes);
//                        DataBufferUtils.release(dataBuffer1);
//                        body.append(new String(bytes, StandardCharsets.UTF_8));
//                    });
//                    log.info("body:" + body.toString());
//                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
//                });
//            } else {
//                log.info("Empty");
//                return chain.filter(exchange);
//            }
//        });
//    }

    @Override
    public int getOrder() {
        return -10000;
    }
}
