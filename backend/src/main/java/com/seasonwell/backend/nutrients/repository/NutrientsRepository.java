package com.seasonwell.backend.nutrients.repository;

import com.seasonwell.backend.nutrients.entity.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
    List<Nutrients> findAll();

    @Query("SELECT n FROM Nutrients n INNER JOIN n.diseaseNutrients dn INNER JOIN dn.disease d WHERE d.disease_code = :diseaseCode")
    List<Nutrients> findAllByDiseaseCode(@Param("diseaseCode") String diseaseCode);

//    @Query("SELECT m FROM Medicine m WHERE m.disease.disease_code IN :disease_code")
//    List<Medicine> findByDiseaseCodeIn(@Param("disease_code") List<String> disease_code);

    @Query("SELECT DISTINCT n FROM Nutrients n INNER JOIN n.diseaseNutrients dn INNER JOIN dn.disease d WHERE d.disease_code IN :diseaseCode")
    List<Nutrients> findByDiseaseCodeIn(@Param("diseaseCode") List<String> diseaseCode);
}