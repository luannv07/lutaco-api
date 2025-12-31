package vn.id.luannv.lutaco.config.initializer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import vn.id.luannv.lutaco.entity.Role;
import vn.id.luannv.lutaco.enumerate.RoleEnum;
import vn.id.luannv.lutaco.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Order(1)
public class RoleInitializer implements CommandLineRunner {
    RoleRepository roleRepository;

    /**
     * Hàm này là hàm triển khai mặc định từ CLR interface, có tác dụng kiểm tra sự tồn tại của Role trong hệ thống,
     * tự động thêm vào hệ thống nếu như chưa tồn tại, được gọi tự động 1 lần duy nhất khi khởi tạo ứng dụng.
     * @param args
     * @throws Exception Xảy ra trong quá trình khởi tạo
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("RoleInitializer started");

        try {
            List<String> roles = roleRepository.findAll()
                    .stream()
                    .map(Role::getRoleName)
                    .toList();
            List<Role> toSaved = new ArrayList<>();

            Arrays.asList(RoleEnum.values()).forEach(r -> {
                if (!roles.contains(r.getRoleName())) {
                    toSaved.add(Role
                            .builder()
                            .roleName(r.getRoleName())
                            .description(r.getDescription())
                            .build());
                }
            });

            roleRepository.saveAll(toSaved);
        } catch (Exception e) {
            log.error("RoleInitializer error: {}", e.getMessage());
        }

    }
}
