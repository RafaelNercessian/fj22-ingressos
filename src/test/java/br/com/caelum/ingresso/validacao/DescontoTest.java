package br.com.caelum.ingresso.validacao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.caelum.ingresso.model.DescontoEstudante;
import br.com.caelum.ingresso.model.DescontoTrintaPorCentoParaBancos;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.SemDesconto;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {

	@Test
	public void deveConcederDescontoDe30PorCentoParaIngressosDeClientesDeBancos() {
		Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));

		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new DescontoTrintaPorCentoParaBancos());

		BigDecimal precoEsperado = new BigDecimal("22.75");

		assertEquals(precoEsperado, ingresso.getPreco());
	}

	@Test
	public void deveConcederDesconto50PorCento() {
		Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);

		Ingresso ingresso = new Ingresso(sessao, new DescontoEstudante());

		BigDecimal precoEsperado = new BigDecimal("16.25");

		assertEquals(precoEsperado, ingresso.getPreco());

	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal(){
		Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		
		BigDecimal precoEsperado = new BigDecimal("32.5");

		assertEquals(precoEsperado, ingresso.getPreco());
	}

}
