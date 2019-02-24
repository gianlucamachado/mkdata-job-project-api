package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers", uniqueConstraints = { @UniqueConstraint(columnNames = { "document_one" }) })
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Customer {

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "customers_phones", joinColumns = { @JoinColumn(name = "customer_id") }, inverseJoinColumns = {
      @JoinColumn(name = "phone_id") })
  @JsonIgnoreProperties("customers")
  private List<Phone> phones = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Long customer_id;

  @NotBlank
  private String name;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String type;

  @NotBlank
  private String document_one;

  @NotBlank
  private String document_two;

  @NotBlank
  @Size(min = 1, max = 1)
  private String group;

  @NotNull
  @Column(name = "is_active", nullable = false)
  private Boolean is_active;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;

  public List<Phone> getPhones() {
    return this.phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  public Long getCustomer_id() {
    return this.customer_id;
  }

  public void setCustomer_id(Long customer_id) {
    this.customer_id = customer_id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDocument_one() {
    return this.document_one;
  }

  public void setDocument_one(String document_one) {
    this.document_one = document_one;
  }

  public String getDocument_two() {
    return this.document_two;
  }

  public void setDocument_two(String document_two) {
    this.document_two = document_two;
  }

  public String getGroup() {
    return this.group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public Boolean isIs_active() {
    return this.is_active;
  }

  public Boolean getIs_active() {
    return this.is_active;
  }

  public void setIs_active(Boolean is_active) {
    this.is_active = is_active;
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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
