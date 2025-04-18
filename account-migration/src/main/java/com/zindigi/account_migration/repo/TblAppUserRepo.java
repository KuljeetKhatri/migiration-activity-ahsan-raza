package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TblAppUserRepo extends JpaRepository<TblAppUser, Long> {
    @Query(value = "SELECT A.*\n" +
            "FROM TBL_CUSTOMER C\n" +
            "INNER JOIN TBL_APP_USER A ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            "WHERE (C.CNIC_HASH = SHA256.ENCRYPT(:cnic)OR C.MOBILE_NO_HASH=SHA256.ENCRYPT(:mobile)) \n" +
            "AND A.PASSWORD = :pin AND C.IS_ACTIVE='Y'", nativeQuery = true)
    TblAppUser validatePin(@Param("cnic") String cnic, @Param("mobile") String mobile, @Param("pin") String pin);

    @Query(value = "SELECT A.*\n" +
            "  FROM TBL_CUSTOMER C\n" +
            " INNER JOIN TBL_APP_USER A ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            " WHERE C.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber) AND C.IS_ACTIVE='Y'", nativeQuery = true)
    TblAppUser appUserByMobileNumber(@Param("mobileNumber") String mobileNumber);

    @Query(value = "SELECT * FROM TBL_APP_USER WHERE REF_ID=:refId AND APP_USER_TYPE_ID=:accountTypeId", nativeQuery = true)
    TblAppUser appUserByRefIdAndAccountTypeId(@Param("refId") long refId, @Param("accountTypeId") long accountTypeId);


    @Query(value = "SELECT A.*\n" +
            "FROM TBL_CUSTOMER C\n" +
            "INNER JOIN TBL_APP_USER A ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
            "WHERE (C.CNIC_HASH = SHA256.ENCRYPT(:cnic) OR C.MOBILE_NO_HASH= SHA256.ENCRYPT(:mobile)) AND C.IS_ACTIVE = 'Y'", nativeQuery = true)
    TblAppUser getAppUserByCnicOrMobile(@Param("cnic") String cnic, @Param("mobile") String mobile);

    TblAppUser findByTblCustomerCustomerId(Long customerId);

    @Query(value = "SELECT A.*\n" +
            "              FROM TBL_AGENT C\n" +
            "             INNER JOIN TBL_APP_USER A ON C.AGENT_ID = A.AGENT_ID\n" +
            "             WHERE C.AGENT_ID = :agentId AND A.APP_USER_TYPE_ID = 2 ", nativeQuery = true)
    Optional<TblAppUser> getAppUserByAgentId(Long agentId);


    @Query(value = "SELECT U.*\n" +
            "  FROM TBL_APP_USER U\n" +
            "  LEFT JOIN TBL_CUSTOMER C ON U.CUSTOMER_ID = C.CUSTOMER_ID\n" +
            "  LEFT JOIN TBL_AGENT A ON U.AGENT_ID = A.AGENT_ID\n" +
            " WHERE (C.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber) OR A.MOBILE_NO_HASH = SHA256.ENCRYPT(:mobileNumber))", nativeQuery = true)
    TblAppUser getAppUserByMobile(String mobileNumber);
}
