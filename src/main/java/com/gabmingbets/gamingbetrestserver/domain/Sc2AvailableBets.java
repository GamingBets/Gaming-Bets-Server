/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "sc2_available_bets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2AvailableBets.findAll", query = "SELECT s FROM Sc2AvailableBets s"),
    @NamedQuery(name = "Sc2AvailableBets.findByIdsc2AvailableBets", query = "SELECT s FROM Sc2AvailableBets s WHERE s.idsc2AvailableBets = :idsc2AvailableBets")})
public class Sc2AvailableBets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsc2_available_bets")
    private Integer idsc2AvailableBets;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "betId")
    private Collection<Sc2Bet> sc2BetCollection;

    @JoinColumn(name = "match_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sc2Matches matchId;
    
    public Sc2AvailableBets() {
    }

    public Sc2Matches getMatchId() {
        return matchId;
    }

    public void setMatchId(Sc2Matches matchId) {
        this.matchId = matchId;
    }

   
    
    public Sc2AvailableBets(Integer idsc2AvailableBets) {
        this.idsc2AvailableBets = idsc2AvailableBets;
    }

    public Integer getIdsc2AvailableBets() {
        return idsc2AvailableBets;
    }

    public void setIdsc2AvailableBets(Integer idsc2AvailableBets) {
        this.idsc2AvailableBets = idsc2AvailableBets;
    }

    @XmlTransient
    public Collection<Sc2Bet> getSc2BetCollection() {
        return sc2BetCollection;
    }

    public void setSc2BetCollection(Collection<Sc2Bet> sc2BetCollection) {
        this.sc2BetCollection = sc2BetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsc2AvailableBets != null ? idsc2AvailableBets.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sc2AvailableBets)) {
            return false;
        }
        Sc2AvailableBets other = (Sc2AvailableBets) object;
        if ((this.idsc2AvailableBets == null && other.idsc2AvailableBets != null) || (this.idsc2AvailableBets != null && !this.idsc2AvailableBets.equals(other.idsc2AvailableBets))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2AvailableBets[ idsc2AvailableBets=" + idsc2AvailableBets + " ]";
    }
    
}
