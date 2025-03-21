package tech.wvs.authproject.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.wvs.authproject.controller.dto.UserRequest;
import tech.wvs.authproject.entity.User;
import tech.wvs.authproject.mapper.UserMapper;
import tech.wvs.authproject.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserRequest request) {
        var entity = UserMapper.toEntity(request);
        String password = entity.getPassword();

        entity.setPassword(passwordEncoder.encode(password));

        return repository.save(entity);
    }
}
