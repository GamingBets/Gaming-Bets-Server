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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sc2_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2Team.findAll", query = "SELECT s FROM Sc2Team s"),
    @NamedQuery(name = "Sc2Team.findById", query = "SELECT s FROM Sc2Team s WHERE s.id = :id"),
    @NamedQuery(name = "Sc2Team.findByName", query = "SELECT s FROM Sc2Team s WHERE s.name = :name"),
    @NamedQuery(name = "Sc2Team.findByLogo", query = "SELECT s FROM Sc2Team s WHERE s.logo = :logo")})
public class Sc2Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "logo")
    private String logo;
    @OneToMany(mappedBy = "team")
    private Collection<Sc2Player> sc2PlayerCollection;
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    @ManyToOne
    private Country nationality;

    public Sc2Team() {
    }

    public Sc2Team(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @XmlTransient
    public Collection<Sc2Player> getSc2PlayerCollection() {
        return sc2PlayerCollection;
    }

    public void setSc2PlayerCollection(Collection<Sc2Player> sc2PlayerCollection) {
        this.sc2PlayerCollection = sc2PlayerCollection;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
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
        if (!(object instanceof Sc2Team)) {
            return false;
        }
        Sc2Team other = (Sc2Team) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2Team[ id=" + id + " ]";
    }
    
}
