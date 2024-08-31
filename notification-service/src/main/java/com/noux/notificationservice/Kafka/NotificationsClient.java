package com.noux.notificationservice.Kafka;

import com.noux.notificationservice.Email.EmailService;
import com.noux.notificationservice.Kafka.order.OrderConfirmation;
import com.noux.notificationservice.Kafka.payment.PaymentConfirmation;
import com.noux.notificationservice.Notification.Notification;
import com.noux.notificationservice.Notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.noux.notificationservice.Notification.NotificationType.ORDER_CONFIRMATION;
import static com.noux.notificationservice.Notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsClient {

    private final NotificationRepository repository;
    private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException, jakarta.mail.MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        var customerName = paymentConfirmation.getClientFirstname() + " " + paymentConfirmation.getClientLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getClientEmail(),
                customerName,
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException, jakarta.mail.MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        var customerName = orderConfirmation.getClient().getFirstname()+ " " + orderConfirmation.getClient().getLastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getClient().getEmail(),
                customerName,
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }
}