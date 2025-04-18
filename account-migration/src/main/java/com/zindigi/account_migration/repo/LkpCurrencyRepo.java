package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LkpCurrencyRepo extends JpaRepository<LkpCurrency, Long> {

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_CURRENCY A \n" +
            " WHERE UPPER(A.CURRENCY_NAME) = UPPER(:currencyName)\n" +
            "   AND A.IS_ACTIVE = 'Y'", nativeQuery = true)
    LkpCurrency findByCurrencyName(@Param("currencyName") String currencyName);

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_CURRENCY A \n" +
            " WHERE UPPER(A.CURRENCY_CODE) = UPPER(:currencyCode)\n" +
            "   AND A.IS_ACTIVE = 'Y'", nativeQuery = true)
    LkpCurrency findByCurrencyCode(@Param("currencyCode") String currencyCode);

    LkpCurrency findByCurrencyId(Long id);

    List<LkpCurrency> findByIsActive(String isActive);

    boolean existsByCurrencyCode(String LkpCurrencyCode);

    boolean existsByCoaCode(String lkpCurrencyCoaCode);

    @Query(value = "SELECT *\n" +
            "  FROM (\n" +
            "SELECT Y.*, COUNT(*) OVER() AS TOTAL_COUNT, ROW_NUMBER() OVER (ORDER BY Y.CREATEDATE DESC) AS ROW_NUM\n" +
            "FROM LKP_CURRENCY Y\n" +
            "WHERE CURRENCY_ID = NVL(:currencyid, CURRENCY_ID)\n" +
            "AND UPPER(CURRENCY_NAME) = UPPER(NVL(:currencyname,CURRENCY_NAME)) \n" +
            "AND CURRENCY_CODE = NVL(:currencycode,CURRENCY_CODE) \n" +
            "AND ISO_CODE = NVL(:isocode,ISO_CODE) \n" +
            "AND NVL(CREATEUSER,0) = NVL(:createdBy,NVL(CREATEUSER,0)) \n" +
            "AND NVL(LASTUPDATEUSER,0) = NVL(:updatedBy, NVL(LASTUPDATEUSER,0))\n" +
            "AND CREATEDATE BETWEEN NVL(TO_DATE(:dateFromInput,'YYYY-MM-DD HH24:MI:SS'),CREATEDATE)   \n" +
            "AND NVL(TO_DATE(:dateToInput,'YYYY-MM-DD HH24:MI:SS'),CREATEDATE)\n" +
            "AND NVL(STATUS_ID,0) = NVL(:status, NVL(STATUS_ID,0)))\n" +
            "WHERE ROW_NUM BETWEEN NVL(:rowStart,ROW_NUM) AND NVL(:rowEnd,ROW_NUM)", nativeQuery = true)
    List<Object[]> findBySearch(@Param("currencyid") String currencyId,
                                @Param("currencyname") String currencyName,
                                @Param("currencycode") String currencyCode,
                                @Param("isocode") String currencyCoaCode,
                                @Param("createdBy") String createUserId,
                                @Param("updatedBy") String updateUserId,
                                @Param("dateFromInput") String dateFrom,
                                @Param("dateToInput") String dateTo,
                                @Param("status") String statusId,
                                @Param("rowStart") Long rowStart,
                                @Param("rowEnd") Long rowEnd
    );


    @Query(value = "SELECT COUNT(*)\n" +
            "FROM LKP_CURRENCY\n" +
            "WHERE (CURRENCY_CODE = :currencyCode\n" +
            "OR  UPPER(CURRENCY_NAME) = UPPER(:currencyName)\n" +
            "OR  COA_CODE = :coaCode) \n" +
            "AND STATUS_ID <>3", nativeQuery = true)
    int countCurrencyByNameCodeAndCOACode(@Param("currencyName") String currencyName,
                                          @Param("currencyCode") String currencyCode,
                                          @Param("coaCode") String coaCode);


    @Query(value = "SELECT COUNT(*)\n" +
            "FROM LKP_CURRENCY\n" +
            "WHERE (CURRENCY_CODE = :currencyCode\n" +
            "OR  UPPER(CURRENCY_NAME) = UPPER(:currencyName)\n" +
            "OR  COA_CODE = :coaCode AND CURRENCY_ID<>:currencyId ) \n" +
            "AND STATUS_ID <>3", nativeQuery = true)
    int countCurrencyByNameCodeAndCOACodeUpdate(@Param("currencyName") String currencyName,
                                                @Param("currencyCode") String currencyCode,
                                                @Param("coaCode") String coaCode,
                                                @Param("currencyId") String currencyID);


    @Query(value = "SELECT COUNT(G.GL_SEGMENT_ID6) FROM \n" +
            "LKP_CURRENCY C\n" +
            "INNER JOIN TBL_GL_CODE_COMBINATIONS G ON C.CURRENCY_ID=G.GL_SEGMENT_ID6\n" +
            "WHERE C.CURRENCY_ID=:currencyId AND G.IS_ACTIVE = 'Y'", nativeQuery = true)
    int countCurrencyReferredRecords(@Param("currencyId") String currencyId);

    Optional<LkpCurrency> findByIsoCode(String code);
}
