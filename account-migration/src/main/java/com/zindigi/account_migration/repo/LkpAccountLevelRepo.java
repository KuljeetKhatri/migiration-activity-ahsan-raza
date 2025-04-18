package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpAccountLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface LkpAccountLevelRepo extends JpaRepository<LkpAccountLevel, Long> {

    List<LkpAccountLevel> findByIsActive(String isActive);

    LkpAccountLevel findByAccountLevelId(long accountLevelId);

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_ACCOUNT_LEVEL A \n" +
            " INNER JOIN LKP_ACCOUNT_CLASSIFICATION L ON A.ACCOUNT_CLASSIFICATION_ID = L.ACCOUNT_CLASSIFICATION_ID\n" +
            " WHERE Upper(A.ACCOUNT_LEVEL_NAME) =:accountLevelName\n" +
            "   AND UPPER(L.ACCOUNT_CLASSIFICATION_NAME) =:accountClassificationName\n" +
            "   AND A.IS_ACTIVE = 'Y'\n" +
            "   AND A.STATUS_ID = 2", nativeQuery = true)
    LkpAccountLevel findByAccountLevelName(@Param("accountLevelName") String accountLevelName, @Param("accountClassificationName") String accountClassificationName);


    @Query(value = "SELECT *  \n" +
            "        FROM LKP_ACCOUNT_LEVEL  \n" +
            "            WHERE ACCOUNT_CLASSIFICATION_ID=NVL(:accountClassificationId,ACCOUNT_CLASSIFICATION_ID)\n" +
            "            AND STATUS_ID = NVL(:statusId,STATUS_ID) AND CREATEUSER = NVL(:createdBy,CREATEUSER) AND NVL(LASTUPDATEUSER,0) = NVL(:updatedBy,NVL(LASTUPDATEUSER,0))\n" +
            "            AND CREATEDATE BETWEEN NVL(TO_DATE(:dateFromInput,'YYYY-MM-DD HH24:MI:SS'),CREATEDATE) AND NVL(TO_DATE(:dateToInput,'YYYY-MM-DD HH24:MI:SS'),CREATEDATE)", nativeQuery = true)
    Page<LkpAccountLevel> getallaccounttypes(@Param("accountClassificationId") String accountClassificationId, @Param("createdBy") String createdBy, @Param("updatedBy") String updatedBy, @Param("statusId") String statusId, @Param("dateFromInput") String dateFromInput, @Param("dateToInput") String dateToInput, Pageable pageable);

    Optional<LkpAccountLevel> findByAccountLevelCode(String accountLevelCode);


//    LkpAccountLevel findByAccountTypeId(Long valueOf);

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_ACCOUNT_LEVEL A \n" +
            " WHERE Upper(A.ACCOUNT_LEVEL_NAME) =Upper(:accountLevelName)\n" +
            "   AND A.IS_ACTIVE = 'Y'\n" +
            "   AND A.STATUS_ID = 2", nativeQuery = true)
    LkpAccountLevel findByAccountLevelNameByName(String accountLevelName);
}
