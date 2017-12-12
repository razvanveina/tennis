
package com.ssn.tennis.model;

import java.io.Serializable;

import com.ssn.tennis.common.Utils;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String user;
  private String password;
  private String name;
  private String surname;
  private boolean admin;

  public User(String user, String password, String name, String surname, boolean admin) {
    super();
    this.user = user;
    this.password = Utils.encrypt(password);
    this.name = name;
    this.surname = surname;
    this.admin = admin;
  }

  public boolean hasName(String name2) {
    return user.equals(name2);
  }

  @Override
  public String toString() {
    return user;
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

  /**
   * @return the user
   */
  public String getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(String user) {
    this.user = user;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the surname
   */
  public String getSurname() {
    return surname;
  }

  /**
   * @param surname the surname to set
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the admin
   */
  public boolean isAdmin() {
    return admin;
  }

  /**
   * @param admin the admin to set
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

}
