package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblAccount;
import com.zindigi.account_migration.model.TblAccountDailyStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblAccountDailyStatsRepo extends JpaRepository<TblAccountDailyStat, Long> {
    @Query(value = "SELECT * FROM TBL_ACCOUNT_DAILY_STATS WHERE ACCOUNT_ID=:accountId",nativeQuery = true)
    List<TblAccount> getAllByAccountId(@Param("accountId") Long accountId);
}