package com.css.bdpfnew.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.css.bdpfnew.model.entity.bdpfnew.DCodeType;
import com.css.bdpfnew.model.vo.VoDCodeType;


public interface DCodeTypeRepository extends JpaRepository<DCodeType, String> {
	/*List<DCodeType> findByTypeCodeLikeOrDescriptionLike(String typeCode,String description);*/
	DCodeType findByTypeCode(String typeCode);
	
	@Query(value="select t.type_code,t.description,t.remark,t.flag,  a.contents  from d_code_type t ,("
			+ "select c.type_code,listagg(c.description,',') WITHIN GROUP(ORDER BY description) as contents from d_code c  "
			+ "where c.type_code not in('code_area','d_industry_type') group by c.type_code) a  "
			+ "where t.type_code=a.type_code and t.flag=1 and t.type_code like ?1 and t.description like ?2",nativeQuery=true)
	List<Object[]>   findByTypeCodeLikeOrDescriptionLike(String typeCode,  String description);
	
	@Query(value="select t.type_code,t.description,t.remark,t.flag,  a.contents  from d_code_type t ,("
			+ "select c.type_code,listagg(c.description,',') WITHIN GROUP(ORDER BY description) as contents from d_code c  "
			+ "where c.type_code not in('code_area','d_industry_type') group by c.type_code) a  "
			+ "where t.type_code=a.type_code and t.flag=1 ",nativeQuery=true)
	List<Object[]>  findAlls();
}
