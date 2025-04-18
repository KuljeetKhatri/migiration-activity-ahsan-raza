package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpRegistrationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LkpRegistrationTypeRepo extends JpaRepository<LkpRegistrationType, Long> {
    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_REGISTRATION_TYPE A \n" +
            " WHERE UPPER(A.REGISTRATION_TYPE_NAME) = UPPER('Customer')\n" +
            "   AND A.IS_ACTIVE = 'Y'", nativeQuery = true)
    LkpRegistrationType getRegistrationType();

    @Query(value = "SELECT A.* \n" +
            "  FROM LKP_REGISTRATION_TYPE A \n" +
            " WHERE UPPER(A.REGISTRATION_TYPE_NAME) = UPPER(:registrationTypeName)\n" +
            "   AND A.IS_ACTIVE = 'Y'", nativeQuery = true)
    LkpRegistrationType findByRegistrationTypeName(String registrationTypeName);

    Optional<LkpRegistrationType> findByRegistrationTypeCode(String registrationTypeCode);
}
