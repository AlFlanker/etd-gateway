package com.otr.portal.gateway.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Configuration
@EnableConfigurationProperties(EtdGatewayProperties.class)
public class GatewayRoutesConfiguration {

    private final ApplicationEventPublisher eventPublisher;

    public GatewayRoutesConfiguration(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Bean
    @RefreshScope
    public RouteLocator etdRouteLocator(RouteLocatorBuilder builder, EtdGatewayProperties properties) {
        return builder.routes()
                .route("etd-route", route -> route
                        .path("/etd/**")
                        .filters(filters -> filters.addRequestHeader(
                                properties.getGatewayHeaderName(),
                                properties.getGatewayHeaderValue()))
                        .uri(properties.getBaseUri().toString()))
                .build();
    }

    @EventListener(RefreshScopeRefreshedEvent.class)
    public void refreshRoutes() {
        eventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }
}
