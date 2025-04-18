package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblDeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblDeviceInfoRepo extends JpaRepository<TblDeviceInfo, Long> {

    @Query(value = "SELECT * FROM TBL_DEVICE_INFO WHERE APP_USER_ID=:appUserId", nativeQuery = true)
    TblDeviceInfo getDeviceInfoByAppUserId(@Param("appUserId") long appUserId);

    @Query(value = "SELECT C.*\n" +
            "    FROM TBL_CUSTOMER A \n" +
            "        INNER JOIN TBL_APP_USER B ON A.CUSTOMER_ID = B.CUSTOMER_ID\n" +
            "        INNER JOIN TBL_DEVICE_INFO C ON B.APP_USER_ID = C.APP_USER_ID\n" +
            "WHERE A.CUSTOMER_ID = :customerId", nativeQuery = true)
    TblDeviceInfo getAppVersionDetailByCustomerId(@Param("customerId") long customerId);

    @Query(value = "SELECT DISTINCT DEVICE_MODEL FROM TBL_DEVICE_INFO WHERE DEVICE_MODEL IS NOT NULL", nativeQuery = true)
    List<String> getByDeviceModel();
}
