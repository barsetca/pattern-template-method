package com.chernyak.pattern.model;

import java.time.LocalDate;
import java.util.Objects;

public class Resume implements Comparable<Resume> {

  private String uuid;
  private String fullName;
  private LocalDate birthday;
  private String city;


  public Resume(String uuid, String fullName) {
    this(uuid, fullName, LocalDate.now(), "");
  }

  public Resume(String uuid, String fullName, LocalDate birthday, String city) {
    this.uuid = uuid;
    this.fullName = fullName;
    this.birthday = birthday;
    this.city = city;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resume resume = (Resume) o;
    return uuid.equals(resume.uuid) && fullName.equals(resume.fullName) && Objects
        .equals(birthday, resume.birthday) && Objects.equals(city, resume.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, fullName, birthday, city);
  }

  @Override
  public int compareTo(Resume o) {
    return fullName.compareTo(o.fullName) == 0 ? uuid.compareTo(o.uuid) : fullName.compareTo(o.fullName);
  }

  @Override
  public String toString() {
    return "Resume{" +
        "uuid='" + uuid + '\'' +
        ", fullName='" + fullName + '\'' +
        ", birthday=" + birthday +
        ", city='" + city + '\'' +
        '}';
  }
}
