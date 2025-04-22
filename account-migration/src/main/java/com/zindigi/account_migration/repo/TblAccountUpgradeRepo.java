package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblAccountUpgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TblAccountUpgradeRepo extends JpaRepository<TblAccountUpgrade, Long> {
    @Query(value = "SELECT * FROM TBL_ACCOUNT_UPGRADE WHERE CUSTOMER_ID=:customerId", nativeQuery = true)
    TblAccountUpgrade findByCustomerId(long customerId);

    @Query(value = "SELECT U.* FROM TBL_CUSTOMER A INNER JOIN TBL_ACCOUNT_UPGRADE U ON A.CUSTOMER_ID=U.CUSTOMER_ID WHERE A.MOBILE_NO_HASH = NVL(:mobileNumber,'')", nativeQuery = true)
    TblAccountUpgrade findByMobileNumber(String mobileNumber);

    @Query(value = "SELECT U.* FROM TBL_CUSTOMER A INNER JOIN TBL_ACCOUNT_UPGRADE U ON A.CUSTOMER_ID=U.CUSTOMER_ID\n" +
            "INNER JOIN LKP_ACCOUNT_LEVEL l ON l.ACCOUNT_LEVEL_ID =u.ACCOUNT_LEVEL_ID \n" +
            "WHERE A.MOBILE_NO_HASH = NVL(:mobileNumber,'') AND l.ACCOUNT_LEVEL_CODE =:accountLevelCode", nativeQuery = true)
    TblAccountUpgrade findByMobileNumberAndAccountLevelCode(String mobileNumber, String accountLevelCode);

    @Query(value = "SELECT LPAD(MERCHANT_TILL_ID_SEQ.NEXTVAL,6,0) FROM DUAL", nativeQuery = true)
    String getTillNo();

    @Query(value = "SELECT * FROM TBL_ACCOUNT_UPGRADE WHERE ACCOUNT_UPGRADE_ID=:valueOf", nativeQuery = true)
    Optional<TblAccountUpgrade> findByAccountUpgradeId(Long valueOf);

}
