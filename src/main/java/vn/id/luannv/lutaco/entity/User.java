package vn.id.luannv.lutaco.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    String email;
    @Column(name = "username", nullable = false, length = 50, unique = true)
    String username;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "first_name", nullable = false, length = 50)
    String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    String lastName;
    @Column(name = "address")
    String address;
    @Column(name = "avatar_link")
    String avatarLink;
    @Column(name = "languageCd", columnDefinition = "tinyint default 1")
    byte languageCd;
    @Column(name = "statusCd", columnDefinition = "tinyint default 1")
    byte statusCd; // decision that user can access their account, or not
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    Role role;
}
