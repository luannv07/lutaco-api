package vn.id.luannv.lutaco.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.id.luannv.lutaco.dto.request.LookupValueFilterRequest;
import vn.id.luannv.lutaco.entity.LookupValue;

import java.util.Optional;

@Repository
public interface LookupValueRepository extends JpaRepository<LookupValue, Long> {

    @Query("""
        SELECT lv
        FROM LookupValue lv
        WHERE
            (:#{#request.lookupValue} IS NULL 
                OR lv.lookupValue LIKE %:#{#request.lookupValue}%)
        AND
            (:#{#request.lookupGroup} IS NULL 
                OR lv.lookupGroup = :#{#request.lookupGroup})
        AND
            (:#{#request.statusFlg} IS NULL 
                OR lv.statusFlg = :#{#request.statusFlg})
    """)
    Page<LookupValue> findAllByFilters(
            @Param("request") LookupValueFilterRequest request,
            Pageable pageable
    );

    @Query("""
    SELECT lv FROM LookupValue lv
    WHERE (lv.lookupValue is null or lv.lookupValue = :lookupValue)
      or (:lookupGroup IS NULL OR lv.lookupGroup = :lookupGroup)
""")
    Optional<LookupValue> findByValueAndOptionalGroup(
            @Param("lookupValue") String lookupValue,
            @Param("lookupGroup") String lookupGroup
    );

}
