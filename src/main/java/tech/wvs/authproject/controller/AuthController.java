package tech.wvs.authproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.wvs.authproject.config.JwtTokenService;
import tech.wvs.authproject.controller.dto.LoginRequest;
import tech.wvs.authproject.controller.dto.LoginResponse;
import tech.wvs.authproject.controller.dto.UserRequest;
import tech.wvs.authproject.controller.dto.UserResponse;
import tech.wvs.authproject.entity.User;
import tech.wvs.authproject.exception.UsernameOrPasswordException;
import tech.wvs.authproject.mapper.UserMapper;
import tech.wvs.authproject.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    public AuthController(UserService service, AuthenticationManager authenticationManager, JwtTokenService tokenService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    //Cadastrar novo user
    @PostMapping(path = "/register")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        var entity = service.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(entity));
    }


    //Login
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());

            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            //Gerar o token JWT para retornar
            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (
                BadCredentialsException e) {
            throw new UsernameOrPasswordException("Usuário ou senha inválidos");
        }

    }
}
