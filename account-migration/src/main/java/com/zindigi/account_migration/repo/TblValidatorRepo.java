package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblValidatorRepo extends JpaRepository<TblValidator, Long> {

    @Query(value = "SELECT D.ATTRIBUTE_NAME, E.ARGUMENT_VALUE, C.IS_MANDATORY\n" +
            "FROM LKP_ACCOUNT_LEVEL A  \n" +
            "INNER JOIN TBL_KYC_SET_HEAD B ON A.KYC_SET_HEAD_ID  = B.KYC_SET_HEAD_ID \n" +
            "INNER JOIN TBL_KYC_SET_DETAIL C ON B.KYC_SET_HEAD_ID = C.KYC_SET_HEAD_ID  \n" +
            "INNER JOIN TBL_KYC_ATTRIBUTES D ON C.KYC_ATTRIBUTES_ID = D.KYC_ATTRIBUTES_ID \n" +
            "INNER JOIN TBL_VALIDATOR E ON D.VALIDATOR_ID = E.VALIDATOR_ID \n" +
            "INNER JOIN LKP_ACCOUNT_CLASSIFICATION L ON A.ACCOUNT_CLASSIFICATION_ID = L.ACCOUNT_CLASSIFICATION_ID \n" +
            "WHERE A.ACCOUNT_LEVEL_NAME =:level2 \n" +
            "AND L.ACCOUNT_CLASSIFICATION_NAME =:accountClassificationName \n" +
            "AND A.IS_ACTIVE = 'Y' \n" +
            "AND A.STATUS_ID = 2\n" +
            "AND D.IS_ACTIVE = 'Y'", nativeQuery = true)
    List<Object> getKyc(@Param("accountClassificationName") String accountClassificationName, @Param("level2") String level2);

}
