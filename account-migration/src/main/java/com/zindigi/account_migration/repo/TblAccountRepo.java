package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblAccountRepo extends JpaRepository<TblAccountModel, Long> {

    @Query(value = "SELECT B.* \n" +
            "            FROM TBL_CUSTOMER A \n" +
            "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID \n" +
            "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_STATUS S on B.ACCOUNT_STATUS_ID=S.ACCOUNT_STATUS_ID \n" +
            "            WHERE (A.MOBILE_NO_HASH = SHA256.ENCRYPT(NVL(:mobileNo,'')) \n" +
            "            OR A.CNIC_HASH = SHA256.ENCRYPT(NVL(:cnic,''))) AND S.ACCOUNT_STATUS_NAME<>'CLOSED'", nativeQuery = true)
    List<TblAccountModel> getAccountByMobileNumberOrCnic(@Param("cnic") String cnic, @Param("mobileNo") String mobileNo);

    @Query(value = "SELECT * FROM TBL_ACCOUNT WHERE ACCOUNT_NO=:accountNumber", nativeQuery = true)
    TblAccountModel findByAccountNo(@Param("accountNumber") String accountNumber);


    @Query(value = "SELECT B.*  \n" +
            "           FROM TBL_CUSTOMER A  \n" +
            "               INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID \n" +
            "               INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID \n" +
            "               INNER JOIN LKP_ACCOUNT_TYPE T on  B.ACCOUNT_TYPE_ID=T.ACCOUNT_TYPE_ID\n" +
            "            WHERE A.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber) AND C.ACCOUNT_LEVEL_NAME=:accountLevelName AND T.ACCOUNT_TYPE_NAME=:accTypeName AND B.ACCOUNT_STATUS_ID <> 4", nativeQuery = true)
    TblAccountModel findByMobileNoHashAndAccountLevelName(@Param("mobileNumber") String mobileNumber, @Param("accountLevelName") String accountLevelName, @Param("accTypeName") String accTypeName);


    @Query(value = "SELECT B.*  \n" +
            "       FROM TBL_CUSTOMER A  \n" +
            "           INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID  \n" +
            "           INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\n" +
            "           INNER JOIN LKP_ACCOUNT_TYPE D ON B.ACCOUNT_TYPE_ID = D.ACCOUNT_TYPE_ID \n" +
            "       WHERE (A.MOBILE_NO_HASH = SHA256.ENCRYPT(NVL(:mobileNo,''))  \n" +
            "       OR A.CNIC_HASH = SHA256.ENCRYPT(NVL(:cnic,'')))\n" +
            "       AND D.ACCOUNT_TYPE_NAME =:accountTypeName AND B.ACCOUNT_STATUS_ID <> 4\n", nativeQuery = true)
    TblAccountModel getAccountByMobileNumberOrCnicAndAccountType(@Param("cnic") String cnic, @Param("mobileNo") String mobileNo, @Param("accountTypeName") String accountTypeName);

    @Query(value = "SELECT B.* FROM TBL_CUSTOMER A INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\n" +
            " INNER JOIN LKP_ACCOUNT_TYPE C ON B.ACCOUNT_TYPE_ID = C.ACCOUNT_TYPE_ID\n" +
            " WHERE A.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber) AND C.ACCOUNT_TYPE_NAME = :accTypeName AND B.ACCOUNT_STATUS_ID <> 4", nativeQuery = true)
    TblAccountModel findByMobileNoHashAndAccountTypeName(@Param("mobileNumber") String mobileNumber, @Param("accTypeName") String accTypeName);


    @Query(value = "SELECT * FROM TBL_ACCOUNT WHERE IS_GL_ACCOUNT = 'Y' AND IS_ACTIVE='Y' AND GL_CODE_COMBINATION_ID =:glId", nativeQuery = true)
    TblAccountModel findGlAccountByGlCodeCombinationId(Long glId);
    @Query(value = "SELECT B.* \n" +
            "            FROM TBL_CUSTOMER A\n" +
            "            INNER JOIN TBL_ACCOUNT B \n" +
            "                ON A.CUSTOMER_ID = B.CUSTOMER_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_LEVEL C \n" +
            "                ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_TYPE D \n" +
            "                ON B.ACCOUNT_TYPE_ID = D.ACCOUNT_TYPE_ID\n" +
            "            WHERE D.ACCOUNT_TYPE_CODE = :accTypeCode \n" +
            "                AND B.ACCOUNT_STATUS_ID <> 4\n" +
            "                AND A.MOBILE_NO_HASH = :mobileNumber \n" +
            "                AND C.ACCOUNT_LEVEL_CODE = :accountLevelCode", nativeQuery = true)
    TblAccountModel findByMobileNoHashAndAccountLevelCodeAndAccountTypeCode(@Param("mobileNumber") String mobileNumber, @Param("accountLevelCode") String accountLevelCode, @Param("accTypeCode") String accTypeCode);

    @Query(value = "SELECT B.*  \n" +
            "       FROM TBL_CUSTOMER A  \n" +
            "           INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID  \n" +
            "           INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\n" +
            "           INNER JOIN LKP_ACCOUNT_TYPE D ON B.ACCOUNT_TYPE_ID = D.ACCOUNT_TYPE_ID \n" +
            "       WHERE (A.MOBILE_NO_HASH = NVL(:mobileNo,'')  \n" +
            "       OR A.CNIC_HASH = NVL(:cnic,''))\n" +
            "       AND D.ACCOUNT_TYPE_CODE =:accountTypeCode AND B.ACCOUNT_STATUS_ID <> 4\n", nativeQuery = true)
    TblAccountModel getAccountByMobileNumberOrCnicAndAccountTypeCode(@Param("cnic") String cnic, @Param("mobileNo") String mobileNo, @Param("accountTypeCode") String accountTypeCode);

    @Query(value = "SELECT B.* FROM TBL_CUSTOMER A INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\n" +
            " INNER JOIN LKP_ACCOUNT_TYPE C ON B.ACCOUNT_TYPE_ID = C.ACCOUNT_TYPE_ID\n" +
            " WHERE A.MOBILE_NO_HASH = :mobileNumber AND C.ACCOUNT_TYPE_CODE = :accTypeCode AND B.ACCOUNT_STATUS_ID <> 4", nativeQuery = true)
    TblAccountModel findByMobileNoHashAndAccountTypeCode(@Param("mobileNumber") String mobileNumber, @Param("accTypeCode") String accTypeCode);

}
