/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.ssn.core.ApplicationFactory;
import com.ssn.core.persistence.WithSessionAndTransaction;
import com.ssn.tennis.common.Utils;
import com.ssn.tennis.controller.TennisManager;
import com.ssn.tennis.model.enums.TournamentType;
import com.ssn.tennis.model.format.TournamentFormats;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class OracleDatabase implements Database {

  private static Database instance = null;

  private OracleDatabase() {
    init();
  }

  @Override
  public void addUser(User user) {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        session.save(user);
      }

    }.run();
  }

  @Override
  public void removeUser(User user) {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        session.delete(user);
      }

    }.run();
  }

  @Override
  public User checkLogin(String user, String password) {
    return new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        setReturnValue(tm.findUserByUserNameAndPassword(user, password));
      }

    }.run();
  }

  @Override
  public User getUserByUsername(String user) {
    return new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        setReturnValue(tm.findUserByUserName(user));
      }

    }.run();
  }

  @Override
  public Tournament getTournamentByName(String name) {
    return new WithSessionAndTransaction<Tournament>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Tournament t = tm.findTournamentByName(name);
        if (t != null) {
          t.getParticipants().toString();
          for (User user : t.getParticipants()) {
            for (Tournament tx : user.getTournaments()) {
              tx.getMatches().toString();
            }
          }
          t.getMatches().toString();
          t.getTeams().toString();
          setReturnValue(t);
        }
      }

    }.run();
  }

  @Override
  public List<User> getUsers() {
    return new WithSessionAndTransaction<ArrayList<User>>() {

      @SuppressWarnings("unchecked")
      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        setReturnValue(tm.findAllUsers());
      }

    }.run();

  }

  @Override
  public void editUser(String oldUser, String user, String pass, String name, String surname, boolean admin) {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        User userDB = tm.findUserByUserName(oldUser);
        userDB.setUser(user);
        userDB.setPassword(pass);
        userDB.setName(name);
        userDB.setSurname(surname);
        userDB.setAdmin(admin);
        session.update(userDB);
      }

    }.run();
  }

  @Override
  public boolean changePassword(String oldUser, String oldPass, String newPass) {
    return new WithSessionAndTransaction<Boolean>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        User tempUser = checkLogin(oldUser, oldPass);
        if (tempUser != null) {
          tempUser.setPassword(Utils.encrypt(newPass));
          session.update(tempUser);
          setReturnValue(true);
        } else {
          setReturnValue(false);
        }
      }
    }.run();
  }

  @Override
  public void addTournament(String name, Date date, TournamentType type, String tournamentFormat) {
    new WithSessionAndTransaction() {

      @Override
      protected void executeBusinessLogic(Session session) {
        Tournament tournament = new Tournament(name, date, type, TournamentFormats.getTournamentFormatsByName(tournamentFormat));
        session.save(tournament);
      }

    }.run();
  }

  @Override
  public ArrayList<Tournament> getTournaments() {
    return new WithSessionAndTransaction<ArrayList<Tournament>>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        ArrayList<Tournament> allTournaments = tm.findAllTournaments();
        for (Tournament t : allTournaments) {
          t.getParticipants().toString();
          t.getMatches().toString();
          t.getTeams().toString();

        }
        setReturnValue(allTournaments);
      }

    }.run();
  }

  @Override
  public boolean checkDuplicateTournament(String name) {
    return new WithSessionAndTransaction<Boolean>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Tournament t = tm.findTournamentByName(name);
        setReturnValue(t != null);
      }

    }.run();
  }

  @Override
  public int getUserRatingByName(String name) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getMatchesWonByUsername(String name) {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesWonByUserName(name);
    }

    return (count);
  }

  @Override
  public int getMatchesLostByUsername(String name) {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesLostByUserName(name);
    }

    return (count);
  }

  @Override
  public void startTournament(String name) {
    new WithSessionAndTransaction<Tournament>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Tournament tour = tm.findTournamentByName(name);
        tour.start(tm.findAllTeams());
        for (Team t : tour.getTeams()) {
          session.save(t);
        }
        for (Match m : tour.getMatches()) {
          if (m.getTeam1().isProxy()) {
            session.save(m.getTeam1());
          }
          if (m.getTeam2().isProxy()) {
            session.save(m.getTeam2());
          }
          session.save(m);
        }
        session.update(tour);
      }
    }.run();
  }

  @Override
  public void addParticipantsToTournament(Tournament t, ArrayList<User> participants) {
    new WithSessionAndTransaction() {

      @Override
      protected void executeBusinessLogic(Session session) {
        Tournament tour = getTournamentByName(t.getName());
        t.getMatches().toString();
        participants.toString();
        tour.setParticipants(participants);
        session.update(tour);
      }
    }.run();

  }

  @Override
  public Team getTeamByParticipants(List<Team> teams, User... participants) {
    ArrayList<User> part = new ArrayList<User>();
    Collections.addAll(part, participants);
    for (Team t : teams) {
      if (t.hasParticipants(part)) {
        return t;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Team> getTeams() {
    return new WithSessionAndTransaction<ArrayList<Team>>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        ArrayList<Team> tims = tm.findAllTeams();
        setReturnValue(tims);
        for (Team tim : tims) {
          tim.getPlayers().toString();
          tim.getTournaments().toString();
          for (Tournament t : tim.getTournaments()) {
            t.getMatches().toString();
            for (Match m : t.getMatches()) {
              m.getTeam1().toString();
              m.getTeam2().toString();
            }
            t.getTeams().toString();
            if (t.getWinner() != null) {
              t.getWinner().getPlayers().toString();
            }
          }
        }
      }

    }.run();
  }

  @Override
  public int getMatchesWonByTeam(Team team) {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesWonByTeam(team);
    }

    return (count);
  }

  @Override
  public int getMatchesLostByTeam(Team team) {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesLostByTeam(team);
    }

    return (count);
  }

  @Override
  public void addTeam(Team team) {
    new WithSessionAndTransaction() {

      @Override
      protected void executeBusinessLogic(Session session) {
        session.save(team);
      }

    }.run();

  }

  @Override
  public void editTournament(String tourName, Date newDate, TournamentType type, String format) {
    new WithSessionAndTransaction<Tournament>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        Tournament tour = getTournamentByName(tourName);
        tour.setStartDate(newDate);
        tour.setType(type);
        tour.setFormat(TournamentFormats.getTournamentFormatsByName(format));
        session.update(tour);
      }
    }.run();

  }

  @Override
  public void removeTournament(Tournament tour) {
    new WithSessionAndTransaction<Tournament>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Tournament x = tm.findTournamentByName(tour.getName());

        for (Match m : x.getMatches()) {
          if (m.getTeam1().isProxy()) {
            session.delete(m.getTeam1());
          }
          if (m.getTeam2().isProxy()) {
            session.delete(m.getTeam2());
          }
          session.delete(m);
        }

        x.setMatches(null);

        x.setParticipants(null);

        x.setTeams(null);

        session.update(x);
        session.delete(x);
      }

    }.run();
  }

  @Override
  public int getUserStars(User user) {
    int counter = 0;
    for (Tournament t : getTournaments()) {
      if (t.isFinished() && t.getWinner().hasPlayer(user.getUser())) {
        counter++;
      }
    }
    return counter;
  }

  @Override
  public int getTeamStars(Team tim) {
    int counter = 0;
    for (Tournament t : getTournaments()) {
      if (t.isFinished() && t.getWinner().toString().equals(tim.toString())) {
        counter++;
      }
    }
    return counter;
  }

  @Override
  public void init() {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        ArrayList<User> findAllUsers = tm.findAllUsers();
        if (findAllUsers.size() == 0) {
          session.save(new User("raz", "qwe", "Razvan", "Veina", true));
          session.save(new User("cni", "cni", "Catalin", "Nichifor", true));
          session.save(new User("dst", "dst", "Dana", "Stan", true));
          session.save(new User("cmt", "cmt", "Cristi", "Matei", false));

          session.save(new User("cpl", "cpl", "Cosmin", "Plugar", false));
          session.save(new User("cba", "cba", "Cristi", "Baciu", false));
          session.save(new User("asa", "asa", "Andreea", "Sas", false));
          session.save(new User("apa", "apa", "Andreea", "Pantea", false));

          session.save(new User("ave", "ave", "Andor", "Vetesi", false));
          session.save(new User("rva", "rva", "Robert", "Valea", false));
          session.save(new User("ema", "ema", "Elena", "Malaescu", false));
          session.save(new User("rvo", "rvo", "Razvan", "Voicu", false));

          session.save(new User("cvo", "cvo", "Cristina", "Volinteriu", false));
          session.save(new User("cco", "cco", "Marius", "Cosma", false));
          session.save(new User("acn", "acn", "Andrei", "Contoman", false));
          session.save(new User("sbr", "sbr", "Sorin", "Brazdau", false));

          session.save(new User("rst", "rst", "Razvan", "Strugariu", false));
          session.save(new User("cci", "cci", "Catalin", "Cinda", false));
          session.save(new User("sch", "sch", "Sorin", "Chis", false));
          session.save(new User("stu", "stu", "Seba", "Tutuianu", false));
        }
      }

    }.run();
  }

  public static Database getInstance() {
    if (instance == null) {
      instance = new OracleDatabase();
    }

    return instance;
  }

  @Override
  public void addMatchScore(Match match, int score1, int score2) {

    new WithSessionAndTransaction() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Match m = tm.readMatchById(match.getId());
        m.setPoints1(score1);
        m.setPoints2(score2);
        m.updateRatings();
        m.getTournament().reiterateMatches();
        session.update(m);
      }
    }.run();
  }

  @Override
  public void cleanupTournaments() {
    new WithSessionAndTransaction() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        ArrayList<Tournament> tournaments = tm.findAllTournaments();
        for (Tournament tournament : tournaments) {
          List<Team> teamsToDelete = tournament.cleanup();
          for (Match m : tournament.getMatches()) {
            session.saveOrUpdate(m);
          }

          for (Team team : teamsToDelete) {
            session.delete(team);
          }
        }
      }
    }.run();
  }

  @Override
  public void duplicateTournament(String pname) {
    new WithSessionAndTransaction() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Tournament tour = tm.findTournamentByName(pname);//ApplicationFactory.getInstance().getDatabase().getTournamentByName(pname);

        String oldName = tour.getName();
        String dupName = "duplicate";
        String suffix = oldName;
        int counter = 0;
        boolean nameValid = false;
        while (!nameValid) {
          if (ApplicationFactory.getInstance().getDatabase().getTournamentByName(suffix) == null) {
            nameValid = true;
            break;
          } else {
            oldName = suffix;
            counter++;
            suffix = "";
          }
          if (oldName.contains(dupName)) {
            int index = oldName.indexOf(dupName) + dupName.length();
            if (index == oldName.length()) {
              suffix = oldName + (counter);
            } else {
              suffix = oldName.substring(0, index);
              suffix = suffix + (counter);
            }

          } else {
            suffix = oldName + dupName;
          }

        }

        String name = suffix;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm ");
        String dateS = dateFormat.format(new Date(System.currentTimeMillis()));
        Date date = null;
        try {
          date = dateFormat.parse(dateS);
        } catch (ParseException e) {
          // TODO Auto-generated catch block
        }
        TournamentType type = tour.getType();
        String tourFormat = tour.getFormat().getName();

        //        ApplicationFactory.getInstance().getDatabase().addTournament(name, date, type, tourFormat);
        //        Tournament tx = ApplicationFactory.getInstance().getDatabase().getTournamentByName(name);
        Tournament tx = new Tournament(name, date, type, TournamentFormats.getTournamentFormatsByName(tourFormat));
        session.save(tx);

        tx.setParticipants(new ArrayList<>(tour.getParticipants()));
        session.update(tx);
      }
    }.run();

  }

  @Override
  public Team getTeamById(long id) {
    return new WithSessionAndTransaction<Team>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Team team = tm.readTeamById(id);
        if (team.isProxy()) {
          if (team.getTeam() != null) {
            team = team.getTeam();
          }
        }
        team.getPlayers().toString();
        team.getTournaments().toString();
        for (Tournament t : team.getTournaments()) {
          t.getMatches().toString();
          for (Match m : t.getMatches()) {
            m.getTeam1().toString();
            m.getTeam2().toString();
          }
          t.getTeams().toString();
          if (t.getWinner() != null) {
            t.getWinner().getPlayers().toString();
          }
        }
        setReturnValue(team);
      }
    }.run();
  }

  @Override
  public List<Match> getMatchesInvolvingTeams(Team t1, Team t2) {
    return new WithSessionAndTransaction<List<Match>>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        List<Match> result = new ArrayList<>();

        TennisManager tm = new TennisManager(session);
        ArrayList<Tournament> tournaments = tm.findAllTournaments();
        for (Tournament tournament : tournaments) {
          result.addAll(tournament.getMatchesInvolvingTeams(t1, t2));
        }
        setReturnValue(result);
      }
    }.run();

  }

}
