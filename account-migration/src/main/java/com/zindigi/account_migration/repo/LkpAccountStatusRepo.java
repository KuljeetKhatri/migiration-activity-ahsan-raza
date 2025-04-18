package com.zindigi.account_migration.repo;


import com.zindigi.account_migration.model.LkpAccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LkpAccountStatusRepo extends JpaRepository<LkpAccountStatus, Long> {
    @Query(value = "SELECT * FROM LKP_ACCOUNT_STATUS WHERE UPPER(ACCOUNT_STATUS_NAME) =UPPER(:accountStatusName)",nativeQuery = true)
    LkpAccountStatus findByAccountStatusName(@Param("accountStatusName") String accountStatusName);

    LkpAccountStatus findByAccountStatusId(Long valueOf);

    List<LkpAccountStatus> findByIsActive(String isActive);

    Optional<LkpAccountStatus> findByAccountStatusCode(String accountStatusCode);

}
