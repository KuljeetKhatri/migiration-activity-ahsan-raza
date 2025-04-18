package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpAppUserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LkpAppUserTypeRepo extends JpaRepository<LkpAppUserType, Long> {
    LkpAppUserType findByAppUserTypeCode(Object agentCode);

    @Query(value = "SELECT * FROM LKP_APP_USER_TYPE WHERE UPPER(APP_USER_TYPE_NAME) = UPPER(:code)", nativeQuery = true)
    LkpAppUserType findByAppUserTypeName(String code);
}
