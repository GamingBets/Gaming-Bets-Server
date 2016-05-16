/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "sc2_tournament")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2Tournament.findAll", query = "SELECT s FROM Sc2Tournament s"),
    @NamedQuery(name = "Sc2Tournament.findByIdtournament", query = "SELECT s FROM Sc2Tournament s WHERE s.idtournament = :idtournament"),
    @NamedQuery(name = "Sc2Tournament.findByName", query = "SELECT s FROM Sc2Tournament s WHERE s.name = :name"),
    @NamedQuery(name = "Sc2Tournament.findByLocation", query = "SELECT s FROM Sc2Tournament s WHERE s.location = :location"),
    @NamedQuery(name = "Sc2Tournament.findByLink", query = "SELECT s FROM Sc2Tournament s WHERE s.link = :link"),
    @NamedQuery(name = "Sc2Tournament.findByStatus", query = "SELECT s FROM Sc2Tournament s WHERE s.status = :status")})
public class Sc2Tournament implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtournament")
    private Integer idtournament;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "location")
    private String location;
    @Size(max = 100)
    @Column(name = "link")
    private String link;
    @Column(name = "status")
    private Integer status;
    @OneToMany(mappedBy = "tournamentId")
    private Collection<Sc2Matches> sc2MatchesCollection;

    public Sc2Tournament() {
    }

    public Sc2Tournament(Integer idtournament) {
        this.idtournament = idtournament;
    }

    public Sc2Tournament(Integer idtournament, String name) {
        this.idtournament = idtournament;
        this.name = name;
    }

    public Integer getIdtournament() {
        return idtournament;
    }

    public void setIdtournament(Integer idtournament) {
        this.idtournament = idtournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Sc2Matches> getSc2MatchesCollection() {
        return sc2MatchesCollection;
    }

    public void setSc2MatchesCollection(Collection<Sc2Matches> sc2MatchesCollection) {
        this.sc2MatchesCollection = sc2MatchesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtournament != null ? idtournament.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sc2Tournament)) {
            return false;
        }
        Sc2Tournament other = (Sc2Tournament) object;
        if ((this.idtournament == null && other.idtournament != null) || (this.idtournament != null && !this.idtournament.equals(other.idtournament))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2Tournament[ idtournament=" + idtournament + " ]";
    }
    
}
