package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblAddressRepo extends JpaRepository<TblAddress, Long> {

    TblAddress findByTblCustomerCustomerIdAndAddressId(Long valueOf, Long valueOf1);

    @Query(value = "SELECT * FROM TBL_ADDRESS WHERE CUSTOMER_ID=:customerID",nativeQuery = true)
    List<TblAddress> getAllTblCustomerId(@Param("customerID") Long customerID);
    @Query(value = "SELECT A.* FROM TBL_ADDRESS A INNER JOIN TBL_CUSTOMER C ON C.CUSTOMER_ID=A.CUSTOMER_ID \n" +
            "INNER JOIN LKP_ADDRESS_TYPE T ON T.ADDRESS_TYPE_ID=A.ADDRESS_TYPE_ID\n" +
            "WHERE T.ADDRESS_TYPE_CODE=:typeCode AND C.MOBILE_NO_HASH=SHA256.ENCRYPT(:mobileNumber)",nativeQuery = true)
    List<TblAddress> findByMobileNumberAndAddressTypeCode(String mobileNumber, String typeCode);
}
