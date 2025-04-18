package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpBusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpBusinessTypeRepo extends JpaRepository<LkpBusinessType,Long> {
    LkpBusinessType findByBusinessTypeCode(String businessCode);
}
