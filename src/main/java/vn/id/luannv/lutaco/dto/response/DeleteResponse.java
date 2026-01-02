package vn.id.luannv.lutaco.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // Quan trọng: Ẩn các trường null khỏi JSON trả về
public class DeleteResponse {
    String message;
    
    String deletedId;        // Dùng khi xóa 1
    List<String> deletedIds; // Dùng khi xóa nhiều
    
    LocalDate deletedAt;
}