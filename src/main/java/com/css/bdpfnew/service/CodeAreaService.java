package com.css.bdpfnew.service;

import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;



public interface CodeAreaService {
	public List<CodeAreaEntity> getCodeAreaList();

    public CodeAreaEntity findCodeAreaByAreaCode(String areaCode);

    public CodeAreaEntity save(CodeAreaEntity codeArea);

    public CodeAreaEntity edit(CodeAreaEntity codeArea);

    public String delete(String areaCode);

}
