package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.dto.ChartOfAccountsRequest;
import com.zindigi.account_migration.model.TblGlCodeCombination;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface TblGlCodeCombinationRepo extends JpaRepository<TblGlCodeCombination, Long> {
    @Query(value = "SELECT * \n" +
            "  FROM TBL_GL_CODE_COMBINATIONS \n" +
            " WHERE GL_SEGMENT_CODE1 = '00' \n" +
            "   AND GL_SEGMENT_CODE2 = '00001'\n" +
            "   AND GL_SEGMENT_CODE3 = '001'\n" +
            "   AND GL_SEGMENT_CODE4 = '01'\n" +
            "   AND GL_SEGMENT_CODE5 = '0000'\n" +
            "   AND GL_SEGMENT_CODE6 = '00'\n" +
            "   AND GL_SEGMENT_CODE7 = '0001'", nativeQuery = true)
    TblGlCodeCombination toFindGlCodeCombination(@Param("accountLevelDesc") String accountLevelId,
                                                 @Param("currencyDesc") String currencyId,
                                                 @Param("customerSegDesc") String customerSegId,
                                                 @Param("accTypeDesc") String accTypeId);

    @Query(value = "SELECT * \n" +
            "  FROM TBL_GL_CODE_COMBINATIONS G\n" +
            "WHERE GL_SEGMENT_DESCRIPTION5 =:levelName",nativeQuery = true)
    TblGlCodeCombination findTblGlCodeCombinationByAccountLevelName(@Param("levelName") String levelName);

    public List<TblGlCodeCombination> findAll();

    @Query(value =  "SELECT COUNT(*) FROM TBL_GL_CODE_COMBINATIONS\n" +
                    "WHERE GL_CODE_COMBINATION = :glCodeCombination AND STATUS_ID <> 3", nativeQuery = true)
    Integer countChartOfAccountByCodeCodeCombination(@Param("glCodeCombination") String chartOfAccountCode);

    @Query(value =  "SELECT COUNT(*) FROM TBL_GL_CODE_COMBINATIONS  \n" +
                    "WHERE GL_CODE_COMBINATION = :glCodeCombination \n" +
                    "AND STATUS_ID <> 3", nativeQuery = true)
    Integer countChartOfAccountByIdAndCodeCombination(@Param("glCodeCombination") String chartOfAccountCode);

    List<TblGlCodeCombination> findAll(Specification<ChartOfAccountsRequest> chartOfAccountsRequestSpecification);
}
