package br.com.makersweb.rinhabackend2025.domain.payment;

/**
 * @author anderson
 */
public interface PaymentGateway {

    Payment create(final Payment aPayment);

    Payment save(final Payment aPayment, final boolean byDefault);

}
