package com.css.bdpfnew.service;


import com.css.bdpfnew.model.entity.bdpfnew.view.VwCardMake;
import com.css.bdpfnew.model.vo.VoPhotoToCards;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;



public interface CardPhotoService extends BaseService<VwCardMake, Long> {
	List<VoUnlockPhoto> getPhotoList(List<String> strList) throws SQLException;
    @Transactional
    void moveToCardsMake(List<VoPhotoToCards> strList);
}
