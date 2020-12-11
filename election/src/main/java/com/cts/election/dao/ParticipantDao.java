package com.cts.election.dao;

import com.cts.election.entity.DailyData;
import com.cts.election.model.ConstituencyStatus;
import com.cts.election.model.PartyStatus;
import com.cts.election.model.WinnerStatus;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ParticipantDao {
  @PersistenceContext EntityManager entityManager;
  String url = "assets/";
  String winner;
  WinnerStatus ws;

  public Map<String, Object> getConstituencyStatus(String constituency) {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> party1 = new HashMap<String, Object>();
    Map<String, Object> party2 = new HashMap<String, Object>();
    List<ConstituencyStatus> p1 = new ArrayList<ConstituencyStatus>();
    List<ConstituencyStatus> p2 = new ArrayList<ConstituencyStatus>();
    int total =
        ((BigInteger)
                    entityManager
                        .createNativeQuery(
                            "select count(associate_id) from master_data where constituency_name='"
                                + constituency
                                + "'")
                        .getSingleResult())
                .intValue()
            * 20;
    List<Object[]> l1 =
        entityManager
            .createNativeQuery(
                "select participant.associate_id,master_data.associate_name,completion_status,party_name from participant inner join master_data "
                    + "where participant.associate_id=master_data.associate_id and constituency_name='"
                    + constituency
                    + "' and party_name='Party 1' order by completion_status desc")
            .getResultList();
    l1.stream()
        .forEach(
            result -> {
              p1.add(
                  new ConstituencyStatus(
                      String.valueOf(result[0]),
                      String.valueOf(result[1]),
                      String.valueOf(result[2]),
                      String.valueOf(result[3])));
            });
    List<Object[]> l2 =
        entityManager
            .createNativeQuery(
                "select participant.associate_id,master_data.associate_name,completion_status,party_name from participant inner join master_data "
                    + "where participant.associate_id=master_data.associate_id and constituency_name='"
                    + constituency
                    + "' and party_name='Party 2' order by completion_status desc")
            .getResultList();
    l2.stream()
        .forEach(
            result -> {
              p2.add(
                  new ConstituencyStatus(
                      String.valueOf(result[0]),
                      String.valueOf(result[1]),
                      String.valueOf(result[2]),
                      String.valueOf(result[3])));
            });
    PartyStatus ps1 = this.getStatusWithoutGender("Party 1", constituency);
    PartyStatus ps2 = this.getStatusWithoutGender("Party 2", constituency);
    party1.put("members", p1);
    party1.put("male", ps1.getMaleVotes());
    party1.put("female", ps1.getFemaleVotes());
    party2.put("members", p2);
    party2.put("male", ps2.getMaleVotes());
    party2.put("female", ps2.getFemaleVotes());
    map.put("Party 1", party1);
    map.put("Party 2", party2);
    map.put("totalVotes", total);
    return map;
  }

  public Set<String> getConstituencies() {
    List<String> list =
        entityManager
            .createNativeQuery("select constituency_name from master_data")
            .getResultList();
    list.forEach(
        (data) -> {
          System.out.println(data);
        });
    return list.stream().collect(Collectors.toSet());
  }

  public Map<String, WinnerStatus> getWinner() {
    Set<String> list = this.getConstituencies();
    Map<String, WinnerStatus> map = new HashMap<String, WinnerStatus>();
    list.stream()
        .forEach(
            constituency -> {
              try {
                winner =
                    entityManager
                        .createNativeQuery(
                            "select winner from winners where constituency_name='"
                                + constituency
                                + "'")
                        .getSingleResult()
                        .toString();
              } catch (NoResultException e) {
                winner = null;
              }
              if (winner != null) {
                ws = new WinnerStatus(constituency, winner, url + winner + "win.png");
                map.put(constituency, ws);
              } else {
                int total =
                    ((BigInteger)
                                entityManager
                                    .createNativeQuery(
                                        "select count(associate_id) from master_data where constituency_name='"
                                            + constituency
                                            + "'")
                                    .getSingleResult())
                            .intValue()
                        * 20;

                PartyStatus p1 = this.getStatusWithoutGender("Party 1", constituency);
                PartyStatus p2 = this.getStatusWithoutGender("Party 2", constituency);
                //				int party1percent = (p1.getVotes()*100)/total;
                //				int party2percent = (p2.getVotes()*100)/total;
                int party1percent = p1.getVotes();
                int party2percent = p2.getVotes();

                if (party1percent > party2percent)
                  ws =
                      new WinnerStatus(
                          constituency, p1.getPartyName(), url + p1.getPartyName() + "lead.png");
                else if (party2percent > party1percent)
                  ws =
                      new WinnerStatus(
                          constituency, p2.getPartyName(), url + p2.getPartyName() + "lead.png");
                else if (party1percent == 0)
                  ws = new WinnerStatus(constituency, "no result", url + "No_Result.png");
                else ws = new WinnerStatus(constituency, "draw", url + "Drawn.png");
                map.put(constituency, ws);
              }
            });

    return sortByValue(map);
  }

  public PartyStatus getStatus(String party, String constituency) {
    int maleVotes = 0, femaleVotes = 0;
    Object male =
        entityManager
            .createNativeQuery(
                "select sum(completion_status) as total_votes from participant inner join master_data "
                    + "where participant.associate_id=master_data.associate_id and constituency_name='"
                    + constituency
                    + "' "
                    + "and party_name='"
                    + party
                    + "' and gender='Male'")
            .getSingleResult();
    if (male != null) maleVotes = ((BigDecimal) male).intValue();

    Object female =
        entityManager
            .createNativeQuery(
                "select sum(completion_status) as total_votes from participant inner join master_data "
                    + "where participant.associate_id=master_data.associate_id and constituency_name='"
                    + constituency
                    + "' "
                    + "and party_name='"
                    + party
                    + "' and gender='Female'")
            .getSingleResult();
    if (female != null) femaleVotes = ((BigDecimal) female).intValue();

    PartyStatus status =
        new PartyStatus(party, constituency, maleVotes + femaleVotes, maleVotes, femaleVotes);
    return status;
  }

  public Map<String, Object> getPartyStatus(String constituency) {
    Map<String, Object> map = new HashMap<String, Object>();
    int total =
        ((BigInteger)
                    entityManager
                        .createNativeQuery(
                            "select count(associate_id) from master_data where constituency_name='"
                                + constituency
                                + "'")
                        .getSingleResult())
                .intValue()
            * 20;
    PartyStatus p1 = this.getStatusWithoutGender("Party 1", constituency);
    PartyStatus p2 = this.getStatusWithoutGender("Party 2", constituency);
    int party1percent = (p1.getVotes() * 100) / total;
    int party2percent = (p2.getVotes() * 100) / total;
    map.put("TotalVotes", total);
    map.put("Party 1", p1);
    map.put("Party 2", p2);
    map.put("P1", party1percent);
    map.put("P2", party2percent);
    return map;
  }

  public List<Object> getMapData() {
    Set<String> list = this.getConstituencies();
    List<Object> map = new ArrayList<Object>();
    list.stream()
        .forEach(
            constituency -> {
              Map<String, Object> data = new HashMap<String, Object>();
              int total =
                  ((BigInteger)
                              entityManager
                                  .createNativeQuery(
                                      "select count(associate_id) from master_data where constituency_name='"
                                          + constituency
                                          + "'")
                                  .getSingleResult())
                          .intValue()
                      * 20;
              PartyStatus p1 = this.getStatusWithoutGender("Party 1", constituency);
              PartyStatus p2 = this.getStatusWithoutGender("Party 2", constituency);
              int party1percent = (p1.getVotes() * 100) / total;
              int party2percent = (p2.getVotes() * 100) / total;
              data.put("Constituency", constituency);
              data.put("Party1", party1percent);
              data.put("Party2", party2percent);
              map.add(data);
            });
    return map;
  }

  public List<DailyData> getDailyData(String constituency) {
    Query query =
        entityManager.createNativeQuery(
            "select * from daily_data where constituency_name='"
                + constituency
                + "' order by created DESC limit 0,7",
            DailyData.class);
    List<DailyData> list = query.getResultList();
    return list;
  }

  public PartyStatus getStatusWithoutGender(String party, String constituency) {
    int totalVotes = 0;
    Object total =
        entityManager
            .createNativeQuery(
                "select sum(completion_status) as total_votes from participant inner join master_data "
                    + "where participant.associate_id=master_data.associate_id and constituency_name='"
                    + constituency
                    + "' "
                    + "and party_name='"
                    + party
                    + "'")
            .getSingleResult();
    if (total != null) totalVotes = ((BigDecimal) total).intValue();

    PartyStatus status = new PartyStatus(party, constituency, totalVotes);
    return status;
  }

  public static HashMap<String, WinnerStatus> sortByValue(Map<String, WinnerStatus> hm) {
    // Create a list from elements of HashMap
    List<Map.Entry<String, WinnerStatus>> list =
        new LinkedList<Map.Entry<String, WinnerStatus>>(hm.entrySet());

    // Sort the list
    Collections.sort(
        list,
        new Comparator<Map.Entry<String, WinnerStatus>>() {
          public int compare(
              Map.Entry<String, WinnerStatus> o1, Map.Entry<String, WinnerStatus> o2) {
            return (o1.getValue().getParty()).compareTo(o2.getValue().getParty());
          }
        });

    // put data from sorted list to hashmap
    HashMap<String, WinnerStatus> temp = new LinkedHashMap<String, WinnerStatus>();
    for (Map.Entry<String, WinnerStatus> aa : list) {
      temp.put(aa.getKey(), aa.getValue());
    }

    temp.forEach(
        (k, v) -> {
          System.out.println(v.getConstituency());
          System.out.println(v.getImageUrl());
        });
    return temp;
  }

  public Object getParticipantDetails(String id) {
    return entityManager
        .createNativeQuery(
            "select participant.associate_id,participant.associate_name,master_data.constituency_name,master_data.party_name,(participant.completion_status*100)/20 from master_data inner join participant on master_data.associate_id=participant.associate_id where master_data.associate_id="
                + id)
        .getSingleResult();
  }
}
