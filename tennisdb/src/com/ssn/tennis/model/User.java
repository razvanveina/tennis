
package com.ssn.tennis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ssn.tennis.common.Utils;

@Entity
@Table(name = "PLAYER")
public class User implements Serializable, Comparable<User> {
  private static final long serialVersionUID = 1L;

  @Id
  private long id;

  @Column(name = "username")
  private String user;
  private String password;
  private String name;
  private String surname;
  private boolean admin;

  public User() {
    //
  }

  public User(String user, String password, String name, String surname, boolean admin) {
    this.user = user;
    this.password = Utils.encrypt(password);
    this.name = name;
    this.surname = surname;
    this.admin = admin;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean hasName(String pname) {
    return user.equals(pname);
  }

  @Override
  public String toString() {
    return user + " (" + getRating() + ")";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    return true;
  }

  public boolean hasPassword(String pass) {
    return password.equals(Utils.encrypt(pass));
  }

  public String getName() {
    return name;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public int getRating() {
    return OracleDatabase.getInstance().getUserRatingByName(user);
  }

  @Override
  public int compareTo(User o) {
    return o.getRating() - this.getRating();
  }

  public int getWon() {
    return OracleDatabase.getInstance().getMatchesWonByUsername(this.user);
  }

  public int getLost() {
    return OracleDatabase.getInstance().getMatchesLostByUsername(this.user);
  }
}
