package com.moip.core.client.creditcard;

import org.springframework.context.annotation.Bean;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class CreditCardClientConfiguration {

	@Bean
	public CreditCardClient buildCreditCardClient() {
		return Feign
				.builder()
				.client(new OkHttpClient())
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.logger(new Slf4jLogger(CreditCardClient.class))
				.logLevel(Logger.Level.FULL)
				.target(CreditCardClientMock.class, "");
	}
}
