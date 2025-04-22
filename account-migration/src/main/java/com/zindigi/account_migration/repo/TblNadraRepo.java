package com.zindigi.account_migration.repo;


import com.zindigi.account_migration.model.TblNadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TblNadraRepo extends JpaRepository<TblNadra, Long> {

    @Query(value = "SELECT * \n" +
            "   FROM TBL_NADRA A\n" +
            "   WHERE  A.CNIC_HASH = SHA256.ENCRYPT(NVL(:cnic,''))",nativeQuery = true)
    TblNadra findByCnic(@Param("cnic") String cnic);

    @Query(value = "SELECT * FROM TBL_NADRA A WHERE  A.CNIC_HASH = SHA256.ENCRYPT(NVL(:cnic,''))" ,nativeQuery = true)
    TblNadra fetchNadraDetailsBycnic(@Param("cnic") String cnic);
    @Query(value = "SELECT B.*\n" +
            "    FROM TBL_CUSTOMER A \n" +
            "        INNER JOIN TBL_NADRA B ON A.CNIC_HASH = B.CNIC_HASH\n" +
            "    WHERE A.MOBILE_NO_HASH = NVL(:mobileNumber,'') OR A.CNIC_HASH = NVL(:cnic,'') AND A.IS_ACTIVE='Y'" ,nativeQuery = true)
    TblNadra fetchNadraDetailsByMobileNumberORCnic(@Param("mobileNumber") String mobileNumber,@Param("cnic") String cnic);
}


