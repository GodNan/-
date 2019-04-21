package com.css.bdpfnew.model.vo.query;

public class VoCard {
    private String name;
    private String cardNum;
    private String gender;
    private String idtKind;
    private String cityidAddrstr;
    private String statusRecord;
    private String pcno;

    public VoCard(String name, String cardNum, String gender, String idtKind, String cityidAddrstr, String statusRecord, String pcno) {
        this.name = name;
        this.cardNum = cardNum;
        this.gender = gender;
        this.idtKind = idtKind;
        this.cityidAddrstr = cityidAddrstr;
        this.statusRecord = statusRecord;
        this.pcno = pcno;
    }
}
