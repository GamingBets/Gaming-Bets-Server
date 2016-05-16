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
@Table(name = "gametyp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gametyp.findAll", query = "SELECT g FROM Gametyp g"),
    @NamedQuery(name = "Gametyp.findByIdGameTyp", query = "SELECT g FROM Gametyp g WHERE g.idGameTyp = :idGameTyp"),
    @NamedQuery(name = "Gametyp.findByName", query = "SELECT g FROM Gametyp g WHERE g.name = :name")})
public class Gametyp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGameTyp")
    private Integer idGameTyp;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "type")
    private Collection<Sc2Matches> sc2MatchesCollection;

    public Gametyp() {
    }

    public Gametyp(Integer idGameTyp) {
        this.idGameTyp = idGameTyp;
    }

    public Integer getIdGameTyp() {
        return idGameTyp;
    }

    public void setIdGameTyp(Integer idGameTyp) {
        this.idGameTyp = idGameTyp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idGameTyp != null ? idGameTyp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gametyp)) {
            return false;
        }
        Gametyp other = (Gametyp) object;
        if ((this.idGameTyp == null && other.idGameTyp != null) || (this.idGameTyp != null && !this.idGameTyp.equals(other.idGameTyp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Gametyp[ idGameTyp=" + idGameTyp + " ]";
    }
    
}
