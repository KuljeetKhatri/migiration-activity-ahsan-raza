package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.dto.TblMerchantDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TblMerchantDocRepo extends JpaRepository<TblMerchantDoc,Long> {
    @Query(value = "SELECT * FROM TBL_MERCHANT_DOCS A\n" +
            "INNER JOIN TBL_MERCHANT B ON A.MERCHANT_ID=B.MERCHANT_ID\n" +
            "WHERE A.MERCHANT_ID=:merchantId AND A.FIELD_NAME=:fieldName",nativeQuery = true)
    TblMerchantDoc findByMerchantIDAndFieldName(@Param("merchantId") long merchantId, @Param("fieldName") String fieldName);


}
