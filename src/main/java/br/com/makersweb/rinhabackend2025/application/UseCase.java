package br.com.makersweb.rinhabackend2025.application;

/**
 * @author anderson
 * @param <IN>
 * @param <OUT>
 */
public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);

}
