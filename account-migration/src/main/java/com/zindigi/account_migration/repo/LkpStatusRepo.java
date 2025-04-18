package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;


public interface LkpStatusRepo extends JpaRepository<LkpStatus, Long> {
    LkpStatus findByStatusId(long statusId);
    @Query(value = "select * from LKP_STATUS WHERE STATUS_CODE=:statusCode",nativeQuery = true)
    LkpStatus findBystatusCode(@Param("statusCode") String statusCode);

    @Query(value = "select * from lkp_status where status_id=:statusId",nativeQuery = true)
    LkpStatus findByStatasId(@Param("statusId") BigDecimal statusId);
}
