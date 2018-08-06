package com.coffeeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class CoffeeshopApplication {

	/**
	 * Setting up default resolver
	 * @return LocaleResolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	/**
	 * Setting up custom path with all message properties file
	 * @return ReloadableResourceBundleMessageSource
	 */
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		final ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setDefaultEncoding("UTF-8");
		res.addBasenames("classpath:i18/messages/exception",
				"classpath:i18/messages/messages","classpath:i18/messages/validation");
		return res;
	}

	/**
	 * Setting up MessageSourceAccessor to use multiple message properties file
	 * @param messageSource
	 * @return
	 */
	@Bean
	public MessageSourceAccessor getMessageSourceAccessor(final MessageSource messageSource) {
		return new MessageSourceAccessor(messageSource, Locale.US);
	}

	public static void main(String[] args) {
		SpringApplication.run(CoffeeshopApplication.class, args);
	}
}
