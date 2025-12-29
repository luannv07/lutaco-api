package vn.id.luannv.lutaco.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<D, E, F> {
    Boolean success;
    Integer statusCode;
    String message;
    D data;
    E errors;
    Integer deletedCount;
    Integer activatedCount;
    F failed;
    @Builder.Default
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    LocalDateTime timestamp = LocalDateTime.now();
}

