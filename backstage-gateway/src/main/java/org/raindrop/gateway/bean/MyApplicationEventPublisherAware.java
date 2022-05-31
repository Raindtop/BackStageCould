//package org.raindrop.gateway.bean;
//
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//
//@Service
//public class MyApplicationEventPublisherAware implements ApplicationEventPublisherAware {
//    @Resource
//    private RedisRouteDefinitionRepository redisRouteDefinitionRepository;
//
//    private ApplicationEventPublisher publisher;
//
//    public void refreshRouters() {
//        this.publisher.publishEvent(new RefreshRoutesEvent(this));
//    }
//
//    /**
//     * 添加路由
//     *
//     * @param routeDefinition
//     */
//    public void addRoute(RouteDefinition routeDefinition) {
//        redisRouteDefinitionRepository.save(Mono.just(routeDefinition));
//    }
//
//    /**
//     * 删除路由
//     *
//     * @param routeDefinition
//     */
//    public void deleteRoute(RouteDefinition routeDefinition) {
//        redisRouteDefinitionRepository.delete(Mono.just(routeDefinition.getId()));
//    }
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.publisher = applicationEventPublisher;
//    }
//}
