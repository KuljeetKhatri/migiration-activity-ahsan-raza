package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LkpChannelRepo extends JpaRepository<LkpChannel, Long> {
    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_CHANNEL A \n" +
            " WHERE UPPER(A.CHANNEL_NAME) = UPPER(:channelName)\n" +
            "   AND A.IS_ACTIVE = 'Y'",nativeQuery = true)
    LkpChannel getChannel(@Param("channelName") String channelName);

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_CHANNEL A \n" +
            " WHERE UPPER(A.CHANNEL_NAME) = UPPER(:channelName)\n" +
            "   AND A.IS_ACTIVE = 'Y'",nativeQuery = true)
    LkpChannel getLkpChannelByChannelName(@Param("channelName") String channelName);

    @Query(value = "SELECT CASE WHEN count(*)>0 THEN 'true' ELSE 'false' END FROM LKP_CHANNEL WHERE CHANNEL_NAME  =:channelName",nativeQuery = true)
    Boolean isExistChannel(String channelName);

    @Query(value = "SELECT * \n" +
            "  FROM LKP_CHANNEL \n" +
            " WHERE UPPER(CHANNEL_NAME) = UPPER(NVL(:channelName, CHANNEL_NAME)) \n" +
            "    AND NVL(STATUS_ID,0) = NVL(:statusId, NVL(STATUS_ID,0)) \n" +
            "    AND NVL(CREATEUSER,0) = NVL(:createdBy,NVL(CREATEUSER,0)) \n" +
            "    AND NVL(LASTUPDATEUSER,0) = NVL(:updatedBy, NVL(LASTUPDATEUSER,0)) \n" +
            "    AND CREATEDATE BETWEEN NVL(TO_DATE(:createdDate,'YYYY-MM-DD HH24:MI:SS'),CREATEDATE)\n" +
            "    AND NVL(TO_DATE(:updatedDate,'YYYY-MM-DD HH24:MI:SS'),CREATEDATE)",nativeQuery = true)
    List<LkpChannel> getAllChannel(String channelName, String statusId, String createdBy, String updatedBy, String createdDate, String updatedDate);

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_CHANNEL A \n" +
            " WHERE UPPER(A.CHANNEL_NAME) = UPPER(:channelName)",nativeQuery = true)
    LkpChannel getChannelByNameWithoutActive(String channelName);

    LkpChannel findByChannelId(Long regChannelId);

    Optional<LkpChannel> findByChannelCode(String code);

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_CHANNEL A \n" +
            " WHERE UPPER(A.CHANNEL_NAME) = UPPER(:channelName)\n" +
            "   AND A.IS_ACTIVE = 'Y'",nativeQuery = true)
    Optional<LkpChannel> findByChannelNameIgnoreCase(String channelName);
}
