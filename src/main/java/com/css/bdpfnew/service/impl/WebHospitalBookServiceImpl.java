package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.dto.DtoHospitalDate;
import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBook;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospitalBook;
import com.css.bdpfnew.repository.WebHospitalBookRepository;
import com.css.bdpfnew.repositorynet.NetHospitalBookNumRepository;
import com.css.bdpfnew.repositorynet.NetHospitalBookRepository;
import com.css.bdpfnew.service.WebHospitalBookService;
import com.css.bdpfnew.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2018/11/20 11:02
 * @Description
 */
@Service
@Transactional
public class WebHospitalBookServiceImpl extends BaseServiceImpl<WebHospitalBook, Long> implements WebHospitalBookService {

    @Autowired
    private WebHospitalBookRepository webHospitalBookRepository;

    @Autowired
    private NetHospitalBookRepository netHospitalBookRepository;

    @Autowired
    private NetHospitalBookNumRepository netHospitalBookNumRepository;

    @Override
    public void save(WebHospitalBook webHospitalBook) {
        webHospitalBookRepository.save(webHospitalBook);
    }

    @Override
    public void update(WebHospitalBook webHospitalBook) {
        webHospitalBookRepository.save(webHospitalBook);
    }

    @Override
    public void delete(String[] uuids) {
//        webHospitalBookRepository.deleteByInWebHospitalUuid(uuids);
    }

    @Override
    public void deleteByHospitalUuid(String hospitalUuid) {
        webHospitalBookRepository.deleteByWebHospitalUuid(hospitalUuid);
    }

    @Override
    public List<WebHospitalBook> findAllByWebHospitalUuid(String hospitalUuid) {
        return webHospitalBookRepository.findAllByWebHospitalUuid(hospitalUuid);
    }

    @Override
    @Transactional
    public void updateDateInfoWebAndNet(List<DtoHospitalDate> hospitalDates, WebHospital webHospital) {
        webHospitalBookRepository.deleteByWebHospitalUuid(webHospital.getUuid());
        for (DtoHospitalDate dtoHospitalDate : hospitalDates) {
            String uuid = UuidUtil.getUuid();
            if(dtoHospitalDate.getUuid() == null || dtoHospitalDate.getUuid() == ""){
                dtoHospitalDate.setUuid(uuid);
            }
            // 政务网信息维护
            WebHospitalBook webHospitalBook = null;
            if(dtoHospitalDate.getDataType() == 1){
                webHospitalBook = new WebHospitalBook(dtoHospitalDate.getDataType(),
                        dtoHospitalDate.getWeekDatas(),
                        dtoHospitalDate.getId(),
                        dtoHospitalDate.getNumber(),
                        dtoHospitalDate.getUuid(),
                        dtoHospitalDate.getIdtFee(),
                        dtoHospitalDate.getRegNeeded(),
                        dtoHospitalDate.getIdtTimeDesc(),
                        dtoHospitalDate.getIdtDesc());
            }else if(dtoHospitalDate.getDataType() == 2) {
                webHospitalBook = new WebHospitalBook(dtoHospitalDate.getDataType(),
                        dtoHospitalDate.getDatas(),
                        dtoHospitalDate.getId(),
                        dtoHospitalDate.getNumber(),
                        dtoHospitalDate.getUuid(),
                        dtoHospitalDate.getIdtFee(),
                        dtoHospitalDate.getRegNeeded(),
                        dtoHospitalDate.getIdtTimeDesc(),
                        dtoHospitalDate.getIdtDesc());
            }
            webHospitalBookRepository.save(webHospitalBook);

            // 互联网信息维护
            NetHospitalBook nethospitalBook  = netHospitalBookRepository.findByHospitalIdAndIdtkind(webHospital.getUuid(),dtoHospitalDate.getId());
            if(nethospitalBook == null){
                nethospitalBook = new NetHospitalBook();
            }else{
                //修改后的数量，不用关变化是负数还是正数
                Integer result = nethospitalBook.getIdtNum() - dtoHospitalDate.getNumber();
                netHospitalBookNumRepository.updateBookNumByHospitalIdAndIdtKind(webHospital.getUuid(),dtoHospitalDate.getId(),result);
            }
            nethospitalBook.setHospitalId(webHospital.getUuid());
            nethospitalBook.setHospitalName(webHospital.getOrgName());
            nethospitalBook.setHospitalAdd(webHospital.getOrgAddress());
            nethospitalBook.setHospitalTel(webHospital.getTelNo());
            nethospitalBook.setIdtkind(dtoHospitalDate.getId());
            nethospitalBook.setIdtNum(dtoHospitalDate.getNumber());
            nethospitalBook.setIdtFee(dtoHospitalDate.getIdtFee());
            nethospitalBook.setRegNeeded(dtoHospitalDate.getRegNeeded());
            nethospitalBook.setIdtTimeDesc(dtoHospitalDate.getIdtTimeDesc());
            nethospitalBook.setIdtDesc(dtoHospitalDate.getIdtDesc());
            if(dtoHospitalDate.getDataType() == 1){
                if(dtoHospitalDate.getWeekDatas() != null && dtoHospitalDate.getWeekDatas().length > 0){
                    String[] datas = dtoHospitalDate.getWeekDatas();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < datas.length; i++) {
                        stringBuffer.append(datas[i]);
                        stringBuffer.append(",");
                    }
                    String s = stringBuffer.toString();
                    nethospitalBook.setWeekdays(s.substring(0,s.length()-1));
                }
                nethospitalBook.setDateType(1);
            }else if(dtoHospitalDate.getDataType() == 2) {
                if(dtoHospitalDate.getDatas() != null && dtoHospitalDate.getDatas().length  > 0){
                    String[] datas = dtoHospitalDate.getDatas();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < datas.length; i++) {
                        stringBuffer.append(datas[i]);
                        stringBuffer.append(",");
                    }
                    String s = stringBuffer.toString();
                    nethospitalBook.setMonthDays(s.substring(0,s.length()-1));
                }
                nethospitalBook.setDateType(2);
            }
            if ((nethospitalBook.getWeekdays() != null && nethospitalBook.getWeekdays().length() > 0)
                    || (nethospitalBook.getMonthDays() != null && nethospitalBook.getMonthDays().length() > 0)){
                netHospitalBookRepository.save(nethospitalBook);
            }
        }
    }
}
