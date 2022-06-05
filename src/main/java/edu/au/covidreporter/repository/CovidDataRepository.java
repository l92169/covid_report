package main.java.edu.au.covidreporter.repository;

import edu.au.covidreporter.model.CovidDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Spring Data Repository for CovidDataEntity
 */

@Repository()
public interface CovidDataRepository extends JpaRepository<CovidDataEntity, Integer>  {

    @Query("select distinct cd.country_region " +
            "from edu.au.covidreporter.model.CovidDataEntity as cd " +
            "order by cd.country_region asc")
    List<String> getAllCountry();

    @Query("select cd " +
            "from edu.au.covidreporter.model.CovidDataEntity as cd "  +
            "where cd.country_region = :country_region "+
            "and cd.date between :from and :to "+
            "order by cd.date asc")
    List<CovidDataEntity> getCovidDataByCountryFromTo(@Param("country_region") String country_region, @Param("from") LocalDate from, @Param("to") LocalDate to);

}
