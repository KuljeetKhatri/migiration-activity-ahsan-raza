package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.dto.TblMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblMerchantRepo extends JpaRepository<TblMerchant,Long> {
    @Query(value = "SELECT LPAD(MERCHANT_TILL_ID_SEQ.NEXTVAL,6,0) FROM DUAL",nativeQuery = true)
    String getTillNo();

    @Query(value = "SELECT\n" +
            "    A.*\n" +
            "FROM\n" +
            "    TBL_MERCHANT A\n" +
            "INNER JOIN\n" +
            "    TBL_ACCOUNT B ON A.ACCOUNT_ID = B.ACCOUNT_ID\n" +
            "INNER JOIN\n" +
            "    LKP_ACCOUNT_TYPE C ON B.ACCOUNT_TYPE_ID = C.ACCOUNT_TYPE_ID\n" +
            "INNER JOIN\n" +
            "    TBL_CUSTOMER D on B.CUSTOMER_ID=D.CUSTOMER_ID\n" +
            "\n" +
            "WHERE\n" +
            "    D.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber)\n" +
            "    AND C.ACCOUNT_TYPE_NAME = :accountTypeName",nativeQuery = true)
    TblMerchant getMerchantByAccountTypeAndMobileNumner(@Param("mobileNumber") String mobileNumber, @Param("accountTypeName") String accountTypeName);

    @Query(value = "SELECT * FROM TBL_MERCHANT A\n" +
            "INNER JOIN TBL_ACCOUNT B ON B.ACCOUNT_ID=A.ACCOUNT_ID\n" +
            "INNER JOIN TBL_CUSTOMER C ON C.CUSTOMER_ID=B.CUSTOMER_ID\n" +
            "WHERE C.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber)",nativeQuery = true)
    TblMerchant findByMobileNumber(@Param("mobileNumber") String mobileNumber);

    @Query(value = "SELECT * FROM (" +
            "SELECT " +
            "C.CUSTOMER_ID, A.ACCOUNT_ID, M.MERCHANT_ID, A.ACCOUNT_NO, N.CNIC, N.CNIC_HASH, " +
            "M.BUSINESS_NAME, B.BUSINESS_TYPE_NAME, M.CREATEDATE, CU.USERNAME AS CREATED_BY, UU.USERNAME AS UPDATED_BY, " +
            "S.STATUS_NAME, C.FULL_NAME, AD.FULL_ADDRESS, M.BUSINESS_ADDRESS, CT.CITY_NAME, MS.MONTHLY_SALE_NAME, " +
            "CF.DOCUMENT_PATH AS CONTRACT_FRONT, CF.DOC_STATUS_ID AS CONTRACT_FRONT_STATUS, " +
            "CB.DOCUMENT_PATH AS CONTRACT_BACK, CB.DOC_STATUS_ID AS CONTRACT_BACK_STATUS, " +
            "SF.DOCUMENT_PATH AS STOREFRONT, SF.DOC_STATUS_ID AS STOREFRONT_STATUS, " +
            "POB.DOCUMENT_PATH AS PROOF_OF_BUSINESS, POB.DOC_STATUS_ID AS PROOF_OF_BUSINESS_STATUS, " +
            "A.IBAN, M.TILL_NO, M.QR, COUNT(*) OVER() AS TOTAL_COUNT, ROW_NUMBER() OVER (ORDER BY M.CREATEDATE DESC) AS ROW_NUM " +
            "FROM TBL_MERCHANT M " +
            "LEFT JOIN TBL_USER UU ON M.LASTUPDATEUSER = UU.USER_ID " +
            "LEFT JOIN TBL_USER CU ON M.CREATEUSER = CU.USER_ID " +
            "INNER JOIN TBL_ACCOUNT A ON M.ACCOUNT_ID = A.ACCOUNT_ID " +
            "INNER JOIN TBL_CUSTOMER C ON A.CUSTOMER_ID = C.CUSTOMER_ID " +
            "INNER JOIN TBL_NADRA N ON C.CNIC_HASH = N.CNIC_HASH " +
            "INNER JOIN LKP_STATUS S ON M.STATUS_ID = S.STATUS_ID " +
            "INNER JOIN TBL_ADDRESS AD ON AD.CUSTOMER_ID = C.CUSTOMER_ID " +
            "INNER JOIN LKP_CITY CT ON CT.CITY_ID = M.CITY_ID " +
            "INNER JOIN LKP_MONTHLY_SALE MS ON MS.MONTHLY_SALE_ID = M.MONTHLY_SALES_EXPECTED_ID " +
            "INNER JOIN TBL_APP_USER I ON I.CUSTOMER_ID = C.CUSTOMER_ID " +
            "INNER JOIN LKP_BUSINESS_TYPE B ON M.BUSINESS_TYPE_ID = B.BUSINESS_TYPE_ID " +
            "LEFT OUTER JOIN (SELECT D.DOCUMENT_PATH, D.APP_USER_ID, S.DOC_STATUS_ID FROM TBL_DOCUMENT D " +
            "LEFT JOIN TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID " +
            "LEFT JOIN LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID WHERE D.DOCUMENT_TYPE_ID = 3) CF ON I.APP_USER_ID = CF.APP_USER_ID " +
            "LEFT OUTER JOIN (SELECT D.DOCUMENT_PATH, D.APP_USER_ID, S.DOC_STATUS_ID FROM TBL_DOCUMENT D " +
            "LEFT JOIN TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID " +
            "LEFT JOIN LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID WHERE D.DOCUMENT_TYPE_ID = 4) CB ON I.APP_USER_ID = CB.APP_USER_ID " +
            "LEFT OUTER JOIN (SELECT D.DOCUMENT_PATH, D.APP_USER_ID, S.DOC_STATUS_ID FROM TBL_DOCUMENT D " +
            "LEFT JOIN TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID " +
            "LEFT JOIN LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID WHERE D.DOCUMENT_TYPE_ID = 5) SF ON I.APP_USER_ID = SF.APP_USER_ID " +
            "LEFT OUTER JOIN (SELECT D.DOCUMENT_PATH, D.APP_USER_ID, S.DOC_STATUS_ID FROM TBL_DOCUMENT D " +
            "LEFT JOIN TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID " +
            "LEFT JOIN LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID WHERE D.DOCUMENT_TYPE_ID = 20) POB ON I.APP_USER_ID = POB.APP_USER_ID " +
            "WHERE (NVL(:cnic, '-') = '-' OR C.CNIC_HASH = SHA256.ENCRYPT(:cnic)) " +
            "AND A.ACCOUNT_NO = NVL(:account_no, A.ACCOUNT_NO) " +
            "AND B.BUSINESS_TYPE_ID = NVL(:businessTypeName, B.BUSINESS_TYPE_ID) " +
            "AND M.BUSINESS_NAME = NVL(:businessName, M.BUSINESS_NAME) " +
            "AND CU.USER_ID = NVL(:createUser, CU.USER_ID) " +
            "AND TRUNC(M.CREATEDATE) BETWEEN TRUNC(NVL(TO_DATE(:dateFromInput, 'YYYY-MM-DD'), M.CREATEDATE)) " +
            "AND TRUNC(NVL(TO_DATE(:dateToInput, 'YYYY-MM-DD'), M.CREATEDATE)) " +
            "AND NVL(UU.USER_ID, '0') = NVL(:updatedBy, NVL(UU.USER_ID, '0')) " +
            "AND S.STATUS_NAME = NVL(:statusName, S.STATUS_NAME)) " +
            "WHERE ROW_NUM BETWEEN NVL(:rowStart, ROW_NUM) AND NVL(:rowEnd, ROW_NUM)", nativeQuery = true)
    List<Object[]> getAllMerchant(@Param("cnic") String cnic,
                                  @Param("account_no") String accountNo,
                                  @Param("businessTypeName") String businessTypeName,
                                  @Param("businessName") String businessName,
                                  @Param("createUser") String createUser,
                                  @Param("dateFromInput") String dateFromInput,
                                  @Param("dateToInput") String dateToInput,
                                  @Param("updatedBy") String updatedBy,
                                  @Param("statusName") String statusName,
                                  @Param("rowStart") Long rowStart,
                                  @Param("rowEnd") Long rowEnd);

    @Query(value = "SELECT\n" +
            "    C.CUSTOMER_ID,\n" +
            "    A.ACCOUNT_ID,\n" +
            "    M.MERCHANT_ID,\n" +
            "    A.ACCOUNT_NO,\n" +
            "    N.CNIC,\n" +
            "    N.CNIC_HASH,\n" +
            "    M.BUSINESS_NAME,\n" +
            "    B.BUSINESS_TYPE_NAME,\n" +
            "    M.CREATEDATE,\n" +
            "    CU.USERNAME AS CREATED_BY,\n" +
            "    UU.USERNAME AS UPDATED_BY,\n" +
            "    S.STATUS_NAME,\n" +
            "    C.FULL_NAME,\n" +
            "    AD.FULL_ADDRESS,\n" +
            "    M.BUSINESS_ADDRESS,\n" +
            "    CT.CITY_NAME,\n" +
            "    MS.MONTHLY_SALE_NAME,\n" +
            "    CF.DOCUMENT_PATH AS CONTRACT_FRONT,\n" +
            "    CF.DOC_STATUS_ID AS CONTRACT_FRONT_STATUS,\n" +
            "    CB.DOCUMENT_PATH AS CONTRACT_BACK,\n" +
            "    CB.DOC_STATUS_ID AS CONTRACT_BACK_STATUS,\n" +
            "    SF.DOCUMENT_PATH AS STOREFRONT,\n" +
            "    SF.DOC_STATUS_ID AS STOREFRONT_STATUS,\n" +
            "    POB.DOCUMENT_PATH AS PROOF_OF_BUSINESS,\n" +
            "    POB.DOC_STATUS_ID AS PROOF_OF_BUSINESS_STATUS,\n" +
            "    A.IBAN,\n" +
            "    M.TILL_NO,\n" +
            "    M.QR\n" +
            "FROM \n" +
            "    TBL_MERCHANT M\n" +
            "LEFT JOIN \n" +
            "    TBL_USER UU ON M.LASTUPDATEUSER = UU.USER_ID\n" +
            "LEFT JOIN \n" +
            "    TBL_USER CU ON M.CREATEUSER = CU.USER_ID\n" +
            "INNER JOIN \n" +
            "    TBL_ACCOUNT A ON M.ACCOUNT_ID = A.ACCOUNT_ID\n" +
            "INNER JOIN \n" +
            "    TBL_CUSTOMER C ON A.CUSTOMER_ID = C.CUSTOMER_ID\n" +
            "INNER JOIN \n" +
            "    TBL_NADRA N ON C.CNIC_HASH = N.CNIC_HASH\n" +
            "INNER JOIN \n" +
            "    LKP_STATUS S ON M.STATUS_ID = S.STATUS_ID\n" +
            "INNER JOIN \n" +
            "    TBL_ADDRESS AD ON AD.CUSTOMER_ID = C.CUSTOMER_ID\n" +
            "INNER JOIN \n" +
            "    LKP_CITY CT ON CT.CITY_ID = M.CITY_ID\n" +
            "INNER JOIN \n" +
            "    LKP_MONTHLY_SALE MS ON MS.MONTHLY_SALE_ID = M.MONTHLY_SALES_EXPECTED_ID\n" +
            "INNER JOIN \n" +
            "    TBL_APP_USER I ON I.CUSTOMER_ID = C.CUSTOMER_ID\n" +
            "INNER JOIN \n" +
            "    LKP_BUSINESS_TYPE B ON M.BUSINESS_TYPE_ID = B.BUSINESS_TYPE_ID\n" +
            "LEFT OUTER JOIN (\n" +
            "    SELECT \n" +
            "        D.DOCUMENT_PATH, \n" +
            "        D.APP_USER_ID, \n" +
            "        S.DOC_STATUS_ID\n" +
            "    FROM \n" +
            "        TBL_DOCUMENT D\n" +
            "    LEFT JOIN \n" +
            "        TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID\n" +
            "    LEFT JOIN \n" +
            "        LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID\n" +
            "    WHERE \n" +
            "        D.DOCUMENT_TYPE_ID = 3\n" +
            ") CF ON I.APP_USER_ID = CF.APP_USER_ID\n" +
            "LEFT OUTER JOIN (\n" +
            "    SELECT \n" +
            "        D.DOCUMENT_PATH, \n" +
            "        D.APP_USER_ID, \n" +
            "        S.DOC_STATUS_ID\n" +
            "    FROM \n" +
            "        TBL_DOCUMENT D\n" +
            "    LEFT JOIN \n" +
            "        TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID\n" +
            "    LEFT JOIN \n" +
            "        LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID\n" +
            "    WHERE \n" +
            "        D.DOCUMENT_TYPE_ID = 4\n" +
            ") CB ON I.APP_USER_ID = CB.APP_USER_ID\n" +
            "LEFT OUTER JOIN (\n" +
            "    SELECT \n" +
            "        D.DOCUMENT_PATH, \n" +
            "        D.APP_USER_ID, \n" +
            "        S.DOC_STATUS_ID\n" +
            "    FROM \n" +
            "        TBL_DOCUMENT D\n" +
            "    LEFT JOIN \n" +
            "        TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID\n" +
            "    LEFT JOIN \n" +
            "        LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID\n" +
            "    WHERE \n" +
            "        D.DOCUMENT_TYPE_ID = 5\n" +
            ") SF ON I.APP_USER_ID = SF.APP_USER_ID\n" +
            "LEFT OUTER JOIN (\n" +
            "    SELECT \n" +
            "        D.DOCUMENT_PATH, \n" +
            "        D.APP_USER_ID, \n" +
            "        S.DOC_STATUS_ID\n" +
            "    FROM \n" +
            "        TBL_DOCUMENT D\n" +
            "    LEFT JOIN \n" +
            "        TBL_MERCHANT_DOCS M ON D.DOCUMENT_ID = M.DOCUMENT_ID\n" +
            "    LEFT JOIN \n" +
            "        LKP_DOC_STATUS S ON M.DOC_STATUS_ID = S.DOC_STATUS_ID\n" +
            "    WHERE \n" +
            "        D.DOCUMENT_TYPE_ID = 20\n" +
            ") POB ON I.APP_USER_ID = POB.APP_USER_ID\n" +
            "WHERE M.MERCHANT_ID=:id",nativeQuery = true)
    Object getMerchantById(@Param("id") Long id);
}
