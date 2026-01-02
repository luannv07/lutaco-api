package vn.id.luannv.lutaco.config.initializer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import vn.id.luannv.lutaco.entity.LookupValue;
import vn.id.luannv.lutaco.entity.Role;
import vn.id.luannv.lutaco.enumerate.LookupValueEnum;
import vn.id.luannv.lutaco.enumerate.RoleEnum;
import vn.id.luannv.lutaco.mapper.LookupValueMapper;
import vn.id.luannv.lutaco.repository.LookupValueRepository;
import vn.id.luannv.lutaco.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Order(1)
public class LookupValueInitializer implements CommandLineRunner {
    LookupValueRepository lookupValueRepository;
    private final LookupValueMapper lookupValueMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("LookupValueInitializer started");

        try {
            List<String> lookupValues = lookupValueRepository.findAll()
                    .stream()
                    .map(LookupValue::getLookupValue)
                    .toList();
            List<LookupValue> toSaved = new ArrayList<>();

            Arrays.asList(LookupValueEnum.values()).forEach(lookupValue -> {
                if (!lookupValues.contains(lookupValue.getLookupValue())) {
                    toSaved.add(LookupValue.builder()
                            .lookupCd(lookupValue.getLookupCd())
                            .description(lookupValue.getDescription())
                            .lookupGroup(lookupValue.getLookupGroup())
                            .statusFlg(lookupValue.getStatusFlg())
                            .lookupValue(lookupValue.getLookupValue())
                            .build());
                }
            });

            lookupValueRepository.saveAll(toSaved);
        } catch (Exception e) {
            log.error("LookupValueInitializer error: {}", e.getMessage());
        }

    }
}
