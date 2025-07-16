package br.com.makersweb.rinhabackend2025.domain.payment;

/**
 * @author anderson
 */
public interface PaymentGateway {

    Payment create(final Payment aPayment);

}
