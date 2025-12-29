package vn.id.luannv.lutaco.config.initializer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
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
public class UserInitializer implements CommandLineRunner {
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;

    public UserInitializer(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Value("${SYS_ADMIN_USERNAME}")
    private String sysAdminUsername;

    @Value("${SYS_ADMIN_PASSWORD}")
    private String sysAdminPassword;

    /**
     * Hàm này là hàm triển khai mặc định từ CLR interface, có tác dụng kiểm tra sự tồn tại của SYS_ADMIN trong hệ thống,
     * tự động thêm vào hệ thống nếu như chưa tồn tại, được gọi tự động 1 lần duy nhất khi khởi tạo ứng dụng.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("UserInitializer started");

        try {
            if (userRepository.findByUsername(sysAdminUsername).isPresent()) return;

            userRepository.save(User.builder()
                    .username(sysAdminUsername)
                    .password(passwordEncoder.encode(sysAdminPassword))
                    .firstName("Luan")
                    .lastName("Nguyen")
                    .email("vanluandvlp@gmail.com")
                    .build());
        } catch (Exception e) {
            log.error("UserInitializer error: {}", e.getMessage());
        }

    }
}
