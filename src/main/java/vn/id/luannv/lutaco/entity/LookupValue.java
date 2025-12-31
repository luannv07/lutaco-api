package vn.id.luannv.lutaco.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(
        name = "lookup_values",
        uniqueConstraints = @UniqueConstraint(columnNames = {"lookup_cd", "lookup_group"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LookupValue extends BaseEntity{
    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "lookup_value", nullable = false)
    String lookupValue;
    @Column(name = "lookup_cd", nullable = false)
    Integer lookupCd;
    @Column(name = "lookup_group", length = 50, nullable = false)
    String lookupGroup;
    @Column(name = "status_flg", nullable = false, columnDefinition = "tinyint default 1")
    byte statusFlg;
    @Column(name = "description")
    String description;

    @PreUpdate
    void preUpdate() {
        if (statusFlg == 0 && this.getDeletedAt() != null) {
            this.setDeletedAt(LocalDate.now());
        }
    }
}