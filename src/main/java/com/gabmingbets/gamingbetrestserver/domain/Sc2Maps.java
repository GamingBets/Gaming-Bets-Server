/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "sc2_maps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2Maps.findAll", query = "SELECT s FROM Sc2Maps s"),
    @NamedQuery(name = "Sc2Maps.findByIdmaps", query = "SELECT s FROM Sc2Maps s WHERE s.idmaps = :idmaps"),
    @NamedQuery(name = "Sc2Maps.findByName", query = "SELECT s FROM Sc2Maps s WHERE s.name = :name")})
public class Sc2Maps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmaps")
    private Integer idmaps;
    @Size(max = 45)
    @Column(name = "name")
    private String name;

    public Sc2Maps() {
    }

    public Sc2Maps(Integer idmaps) {
        this.idmaps = idmaps;
    }

    public Integer getIdmaps() {
        return idmaps;
    }

    public void setIdmaps(Integer idmaps) {
        this.idmaps = idmaps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmaps != null ? idmaps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sc2Maps)) {
            return false;
        }
        Sc2Maps other = (Sc2Maps) object;
        if ((this.idmaps == null && other.idmaps != null) || (this.idmaps != null && !this.idmaps.equals(other.idmaps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2Maps[ idmaps=" + idmaps + " ]";
    }
    
}
