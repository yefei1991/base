package com.company.project.model;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Chapter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Column(name = "novelId")
  private BigInteger novelid;

  private String url;

  private String title;

  private String prev;

  private String next;

  private String content;

  private int sort;

  private boolean downloaded = false;

  public boolean isDownloaded() {
    return downloaded;
  }

  public void setDownloaded(boolean downloaded) {
    this.downloaded = downloaded;
  }

  public int getSort() {
    return sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public BigInteger getNovelid() {
    return novelid;
  }

  public void setNovelid(BigInteger novelid) {
    this.novelid = novelid;
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
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return prev
   */
  public String getPrev() {
    return prev;
  }

  /**
   * @param prev
   */
  public void setPrev(String prev) {
    this.prev = prev;
  }

  /**
   * @return next
   */
  public String getNext() {
    return next;
  }

  /**
   * @param next
   */
  public void setNext(String next) {
    this.next = next;
  }

  /**
   * @return content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content
   */
  public void setContent(String content) {
    this.content = content;
  }
}
