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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "sc2_bet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2Bet.findAll", query = "SELECT s FROM Sc2Bet s"),
    @NamedQuery(name = "Sc2Bet.findByIdsc2Bet", query = "SELECT s FROM Sc2Bet s WHERE s.idsc2Bet = :idsc2Bet"),
    @NamedQuery(name = "Sc2Bet.findByBettedResult", query = "SELECT s FROM Sc2Bet s WHERE s.bettedResult = :bettedResult"),
    @NamedQuery(name = "Sc2Bet.findByStatus", query = "SELECT s FROM Sc2Bet s WHERE s.status = :status"),
    @NamedQuery(name = "Sc2Bet.findByProcessed", query = "SELECT s FROM Sc2Bet s WHERE s.processed = :processed"),
    @NamedQuery(name = "Sc2Bet.findByUserId", query = "SELECT s FROM Sc2Bet s WHERE s.userId = :userId")
    })
public class Sc2Bet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsc2_bet")
    private Integer idsc2Bet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "betted_result")
    private int bettedResult;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Column(name = "processed")
    private Integer processed;
    @JoinColumn(name = "bet_id", referencedColumnName = "idsc2_available_bets")
    @ManyToOne(optional = false)
    private Sc2AvailableBets betId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "input")
    private Integer input;

    public Sc2Bet() {
    }

    public Sc2Bet(Integer idsc2Bet) {
        this.idsc2Bet = idsc2Bet;
    }

    public Sc2Bet(Integer idsc2Bet, int bettedResult, int status) {
        this.idsc2Bet = idsc2Bet;
        this.bettedResult = bettedResult;
        this.status = status;
    }

    public Integer getIdsc2Bet() {
        return idsc2Bet;
    }

    public void setIdsc2Bet(Integer idsc2Bet) {
        this.idsc2Bet = idsc2Bet;
    }

    public int getBettedResult() {
        return bettedResult;
    }

    public void setBettedResult(int bettedResult) {
        this.bettedResult = bettedResult;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getProcessed() {
        return processed;
    }

    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    public Sc2AvailableBets getBetId() {
        return betId;
    }

    public void setBetId(Sc2AvailableBets betId) {
        this.betId = betId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Integer getInput() {
		return input;
	}

	public void setInput(Integer input) {
		this.input = input;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idsc2Bet != null ? idsc2Bet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sc2Bet)) {
            return false;
        }
        Sc2Bet other = (Sc2Bet) object;
        if ((this.idsc2Bet == null && other.idsc2Bet != null) || (this.idsc2Bet != null && !this.idsc2Bet.equals(other.idsc2Bet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2Bet[ idsc2Bet=" + idsc2Bet + " ]";
    }
    
}
