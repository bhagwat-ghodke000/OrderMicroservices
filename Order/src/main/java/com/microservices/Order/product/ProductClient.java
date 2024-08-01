package com.microservices.Order.product;

import com.microservices.Order.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductClient {

    @Value("${http://localhost:8222/api/v1/products}")
    private String productUrl;

    @Autowired
    private RestTemplate restTemplate;


    public List<PurchaseResponseDto> purchaseProduct(List<PurchaseRequest> request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(request, headers);

        ParameterizedTypeReference<List<PurchaseResponseDto>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<PurchaseResponseDto>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        if(responseEntity.getStatusCode().isError()){

            throw new BusinessException("An error occurred in the product purchase"+responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}

