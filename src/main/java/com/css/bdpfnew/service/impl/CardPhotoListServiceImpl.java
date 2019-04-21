package com.css.bdpfnew.service.impl;


import com.css.bdpfnew.model.entity.bdpfnew.view.VwCardMakeCitizens;
import com.css.bdpfnew.repository.CardPhotoListRepository;
import com.css.bdpfnew.service.CardPhotoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CardPhotoListServiceImpl extends BaseServiceImpl<VwCardMakeCitizens, Long> implements CardPhotoListService {
    @Autowired
    public void setBaseDao(CardPhotoListRepository cardPhotoListRepository) {
        super.setBaseDao(cardPhotoListRepository);
    }
}
