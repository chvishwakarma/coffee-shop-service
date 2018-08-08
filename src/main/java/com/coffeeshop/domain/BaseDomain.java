package com.coffeeshop.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Base for all other entities to have common fields like
 * ID, CREATED_AT, UPDATED_AT
 * @author Chandan Vishwakarma
 * @see com.coffeeshop.domain
 */
@MappedSuperclass
public abstract class BaseDomain implements Serializable{

    /**
     * Auto generated primary key
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    protected Long id;

    /**
     * timestamp when record created in table
     * used for auditing
     */
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * timestamp when record is updated in table
     * used for auditing
     */
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /**
     * id of user who created the record
     * used for auditing
     * when value of this column is 0 it means created by system
     */
    @Column(name = "created_by", columnDefinition = "BIGINT UNSIGNED")
    private Long createdBy;

    /**
     * id of user who updated the record
     * used for auditing
     * when value of this column is 0 it means created by system
     */
    @Column(name = "updated_by", columnDefinition = "BIGINT UNSIGNED")
    private Long updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }
}
