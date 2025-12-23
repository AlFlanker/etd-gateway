package com.otr.portal.gateway.config;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gateway.etd")
public class EtdGatewayProperties {

    private URI baseUri = URI.create("http://localhost:8081");
    private String gatewayHeaderName = "X-Api-Gateway";
    private String gatewayHeaderValue = "etd-gateway";

    public URI getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(URI baseUri) {
        this.baseUri = baseUri;
    }

    public String getGatewayHeaderName() {
        return gatewayHeaderName;
    }

    public void setGatewayHeaderName(String gatewayHeaderName) {
        this.gatewayHeaderName = gatewayHeaderName;
    }

    public String getGatewayHeaderValue() {
        return gatewayHeaderValue;
    }

    public void setGatewayHeaderValue(String gatewayHeaderValue) {
        this.gatewayHeaderValue = gatewayHeaderValue;
    }
}
