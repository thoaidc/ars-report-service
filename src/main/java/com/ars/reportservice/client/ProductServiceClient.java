package com.ars.reportservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceClient {
}
