package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblRaastRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TblRaastRequestRepo extends JpaRepository<TblRaastRequest,Long> {
    @Query(value = "SELECT * FROM TBL_RAAST_REQUEST R WHERE R.DOC_VALUE=:docValue",nativeQuery = true)
    TblRaastRequest findByDocValue(@Param("docValue") String docValue);
}
