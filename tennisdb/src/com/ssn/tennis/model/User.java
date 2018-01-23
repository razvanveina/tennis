
package com.ssn.tennis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ssn.tennis.common.Utils;

@Entity
@Table(name = "PLAYER")
@NamedQuery(name = User.USER_BY_NAME, query = "from User where user = :user")
public class User implements Serializable, Comparable<User> {
  public static final String USER_BY_NAME = "User.by.name";
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen", name = "gen")
  private long id;

  @Column(name = "username")
  private String user;
  private String password;
  private String name;
  private String surname;
  private boolean admin;

  @ManyToMany(mappedBy = "players")
  private List<Team> teams = new ArrayList<>();

  @ManyToMany(mappedBy = "participants")
  private List<Tournament> tournaments = new ArrayList<>();

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

  /**
   * @return the teams
   */
  public List<Team> getTeams() {
    return teams;
  }

  /**
   * @param teams the teams to set
   */
  public void setTeams(ArrayList<Team> teams) {
    this.teams = teams;
  }

  /**
   * @return the tournaments
   */
  public List<Tournament> getTournaments() {
    return tournaments;
  }

  /**
   * @param tournaments the tournaments to set
   */
  public void setTournaments(List<Tournament> tournaments) {
    this.tournaments = tournaments;
  }

}
