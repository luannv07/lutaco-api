package vn.id.luannv.lutaco.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Column(name = "created_at")
    @CreationTimestamp
    LocalDate createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDate updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime default null")
    LocalDate deletedAt; // after set statusCd = 0 (disabled), then use @Scheduled to hard delete after x days
}
