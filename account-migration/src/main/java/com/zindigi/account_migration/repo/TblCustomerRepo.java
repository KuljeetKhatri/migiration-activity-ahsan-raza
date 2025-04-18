package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblCustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TblCustomerRepo extends JpaRepository<TblCustomer, Long> {


    @Query(value = "SELECT  * FROM TBL_CUSTOMER A \n" +
            "                WHERE (CNIC_HASH = SHA256.ENCRYPT(NVL(:cnic,''))) AND A.IS_ACTIVE='Y'", nativeQuery = true)
    TblCustomer findTblCustomerByCnic(@Param("cnic") String cnic);


    @Query(value = "SELECT C.* FROM TBL_CUSTOMER C INNER JOIN TBL_ACCOUNT A ON C.CUSTOMER_ID = A.CUSTOMER_ID WHERE C.CNIC =:cnic AND A.MOBILE_NO =:mobile_No AND A.ACCOUNT_STATUS_ID NOT IN (4) AND C.IS_ACTIVE='Y'", nativeQuery = true)
    TblCustomer verifyAccountExistance(@Param("mobile_No") String mobile_No, @Param("cnic") String cnic);


    @Query(value = "SELECT * FROM TBL_CUSTOMER WHERE CNIC =:cnic AND IS_ACTIVE='Y'", nativeQuery = true)
    TblCustomer getByCnic(@Param("cnic") String cnic);

    @Query(value = "SELECT COUNT(*)\n" +
            "  FROM TBL_CUSTOMER C\n" +
            " INNER JOIN TBL_ACCOUNT A ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            " INNER JOIN LKP_ACCOUNT_STATUS S ON A.ACCOUNT_STATUS_ID = S.ACCOUNT_STATUS_ID\n" +
            " WHERE C.CNIC = :cnic\n" +
            "   AND C.MOBILE_NO = :mobileNumber\n" +
            "   AND S.ACCOUNT_STATUS_CODE NOT IN ('CLS') AND C.IS_ACTIVE='Y'", nativeQuery = true)
    int nadraDuplicationCheck(@Param("mobileNumber") String mobileNumber, @Param("cnic") String cnic);


    @Query(value = "SELECT A.MOTHER_MAIDEN_NAME\n" +
            "   FROM TBL_CUSTOMER A \n" +
            "   WHERE A.MOBILE_NO_HASH = SHA256.ENCRYPT(NVL(:mobileNumber,'')) AND A.IS_ACTIVE='Y'\n" +
            "UNION ALL\n" +
            "SELECT MOTHER_NAME\n" +
            "  FROM (SELECT MOTHER_NAME FROM TBL_MOTHER_NAME WHERE MOTHER_NAME NOT LIKE 'NA' ORDER BY DBMS_RANDOM.RANDOM) \n" +
            " WHERE ROWNUM <:length", nativeQuery = true)
    List<String> getkycDataByMobileNumber(@Param("mobileNumber") String mobileNumber, @Param("length") int length);

    @Query(value = "SELECT * FROM TBL_CUSTOMER A WHERE A.MOBILE_NO_HASH = SHA256.ENCRYPT(NVL(:mobileNumber,'')) AND A.IS_ACTIVE='Y'", nativeQuery = true)
    TblCustomer findByMobileNumberHash(@Param("mobileNumber") String mobileNumber);

    @Query(value = "SELECT COUNT(*) FROM TBL_CUSTOMER A WHERE A.MOBILE_NO_HASH = SHA256.ENCRYPT(NVL(:mobileNumber,'')) OR A.CNIC_HASH = SHA256.ENCRYPT(NVL(:cnic,'')) AND A.IS_ACTIVE='Y'", nativeQuery = true)
    int findByMobileNumberHashAndCnicHash(String mobileNumber, String cnic);

    TblCustomer findByFullName(String name);


    @Query(value = "SELECT * FROM TBL_CUSTOMER A WHERE A.CNIC_HASH = SHA256.ENCRYPT(NVL(:cnic,'')) AND A.IS_ACTIVE='Y'", nativeQuery = true)
    TblCustomer findByCnicHash(String cnic);

    TblCustomer findByBvs(String bvs);

    @Query(value = "SELECT NIC_FRONT.DOCUMENT_PATH AS CNIC_SCAN_COPY, NIC_FRONT.DOCUMENT_ID AS CNIC_SCAN_COPY_ID,\n" +
            "       CUST_PIC.DOCUMENT_PATH AS LIVE_PHOTO, CUST_PIC.DOCUMENT_ID AS LIVE_PHOTO_ID,\n" +
            "       SIGN_PIC.DOCUMENT_PATH AS SIGNATURE, SIGN_PIC.DOCUMENT_ID AS SIGNATURE_ID,\n" +
            "       NIC_FRONT.DOCUMENT_PATH AS CNIC_FRONT_PIC, NIC_FRONT.DOCUMENT_ID AS CNIC_FRONT_PIC_ID,\n" +
            "       NIC_BACK.DOCUMENT_PATH AS CNIC_BACK_PIC, NIC_BACK.DOCUMENT_ID AS CNIC_BACK_PIC_ID, \n" +
            "       CUST_PIC.DOCUMENT_PATH AS CUSTOMER_PIC, CUST_PIC.DOCUMENT_ID AS CUSTOMER_PIC_ID,\n" +
            "       SIGN_PIC.DOCUMENT_PATH AS SIGNATURE_PIC, SIGN_PIC.DOCUMENT_ID AS SIGNATURE_PIC_ID, \n" +
            "       POI.DOCUMENT_PATH AS PROOF_OF_INCOME_PIC, POI.DOCUMENT_ID AS PROOF_OF_INCOME_PIC_ID,\n" +
            "       POP.DOCUMENT_PATH AS PROOF_OF_PROFESSION_PIC, POP.DOCUMENT_ID AS PROOF_OF_PROFESSION_PIC_ID,\n" +
            "       TC_PIC.DOCUMENT_PATH AS TERMS_AND_CONDITION_PIC, TC_PIC.DOCUMENT_ID AS TERMS_AND_CONDITION_PIC_ID,\n" +
            "       UBILL.DOCUMENT_ID AS UBILL_ID, UBILL.DOCUMENT_PATH  AS UBILL_PATH,\n" +
            "       NVL(NIC_FRONT.IS_DISCREPANT,'N') AS CNIC_SCAN_COPY_DISCREPANT,\n" +
            "       NVL(CUST_PIC.IS_DISCREPANT,'N') AS LIVE_PHOTO_DISCREPANT,\n" +
            "       NVL(SIGN_PIC.IS_DISCREPANT,'N') AS SIGNATURE_DISCREPANT,\n" +
            "       NVL(NIC_BACK.IS_DISCREPANT,'N') AS CNIC_BACK_PIC_DISCREPANT,\n" +
            "       NVL(POI.IS_DISCREPANT,'N') AS PROOF_OF_INCOME_PIC_DISCREPANT,\n" +
            "       NVL(POP.IS_DISCREPANT,'N') AS PROOF_OF_PROFESSION_PIC_DISCREPANT,\n" +
            "       NVL(TC_PIC.IS_DISCREPANT,'N') AS TERMS_AND_CONDITION_PIC_DISCREPANT,\n" +
            "       NVL(UBILL.IS_DISCREPANT,'N') AS UBILL_DISCREPANT,\n" +
            "       PCNIC_FRONT.DOCUMENT_ID AS PCNIC_FRONT_ID, PCNIC_FRONT.DOCUMENT_PATH  AS PCNIC_FRONT_PATH,\n" +
            "       PCNIC_BACK.DOCUMENT_ID AS PCNIC_BACK_ID, PCNIC_BACK.DOCUMENT_PATH  AS PCNIC_BACK_PATH,\n" +
            "       BFORM.DOCUMENT_ID AS BFORM_ID, BFORM.DOCUMENT_PATH  AS BFORM_PATH,\n" +
            "       NVL(PCNIC_FRONT.IS_DISCREPANT,'N') AS PCNIC_FRONT_DISCREPANT,\n" +
            "       NVL(PCNIC_BACK.IS_DISCREPANT,'N') AS PCNIC_BACK_DISCREPANT,\n" +
            "       NVL(BFORM.IS_DISCREPANT,'N') AS BFORM_DISCREPANT,\n" +
            "       VIDEO.DOCUMENT_ID AS VIDEO_ID, VIDEO.DOCUMENT_PATH  AS VIDEO_PATH,\n" +
            "       NVL(VIDEO.IS_DISCREPANT,'N') AS VIDEO_DISCREPANT\n" +
            "FROM TBL_CUSTOMER  A\n" +
            "INNER JOIN TBL_APP_USER B ON B.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID, IS_DISCREPANT \n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 5)  CUST_PIC ON B.APP_USER_ID = CUST_PIC.APP_USER_ID \n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID, IS_DISCREPANT \n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 3)  NIC_FRONT ON B.APP_USER_ID = NIC_FRONT.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID, IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 4)  NIC_BACK ON B.APP_USER_ID = NIC_BACK.APP_USER_ID \n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID, IS_DISCREPANT \n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 7)  SIGN_PIC ON B.APP_USER_ID = SIGN_PIC.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID, IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 10 )  POI ON B.APP_USER_ID = POI.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID , IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 19 )  POP ON B.APP_USER_ID = POP.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID , IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 14 )  UBILL ON B.APP_USER_ID = UBILL.APP_USER_ID                  \n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID , IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 6 )  TC_PIC ON B.APP_USER_ID = TC_PIC.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID , IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 11 )  PCNIC_FRONT ON B.APP_USER_ID = PCNIC_FRONT.APP_USER_ID                       \n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID , IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 13 )  PCNIC_BACK ON B.APP_USER_ID = PCNIC_BACK.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID , IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 12 )  BFORM ON B.APP_USER_ID = BFORM.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID, DOCUMENT_ID , IS_DISCREPANT\n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 15 )  VIDEO ON B.APP_USER_ID = VIDEO.APP_USER_ID                                    \n" +
            "WHERE A.CUSTOMER_ID = :customerId", nativeQuery = true)
    Object getDescrepantDetails(String customerId);

    @Query(value = "SELECT A.FULL_NAME, A.FATHER_HUSBAND_NAME AS FATHER_HUSBAND_NAME,\n" +
            "      (CASE A.GENDER WHEN 'male' THEN 'MALE' ELSE 'FEMALE' END) AS GENDER, A.CNIC,A.CNIC_ISSUANCE_DATE,A.DOB,\n" +
            "      (CASE WHEN C.ACCOUNT_LEVEL_NAME = 'LEVEL 1' THEN D.LEVEL_CHANGE_DATE ELSE NULL END) AS LEVEL_1_UPDATED_ON,\n" +
            "      A.BIRTH_PLACE, A.MOTHER_MAIDEN_NAME , A.MOBILE_NO, A.CLS_VERIFIED,\n" +
            "      M.EMAIL AS EMAIL_ADDRESS,MAIL_ADD.FULL_ADDRESS AS MAILING_ADDRESS,PAD.FULL_ADDRESS PERMANENT_ADDRESS,\n" +
            "      NVL(M.DUAL_NATIONALITY,'N') AS DUAL_NATIONALITY, P.ULTRA_USAGE_NAME PURPOSE_OF_ACCOUNT, \n" +
            "      NVL(US_BORN.ANSWER, 'N') AS US_CITIZEN, M.GREEN_CARD_HOLDER AS GREEN_CARD_HOLDER, \n" +
            "      NVL(M.PMD,'N') AS CNIC_MSISDN_ACTHENTICATION,\n" +
            "      NVL(A.NADRA_VERIFIED, 'N') AS NADRA_VERISYS, \n" +
            "      NVL(A.BVS,'N') AS BIOMETRIC_DONE,\n" +
            "      NVL(D.IS_T_AND_C_ACCEPTED,'N') AS T_C_APPROVAL, \n" +
            "      Z.COUNTRY_NAME AS COUNTRY_OF_RESIDENCE,Q.COUNTRY_NAME AS COUNTRY_OF_BIRTH, DN.COUNTRY_NAME,\n" +
            "      E.MONTHLY_CREDIT_AMOUNT, E.MONTHLY_DEBIT_AMOUNT,\n" +
            "      TRUNC(A.CREATEDATE) AS APPLICATION_SUBMITTED_DATE, TRUNC(A.LASTUPDATEDATE) AS APPLICATION_APPROVED_ON,\n" +
            "      J.LONGITUDE AS LONGITUDE , J.LATITUDE AS LATITUDE, A.CUSTOMER_ID AS INITIAL_TRACK_NUMBER,\n" +
            "      NIC_FRONT.DOCUMENT_PATH AS CNIC_SCAN_COPY,\n" +
            "      CUST_PIC.DOCUMENT_PATH AS LIVE_PHOTO,\n" +
            "      SIGN_PIC.DOCUMENT_PATH AS SIGNATURE,\n" +
            "      NIC_FRONT.DOCUMENT_PATH AS CNIC_FRONT_PIC,\n" +
            "      NIC_BACK.DOCUMENT_PATH AS CNIC_BACK_PIC, \n" +
            "      CUST_PIC.DOCUMENT_PATH AS CUSTOMER_PIC,\n" +
            "      SIGN_PIC.DOCUMENT_PATH AS SIGNATURE_PIC, POI.DOCUMENT_PATH AS PROOF_OF_INCOME_PIC, POP.DOCUMENT_PATH AS PROOF_OF_PROFESSION_PIC,\n" +
            "      BFP.DOCUMENT_PATH AS B_FORM, PCF.DOCUMENT_PATH AS PARENT_CNIC_FRONT, PCB.DOCUMENT_PATH AS PARENT_CNIC_BACK,\n" +
            "      C.ACCOUNT_LEVEL_NAME,A.NADRA_VERIFIED,E.STATUS_NAME, AB.REMARKS,\n" +
            "      A.CNIC_EXPIRY_DATE, D.IBAN,                      \n" +
            "      H.SEGMENT_NAME, L.CHANNEL_NAME, D.CREATEDATE AS ACCOUNT_OPENING_DATE,                          \n" +
            "      J.APP_VERSION_NAME, M.CREATEDATE AS ULTRA_AC_OPENING_DATE,                       \n" +
            "      F.FULL_ADDRESS, A.EMAIL, G.CITY_NAME,D.ACTUAL_BALANCE, D.ACCOUNT_ID,AA.ACCOUNT_STATUS_NAME,  \n" +
            "      US_BORN.ANSWER AS US_BORN, US_TEL_NO.ANSWER AS US_TEL_NO, US_SIGN_AUTH.ANSWER AS US_SIGN_AUTH, US_LINKS.ANSWER AS US_LINKS,\n" +
            "      R.FEDERAL_TAX_CLASSIFICATION_NAME, M.TAX_IDENTIFICATION_NUMBER, Z.COUNTRY_NAME AS JURISDICTION_OF_TAX_RESIDENCE_NAME,M.RESIDENCE_ADDRESS,\n" +
            "      TC_PIC.DOCUMENT_PATH AS TERMS_AND_CONDITION_PIC, CUST_VIDEO.DOCUMENT_PATH, M.PROOF_OF_INCOME_ID,  \n" +
            "      M.CHEQUE_BOOK, N.MONTHLY_SPENDING_NAME AS MONTHLY_SPENDING, \n" +
            "      A.CNIC_HASH,JSON_VALUE(A.KYC, '$.parentCnic' returning varchar2(200)) PARENT_CNIC, NVL(HRA_DATA.HRA,'N') HRA,  \n" +
            "      T.CURRENCY_NAME, UTLB.DOCUMENT_PATH AS UTILITY_BILL, UU.AREA_NAME, M.STREET_NO, M.HOUSE_NO,\n" +
            "      HRA_DATA.HRA_PURPOSE_OF_ACCOUNT,HRA_DATA.HRA_OCCUPATION,  HRA_DATA.HRA_SOURCE_OF_INCOME,\n" +
            "      HRA_DATA.HRA_ORIGINATOR_RELATIONSHIP_NAME, HRA_DATA.HRA_NOK_NAME, HRA_DATA.HRA_NOK_CNIC, HRA_DATA.HRA_NOK_MOBILE_NO,\n" +
            "      HRA_DATA.HRA_NOK_RELATIONSHIP_NAME, HRA_DATA.DAILY_DEBIT_AMOUNT AS DAILY_DEBIT_CONSUMED, HRA_DATA.DAILY_CREDIT_AMOUNT AS  DAILY_CREDIT_CONSUMED,\n" +
            "      HRA_DATA.MONTHLY_DEBIT_AMOUNT AS  MONTHLY_DEBIT_CONSUMED,  HRA_DATA.MONTHLY_CREDIT_AMOUNT AS  MONTHLY_CREDIT_CONSUMED,\n" +
            "      HRA_DATA.YEARLY_DEBIT_AMOUNT AS  YEARLY_DEBIT_CONSUMED,  HRA_DATA.YEARLY_CREDIT_AMOUNT AS  YEARLY_CREDIT_CONSUMED,\n" +
            "      HRA_DATA.DAILY_DEBIT_AMOUNT_REMAINING, HRA_DATA.DAILY_CREDIT_AMOUNT_REMAINING, HRA_DATA.MONTHLY_DEBIT_AMOUNT_REMAINING, HRA_DATA.MONTHLY_CREDIT_AMOUNT_REMAINING,\n" +
            "      HRA_DATA.YEARLY_DEBIT_AMOUNT_REMAINING, HRA_DATA.YEARLY_CREDIT_AMOUNT_REMAINING, HRA_DATA.DAILY_DEBIT_COUNT, HRA_DATA.MONTHLY_DEBIT_COUNT,\n" +
            "      HRA_DATA.YEARLY_DEBIT_COUNT,  HRA_DATA.DAILY_CREDIT_COUNT, HRA_DATA.MONTHLY_CREDIT_COUNT, HRA_DATA.YEARLY_CREDIT_COUNT,\n" +
            "      HRA_DATA.MAX_AMT_LIMIT AS MAXIMUM_BALANCE,HRA_DATA.HRA_ORIGINATOR_COUNTRY AS INTERNATIONAL_REMITTANCE_LOCATION,J.DEVICE_MODEL,D.AVAILABLE_BALANCE, \n" +
            "      O.OCCUPATION_NAME AS OCCUPATION,\n" +
            "      '' AS LAND_LINE_NO, '' SOURCE_OF_INCOME, 'Y' AS PAKISTANI_RESIDENT, 'N' AS US_RESIDENT, '' AS DISCREPANT_DOCS, '' AS AML, '' AS PIN_GENERATION,\n" +
            "      'Y' AS DIGITAL_CONSENT, 'Y' AS BENEFICIAL_OWNERSHIP_CONSENT, 'Y' AS FACTA, 'Y' AS ACCOUNT_OPENING_CONSENT\n" +
            "FROM TBL_CUSTOMER  A\n" +
            "INNER JOIN TBL_APP_USER X ON A.CUSTOMER_ID = X.CUSTOMER_ID\n" +
            "INNER JOIN TBL_ACCOUNT D ON A.CUSTOMER_ID = D.CUSTOMER_ID\n" +
            "INNER JOIN LKP_ACCOUNT_LEVEL C ON D.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID \n" +
            "INNER JOIN LKP_STATUS E ON E.STATUS_ID = A.STATUS_ID \n" +
            "INNER JOIN TBL_ADDRESS F ON A.CUSTOMER_ID = F.CUSTOMER_ID \n" +
            "LEFT JOIN LKP_CITY G ON F.CITY_ID = G.CITY_ID \n" +
            "INNER JOIN LKP_SEGMENT H ON A.SEGMENT_ID = H.SEGMENT_ID \n" +
            "INNER JOIN LKP_CHANNEL L ON L.CHANNEL_ID = D.CHANNEL_ID\n" +
            "INNER JOIN TBL_APP_USER  I ON I.CUSTOMER_ID = A.CUSTOMER_ID \n" +
            "LEFT JOIN TBL_DEVICE_INFO J ON I.APP_USER_ID = J.APP_USER_ID\n" +
            "INNER JOIN LKP_ACCOUNT_TYPE T ON D.ACCOUNT_TYPE_ID = T.ACCOUNT_TYPE_ID\n" +
            "INNER JOIN LKP_CURRENCY T ON T.CURRENCY_ID = D.CURRENCY_ID\n" +
            "LEFT JOIN TBL_ULTRA_CUSTOMER M ON M.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            "LEFT JOIN LKP_MONTHLY_SPENDING N ON M.EXPECTED_MONTHLY_CASHFLOW_ID = N.MONTHLY_SPENDING_ID\n" +
            "LEFT JOIN LKP_OCCUPATION O ON M.OCCUPATION_ID = O.OCCUPATION_ID\n" +
            "LEFT JOIN LKP_ULTRA_USAGE P ON M.ULTRA_USAGE_ID = P.ULTRA_USAGE_ID\n" +
            "LEFT JOIN LKP_COUNTRY Q ON M.COUNTRY_OF_BIRTH_ID = Q.COUNTRY_ID\n" +
            "LEFT JOIN LKP_COUNTRY DN ON M.COUNTRY_ID = DN.COUNTRY_ID\n" +
            "LEFT JOIN LKP_COUNTRY Z ON M.COUNTRY_OF_TAX_RESIDENCE_ID = Z.COUNTRY_ID\n" +
            "LEFT JOIN LKP_AREA UU  ON M.AREA_ID = UU.AREA_ID\n" +
            "LEFT JOIN LKP_ACCOUNT_STATUS AA ON AA.ACCOUNT_STATUS_ID=D.ACCOUNT_STATUS_ID\n" +
            "LEFT JOIN TBL_ACCOUNT_HIST  AB ON AB.ACCOUNT_ID=D.ACCOUNT_ID\n" +
            "INNER JOIN VW_CUSTOMER_LIMITS E ON D.ACCOUNT_ID = E.ACCOUNT_ID\n" +
            "LEFT JOIN (SELECT FULL_ADDRESS, CUSTOMER_ID \n" +
            "             FROM TBL_ADDRESS A\n" +
            "            INNER JOIN LKP_ADDRESS_TYPE P ON A.ADDRESS_TYPE_ID = P.ADDRESS_TYPE_ID\n" +
            "            WHERE P.ADDRESS_TYPE_NAME = 'MAILING ADDRESS') MAD ON A.CUSTOMER_ID = MAD.CUSTOMER_ID\n" +
            "LEFT JOIN (SELECT FULL_ADDRESS, CUSTOMER_ID \n" +
            "             FROM TBL_ADDRESS A\n" +
            "            INNER JOIN LKP_ADDRESS_TYPE P ON A.ADDRESS_TYPE_ID = P.ADDRESS_TYPE_ID\n" +
            "            WHERE P.ADDRESS_TYPE_NAME = 'PERMANENT ADDRESS') PAD ON A.CUSTOMER_ID = PAD.CUSTOMER_ID\n" +
            "LEFT JOIN LKP_FEDERAL_TAX_CLASSIFICATION R ON R.FEDERAL_TAX_CLASSIFICATION_ID = M.FEDERAL_TAX_CLASSIFICATION_ID\n" +
            "LEFT OUTER JOIN (SELECT ANSWER, ULTRA_CUSTOMER_ID\n" +
            "                   FROM TBL_ULTRA_CUSTOMER_FATCA \n" +
            "                  WHERE ULTRA_FATCA_QUESTION_ID = 1)  US_BORN ON US_BORN.ULTRA_CUSTOMER_ID = M.ULTRA_CUSTOMER_ID\n" +
            "LEFT OUTER JOIN (SELECT ANSWER, ULTRA_CUSTOMER_ID\n" +
            "                   FROM TBL_ULTRA_CUSTOMER_FATCA \n" +
            "                  WHERE ULTRA_FATCA_QUESTION_ID = 2)  US_TEL_NO ON US_TEL_NO.ULTRA_CUSTOMER_ID = M.ULTRA_CUSTOMER_ID\n" +
            "LEFT OUTER JOIN (SELECT ANSWER, ULTRA_CUSTOMER_ID\n" +
            "                   FROM TBL_ULTRA_CUSTOMER_FATCA \n" +
            "                  WHERE ULTRA_FATCA_QUESTION_ID = 3)  US_SIGN_AUTH ON US_SIGN_AUTH.ULTRA_CUSTOMER_ID = M.ULTRA_CUSTOMER_ID\n" +
            "LEFT OUTER JOIN (SELECT ANSWER, ULTRA_CUSTOMER_ID\n" +
            "                   FROM TBL_ULTRA_CUSTOMER_FATCA \n" +
            "                  WHERE ULTRA_FATCA_QUESTION_ID = 4)  US_LINKS ON US_LINKS.ULTRA_CUSTOMER_ID = M.ULTRA_CUSTOMER_ID \n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID \n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 15) CUST_VIDEO ON I.APP_USER_ID = CUST_VIDEO.APP_USER_ID  \n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID \n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 6)  TC_PIC ON I.APP_USER_ID = TC_PIC.APP_USER_ID \n" +
            "LEFT OUTER JOIN (SELECT DOCUMENT_PATH, APP_USER_ID \n" +
            "                   FROM TBL_DOCUMENT \n" +
            "                  WHERE DOCUMENT_TYPE_ID = 14) UTLB ON I.APP_USER_ID = UTLB.APP_USER_ID \n" +
            "LEFT OUTER JOIN (SELECT CUSTOMER_ID, FULL_ADDRESS \n" +
            "                   FROM TBL_ADDRESS \n" +
            "                  WHERE ADDRESS_TYPE_ID = 4) MAIL_ADD ON A.CUSTOMER_ID = MAIL_ADD.CUSTOMER_ID                                        \n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "             FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 5) CUST_PIC ON CUST_PIC.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 3\n" +
            "                 ) NIC_FRONT ON NIC_FRONT.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 4\n" +
            "                 ) NIC_BACK ON NIC_BACK.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 7\n" +
            "                 ) SIGN_PIC ON SIGN_PIC.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 10\n" +
            "                 ) POI ON POI.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 19\n" +
            "                 ) POP ON POP.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 11\n" +
            "                 ) PCF ON PCF.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 13\n" +
            "                 ) PCB ON PCB.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT JOIN (SELECT B.APP_USER_ID, B.DOCUMENT_PATH , B.DOCUMENT_TYPE_ID\n" +
            "               FROM TBL_DOCUMENT B WHERE B.DOCUMENT_TYPE_ID = 12\n" +
            "                 ) BFP ON BFP.APP_USER_ID = X.APP_USER_ID\n" +
            "LEFT OUTER JOIN (SELECT JSON_VALUE(B.KYC, '$.accountPurposeName' returning varchar2(200)) AS HRA_PURPOSE_OF_ACCOUNT,'Y' HRA,\n" +
            "                        JSON_VALUE(B.KYC, '$.occupationName' returning varchar2(200)) AS HRA_OCCUPATION,\n" +
            "                        JSON_VALUE(B.KYC, '$.sourceOfIncomeName' returning varchar2(200)) AS HRA_SOURCE_OF_INCOME,\n" +
            "                        JSON_VALUE(B.KYC, '$.originatorInfo[0].relationshipName' returning varchar2(200)) AS HRA_ORIGINATOR_RELATIONSHIP_NAME,\n" +
            "                        JSON_VALUE(B.KYC, '$.nokName' returning varchar2(200)) AS HRA_NOK_NAME,\n" +
            "                        JSON_VALUE(B.KYC, '$.nokCnic' returning varchar2(200)) AS HRA_NOK_CNIC,\n" +
            "                        JSON_VALUE(B.KYC, '$.nokMobileNo' returning varchar2(200)) AS HRA_NOK_MOBILE_NO,\n" +
            "                        JSON_VALUE(B.KYC, '$.nokRelationshipName' returning varchar2(200)) AS HRA_NOK_RELATIONSHIP_NAME,\n" +
            "                        JSON_VALUE(B.KYC, '$.originatorInfo[0].countryName' returning varchar2(200)) AS HRA_ORIGINATOR_COUNTRY,\n" +
            "                                            B.CNIC_HASH,   \n" +
            "                                            K.DAILY_DEBIT_AMOUNT, K.DAILY_CREDIT_AMOUNT,\n" +
            "                                            K.MONTHLY_DEBIT_AMOUNT,  K.MONTHLY_CREDIT_AMOUNT,\n" +
            "                                            K.YEARLY_DEBIT_AMOUNT,  K.YEARLY_CREDIT_AMOUNT,\n" +
            "                                            DAILY_DEBIT_AMOUNT_REMAINING, DAILY_CREDIT_AMOUNT_REMAINING,\n" +
            "                                            K.MONTHLY_DEBIT_AMOUNT_REMAINING, K.MONTHLY_CREDIT_AMOUNT_REMAINING,\n" +
            "                                            K.YEARLY_DEBIT_AMOUNT_REMAINING, K.YEARLY_CREDIT_AMOUNT_REMAINING,\n" +
            "                                            K.DAILY_DEBIT_COUNT, K.MONTHLY_DEBIT_COUNT, K.YEARLY_DEBIT_COUNT,\n" +
            "                                            K.DAILY_CREDIT_COUNT, K.MONTHLY_CREDIT_COUNT, K.YEARLY_CREDIT_COUNT, L.MAX_AMT_LIMIT                                                                                          \n" +
            "                FROM TBL_CUSTOMER B \n" +
            "                INNER JOIN TBL_ACCOUNT A ON B.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            "                INNER JOIN LKP_ACCOUNT_LEVEL L ON A.ACCOUNT_LEVEL_ID = L.ACCOUNT_LEVEL_ID\n" +
            "                INNER JOIN VW_CUSTOMER_LIMITS K ON K.ACCOUNT_ID = A.ACCOUNT_ID \n" +
            "                WHERE A.ACCOUNT_TYPE_ID = 3) HRA_DATA ON HRA_DATA.CNIC_HASH = A.CNIC_HASH\n" +
            "WHERE A.MOBILE_NO_HASH =  SHA256.ENCRYPT(:mobileNumber)\n" +
            "AND T.ACCOUNT_TYPE_NAME = 'WALLET' AND ROWNUM <= 1", nativeQuery = true)
    Object getCustomerInfo(String mobileNumber);

    @Query(value = "SELECT * FROM TBL_CUSTOMER WHERE CNIC_HASH = SHA256.ENCRYPT(:cnic) AND IS_ACTIVE='Y'", nativeQuery = true)
    TblCustomer findCustomerByCnic(@Param("cnic") String cnic);


    @Query(value = "SELECT * FROM TBL_CUSTOMER WHERE CNIC_HASH = SHA256.ENCRYPT(:cnic) AND IS_ACTIVE = 'Y'", nativeQuery = true)
    TblCustomer findActiveCustomer(@Param("cnic") String cnic);

    @Query(value = "SELECT COUNT(EMAIL_USED) ACCOUNTS, EMAIL_USED FROM (\r\n"
            + "    SELECT CASE WHEN A.EMAIL IS NULL THEN 'NO' ELSE 'YES' END AS EMAIL_USED\r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "    INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\r\n"
            + "    LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND NVL(J.CITY_ID,0) = NVL(X.REG_CITY_ID,NVL(J.CITY_ID,0))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + ") GROUP BY EMAIL_USED\r\n"
            + "", nativeQuery = true)
    List<Object[]> getEmailCount();

    @Query(value = "SELECT COUNT(ACCOUNT_DISBURSMENT_TYPE) ACCOUNTS, ACCOUNT_DISBURSMENT_TYPE FROM (\r\n"
            + "SELECT CASE WHEN NVL(B.IS_DISBURSMENT_ACCOUNT,'N') = 'N' THEN 'NON PAYROLL' ELSE 'PAYROLL' END AS ACCOUNT_DISBURSMENT_TYPE\r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "    INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\r\n"
            + "    LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND NVL(J.CITY_ID,0) = NVL(X.REG_CITY_ID,NVL(J.CITY_ID,0))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + ") GROUP BY ACCOUNT_DISBURSMENT_TYPE\r\n"
            + "", nativeQuery = true)
    List<Object[]> getAccountsDisbursmentCount();


    @Query(value = "SELECT COUNT(*) ACCOUNTS, F.CHANNEL_NAME \r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "    INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\r\n"
            + "    LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND NVL(J.CITY_ID,0) = NVL(X.REG_CITY_ID,NVL(J.CITY_ID,0))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "GROUP BY F.CHANNEL_NAME\r\n"
            + "", nativeQuery = true)
    List<Object[]> getRegisterationChannel();


    @Query(value = "SELECT COUNT(*) ACCOUNTS, J.CITY_NAME \r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "   INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "   INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\r\n"
            + "    LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND NVL(J.CITY_ID,0) = NVL(X.REG_CITY_ID,NVL(J.CITY_ID,0))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "GROUP BY J.CITY_NAME\r\n"
            + "", nativeQuery = true)
    List<Object[]> getCityWiseRegisterationData();


    @Query(value = "SELECT COUNT(*) ACCOUNTS, H.DEVICE_TYPE AS OS_TYPE, TRUNC(B.CREATEDATE) AS DATED\r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "   INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "   INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\r\n"
            + "    LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND NVL(J.CITY_ID,0) = NVL(X.REG_CITY_ID,NVL(J.CITY_ID,0))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "GROUP BY H.DEVICE_TYPE, TRUNC(B.CREATEDATE) ORDER BY TRUNC(B.CREATEDATE)\r\n"
            + "", nativeQuery = true)
    List<Object[]> getOsTypeWiseData();


    @Query(value = "SELECT COUNT(*) ACCOUNTS, C.ACCOUNT_LEVEL_NAME\r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "   INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "   INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\r\n"
            + "    LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND NVL(J.CITY_ID,0) = NVL(X.REG_CITY_ID,NVL(J.CITY_ID,0))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "GROUP BY C.ACCOUNT_LEVEL_NAME\r\n"
            + "", nativeQuery = true)
    List<Object[]> getNumberOfAccounts();


    @Query(value = "SELECT SUM(TRANSACTIONS_COUNT_OUTGOING_IBFT) TRANSACTIONS_COUNT_OUTGOING_IBFT,\r\n"
            + "        SUM(TRANSACTIONS_COUNT_INCOMING_IBFT) TRANSACTIONS_COUNT_INCOMING_IBFT,\r\n"
            + "        SUM (OUTGOING_IBFT_AMOUNT) AS OUTGOING_IBFT_AMOUNT, \r\n"
            + "        SUM(INCOMING_IBFT_AMOUNT) AS INCOMING_IBFT_AMOUNT,\r\n"
            + "        BANK_NAME  \r\n"
            + "    FROM (\r\n"
            + "     SELECT 0 AS TRANSACTIONS_COUNT_OUTGOING_IBFT, COUNT(*) AS TRANSACTIONS_COUNT_INCOMING_IBFT,\r\n"
            + "            0 AS OUTGOING_IBFT_AMOUNT, SUM(F.TRANSACTION_AMOUNT) AS INCOMING_IBFT_AMOUNT, G.BANK_NAME \r\n"
            + "        FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                              TRANS_IBFT_BANK_ID, TRANS_IBFT_AMOUNT   \r\n"
            + "                       FROM TBL_RISK_DASHBOARD \r\n"
            + "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "            INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "            LEFT JOIN TBL_CMS_BANK G ON G.CMS_BANK_ID = F.DR_BANK_ID\r\n"
            + "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "            AND F.PRODUCT_ID  = 10245434\r\n"
            + "            AND NVL(F.CR_BANK_ID,'0') = NVL(X.TRANS_IBFT_BANK_ID, NVL(F.CR_BANK_ID,'0'))   \r\n"
            + "            AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_IBFT_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0'))   \r\n"
            + "        GROUP BY G.BANK_NAME\r\n"
            + " \r\n"
            + "      UNION ALL\r\n"
            + "      \r\n"
            + "      SELECT COUNT(*) AS TRANSACTIONS_COUNT_OUTGOING_IBFT, 0 AS TRANSACTIONS_COUNT_INCOMING_IBFT,\r\n"
            + "             SUM(F.TRANSACTION_AMOUNT) AS OUTGOING_IBFT_AMOUNT, 0 AS INCOMING_IBFT_AMOUNT, G.BANK_NAME \r\n"
            + "        FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                              TRANS_IBFT_BANK_ID, TRANS_IBFT_AMOUNT   \r\n"
            + "                       FROM TBL_RISK_DASHBOARD \r\n"
            + "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "            INNER JOIN TBL_TRANS_HEAD F ON (F.CR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "            LEFT JOIN TBL_CMS_BANK G ON G.CMS_BANK_ID = F.CR_BANK_ID\r\n"
            + "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "            AND F.PRODUCT_ID  = 10245435\r\n"
            + "            AND NVL(F.DR_BANK_ID,'0') = NVL(X.TRANS_IBFT_BANK_ID, NVL(F.DR_BANK_ID,'0'))   \r\n"
            + "            AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_IBFT_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0'))   \r\n"
            + "        GROUP BY G.BANK_NAME\r\n"
            + "        ) GROUP BY BANK_NAME\r\n"
            + "", nativeQuery = true)
    List<Object[]> getIbftInOutReport();


    @Query(value = "SELECT  COUNT(*) AS CNT, SUM(F.TRANSACTION_AMOUNT) AS TRANSACTION_AMOUNT, F.PRODUCT_NAME \r\n"
            + "        FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                              TRANS_IBFT_BANK_ID, TRANS_IBFT_AMOUNT   \r\n"
            + "                       FROM TBL_RISK_DASHBOARD \r\n"
            + "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "            INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "GROUP BY F.PRODUCT_NAME\r\n"
            + "", nativeQuery = true)
    List<Object[]> getTransectionTypeData();


    @Query(value = "SELECT  COUNT(*) AS CNT, SUM(F.TRANSACTION_AMOUNT) AS TRANSACTION_AMOUNT\r\n"
            + "        FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                              TRANS_P2P_AMOUNT   \r\n"
            + "                       FROM TBL_RISK_DASHBOARD \r\n"
            + "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "            INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "            AND F.PRODUCT_ID IN (50011, 50010, 2510801)\r\n"
            + "            AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_P2P_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0')) \r\n"
            + "GROUP BY F.PRODUCT_NAME\r\n"
            + "", nativeQuery = true)
    List<Object[]> getP2pData();


    @Query(value = "SELECT  COUNT(*) AS CNT, SUM(F.TRANSACTION_AMOUNT) AS TRANSACTION_AMOUNT, G.NAME_ENGLISH PRODUCT, TRUNC(F.CREATEDATE) DATED\r\n"
            + "        FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                             TRANS_UBP_COMPANY, TRANS_UBP_AMOUNT   \r\n"
            + "                       FROM TBL_RISK_DASHBOARD \r\n"
            + "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "            INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "            INNER JOIN TBL_CMS_COMPANY G ON F.UTIILITY_COMPANY_CODE = G.PRODUCT_ID\r\n"
            + "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "            AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_UBP_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0'))\r\n"
            + "            AND G.NAME_ENGLISH = NVL(X.TRANS_UBP_COMPANY, G.NAME_ENGLISH) \r\n"
            + "            \r\n"
            + "        GROUP BY TRUNC(F.CREATEDATE), G.NAME_ENGLISH\r\n"
            + "", nativeQuery = true)
    List<Object[]> getUbpData();


    @Query(value = "SELECT SUM(TRANSACTIONS_COUNT_CASH_OUT) + SUM(TRANSACTIONS_COUNT_CASH_IN) TRANSACTIONS_COUNT_CASH_IN_OUT,\n" +
            "        SUM (CASH_IN_AMOUNT) + SUM (CASH_OUT_AMOUNT) AS CASH_IN_OUT_AMOUNT,\n" +
            "        CITY_NAME  \n" +
            "    FROM (\n" +
            "     SELECT 0 AS TRANSACTIONS_COUNT_CASH_OUT, COUNT(*) AS TRANSACTIONS_COUNT_CASH_IN,\n" +
            "            0 AS CASH_IN_AMOUNT, SUM(F.TRANSACTION_AMOUNT) AS CASH_OUT_AMOUNT, J.CITY_NAME \n" +
            "        FROM TBL_CUSTOMER A \n" +
            "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID \n" +
            "            INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\n" +
            "            LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\n" +
            "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\n" +
            "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\n" +
            "                             TRANS_CASH_INOUT_AMOUNT  \n" +
            "                       FROM TBL_RISK_DASHBOARD \n" +
            "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\n" +
            "            INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\n" +
            "            LEFT JOIN LKP_BANK G ON G.BANK_ID = F.CR_BANK_ID\n" +
            "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\n" +
            "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\n" +
            "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\n" +
            "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\n" +
            "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \n" +
            "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\n" +
            "            AND F.PRODUCT_ID  = 50002\n" +
            "            \n" +
            "            AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_CASH_INOUT_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0'))   \n" +
            "        GROUP BY J.CITY_NAME \n" +
            " \n" +
            "      UNION ALL\n" +
            "      \n" +
            "      SELECT COUNT(*) AS TRANSACTIONS_COUNT_CASH_OUT, 0 AS TRANSACTIONS_COUNT_CASH_IN,\n" +
            "             SUM(F.TRANSACTION_AMOUNT) AS CASH_IN_AMOUNT, 0 AS CASH_OUT_AMOUNT, J.CITY_NAME  \n" +
            "        FROM TBL_CUSTOMER A \n" +
            "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\n" +
            "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \n" +
            "            INNER JOIN TBL_ADDRESS I ON A.CUSTOMER_ID = I.CUSTOMER_ID\n" +
            "            LEFT JOIN LKP_CITY J ON I.CITY_ID = J.CITY_ID\n" +
            "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\n" +
            "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\n" +
            "                              TRANS_CASH_INOUT_AMOUNT   \n" +
            "                       FROM TBL_RISK_DASHBOARD \n" +
            "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\n" +
            "            INNER JOIN TBL_TRANS_HEAD F ON (F.CR_ACCOUNT_ID = B.ACCOUNT_ID)\n" +
            "            LEFT JOIN LKP_BANK G ON G.BANK_ID = F.DR_BANK_ID\n" +
            "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\n" +
            "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\n" +
            "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\n" +
            "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\n" +
            "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \n" +
            "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\n" +
            "            AND F.PRODUCT_ID IN (10245111,10245115, 10245160,50002,50006, 50051, 2510802, 10245315,10245255,10245256, 50050, 10245312, 10245313, 10245297,50026, 50053 ,50000, 50010)\n" +
            "            AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_CASH_INOUT_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0'))   \n" +
            "        GROUP BY J.CITY_NAME \n" +
            "        ) GROUP BY CITY_NAME", nativeQuery = true)
    List<Object[]> getCashInOutData();


    @Query(value = "SELECT  COUNT(*) AS CNT, SUM(F.TRANSACTION_AMOUNT) AS TRANSACTION_AMOUNT, G.NAME_ENGLISH PRODUCT\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "    INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     TRANS_ELOAD_COMPANY, TRANS_ELOAD_AMOUNT   \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "    INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "    INNER JOIN TBL_CMS_COMPANY G ON F.UTIILITY_COMPANY_CODE = G.PRODUCT_ID\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "    AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "    AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "    AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "    AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "    AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "    AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_ELOAD_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0'))\r\n"
            + "    AND G.NAME_ENGLISH = NVL(X.TRANS_ELOAD_COMPANY, G.NAME_ENGLISH)\r\n"
            + "    AND G.NAME_ENGLISH IN ('JAZZ','UFONE','TELENOR','ZONG') \r\n"
            + "GROUP BY G.NAME_ENGLISH", nativeQuery = true)
    List<Object[]> getEloadData();


    @Query(value = "SELECT COUNT(CUSTOMER_ID) AS CNT, CATEGORY_NAME, TRANS_BALANCE_BRACKETS \r\n"
            + "FROM (\r\n"
            + " \r\n"
            + "SELECT  B.CUSTOMER_ID, X.TRANS_BALANCE_BRACKETS,\r\n"
            + "(CASE  \r\n"
            + "            WHEN (B.ACTUAL_BALANCE) <=500 THEN '0<500'\r\n"
            + "            WHEN (B.ACTUAL_BALANCE) >500 AND  (B.ACTUAL_BALANCE) <=5000 THEN '>500<5000'\r\n"
            + "            WHEN (B.ACTUAL_BALANCE) >5000 AND  (B.ACTUAL_BALANCE) <=25000 THEN '>5000<25000'\r\n"
            + "            WHEN (B.ACTUAL_BALANCE) >25000 AND  (B.ACTUAL_BALANCE) <=50000 THEN '>25000<50000'\r\n"
            + "            WHEN (B.ACTUAL_BALANCE) >50000  THEN '>50000'\r\n"
            + "          END\r\n"
            + "        ) CATEGORY_NAME\r\n"
            + " \r\n"
            + "        FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                             TRANS_P2P_AMOUNT, TRANS_BALANCE_BRACKETS   \r\n"
            + "                       FROM TBL_RISK_DASHBOARD \r\n"
            + "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "          AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "          AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "          AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "          AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "          AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + ") \r\n"
            + "WHERE CATEGORY_NAME = NVL(TRANS_BALANCE_BRACKETS,CATEGORY_NAME)\r\n"
            + "GROUP BY CATEGORY_NAME, TRANS_BALANCE_BRACKETS\r\n"
            + "", nativeQuery = true)
    List<Object[]> getBalanceBracketsData();


    @Query(value = "SELECT PRODUCT_NAME, SUM(WHTAX) WHTAX, SUM(COMMISSION_AMOUNT) COMMISSION_AMOUNT\r\n"
            + "FROM (\r\n"
            + "SELECT CASE WHEN A.CHARGES_ID = 3 THEN NVL(A.AMOUNT_CR,0) ELSE 0 END AS COMMISSION_AMOUNT,\r\n"
            + "       CASE WHEN A.CHARGES_ID = 4 THEN NVL(A.AMOUNT_DR,0) ELSE 0 END AS WHTAX, P.PRODUCT_NAME\r\n"
            + "FROM TBL_TRANS_DETAIL A\r\n"
            + "INNER JOIN TBL_TRANS_HEAD H ON A.TRANS_HEAD_ID = H.TRANS_HEAD_ID \r\n"
            + "INNER JOIN TBL_PRODUCT P ON H.PRODUCT_ID = P.PRODUCT_ID\r\n"
            + "LEFT JOIN (SELECT '1' AS JOIN_COL, TRANS_RC_PRODUCT_ID, TRANS_RC_WHTAX,TRANS_RC_AMOUNT, FROM_DATE, TO_DATE   \r\n"
            + "             FROM TBL_RISK_DASHBOARD \r\n"
            + "            WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "WHERE H.PRODUCT_ID = NVL(X.TRANS_RC_PRODUCT_ID, H.PRODUCT_ID)\r\n"
            + "AND H.TRANSACTION_DATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), H.TRANSACTION_DATE) \r\n"
            + "                           AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), H.TRANSACTION_DATE)\r\n"
            + "AND NVL(A.AMOUNT_CR,0) = NVL(X.TRANS_RC_AMOUNT, NVL(A.AMOUNT_CR,0))\r\n"
            + "AND NVL(A.AMOUNT_DR,0) = NVL(X.TRANS_RC_WHTAX, NVL(A.AMOUNT_DR,0))\r\n"
            + "AND A.CHARGES_ID IN (3,4) -- AGENT COMMISSION, WHTAX\r\n"
            + "AND A.ACCOUNT_TYPE = 'A')\r\n"
            + "GROUP BY PRODUCT_NAME\r\n"
            + "", nativeQuery = true)
    List<Object[]> getRetailerCommissionData();


    @Query(value = "SELECT  SUM(CASE WHEN C.DAILY_AMT_LIMIT_DR < G.DAILY_AMT_LIMIT_DR THEN G.DAILY_AMT_LIMIT_DR - C.DAILY_AMT_LIMIT_DR ELSE 0 END +\r\n"
            + "        CASE WHEN C.MONTHLY_AMT_LIMIT_DR < G.MONTHLY_AMT_LIMIT_DR THEN G.MONTHLY_AMT_LIMIT_DR - C.MONTHLY_AMT_LIMIT_DR ELSE 0 END+\r\n"
            + "        CASE WHEN C.YEARLY_AMT_LIMIT_DR < G.YEARLY_AMT_LIMIT_DR THEN G.YEARLY_AMT_LIMIT_DR - C.YEARLY_AMT_LIMIT_DR ELSE 0 END +\r\n"
            + "        CASE WHEN C.DAILY_AMT_LIMIT_CR < G.DAILY_AMT_LIMIT_CR THEN G.DAILY_AMT_LIMIT_CR - C.DAILY_AMT_LIMIT_CR ELSE 0 END +\r\n"
            + "        CASE WHEN C.MONTHLY_AMT_LIMIT_CR < G.MONTHLY_AMT_LIMIT_CR THEN G.MONTHLY_AMT_LIMIT_CR - C.MONTHLY_AMT_LIMIT_CR ELSE 0 END +\r\n"
            + "        CASE WHEN C.YEARLY_AMT_LIMIT_CR < G.YEARLY_AMT_LIMIT_CR THEN G.YEARLY_AMT_LIMIT_CR - C.YEARLY_AMT_LIMIT_CR ELSE 0 END) AS AMOUNT,\r\n"
            + "        SUM(CASE WHEN C.DAILY_TRANS_LIMIT_DR < G.DAILY_TRANS_LIMIT_DR THEN G.DAILY_TRANS_LIMIT_DR - C.DAILY_TRANS_LIMIT_DR ELSE 0 END +\r\n"
            + "        CASE WHEN C.MONTHLY_TRANS_LIMIT_DR < G.MONTHLY_TRANS_LIMIT_DR THEN G.MONTHLY_TRANS_LIMIT_DR - C.MONTHLY_TRANS_LIMIT_DR ELSE 0 END +\r\n"
            + "        CASE WHEN C.YEARLY_TRANS_LIMIT_DR < G.YEARLY_TRANS_LIMIT_DR THEN G.YEARLY_TRANS_LIMIT_DR - C.YEARLY_TRANS_LIMIT_DR ELSE 0 END +\r\n"
            + "        CASE WHEN C.DAILY_TRANS_LIMIT_CR < G.DAILY_TRANS_LIMIT_CR THEN G.DAILY_TRANS_LIMIT_CR - C.DAILY_TRANS_LIMIT_CR ELSE 0 END +\r\n"
            + "        CASE WHEN C.MONTHLY_TRANS_LIMIT_CR < G.MONTHLY_TRANS_LIMIT_CR THEN G.MONTHLY_TRANS_LIMIT_CR - C.MONTHLY_TRANS_LIMIT_CR ELSE 0 END +\r\n"
            + "        CASE WHEN C.YEARLY_TRANS_LIMIT_CR < G.YEARLY_TRANS_LIMIT_CR THEN G.YEARLY_TRANS_LIMIT_CR - C.YEARLY_TRANS_LIMIT_CR ELSE 0 END) AS COUNT\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "    INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                      TRANS_UBP_COMPANY, TRANS_UBP_AMOUNT   \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1 = X.JOIN_COL\r\n"
            + "    INNER JOIN TBL_ACCOUNT_LIMIT G ON G.ACCOUNT_ID = B.ACCOUNT_ID\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "    AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "    AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "    AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "    AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "    AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "    AND (C.DAILY_AMT_LIMIT_DR < G.DAILY_AMT_LIMIT_DR \r\n"
            + "         OR C.MONTHLY_AMT_LIMIT_DR < G.MONTHLY_AMT_LIMIT_DR\r\n"
            + "         OR C.YEARLY_AMT_LIMIT_DR < G.YEARLY_AMT_LIMIT_DR\r\n"
            + "         OR C.DAILY_AMT_LIMIT_CR < G.DAILY_AMT_LIMIT_CR \r\n"
            + "         OR C.MONTHLY_AMT_LIMIT_CR < G.MONTHLY_AMT_LIMIT_CR\r\n"
            + "         OR C.YEARLY_AMT_LIMIT_CR < G.YEARLY_AMT_LIMIT_CR\r\n"
            + "         OR C.DAILY_TRANS_LIMIT_DR < G.DAILY_TRANS_LIMIT_DR \r\n"
            + "         OR C.MONTHLY_TRANS_LIMIT_DR < G.MONTHLY_AMT_LIMIT_DR\r\n"
            + "         OR C.YEARLY_TRANS_LIMIT_DR < G.YEARLY_TRANS_LIMIT_DR\r\n"
            + "         OR C.DAILY_TRANS_LIMIT_CR < G.DAILY_TRANS_LIMIT_CR \r\n"
            + "         OR C.MONTHLY_TRANS_LIMIT_CR < G.MONTHLY_TRANS_LIMIT_CR\r\n"
            + "         OR C.YEARLY_TRANS_LIMIT_CR < G.YEARLY_TRANS_LIMIT_CR)\r\n"
            + "", nativeQuery = true)
    List<Object[]> getEbLimitCustomerData();


    @Query(value = "SELECT TRANSACTION_DATE, SUM(IBFT) AS IBFT, SUM(TOHFA) AS TOHFA, SUM(FUND_TRANSFER) AS FUND_TRANSFER FROM (\r\n"
            + "SELECT  F.CR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE) TRANSACTION_DATE, COUNT(F.DR_ACCOUNT_ID)  AS IBFT, 0 AS TOHFA, 0 AS FUND_TRANSFER\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                 FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                 RBS_MTO_TOHFA, RBS_MTO_IBFT, RBS_MTO_P2P   \r\n"
            + "           FROM TBL_RISK_DASHBOARD \r\n"
            + "          WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "                     AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "AND X.RBS_MTO_IBFT = 'Y' AND F.PRODUCT_ID = 10245435\r\n"
            + "HAVING COUNT(F.DR_ACCOUNT_ID) > 1     \r\n"
            + "GROUP BY F.CR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE)\r\n"
            + "\r\n"
            + "UNION ALL\r\n"
            + "\r\n"
            + "SELECT  F.CR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE) TRANSACTION_DATE, 0  AS IBFT, COUNT(F.DR_ACCOUNT_ID) AS TOHFA, 0 AS FUND_TRANSFER\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                 FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                 RBS_MTO_TOHFA, RBS_MTO_IBFT, RBS_MTO_P2P   \r\n"
            + "           FROM TBL_RISK_DASHBOARD \r\n"
            + "          WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "                     AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "AND X.RBS_MTO_TOHFA = 'Y' AND F.PRODUCT_ID = 10245436\r\n"
            + "HAVING COUNT(F.DR_ACCOUNT_ID) > 1     \r\n"
            + "GROUP BY F.CR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE)\r\n"
            + "\r\n"
            + "UNION ALL\r\n"
            + "\r\n"
            + "SELECT  F.CR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE) TRANSACTION_DATE,  0  AS IBFT,0 AS TOHFA,  COUNT(F.DR_ACCOUNT_ID)  AS FUND_TRANSFER\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                 FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                 RBS_MTO_TOHFA, RBS_MTO_IBFT, RBS_MTO_P2P   \r\n"
            + "           FROM TBL_RISK_DASHBOARD \r\n"
            + "          WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "                     AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "AND X.RBS_MTO_P2P = 'Y' AND F.PRODUCT_ID = 10245399\r\n"
            + "HAVING COUNT(F.DR_ACCOUNT_ID) > 1     \r\n"
            + "GROUP BY F.CR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE)\r\n"
            + ") GROUP BY TRANSACTION_DATE", nativeQuery = true)
    List<Object[]> getManyToOneFundTranferData();


    @Query(value = "SELECT TRANSACTION_DATE, SUM(IBFT) AS IBFT, SUM(TOHFA) AS TOHFA, SUM(FUND_TRANSFER) AS FUND_TRANSFER\r\n"
            + "FROM (\r\n"
            + "SELECT COUNT(F.CR_ACCOUNT_ID), F.DR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE) TRANSACTION_DATE, COUNT(F.DR_ACCOUNT_ID)  AS IBFT, 0 AS TOHFA, 0 AS FUND_TRANSFER\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                 FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                 RBS_OTM_TOHFA, RBS_OTM_IBFT, RBS_OTM_P2P   \r\n"
            + "           FROM TBL_RISK_DASHBOARD \r\n"
            + "          WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "                     AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "AND X.RBS_OTM_IBFT = 'Y' AND F.PRODUCT_ID = 10245434\r\n"
            + "HAVING COUNT(F.CR_ACCOUNT_ID) > 1     \r\n"
            + "GROUP BY F.DR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE)\r\n"
            + "\r\n"
            + "UNION ALL\r\n"
            + "\r\n"
            + "SELECT COUNT(F.CR_ACCOUNT_ID), F.DR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE) TRANSACTION_DATE,  0  AS IBFT, COUNT(F.DR_ACCOUNT_ID) AS TOHFA, 0 AS FUND_TRANSFER\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                 FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                 RBS_OTM_TOHFA, RBS_OTM_IBFT, RBS_OTM_P2P   \r\n"
            + "           FROM TBL_RISK_DASHBOARD \r\n"
            + "          WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "                     AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "AND X.RBS_OTM_TOHFA = 'Y' AND F.PRODUCT_ID = 10245436\r\n"
            + "HAVING COUNT(F.CR_ACCOUNT_ID) > 1     \r\n"
            + "GROUP BY F.DR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE)\r\n"
            + "\r\n"
            + "UNION ALL\r\n"
            + "\r\n"
            + "SELECT COUNT(F.CR_ACCOUNT_ID), F.DR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE) TRANSACTION_DATE,  0  AS IBFT,0 AS TOHFA,  COUNT(F.DR_ACCOUNT_ID)  AS FUND_TRANSFER\r\n"
            + "FROM TBL_CUSTOMER A \r\n"
            + "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                 FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                 RBS_OTM_TOHFA, RBS_OTM_IBFT, RBS_OTM_P2P   \r\n"
            + "           FROM TBL_RISK_DASHBOARD \r\n"
            + "          WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "                     AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "AND X.RBS_OTM_P2P = 'Y' AND F.PRODUCT_ID = 10245399\r\n"
            + "HAVING COUNT(F.CR_ACCOUNT_ID) > 1     \r\n"
            + "GROUP BY F.DR_ACCOUNT_ID, TRUNC(F.TRANSACTION_DATE))\r\n"
            + "GROUP BY TRANSACTION_DATE", nativeQuery = true)
    List<Object[]> getOneToManyFundTranferData();


    @Query(value = "SELECT  COUNT(*) AS CNT, ROUND(SUM(F.TRANSACTION_AMOUNT)/COUNT(*)) AS TRANSACTION_PER_COUNT, SUM(F.TRANSACTION_AMOUNT) TRANSACTION_AMOUNT\r\n"
            + "        FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \r\n"
            + "            LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                             FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                              TRANS_UBP_COMPANY, TRANS_UBP_AMOUNT   \r\n"
            + "                       FROM TBL_RISK_DASHBOARD \r\n"
            + "                      WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "            INNER JOIN TBL_TRANS_HEAD F ON (F.DR_ACCOUNT_ID = B.ACCOUNT_ID)\r\n"
            + "        WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "            AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "            AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "            AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "            AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "            AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "            AND NVL(F.TRANSACTION_AMOUNT,'0') = NVL(X.TRANS_UBP_AMOUNT, NVL(F.TRANSACTION_AMOUNT,'0'))", nativeQuery = true)
    List<Object[]> getCustomerUbpsData();


    @Query(value = "SELECT COUNT(DISTINCT F.CHANNEL_NAME) CHANNEL,COUNT(B.ACCOUNT_ID) CNT, TRUNC(B.STATUS_CHANGE_DATE) AS DATED\r\n"
            + "             FROM TBL_CUSTOMER A \r\n"
            + "            INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "            INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "            INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "            LEFT JOIN LKP_ACCOUNT_STATUS PS ON B.PREVIOUS_STATUS_ID  = PS.ACCOUNT_STATUS_ID\r\n"
            + "             LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                                FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID, RISK_DASHBOARD_ID\r\n"
            + "                          FROM TBL_RISK_DASHBOARD \r\n"
            + "                         WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + "            WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "              AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "              AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "              AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "              AND E.ACCOUNT_STATUS_NAME = 'ACTIVE'\r\n"
            + "              AND F.CHANNEL_NAME IN (SELECT DS_NAME FROM TBL_RISK_DASHBOARD_DS WHERE IS_ACTIVE = 'Y' AND DS_TYPE = 'C' AND RISK_DASHBOARD_ID = X.RISK_DASHBOARD_ID)\r\n"
            + "              AND PS.ACCOUNT_STATUS_NAME IN (SELECT DS_NAME FROM TBL_RISK_DASHBOARD_DS WHERE IS_ACTIVE = 'Y' AND DS_TYPE = 'S' AND RISK_DASHBOARD_ID = X.RISK_DASHBOARD_ID)\r\n"
            + "              AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "              AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + "            GROUP BY B.STATUS_CHANGE_DATE", nativeQuery = true)
    List<Object[]> getDormancyRevivalData();


    @Query(value = "SELECT  C.ACCOUNT_LEVEL_NAME, COUNT(*) AS CNT, SUM(B.ACTUAL_BALANCE) TOTAL_BALANCE\n" +
            "FROM TBL_CUSTOMER A \n" +
            "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\n" +
            "INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\n" +
            "INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\n" +
            "INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID  \n" +
            "LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE, RISK_DASHBOARD_ID,\n" +
            "                  FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID\n" +
            "             FROM TBL_RISK_DASHBOARD \n" +
            "            WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL      \n" +
            "WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\n" +
            "AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\n" +
            "AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\n" +
            "AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\n" +
            "AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \n" +
            "AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\n" +
            "GROUP BY C.ACCOUNT_LEVEL_NAME, B.AVAILABLE_BALANCE", nativeQuery = true)
    List<Object[]> getDormancyAccountsData();


    @Query(value = "SELECT  H.DEVICE_TYPE AS DEVICE_TYPE, COUNT(*) ACCOUNTS,H.DEVICE_MODEL\r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "   INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "   INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    \r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + " WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)\r\n"
            + " GROUP BY H.DEVICE_TYPE, H.DEVICE_MODEL", nativeQuery = true)
    List<Object[]> getNumberOfAccount();


    @Query(value = "SELECT  COUNT(DISTINCT H.DEVICE_MODEL) AS DEVICE_TYPE, COUNT(*) ACCOUNTS\r\n"
            + "    FROM TBL_CUSTOMER A \r\n"
            + "   INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID\r\n"
            + "   INNER JOIN LKP_ACCOUNT_LEVEL C ON B.ACCOUNT_LEVEL_ID = C.ACCOUNT_LEVEL_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_CLASSIFICATION D ON  D.ACCOUNT_CLASSIFICATION_ID = C.ACCOUNT_CLASSIFICATION_ID\r\n"
            + "    INNER JOIN LKP_ACCOUNT_STATUS E ON B.ACCOUNT_STATUS_ID = E.ACCOUNT_STATUS_ID\r\n"
            + "    INNER JOIN LKP_CHANNEL F ON  B.CHANNEL_ID = F.CHANNEL_ID\r\n"
            + "    INNER JOIN TBL_APP_USER G ON A.CUSTOMER_ID = G.CUSTOMER_ID\r\n"
            + "    INNER JOIN TBL_DEVICE_INFO H ON H.APP_USER_ID = G.APP_USER_ID\r\n"
            + "    \r\n"
            + "    LEFT JOIN (SELECT '1' AS JOIN_COL, ACCOUNT_CLASSIFICATION_ID, GENDER, AGE,\r\n"
            + "                     FROM_DATE, TO_DATE, ACCOUNT_LEVEL_ID, ACCOUNT_STATUS_ID,\r\n"
            + "                     REG_CHANNEL_ID, REG_CITY_ID,REG_OS_TYPE    \r\n"
            + "               FROM TBL_RISK_DASHBOARD \r\n"
            + "              WHERE IS_ACTIVE = 'Y' AND STATUS_ID = 2) X ON 1=X.JOIN_COL\r\n"
            + " WHERE D.ACCOUNT_CLASSIFICATION_ID = NVL(X.ACCOUNT_CLASSIFICATION_ID, D.ACCOUNT_CLASSIFICATION_ID)\r\n"
            + "   AND NVL(A.GENDER, '-') = NVL(X.GENDER, NVL(A.GENDER, '-'))\r\n"
            + "   AND C.ACCOUNT_LEVEL_ID = NVL(X.ACCOUNT_LEVEL_ID, C.ACCOUNT_LEVEL_ID)\r\n"
            + "   AND E.ACCOUNT_STATUS_ID = NVL(X.ACCOUNT_STATUS_ID, E.ACCOUNT_STATUS_ID)\r\n"
            + "   AND F.CHANNEL_ID = NVL(X.REG_CHANNEL_ID, F.CHANNEL_ID)\r\n"
            + "   AND NVL(H.DEVICE_TYPE,'-') = NVL(X.REG_OS_TYPE, NVL(H.DEVICE_TYPE,'-'))\r\n"
            + "   AND B.CREATEDATE BETWEEN NVL(TO_DATE(X.FROM_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE) \r\n"
            + "   AND NVL(TO_DATE(X.TO_DATE,'YYYY-MM-DD HH24:MI:SS'), B.CREATEDATE)", nativeQuery = true)
    List<Object[]> getNumberOfDevicesPerAccounts();

    @Query(value = "SELECT A.CUSTOMER_ID, A.CNIC_ISSUANCE_DATE, B.ACCOUNT_ID " +
            "FROM TBL_CUSTOMER A " +
            "INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID " +
            "WHERE (:cnic IS NULL OR A.CNIC_HASH = SHA256.ENCRYPT(:cnic)) " +
            "  AND (:mobileNo IS NULL OR A.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNo)) " +
            "  AND (:accountNo IS NULL OR B.ACCOUNT_NO = :accountNo) AND A.IS_ACTIVE='Y'", nativeQuery = true)
    List<Object[]> getCustomerDetailsByCnicOrMobOrAccountNo(@Param("cnic") String cnic, @Param("mobileNo") String mobileNo, @Param("accountNo") String accountNo);

    @Query(value = "SELECT A.* FROM TBL_CUSTOMER A  " +
            " INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID  " +
            " WHERE (A.CNIC_HASH = SHA256.ENCRYPT(:cnic))  " +
            " AND (A.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNo))  " +
            " AND (B.ACCOUNT_NO = :accountNo)  AND A.IS_ACTIVE='Y'", nativeQuery = true)
    List<TblCustomer> getCustomerByCnicMobAccountNo(@Param("cnic") String cnic, @Param("mobileNo") String mobileNo, @Param("accountNo") String accountNo);

    @Query(value = "SELECT COUNT(1) \n" +
            " FROM TBL_CUSTOMER C\n" +
            " INNER JOIN TBL_ACCOUNT A ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            " INNER JOIN TBL_DEBIT_CARD D ON A.ACCOUNT_ID = D.ACCOUNT_ID \n" +
            " WHERE C.MOBILE_NO_HASH = SHA256.ENCRYPT(:contactNumber)", nativeQuery = true)
    int isDebitCardHolder(String contactNumber);

    @Query(value = "SELECT CASE \n" +
            "           WHEN COUNT(c.email_Hash) > 0 THEN 1 \n" +
            "           ELSE 0 \n" +
            "       END \n" +
            "FROM TBL_CUSTOMER c \n" +
            "WHERE c.email_Hash = SHA256.ENCRYPT(:emailHash)", nativeQuery = true)
    long isEmailHashExist(String emailHash);

    @Query(value = "SELECT C.* FROM TBL_CUSTOMER C WHERE C.MOBILE_NO_HASH=SHA256.ENCRYPT(:mobileNumber) AND C.EMAIL_HASH=SHA256.ENCRYPT(:email) AND C.IS_ACTIVE='Y'", nativeQuery = true)
    TblCustomer findByMobileNumberHashAndEmailHash(String email, String mobileNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TBL_CUSTOMER " +
            "SET CRP_RATING = :crpRating, " +
            "CRP_SCORE = :crpScore, " +
            "CRP_DATE = :crpDate, " +
            "CRP_NEXT_DATE = :crpNextDate, " +
            "UPDATEINDEX = COALESCE(UPDATEINDEX + 1, 1), " +
            "LASTUPDATEDATE = :lastUpdateDate " +
            "WHERE CUSTOMER_ID = :id", nativeQuery = true)
    void updateCustomerInfo(@Param("crpRating") String crpRating,
                            @Param("crpScore") BigDecimal crpScore,
                            @Param("crpDate") Date crpDate,
                            @Param("crpNextDate") Date crpNextDate,
                            @Param("lastUpdateDate") Date lastUpdateDate,
                            @Param("id") Long id);


    @Query(value ="SELECT count(*) FROM TBL_CUSTOMER A  " +
            " INNER JOIN TBL_ACCOUNT B ON A.CUSTOMER_ID = B.CUSTOMER_ID  " +
            " WHERE (A.CNIC_HASH = SHA256.ENCRYPT(:cnic))  " +
            " AND (A.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNo))  " +
            " AND (B.ACCOUNT_NO = :accountNo)", nativeQuery = true)
    BigDecimal findCountByCnicHash(@Param("cnic") String cnic, @Param("mobileNo") String mobileNo, @Param("accountNo") String accountNo);


}
