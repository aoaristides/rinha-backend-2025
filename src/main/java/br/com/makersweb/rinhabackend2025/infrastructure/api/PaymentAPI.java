package br.com.makersweb.rinhabackend2025.infrastructure.api;

import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.CreatePaymentProcessorRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author anderson
 */
@RequestMapping(value = "payments")
public interface PaymentAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> createPayment(@RequestBody CreatePaymentProcessorRequest input);

}
