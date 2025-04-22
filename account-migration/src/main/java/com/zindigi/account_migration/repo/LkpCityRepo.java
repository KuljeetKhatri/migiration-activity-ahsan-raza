package com.zindigi.account_migration.repo;

import com.mfs.commonservice.model.LkpCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LkpCityRepo extends JpaRepository<LkpCity, Long> {
    LkpCity findByCityCode(String cityCode);

    List<LkpCity> findByIsActive(String isActive);

    LkpCity findByCityId(Long regCityLocationId);

    @Query(value = "SELECT * FROM LKP_CITY WHERE UPPER(CITY_NAME)=UPPER(:cityName) AND IS_ACTIVE='Y'",nativeQuery = true)
    LkpCity findByCityNameInUpper(String cityName);
}
