package com.company.project.model;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Novel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  private String name;

  private String author;

  private String type;

  private String url;

  @Column(name = "createTime")
  private Date createtime;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @param author
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @return type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return createTime
   */
  public Date getCreatetime() {
    return createtime;
  }

  /**
   * @param createtime
   */
  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }
}
