package vn.id.luannv.lutaco.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.luannv.lutaco.dto.request.LookupValueFilterRequest;
import vn.id.luannv.lutaco.dto.response.ApiResponse;
import vn.id.luannv.lutaco.service.LookupValueService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/lookup-values")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LookupValueController {
    LookupValueService lookupValueService;

    @GetMapping
    public ResponseEntity<?> lookupValues(@ModelAttribute LookupValueFilterRequest request,
                                          @RequestParam(required = false, defaultValue = "0") Integer page,
                                          @RequestParam(required = false, defaultValue = "5") Integer limit) {
        return ResponseEntity.ok().body(ApiResponse.builder()
                .success(true)
                .data(lookupValueService.getAll(request, page, limit))
                .message("Lay danh sach tham so thanh cong")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lookupValueById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ApiResponse.builder()
                .data(lookupValueService.getById(id))
                .success(true)
                .message("Lay thanh cong tham so")
                .build());
    }

    @GetMapping("/detail")
    public ResponseEntity<?> lookupValueByDetail(@RequestParam String lookupValue, @RequestParam String lookupGroup) {
        return ResponseEntity.ok().body(ApiResponse.builder()
                .data(lookupValueService.getByFields(lookupValue, lookupGroup))
                .success(true)
                .message("Lay danh sach tham so thanh cong")
                .build());
    }
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable Long id) {
        lookupValueService.activate(id);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .success(true)
                .message("Activate lookup value thanh cong")
                .build());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable Long id) {
        lookupValueService.deactivate(id);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .success(true)
                .message("Deactivate lookup value thanh cong")
                .build());
    }
}
