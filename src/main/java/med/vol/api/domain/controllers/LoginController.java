package med.vol.api.domain.controllers;

import med.vol.api.domain.dtos.request.LoginRequest;
import med.vol.api.domain.dtos.response.TokenAuthenticationResponse;
import med.vol.api.domain.entities.User;
import med.vol.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.login(), loginRequest.password());
        Authentication authentication =
                this.manager.authenticate(authenticationRequest);
        String tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenAuthenticationResponse(tokenJwt));

    }


}