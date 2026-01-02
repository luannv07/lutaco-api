package vn.id.luannv.lutaco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.id.luannv.lutaco.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
           "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
    Page<User> searchByName(String keyword, Pageable pageable);

}
