package com.zindigi.account_migration.repo;

import com.mfs.commonservice.model.LkpAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LkpAccountTypeRepo extends JpaRepository<LkpAccountType, Long> {
    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_ACCOUNT_TYPE A \n" +
            " WHERE UPPER(A.ACCOUNT_TYPE_NAME) = UPPER('HRA')\n" +
            "   AND A.IS_ACTIVE = 'Y'",nativeQuery = true)
    LkpAccountType getAccountType();

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_ACCOUNT_TYPE A \n" +
            " WHERE UPPER(A.ACCOUNT_TYPE_NAME) = UPPER(:accountTypeName)\n" +
            "   AND A.IS_ACTIVE = 'Y'",nativeQuery = true)
    LkpAccountType findByAccountTypeName(@Param("accountTypeName") String accountTypeName);

    LkpAccountType findByAccountTypeId(Long valueOf);

    Optional<LkpAccountType> findByAccountTypeCode(String s);
}
