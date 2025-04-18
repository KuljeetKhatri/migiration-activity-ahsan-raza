package com.zindigi.account_migration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.zindigi.account_migration.util.AccountMigrationFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableEncryptableProperties
@EnableAsync
@EnableTransactionManagement
@OpenAPIDefinition(info = @Info(title = "Account" , version = "1.0", description = "Account Layer"))

public class AccountMigrationApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AccountMigrationApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AccountMigrationApplication.class);
	}

	@Bean
	public FilterRegistrationBean<AccountMigrationFilter> headerRemovalFilter() {
		FilterRegistrationBean<AccountMigrationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AccountMigrationFilter());
		registrationBean.addUrlPatterns("/*"); // Apply the filter to all URLs
		return registrationBean;
	}
}
