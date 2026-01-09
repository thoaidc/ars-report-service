package com.ars.reportservice.client;

import com.ars.reportservice.dto.request.RevenueReportFilter;
import com.dct.model.dto.response.BaseResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceClient {
    @GetMapping("/api/p/v1/orders/reports/revenues")
    BaseResponseDTO getRevenueReport(@SpringQueryMap RevenueReportFilter requestDTO);
}
