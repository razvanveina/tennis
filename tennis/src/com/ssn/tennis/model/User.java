
package com.ssn.tennis.model;

import java.io.Serializable;

public class User implements Serializable {
  private String name;
  private String password;
  private boolean admin = false;

  public User(String name, String password, boolean admin) {
    super();
    this.name = name;
    this.password = password;
    this.admin = admin;
  }

  @Override
  public String toString() {
    return "User [name=" + name + "]";
  }

  public boolean isAdmin() {
    return admin;
  }

  /**
   * @param user
   * @param password2
   * @return
   */
  public boolean hasNameAndPwd(String name, String password) {
    return name.equals(this.name) && this.password.equals(password);
  }

}
