package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "phones")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Phone {

  @ManyToMany(mappedBy = "phones")
  private Set<Customer> customers = new HashSet<>();

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long phone_id;

  @NotBlank
  private String ddd;

  @NotBlank
  private String number;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;

  public Set<Customer> getCustomers() {
    return this.customers;
  }

  public void setCustomers(Set<Customer> customers) {
    this.customers = customers;
  }

  public Long getPhone_id() {
    return this.phone_id;
  }

  public void setPhone_id(Long phone_id) {
    this.phone_id = phone_id;
  }

  public String getDdd() {
    return this.ddd;
  }

  public void setDdd(String ddd) {
    this.ddd = ddd;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

}
