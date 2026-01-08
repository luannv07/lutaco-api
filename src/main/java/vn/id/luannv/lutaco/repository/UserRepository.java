package vn.id.luannv.lutaco.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import vn.id.luannv.lutaco.dto.request.UserFilterRequest;
import vn.id.luannv.lutaco.entity.User;

import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("""
        select u from User u
        where (:#{#request.username} is null or lower(u.username) like concat('%', lower(:#{#request.username}), '%'))
            and (:#{#request.address} is null or lower(u.address) like concat('%', lower(:#{#request.address}), '%' ))
            and (:#{#request.isActive} is null or u.isActive = :#{#request.isActive})
            and (:#{#request.roleId} is null or u.role.id = :#{#request.roleId})
        order by u.createdDate asc
    """)
    Page<User> findByFilters(@Param("request") UserFilterRequest request, Pageable pageable);
}
