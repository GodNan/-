package com.css.bdpfnew.model.entity.bdpfnew;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by wan on 2017/1/17.
 */
@Entity
@Table(name="test_man")
public class Superman {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String userName;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    private String sex;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Superman{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", birthDay=" + birthDay +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
