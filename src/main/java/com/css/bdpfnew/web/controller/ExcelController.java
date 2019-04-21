package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.constant.CommonConfig;
import com.css.bdpfnew.model.dto.DtoCitizenKindHospital;
import com.css.bdpfnew.model.util.ExcelData;
import com.css.bdpfnew.model.vo.query.VoCredentials;
import com.css.bdpfnew.service.CitizenCardService;
import com.css.bdpfnew.service.CitizenService;
import com.css.bdpfnew.service.bdpfnet.NetIdtCitizenKindHospitalService;
import com.css.bdpfnew.util.ExcelUtils;
import com.css.bdpfnew.util.SM4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/excel")
@RestController
public class ExcelController {
    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private CitizenService citizenService;
    @Autowired
    private CitizenCardService citizenCardService;
    @Autowired
    private NetIdtCitizenKindHospitalService netIdtCitizenKindHospitalService;

    @RequestMapping(value="/certification",method = RequestMethod.POST)
    public Message certification(@RequestBody VoCredentials voCredentials){
        List<Object[]> list = citizenCardService.findByVoCredentials(voCredentials);
        int rowIndex = 0;
        ExcelData data = new ExcelData();
        data.setName("Sheet");
        List<String> titles = new ArrayList();
        titles.add("姓名");
        titles.add("残疾证号");
        titles.add("性别");
        titles.add("残疾类别");
        titles.add("残疾等级");
        titles.add("地区");
        titles.add("固定电话");
        titles.add("移动电话");
        titles.add("制卡批次");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Object[] strings = list.get(i);
            List<Object> row = new ArrayList();
            row.add(strings[0]);
            row.add(strings[1]);
            row.add(strings[2]);
            row.add(strings[3]);
            row.add(strings[4]);
            row.add(strings[5]);
            row.add(strings[6]);
            row.add(strings[7]);
            row.add(strings[8]);
            rows.add(row);
        }
        data.setRows(rows);
        String fileName = "";
        try {
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            fileName = dateFormat.format(now)+"_"+(int)((Math.random()*9+1)*1000);
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
            File tmpDir = new File(path);
            // 判断上传文件的保存目录是否存在
            if (!tmpDir.exists() && !tmpDir.isDirectory())
            {
                tmpDir.createNewFile();
                // 创建目录
                tmpDir.mkdir();
            }
            System.out.println(path);
            ExcelUtils.generateExcel(data,path + fileName + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Message.success("成功",commonConfig.basePath + fileName + ".xls");
    }

    @RequestMapping(value = "/documents", method = RequestMethod.POST)
    public Message documents(@RequestBody VoCredentials credentials){
        //TODO：明确查询条件，已发证是否要包含已注销
        List<Object[]> list = citizenService.findByCredentials(credentials);
//        return Message.success("成功",page);
        int rowIndex = 0;
        ExcelData data = new ExcelData();
        data.setName("Sheet");
        List<String> titles = new ArrayList();
        titles.add("身份证号");
        titles.add("姓名");
        titles.add("残疾证号");
        titles.add("性别");
        titles.add("残疾类别");
        titles.add("多重类别");
        titles.add("残疾等级");
        titles.add("银行卡号");
        titles.add("户籍地");
        titles.add("户籍地地址");
        titles.add("居住地");
        titles.add("居住地地址");
        titles.add("固定电话");
        titles.add("移动电话");
        titles.add("档案号");
        titles.add("户口类别");
        titles.add("政治面貌");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Object[] strings = list.get(i);
            List<Object> row = new ArrayList();
            row.add(strings[0]);
            row.add(strings[1]);
            row.add(strings[2]);
            row.add(strings[3]);
            row.add(strings[4]);
            row.add(strings[14]);
            row.add(strings[5]);
            row.add(strings[6]);
            row.add(strings[7]);
            row.add(strings[8]);
            row.add(strings[9]);
            row.add(strings[10]);
            row.add(strings[11]);
            row.add(strings[12]);
            row.add(strings[13]);
            row.add(strings[15]);
            row.add(strings[16]);
            rows.add(row);
        }
        data.setRows(rows);
        String fileName = "";
        try {
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            fileName = dateFormat.format(now)+"_"+(int)((Math.random()*9+1)*1000);

            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
            File tmpDir = new File(path);
            // 判断上传文件的保存目录是否存在
            if (!tmpDir.exists() && !tmpDir.isDirectory())
            {
                tmpDir.createNewFile();
                // 创建目录
                tmpDir.mkdir();
            }

            System.out.println(path);
            ExcelUtils.generateExcel(data,path + fileName + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Message.success("成功",commonConfig.basePath + fileName + ".xls");
    }


    /**
     * 医院预约人员列表导出
     * @param dtoCitizenKindHospital
     * @return
     */
    @RequestMapping(value = "/hospitalBookList", method = RequestMethod.POST)
    public Message hospitalBookList(@RequestBody DtoCitizenKindHospital dtoCitizenKindHospital){
        List<Object[]> list = netIdtCitizenKindHospitalService.findByCondition(dtoCitizenKindHospital);
        int rowIndex = 0;
        ExcelData data = new ExcelData();
        data.setName("Sheet");
        List<String> titles = new ArrayList();
        titles.add("姓名");
        titles.add("区划");
        titles.add("身份证号");
        titles.add("评残类别");
        titles.add("申请类型");
        titles.add("预约就诊时间");
        titles.add("联系电话");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Object[] strings = list.get(i);
            String name = "";
            try {
                name = SM4.getStringAfterDecrypted(String.valueOf(strings[0]));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            List<Object> row = new ArrayList();
            row.add(name);
            row.add(strings[1]);
            row.add(strings[2]);
            row.add(strings[3]);
            row.add(strings[4]);
            row.add((new SimpleDateFormat("yyyy-MM-dd")).format(strings[5]));
            row.add(strings[6]);
            rows.add(row);
        }
        data.setRows(rows);
        String fileName = "";
        try {
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            fileName = dateFormat.format(now)+"_"+(int)((Math.random()*9+1)*1000);

            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
            File tmpDir = new File(path);
            // 判断上传文件的保存目录是否存在
            if (!tmpDir.exists() && !tmpDir.isDirectory())
            {
                tmpDir.createNewFile();
                // 创建目录
                tmpDir.mkdir();
            }

            System.out.println(path);
            ExcelUtils.generateExcel(data,path + fileName + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Message.success("成功",commonConfig.basePath + fileName + ".xls");
    }

}

