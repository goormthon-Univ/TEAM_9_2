package com.seasonwell.backend.medicine.repository;

import com.seasonwell.backend.medicine.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {

    @Query("SELECT m FROM Medicine m WHERE lower(m.medicine_name) LIKE lower(concat('%', :keyword, '%'))")
    List<Medicine> findByMedicine_nameContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT m FROM Medicine m WHERE m.disease.disease_code IN :disease_code")
    List<Medicine> findByDiseaseCodeIn(@Param("disease_code") List<String> disease_code);

    @Query("SELECT m FROM Medicine m WHERE m.disease.disease_code IN :disease_code")
    List<Medicine> findByDiseaseCode(@Param("disease_code") String disease_code);

    @Query("SELECT m FROM Medicine m JOIN m.disease d WHERE lower(d.disease_symptom) LIKE lower(concat('%', :keyword, '%'))")
    List<Medicine> findByDiseaseSymptomContainingIgnoreCase(@Param("keyword") String keyword);
}
