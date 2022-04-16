package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "web")
public class WebController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello World";
	}

	@GetMapping(value = "/hellobean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Amod");
	}

	@GetMapping(value = "/hello/path/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable(value = "name") String name) {
		return new HelloWorldBean(String.format("Hello %s", name));
	}

	@GetMapping(value = "/hello/internationalised")
	public String helloInternationalised(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		// return "Hello World";
		return messageSource.getMessage("good.morning.message", null, "Default Message",
				// locale);
				LocaleContextHolder.getLocale());
	}

}
