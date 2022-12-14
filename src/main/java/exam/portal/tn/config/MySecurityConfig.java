package exam.portal.tn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import exam.portal.tn.services.UserDetailsIServiceImpl;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private UserDetailsIServiceImpl userDetailsImpl;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.userDetailsService(this.userDetailsImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http
				.csrf()
				.disable()
				.cors()
				.disable()
				.authorizeRequests()
				.antMatchers("/generate-token","/api/addCommentToProduct/{idProduct}","/user/","/user/users","/user/Imgusers/{id}","/user/editUserImag/{id}", "/product/","/product/GetAll","/product/DelPro/{productId}","/product/GetOne/{productId}","/product/prodIma","/product/Imgproduct/{productId}","/product/UpdatePro","/product/products/export/excel","/para/parametres/{id}","/para/parametres","/product/Jasper/report","/product/findAllProducts","/product/findProductById/{id}","/api/findTagsForProduct/{idProduct}","/api/findCommentsForProduct/{idProduct}","/product/findByName/{name}","/user/findByUsername/{username}","/api/findProductsForTag/{idTag}","/api/findAllCategories","/user/addUser","/api/ImgCart/{idCart}","/product/editProduct/{productId}","/api/orders/orders","/user/Imguserss/{username}").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
			
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
				http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
