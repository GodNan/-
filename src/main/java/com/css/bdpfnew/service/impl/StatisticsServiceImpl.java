package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.dto.report.DtoCustomAge;
import com.css.bdpfnew.model.entity.bdpfnew.DCode;
import com.css.bdpfnew.model.entity.bdpfnew.Process;
import com.css.bdpfnew.model.dto.report.DtoCredentials;
import com.css.bdpfnew.repository.DCodeRepository;
import com.css.bdpfnew.repository.ProcessRepository;
import com.css.bdpfnew.repository.StatisticsRepository;
import com.css.bdpfnew.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private DCodeRepository dCodeRepository;

    @Override
    public List documentStatistics(DtoCredentials credentials) {
        List documents = statisticsRepository.documents(credentials);
        return documents;
    }

    @Override
    public List giveCredentialsStatistics(DtoCredentials credentials) {
        List giveCredentials = statisticsRepository.giveCredentials(credentials);
        return giveCredentials;
    }

    @Override
    public List ageStatistics(String cityId, Integer ageGroup, String[] certDate) {
        List age = statisticsRepository.ageGroup(cityId,ageGroup,certDate);
        return age;
    }

    @Override
    public List customAgeStatistics(DtoCustomAge dtoCustomAge) {
        if(dtoCustomAge.getIsNull()){
            dtoCustomAge.setDefaultAge();
        }
        List list = statisticsRepository.customAge(
                dtoCustomAge.getAge1(),dtoCustomAge.getAge2(),dtoCustomAge.getAge3(),dtoCustomAge.getAge4(),
                dtoCustomAge.getCityId(),dtoCustomAge.getCertDate(),dtoCustomAge.getGender(),dtoCustomAge.getHukouKind());
        return list;
    }

    @Override
    public List genderdStatistics(String cityId, String[] certDate) {
        return statisticsRepository.genderStatistics(cityId, certDate);
    }

    @Override
    public List hukouStatistics(String cityId, String[] certDate) {
        return statisticsRepository.hukouStatistics(cityId, certDate);
    }

    @Override
    public List documentsRequestStatistics(String cityId, String[] certDate) {
        List<DCode> cardStatus = dCodeRepository.findByTypeCodeOrderByOrderNumAsc("d_card_status");
        List request = statisticsRepository.documentsRequestStatistics(cityId, certDate, cardStatus);
        return request;
    }

    @Override
    public List scheduleStatistics(String cityId, Integer processId, String[] certDate) {
        Process process = processRepository.findByProcessId(processId);
        String[] flowIds = process.getFlow().split(",");
        List schedule = statisticsRepository.scheduleStatistics(cityId,processId,certDate,flowIds);
        return schedule;
    }

    @Override
    public List requestStatistics(String cityId, String[] certDate) {
        List<Process> processes = processRepository.findAllByOrderByProcessIdAsc();
        List request = statisticsRepository.requestStatistics(cityId, certDate, processes);
        return request;
    }

    @Override
    public List idtLevelStatistics(String cityId, String[] certDate) {
        return statisticsRepository.idtLevelStatistics(cityId, certDate);
    }

    @Override
    public List idtKindStatistics(String cityId, String[] certDate) {
        return statisticsRepository.idtKindStatistics(cityId, certDate);
    }

}
