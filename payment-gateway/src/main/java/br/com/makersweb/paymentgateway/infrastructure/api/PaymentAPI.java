package br.com.makersweb.paymentgateway.infrastructure.api;

import br.com.makersweb.paymentgateway.domain.models.CreatePaymentRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aaristides
 */
@RequestMapping(value = "payments")
public interface PaymentAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> createPayment(@RequestBody @Validated CreatePaymentRequest input);

    @PostMapping("/purge")
    ResponseEntity<Void> purge();

}
