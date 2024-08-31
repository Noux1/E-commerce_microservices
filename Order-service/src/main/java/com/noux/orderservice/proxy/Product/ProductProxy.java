package com.noux.orderservice.proxy.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductProxy {

//
//    @Value("${application.config.product-service-url}")
//    private String productUrl;
//    private final RestTemplate restTemplate;
//
//    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
//
//        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
//        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
//        };
//        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
//                productUrl + "/purchase",
//                POST,
//                requestEntity,
//                responseType
//        );
//
//        return  responseEntity.getBody();
//    }
//
//    // if I need to calculate the total amount on the server side
//    public BigDecimal getProductPrice(Long productId) {
//        String url = productUrl + "/products/" + productId + "/price";
//        ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(
//                url,
//                GET,
//                null,
//                BigDecimal.class
//        );
//
//        return responseEntity.getBody();
//    }


    @Value("${application.config.product-service-url}")
    private String productUrl;

    private final WebClient.Builder webClientBuilder;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        WebClient webClient = webClientBuilder.baseUrl(productUrl).build();

        return webClient.post()
                .uri("/purchase")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestBody), new ParameterizedTypeReference<List<PurchaseRequest>>() {})
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PurchaseResponse>>() {})
                .block(); // blocking call to convert Mono to List
    }

    public BigDecimal getProductPrice(Long productId) {
        WebClient webClient = webClientBuilder.baseUrl(productUrl).build();

        return webClient.get()
                .uri("/products/{id}/price", productId)
                .retrieve()
                .bodyToMono(BigDecimal.class)
                .block(); // blocking call to convert Mono to BigDecimal
    }

}
