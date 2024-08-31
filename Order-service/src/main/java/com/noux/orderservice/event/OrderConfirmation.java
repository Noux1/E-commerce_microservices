package com.noux.orderservice.event;


import com.noux.orderservice.enums.PaymentMethod;
import com.noux.orderservice.proxy.Client.ClientResponse;
import com.noux.orderservice.proxy.Product.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderConfirmation {
            String orderReference;
            BigDecimal totalAmount;
            PaymentMethod paymentMethod;
            ClientResponse client ;
            List<PurchaseResponse> products;
}
