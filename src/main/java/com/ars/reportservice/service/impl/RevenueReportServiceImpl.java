package com.ars.reportservice.service.impl;

import com.ars.reportservice.client.OrderServiceClient;
import com.ars.reportservice.client.ProductServiceClient;
import com.ars.reportservice.dto.request.RevenueFilterRequest;
import com.ars.reportservice.service.RevenueReportService;
import com.dct.model.dto.response.BaseResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RevenueReportServiceImpl implements RevenueReportService {
    private final ProductServiceClient productServiceClient;
    private final OrderServiceClient orderServiceClient;

    public RevenueReportServiceImpl(ProductServiceClient productServiceClient, OrderServiceClient orderServiceClient) {
        this.productServiceClient = productServiceClient;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public BaseResponseDTO getRevenueReport(RevenueFilterRequest request) {
        return null;
    }
}
