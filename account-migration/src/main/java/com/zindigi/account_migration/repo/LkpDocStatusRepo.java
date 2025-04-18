package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpDocStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpDocStatusRepo extends JpaRepository<LkpDocStatus, Long> {
    LkpDocStatus findByDocStatusCode(String docStatusCode);
    LkpDocStatus findByDocStatusId(Long id);
}
