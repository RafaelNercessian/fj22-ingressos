package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class DescontoTrintaPorCentoParaBancos implements Desconto {

	private BigDecimal percentualDeDesconto = new BigDecimal("0.3");

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
	}

	private BigDecimal trintaPorCentoSobre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(percentualDeDesconto);
	}

	@Override
	public String getDescricao() {
		return "Desconto Banco";
	}

}
