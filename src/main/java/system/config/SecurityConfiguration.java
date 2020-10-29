package system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.sql.SQLSyntaxErrorException;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	
	
	private final String DOCTOR_QUERY = "select username, password, active from doctor where doctor_id =?";
	private final String USER_QUERY = "select username, password, active from user where username=?";
	//  private final String ROLES_QUERY = "select d.doctor_id, r.name from doctor d inner join role r on(d.roled_id=r.id) where d.doctor_id=?";
	  private final String ROLES_QUERY1 = "select u.username, r.name from user u inner join role r on(u.role_id=r.id) where u.username=?";
	   
	  @Override
	    protected void configure(AuthenticationManagerBuilder auth)
	            throws Exception {
	        auth.
	                jdbcAuthentication()
	                .usersByUsernameQuery(USER_QUERY)
	               .authoritiesByUsernameQuery(ROLES_QUERY1)
	                .dataSource(dataSource)
	                .passwordEncoder(bCryptPasswordEncoder);
	     
	      
	    }

	  	 @Override
		protected void configure(HttpSecurity http) throws Exception {
	  		
			http.authorizeRequests()
			 // .antMatchers("/").permitAll()
			 // .antMatchers("/index.html").permitAll()
	         .antMatchers("/login").permitAll()
	         .antMatchers("/login.html").permitAll()
	        .antMatchers("/registration").permitAll()
	           .antMatchers("/admin/**").hasAuthority("ADMIN")
	           .antMatchers("/doctor/**").hasAuthority("Doctor").anyRequest()
	          .authenticated().and().csrf().disable()
	          .formLogin()
	          .loginPage("/login").failureUrl("/login?error=true")
	          .defaultSuccessUrl("/defaultURL",true)
	          .usernameParameter("username")
	          .passwordParameter("password")
	          .and().logout()
	          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	          .logoutSuccessUrl("/").and().exceptionHandling()
	          .accessDeniedPage("/access_denied");
		
			
			 http.sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		 }
		
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                .ignoring()
	               .antMatchers("/resources/**", "/static/**", "/js/**", "/css/**", "/img/**", "/json/**");
	       
	    
	    
	    }
}