/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "sc2_player")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2Player.findAll", query = "SELECT s FROM Sc2Player s"),
    @NamedQuery(name = "Sc2Player.findById", query = "SELECT s FROM Sc2Player s WHERE s.id = :id"),
    @NamedQuery(name = "Sc2Player.findByDob", query = "SELECT s FROM Sc2Player s WHERE s.dob = :dob"),
    @NamedQuery(name = "Sc2Player.findByIngameName", query = "SELECT s FROM Sc2Player s WHERE s.ingameName = :ingameName"),
    @NamedQuery(name = "Sc2Player.findByRealName", query = "SELECT s FROM Sc2Player s WHERE s.realName = :realName")})
public class Sc2Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;
    @Size(max = 45)
    @Column(name = "ingame_name")
    private String ingameName;
    @Size(max = 45)
    @Column(name = "real_name")
    private String realName;
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    @ManyToOne
    private Country nationality;
    @JoinColumn(name = "race", referencedColumnName = "idraces")
    @ManyToOne
    private Sc2Races race;
    @JoinColumn(name = "team", referencedColumnName = "id")
    @ManyToOne
    private Sc2Team team;
    @OneToMany(mappedBy = "player1")
    private Collection<Sc2Matches> sc2MatchesCollection;
    @OneToMany(mappedBy = "player2")
    private Collection<Sc2Matches> sc2MatchesCollection1;

    public Sc2Player() {
    }

    public Sc2Player(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getIngameName() {
        return ingameName;
    }

    public void setIngameName(String ingameName) {
        this.ingameName = ingameName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Sc2Races getRace() {
        return race;
    }

    public void setRace(Sc2Races race) {
        this.race = race;
    }

    public Sc2Team getTeam() {
        return team;
    }

    public void setTeam(Sc2Team team) {
        this.team = team;
    }

    @XmlTransient
    public Collection<Sc2Matches> getSc2MatchesCollection() {
        return sc2MatchesCollection;
    }

    public void setSc2MatchesCollection(Collection<Sc2Matches> sc2MatchesCollection) {
        this.sc2MatchesCollection = sc2MatchesCollection;
    }

    @XmlTransient
    public Collection<Sc2Matches> getSc2MatchesCollection1() {
        return sc2MatchesCollection1;
    }

    public void setSc2MatchesCollection1(Collection<Sc2Matches> sc2MatchesCollection1) {
        this.sc2MatchesCollection1 = sc2MatchesCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sc2Player)) {
            return false;
        }
        Sc2Player other = (Sc2Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2Player[ id=" + id + " ]";
    }
    
}
