package com.coffeeshop.domain.user;

import com.coffeeshop.constant.StatusType;
import com.coffeeshop.constant.UserType;
import com.coffeeshop.domain.BaseDomain;

import javax.persistence.*;

/**
 * Base User table entity, by extending this we can have multitype
 * of user table. In this it will store all common information like
 * email password for all users type
 * @author chandan vishwakarma
 * @see com.coffeeshop.domain.user.staff
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseDomain{

    /**
     * unique email address
     * In case of Staff user this has to be from domain like name@coffeeshopexample.com
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * encrypted password
     * In case of user type Customer this field will be blank
     * should be used in future
     * @see com.coffeeshop.constant.UserType
     */
    @Column(name = "password", length = 200)
    private String password;

    /**
     * user type values from predefined constant
     * @see com.coffeeshop.constant.UserType
     */
    @Column(name = "user_type", length = 2)
    private int type;

    /**
     * status value as int from predefined constant
     * @see com.coffeeshop.constant.StatusType
     */
    @Column(name = "user_status", length = 2)
    private int status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return UserType.parse(this.type);
    }

    public void setType(UserType type) {
        this.type = type.getValue();
    }

    public StatusType getStatus() {
        return StatusType.parse(this.status);
    }

    public void setStatus(StatusType status) {
        this.status = status.getValue();
    }
}
