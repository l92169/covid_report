package main.java.edu.au.covidreporter.repository;

import edu.au.covidreporter.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data Repository for ReportEntity
 */

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Integer>  {

    @Query("select  re " +
            "from edu.au.covidreporter.model.ReportEntity as re order by re desc"
           )
    List<ReportEntity> getAllReports();

    @Query("select re " +
            "from edu.au.covidreporter.model.ReportEntity as re " +
            "where re.id = :reportId"
    )
    ReportEntity getReportbyId(@Param("reportId") Integer reportId);




}
