package app.spring;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "app.spring.controller")
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	private static final boolean CACHE_THYMELEAF_TEMPLATES = false;
	private final String UTF8_ENCODING = "UTF-8";
	
	@Bean
	public ViewResolver viewResolver() {
	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	    resolver.setTemplateEngine(templateEngine());
	    resolver.setCharacterEncoding(UTF8_ENCODING);
	    resolver.setCache(CACHE_THYMELEAF_TEMPLATES);
	    return resolver;
	}

	@Bean
	public TemplateEngine templateEngine() {
	    //this method must be defined as a bean otherwise i18n messages are not found
	    //if method defined as private TemplateEngine templateEngine() messages are not found
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setEnableSpringELCompiler(true);
	    engine.addTemplateResolver(templateResolver());

	    return engine;
	}

	private ITemplateResolver templateResolver() {
	    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	    resolver.setApplicationContext(applicationContext);
	    resolver.setPrefix("/WEB-INF/templates/");
	    resolver.setTemplateMode(TemplateMode.HTML);
	    resolver.setSuffix(".html");
	    resolver.setCacheable(CACHE_THYMELEAF_TEMPLATES);
	    resolver.setCharacterEncoding(UTF8_ENCODING);
	    return resolver;
	}   

	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("WEB-INF/messages/messages");
	    messageSource.setUseCodeAsDefaultMessage(true);
	    messageSource.setDefaultEncoding(UTF8_ENCODING);
	    messageSource.setFallbackToSystemLocale(false);
	    messageSource.setCacheSeconds((int)TimeUnit.HOURS.toSeconds(1));
	    return messageSource;
	}
	
	/*
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/messages");
		return messageSource;
	}
	
	 * @Bean public InternalResourceViewResolver
	 * getInternalResourceViewResolver() { InternalResourceViewResolver resolver
	 * = new InternalResourceViewResolver();
	 * resolver.setPrefix("/WEB-INF/views/"); resolver.setSuffix(".jsp");
	 * 
	 * return resolver; }
	 */

}
