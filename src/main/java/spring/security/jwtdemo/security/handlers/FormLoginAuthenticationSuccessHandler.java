package spring.security.jwtdemo.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import spring.security.jwtdemo.dto.TokenDto;
import spring.security.jwtdemo.security.AccountContext;
import spring.security.jwtdemo.security.JwtFactory;
import spring.security.jwtdemo.security.tokens.PostAuthorizationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
This object handles authentication success.
 */

@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtFactory jwtFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override // writes JWT value as a DTO on response.
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        AccountContext accountContext = (AccountContext) token.getPrincipal();
        String tokenString = jwtFactory.generateToken(accountContext);

        processResponse(response, convertToDto(tokenString));
    }

    private TokenDto convertToDto(String token) {
        return new TokenDto(token);
    }

    private void processResponse(HttpServletResponse res, TokenDto dto) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(objectMapper.writeValueAsString(dto));
    }
}
