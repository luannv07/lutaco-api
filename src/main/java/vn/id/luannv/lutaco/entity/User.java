package vn.id.luannv.lutaco.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "USERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false, nullable = false)
    UUID id;
    @Column(name = "USERNAME", unique = true, nullable = false)
    String username;
    @Column(name = "PASSWORD", nullable = false)
    String password;
    @Column(name = "FULL_NAME", nullable = false)
    String fullName;
    @Column(name = "ADDRESS")
    String address;
    @Column(name = "EMAIL", nullable = false)
    String email;
    @Column(name = "GENDER", nullable = false)
    String gender;
    @Column(name = "IS_ACTIVE", nullable = false)
    Boolean isActive = false;
    @JoinColumn(name = "ROLE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    Role role;
}
