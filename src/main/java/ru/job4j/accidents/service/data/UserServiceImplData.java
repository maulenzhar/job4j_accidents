package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.entity.User;
import ru.job4j.accidents.repository.data.AuthorityRepository;
import ru.job4j.accidents.repository.data.UserRepository;
import ru.job4j.accidents.service.UserService;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImplData implements UserService<User> {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    @Override
    public User save(User user) {
        try {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorities.findByAuthority("ROLE_USER"));
            return users.save(user);
        } catch (Exception e) {
            log.error("Internal error: {}", e.getMessage(), e);
            return null;
        }
    }
}
