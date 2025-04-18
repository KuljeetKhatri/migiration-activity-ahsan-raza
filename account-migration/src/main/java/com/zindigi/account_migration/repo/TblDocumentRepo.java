package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblDocumentRepo extends JpaRepository<TblDocument, Long> {

    @Query(value = "SELECT A.* FROM TBL_DOCUMENT A INNER JOIN TBL_APP_USER B ON A.APP_USER_ID = B.APP_USER_ID INNER JOIN TBL_CUSTOMER C ON C.CUSTOMER_ID = B.CUSTOMER_ID WHERE  C.CUSTOMER_ID = :customerId AND C.IS_ACTIVE='Y'",nativeQuery = true)
    List<TblDocument> getcustomeraccountdocs(Long customerId);

    @Query(value = "SELECT D.DOCUMENT_TYPE_NAME, C.DOCUMENT_PATH, C.DOCUMENT_ID\n" +
            "FROM TBL_CUSTOMER  A\n" +
            "    INNER JOIN TBL_APP_USER B ON B.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            "    INNER JOIN TBL_DOCUMENT C ON C.APP_USER_ID = B.APP_USER_ID\n" +
            "    INNER JOIN LKP_DOCUMENT_TYPE D ON C.DOCUMENT_TYPE_ID = D.DOCUMENT_TYPE_ID\n" +
            "WHERE  A.IS_ACTIVE='Y' AND A.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber) AND D.DOCUMENT_TYPE_NAME=:docType",nativeQuery = true)
    Object fetchDocumentDetailsByMobileNumber(@Param("mobileNumber") String mobileNumber, @Param("docType") String docType);

    @Query(value = "SELECT CASE COUNT(*) WHEN 0 THEN 'N' ELSE 'Y' END AS UPLOADED \n" +
            "    FROM TBL_CUSTOMER A \n" +
            "      INNER JOIN TBL_APP_USER B ON A.CUSTOMER_ID = B.CUSTOMER_ID  \n" +
            "      INNER JOIN TBL_DOCUMENT C ON B.APP_USER_ID = C.APP_USER_ID\n" +
            "      INNER JOIN LKP_DOCUMENT_TYPE D ON C.DOCUMENT_TYPE_ID = D.DOCUMENT_TYPE_ID\n" +
            "WHERE D.DOCUMENT_TYPE_NAME =:docType\n" +
            "    AND A.EMAIL =:email AND A.IS_ACTIVE='Y'",nativeQuery = true)
    String checkDocumentByEmailAndDocumentType(@Param("email") String email, @Param("docType") String docType);

    @Query(value = "SELECT D.* \n" +
            "    FROM TBL_APP_USER A\n" +
            "        INNER JOIN TBL_CUSTOMER C ON A.CUSTOMER_ID = C.CUSTOMER_ID\n" +
            "        INNER JOIN TBL_DOCUMENT D ON A.APP_USER_ID = D.APP_USER_ID\n" +
            "        INNER JOIN LKP_DOCUMENT_TYPE T ON T.DOCUMENT_TYPE_ID = D.DOCUMENT_TYPE_ID\n" +
            "    WHERE  C.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber) \n" +
            "        AND  C.IS_ACTIVE='Y' AND T.DOCUMENT_TYPE_NAME=:docType ",nativeQuery = true)
    TblDocument getDocumentByMobileNumberAndDocumnetTypeName(@Param("mobileNumber") String mobileNumber, @Param("docType") String docType);


    List<TblDocument> findByLkpDocumentTypeDocumentTypeId(Long documentTypeId);

    @Query(value = "SELECT D.*\n" +
            "FROM TBL_MERCHANT M\n" +
            "    INNER JOIN TBL_ACCOUNT A ON A.ACCOUNT_ID = M.ACCOUNT_ID\n" +
            "    INNER JOIN TBL_CUSTOMER C ON C.CUSTOMER_ID=A.CUSTOMER_ID\n" +
            "    INNER JOIN TBL_APP_USER A ON A.CUSTOMER_ID = C.CUSTOMER_ID\n" +
            "    INNER JOIN TBL_DOCUMENT D ON A.APP_USER_ID = D.APP_USER_ID\n" +
            "    INNER JOIN LKP_DOCUMENT_TYPE T ON T.DOCUMENT_TYPE_ID = D.DOCUMENT_TYPE_ID\n" +
            "WHERE  \n" +
            "    M.MERCHANT_ID =:merchantId \n" +
            "    AND T.DOCUMENT_TYPE_NAME =:documentName AND C.IS_ACTIVE='Y'",nativeQuery = true)
    TblDocument getDocumentByMerchantIdAndDocumnetTypeName(@Param("merchantId") Long merchantId, @Param("documentName") String documentName);


}
