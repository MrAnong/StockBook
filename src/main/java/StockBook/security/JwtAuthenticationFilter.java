package StockBook.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import StockBook.dto.responses.UsersResponse;
import StockBook.dto.utilities.UsersDetails;
import StockBook.service.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	 @Autowired
	    private UsersService usersService; // Inject your UserService for loading user details

	    private final String secretKey = "your_secret_key"; // Store securely

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	        String token = request.getHeader("Authorization");

	        if (token != null && token.startsWith("Bearer ")) {
	            token = token.substring(7); // Remove "Bearer " prefix
	            try {
	                Claims claims = Jwts.parser()
	                        .setSigningKey(secretKey)
	                        .parseClaimsJws(token)
	                        .getBody();

	                String username = claims.getSubject(); // Extract username from claims

	                // Create a UserDetails object for authentication
	                UsersDetails userDetails = new UsersDetails();
	                
	                userDetails.setEmail_PhoneNumber_Username(username); // Set the identifier from claims
	                userDetails.setPassword(""); // Password will not be set here as it's not needed for JWT validation

	                UsersResponse userResponse = usersService.login(userDetails); // Use your custom login method

	                if (userResponse != null && userResponse.getUser() != null) {
	                    // Create an authentication token with user details
	                    UsernamePasswordAuthenticationToken authentication =
	                            new UsernamePasswordAuthenticationToken(userResponse.getUser().getUsername(), null,
	                            AuthorityUtils.createAuthorityList(userResponse.getUser().getRole().getName())); // Assuming getRole() returns Role object
	                    SecurityContextHolder.getContext().setAuthentication(authentication);
	                }
	            } catch (Exception e) {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
	                return; // Stop further processing
	            }
	        }
	        chain.doFilter(request, response);
	    }
}