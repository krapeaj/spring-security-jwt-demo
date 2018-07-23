package spring.security.jwtdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.jwtdemo.security.tokens.PostProcessingJwt;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Spring SPEL expression. Only users with role = USER_ROLE can pass through.
    public String getUserName(Authentication authentication) {
        PostProcessingJwt token = (PostProcessingJwt) authentication;
        String userId = token.getName();
        logger.debug("USER ID: {}", userId);
        return userId;
    }
}