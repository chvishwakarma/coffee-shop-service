package com.coffeeshop.domain.user.staff;

import com.coffeeshop.constant.GenderType;
import com.coffeeshop.domain.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Staff related information stored in this
 * @author chandan vishwakarma
 */
@Entity
@Table(name = "staff")
public class Staff extends User{

    /**
     * staff full name
     */
    @Column(name = "name", length = 100)
    private String name;

    /**
     * mobile number upto 10 digit
     */
    @Column(name = "mobile", length = 10)
    private String mobile;

    /**
     * staff gender from predefined constant
     * @see com.coffeeshop.constant.GenderType
     */
    @Column(name = "gender", length = 2)
    private int gender;

    /**
     * date of birth of staff
     */
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public GenderType getGender() {
        return GenderType.parse(this.gender);
    }

    public void setGender(GenderType gender) {
        this.gender = gender.getValue();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
