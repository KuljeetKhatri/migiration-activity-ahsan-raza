package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpAddressType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpAddressTypeRepo extends JpaRepository<LkpAddressType, Long> {
    LkpAddressType findByAddressTypeCode(String code);
}
