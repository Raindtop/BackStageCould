package org.raindrop.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        log.info("<-------------Loading Route Info. Start----------->");
        List<RouteDefinition> routeDefinitions = JSONObject.parseArray((String)redisTemplate.opsForValue().get("GATEWAY"), RouteDefinition.class);
        log.info("<-------------Loading Route Info. End----------->");
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(obj ->{
            List<RouteDefinition> routeDefinitions = JSONObject.parseArray((String)redisTemplate.opsForValue().get("GATEWAY"), RouteDefinition.class);
            routeDefinitions.add(obj);
            redisTemplate.opsForValue().set("GATEWAY", JSONObject.toJSONString(routeDefinitions), 3600, TimeUnit.SECONDS);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id ->{
            List<RouteDefinition> oldRoute = JSONObject.parseArray((String)redisTemplate.opsForValue().get("GATEWAY"), RouteDefinition.class);
            List<RouteDefinition> newRoute = oldRoute.stream().filter(route -> !route.getId().equals(id)).collect(Collectors.toList());
            redisTemplate.opsForValue().set("GATEWAY", JSONObject.toJSONString(newRoute), 3600, TimeUnit.SECONDS);
            return Mono.empty();
        });
    }

    public static void main(String[] args) {
//        Mono.just("");
//        Mono.fromCallable(() -> "9999").subscribe(System.out::println);
//        Flux.generate(sink -> {
//            sink.next("Echo");
//            sink.complete();
//        }).subscribe(System.out::println);

        Flux.interval(Duration.of(1, ChronoUnit.MILLIS)).subscribe(System.out::println);
        System.out.println("------");
//        Flux.range(1, 91).buffer(11).takeWhile(i -> i.size() > 10).subscribe(System.out::println);
//        Flux.range(1, 10).map(x -> x*x).subscribe(System.out::println);
//
//        Flux.merge(Flux.interval(
//                Duration.of(0, ChronoUnit.MILLIS),
//                Duration.of(100, ChronoUnit.MILLIS)).take(2),
//                Flux.interval(
//                        Duration.of(50, ChronoUnit.MILLIS),
//                        Duration.of(100, ChronoUnit.MILLIS)).take(2))
//                .toStream()
//                .forEach(System.out::println);
//        System.out.println("---");
//        Flux.mergeSequential(Flux.interval(
//                Duration.of(0, ChronoUnit.MILLIS),
//                Duration.of(100, ChronoUnit.MILLIS)).take(2),
//                Flux.interval(
//                        Duration.of(50, ChronoUnit.MILLIS),
//                        Duration.of(100, ChronoUnit.MILLIS)).take(2))
//                .toStream()
//                .forEach(System.out::println);

    }
}
