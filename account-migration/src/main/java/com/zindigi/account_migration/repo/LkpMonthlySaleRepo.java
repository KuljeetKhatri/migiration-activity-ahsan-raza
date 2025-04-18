package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.dto.LkpMonthlySale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpMonthlySaleRepo extends JpaRepository<LkpMonthlySale,Long> {
    LkpMonthlySale findByMonthlySaleCode(String monthlySaleCode);
}
