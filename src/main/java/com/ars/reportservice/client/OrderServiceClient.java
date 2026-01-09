package com.ars.reportservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceClient {

}
