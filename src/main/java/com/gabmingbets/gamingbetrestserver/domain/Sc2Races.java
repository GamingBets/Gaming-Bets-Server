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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "sc2_races")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2Races.findAll", query = "SELECT s FROM Sc2Races s"),
    @NamedQuery(name = "Sc2Races.findByIdraces", query = "SELECT s FROM Sc2Races s WHERE s.idraces = :idraces"),
    @NamedQuery(name = "Sc2Races.findByName", query = "SELECT s FROM Sc2Races s WHERE s.name = :name"),
    @NamedQuery(name = "Sc2Races.findByShortcut", query = "SELECT s FROM Sc2Races s WHERE s.shortcut = :shortcut")})
public class Sc2Races implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idraces")
    private Integer idraces;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "shortcut")
    private String shortcut;
    @OneToMany(mappedBy = "race")
    private Collection<Sc2Player> sc2PlayerCollection;

    public Sc2Races() {
    }

    public Sc2Races(Integer idraces) {
        this.idraces = idraces;
    }

    public Integer getIdraces() {
        return idraces;
    }

    public void setIdraces(Integer idraces) {
        this.idraces = idraces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    @XmlTransient
    public Collection<Sc2Player> getSc2PlayerCollection() {
        return sc2PlayerCollection;
    }

    public void setSc2PlayerCollection(Collection<Sc2Player> sc2PlayerCollection) {
        this.sc2PlayerCollection = sc2PlayerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idraces != null ? idraces.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sc2Races)) {
            return false;
        }
        Sc2Races other = (Sc2Races) object;
        if ((this.idraces == null && other.idraces != null) || (this.idraces != null && !this.idraces.equals(other.idraces))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2Races[ idraces=" + idraces + " ]";
    }
    
}
