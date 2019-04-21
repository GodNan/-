package com.css.bdpfnew.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.css.bdpfnew.model.entity.bdpfnew.DCode;


public interface DCodeRepository extends JpaRepository<DCode, String> {
	List<DCode> findByCodeLikeAndDescriptionLike(String code,String description);
	DCode findByUuid(String uuid);
	DCode findByCodeAndTypeCode(String code, String typeCode);
	List<DCode> findByTypeCode(String typeCode);
	List<DCode> findByTypeCodeAndRemark(String typeCode, String remark);

	@Query(value = "select code.description from DCode code where code.typeCode = :typeCode order by code.orderNum asc")
    List<String> findDescriptionByTypeCode(@Param("typeCode") String typeCode);

	List<DCode> findByTypeCodeOrderByOrderNumAsc(String typeCode);
}
