package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.LkpSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LkpSegmentRepo extends JpaRepository<LkpSegment, Long> {

    @Query(value = "SELECT * FROM (" +
            "SELECT S.*, COUNT(*) OVER() AS TOTAL_COUNT, ROW_NUMBER() OVER (ORDER BY S.CREATEDATE DESC) AS ROW_NUM " +
            "FROM LKP_SEGMENT S " +
            "WHERE UPPER(NVL(S.SEGMENT_NAME, '-')) = UPPER(NVL(:segmentName, NVL(S.SEGMENT_NAME, '-'))) " +
            "AND S.STATUS_ID = NVL(:statusId, S.STATUS_ID) " +
            "AND S.CREATEUSER = NVL(:createdBy, S.CREATEUSER) " +
            "AND NVL(S.LASTUPDATEUSER, 0) = NVL(:updatedBy, NVL(S.LASTUPDATEUSER, 0)) " +
            "AND NVL(S.SALES_ROLE_DETAIL_ID, 0) = NVL(:salesRoleDetailId, NVL(S.SALES_ROLE_DETAIL_ID, 0)) " +
            "AND S.CREATEDATE BETWEEN NVL(TO_DATE(:dateFrom,'YYYY-MM-DD HH24:MI:SS'),S.CREATEDATE) " +
            "AND NVL(TO_DATE(:dateTo,'YYYY-MM-DD HH24:MI:SS'),S.CREATEDATE)) " +
            "WHERE ROW_NUM BETWEEN NVL(:rowStart, ROW_NUM) AND NVL(:rowEnd, ROW_NUM)", nativeQuery = true)
    List<Object[]> getAllLkpSegment(@Param("segmentName") String segmentName,
                                    @Param("statusId") String statusId,
                                    @Param("createdBy") String createdBy,
                                    @Param("updatedBy") String updatedBy,
                                    @Param("salesRoleDetailId") String salesRoleDetailId,
                                    @Param("dateFrom") String dateFrom,
                                    @Param("dateTo") String dateTo,
                                    @Param("rowStart") Long rowStart,
                                    @Param("rowEnd") Long rowEnd);

    @Query(value = "SELECT A.*\n" +
            "  FROM LKP_SEGMENT A \n" +
            " WHERE SEGMENT_ID =(:segmentId)\n" +
            "   AND A.IS_ACTIVE = 'Y'\n" +
            "   AND A.STATUS_ID = 2", nativeQuery = true)
    LkpSegment findBySegmentsName(@Param("segmentId") String segmentName);

   // List<LkpSegment> findBySegmentName(String segmentName);

    @Query(value = "SELECT * FROM LKP_SEGMENT WHERE SEGMENT_NAME = :segmentName", nativeQuery = true)
    LkpSegment findIdByName(@Param("segmentName") String segmentName);

    Optional<LkpSegment> findBySegmentCode(String code);
}
