package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.DCode;
import com.css.bdpfnew.model.entity.bdpfnew.Process;
import com.css.bdpfnew.model.dto.report.DtoCredentials;

import java.util.List;

public interface StatisticsRepository {
    public List documents(DtoCredentials credentials);

    List giveCredentials(DtoCredentials credentials);

    List ageGroup(String cityId, Integer ageGroup, String[] certDate);

    List scheduleStatistics(String cityId, Integer processId, String[] certDate, String[] processIds);

    List requestStatistics(String cityId, String[] certDate, List<Process> processes);

    List documentsRequestStatistics(String cityId, String[] certDate, List<DCode> cardStatus);

    List idtLevelStatistics(String cityId, String[] certDate);

    List idtKindStatistics(String cityId, String[] certDate);

    List genderStatistics(String cityId, String[] certDate);

    List hukouStatistics(String cityId, String[] certDate);

    List customAge(Integer[] age1, Integer[] age2, Integer[] age3, Integer[] age4, String cityId, String[] certDate, Integer gender, Integer hukouKind);
}
