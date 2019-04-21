package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.vo.query.VoCredentials;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2019/1/1 17:29
 * @Description
 */
public interface ExcelRepository {
    List<Object[]> findByCredentials(VoCredentials credentials);

    List<Object[]> findByVoCredentials(VoCredentials voCredentials);
}
