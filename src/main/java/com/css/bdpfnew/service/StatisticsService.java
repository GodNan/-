package com.css.bdpfnew.service;

import com.css.bdpfnew.model.dto.report.DtoCredentials;
import com.css.bdpfnew.model.dto.report.DtoCustomAge;

import java.util.List;

public interface StatisticsService {
    List documentStatistics(DtoCredentials credentials);

    List giveCredentialsStatistics(DtoCredentials credentials);

    List scheduleStatistics(String cityId, Integer processId, String[] certDate);

    List requestStatistics(String cityId, String[] certDate);

    List idtLevelStatistics(String cityId, String[] certDate);

    List idtKindStatistics(String cityId, String[] certDate);

    List ageStatistics(String cityId, Integer ageGroup, String[] certDate);

    List customAgeStatistics(DtoCustomAge dtoCustomAge);

    List genderdStatistics(String cityId, String[] certDate);

    List hukouStatistics(String cityId, String[] certDate);

    List documentsRequestStatistics(String cityId, String[] certDate);
}
