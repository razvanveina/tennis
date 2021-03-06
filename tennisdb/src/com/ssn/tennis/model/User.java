
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ssn.tennis.common.Utils;

@Entity
@Table(name = "PLAYER")
@NamedQueries({ // 
  @NamedQuery(name = User.USER_BY_NAME, query = "from User where user = :user"), //
  @NamedQuery(name = User.USER_ALL, query = "from User"), //
  @NamedQuery(name = User.USER_BY_NAME_AND_PASS, query = "from User where user = :user and password = :pass"), //
})
public class User implements Serializable, Comparable<User> {
  public static final String USER_BY_NAME = "User.by.name";
  public static final String USER_BY_NAME_AND_PASS = "User.by.name.and.pass";
  private static final long serialVersionUID = 1L;
  public static final String USER_ALL = "Users.all";

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
  private int rating = -1;

  @ManyToMany(mappedBy = "players")
  private List<Team> teams = new ArrayList<>();

  @ManyToMany(mappedBy = "participants")
  private List<Tournament> tournaments = new ArrayList<>();
  private int lost;
  private int won;
  private int stars;

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

  public void setRating(int rating) {
    this.rating = rating;
  }

  public void recomputeRatings() {
    System.out.println("Start: " + System.currentTimeMillis());
    this.lost = computeLost();
    System.out.println(System.currentTimeMillis());
    this.won = computeWon();
    System.out.println(System.currentTimeMillis());

    this.rating = (won + lost > 0 ? won * 1000 / (won + lost) : 0);
    this.stars = computeStars();//ApplicationFactory.getInstance().getDatabase().getUserStars(this);
    System.out.println(System.currentTimeMillis());
  }

  private int computeStars() {
    int count = 0;

    for (Tournament t : getTournaments()) {
      if (t.isFinished() && t.getWinner().hasPlayer(this.user)) {
        count++;
      }
    }

    return (count);
  }

  public int getRating() {
    return rating;
  }

  @Override
  public int compareTo(User o) {
    return o.getRating() - this.getRating();
  }

  public int computeWon() {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesWonByUserName(user);
    }

    return (count);

  }

  public int computeLost() {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesLostByUserName(user);
    }

    return (count);
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

  public void updateRatings() {
    recomputeRatings();
  }

  /**
   * @return the lost
   */
  public int getLost() {
    return lost;
  }

  /**
   * @param lost the lost to set
   */
  public void setLost(int lost) {
    this.lost = lost;
  }

  /**
   * @return the won
   */
  public int getWon() {
    return won;
  }

  /**
   * @param won the won to set
   */
  public void setWon(int won) {
    this.won = won;
  }

  /**
   * @return the stars
   */
  public int getStars() {
    return stars;
  }

  /**
   * @param stars the stars to set
   */
  public void setStars(int stars) {
    this.stars = stars;
  }

}
