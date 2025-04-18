package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblUltraCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUltraCustomerRepo extends JpaRepository<TblUltraCustomer, Long> {
    @Query(value = "SELECT * FROM TBL_ULTRA_CUSTOMER WHERE EMAIL=:email",nativeQuery = true)
    TblUltraCustomer findByEmail(@Param("email") String email);
    @Query(value = "SELECT * FROM TBL_ULTRA_CUSTOMER WHERE CUSTOMER_ID=:customerId",nativeQuery = true)
    TblUltraCustomer findByCustomerId(@Param("customerId") Long customerId);
    @Query(value = "SELECT U.* FROM TBL_CUSTOMER A INNER JOIN TBL_ULTRA_CUSTOMER U ON A.CUSTOMER_ID=U.CUSTOMER_ID WHERE A.MOBILE_NO_HASH = SHA256.ENCRYPT(NVL(:mobileNumber,''))",nativeQuery = true)
    TblUltraCustomer findByMobileNunber(@Param("mobileNumber") String mobileNumber);
    @Query(value = "   SELECT U.CHEQUE_BOOK\n" +
            "       FROM TBL_CUSTOMER C\n" +
            "       INNER JOIN TBL_ULTRA_CUSTOMER U ON U.CUSTOMER_ID=C.CUSTOMER_ID\n" +
            "       WHERE C.MOBILE_NO_HASH=SHA256.ENCRYPT(:mobileNumber)",nativeQuery = true)
    String getChequeBookStatusByMobileNumber(@Param("mobileNumber") String mobileNumber);
    @Query(value = "   SELECT U.ULTRA_CUSTOMER_ID\n" +
            "       FROM TBL_CUSTOMER C\n" +
            "       INNER JOIN TBL_ULTRA_CUSTOMER U ON U.CUSTOMER_ID=C.CUSTOMER_ID\n" +
            "       WHERE C.MOBILE_NO_HASH=SHA256.ENCRYPT(:mobileNumber)",nativeQuery = true)
    Long getUltaCustomerIdByMobileNumber(@Param("mobileNumber") String mobileNumber);
}
