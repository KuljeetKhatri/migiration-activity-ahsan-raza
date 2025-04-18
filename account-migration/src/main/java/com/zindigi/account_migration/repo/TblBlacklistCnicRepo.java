package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblBlacklistCnic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TblBlacklistCnicRepo extends JpaRepository<TblBlacklistCnic, Long> {
    @Query(value = "SELECT COUNT(CNIC)\n" +
            "FROM TBL_BLACKLIST_CNIC\n" +
            "WHERE CNIC = :cnic\n" +
            "AND IS_BLACKLIST = 1 ", nativeQuery = true)

    int checkCnicBlacklisting(@Param("cnic") Long cnic);

    TblBlacklistCnic findByCnic(String cnic);
}
