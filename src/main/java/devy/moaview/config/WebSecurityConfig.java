package devy.moaview.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * Spring Security 설정 클래스
 *
 * @author devy
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/** 기본 관리자 계정 */
	@Value("${devy.moaview.admin.id}")
	private String DEFAULT_ADMIN_USER;

	/** 기본 관리자 비밀번호 */
	@Value("${devy.moaview.admin.pw}")
	private String DEFAULT_ADMIN_PASSWORD;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser(DEFAULT_ADMIN_USER)
				.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(DEFAULT_ADMIN_PASSWORD))
				.roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/img/**");
	}
}
