package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblRaastResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TblRaastResponseRepo extends JpaRepository<TblRaastResponse, Long> {
    @Query(value = "SELECT * FROM TBL_RAAST_RESPONSE R WHERE R.RAAST_REQUEST_ID=:raastRequestId", nativeQuery = true)
    TblRaastResponse findByRaastRequestId(@Param("raastRequestId") long raastRequestId);

    @Query(value = "SELECT E.* FROM TBL_RAAST_REQUEST R INNER JOIN TBL_RAAST_RESPONSE E on E.RAAST_REQUEST_ID=R.RAAST_REQUEST_ID where R.DOC_VALUE=:cnic", nativeQuery = true)
    TblRaastResponse findByDocValue(@Param("cnic") String cnic);

    @Query(value = "select r.* from tbl_raast_response r inner join tbl_raast_request t on t.raast_request_id=r.raast_request_id where t.alias_value=:aliasValue",nativeQuery = true)
    TblRaastResponse findByAliasValue(@Param("aliasValue") String aliasValue);
}
