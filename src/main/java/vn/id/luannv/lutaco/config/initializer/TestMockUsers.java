package vn.id.luannv.lutaco.config.initializer;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.id.luannv.lutaco.entity.User;
import vn.id.luannv.lutaco.repository.UserRepository;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class TestMockUsers implements CommandLineRunner {
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;

    public TestMockUsers(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
