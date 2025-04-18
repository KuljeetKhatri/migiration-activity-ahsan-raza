package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpRaastAliasType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LKpRaastAliasTypeRepo extends JpaRepository<LkpRaastAliasType,Long> {
    @Query(value = "SELECT * FROM LKP_RAAST_ALIAS_TYPE WHERE RAAST_ALIAS_TYPE_CODE=:aliasType",nativeQuery = true)
    LkpRaastAliasType findByRaastAliasTypeCode(@Param("aliasType") String aliasType);
    @Query(value = "SELECT * FROM LKP_RAAST_ALIAS_TYPE WHERE RAAST_ALIAS_TYPE_NAME=:aliasType",nativeQuery = true)
    LkpRaastAliasType findByRaastAliasTypeName(@Param("aliasType") String aliasType);
}
