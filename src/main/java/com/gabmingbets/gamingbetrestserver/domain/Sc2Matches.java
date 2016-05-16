/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andre
 */
@Entity
@Table(name = "sc2_matches")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sc2Matches.findAll", query = "SELECT s FROM Sc2Matches s"),
    @NamedQuery(name = "Sc2Matches.findById", query = "SELECT s FROM Sc2Matches s WHERE s.id = :id"),
    @NamedQuery(name = "Sc2Matches.findByResult", query = "SELECT s FROM Sc2Matches s WHERE s.result = :result"),
    @NamedQuery(name = "Sc2Matches.findByDate", query = "SELECT s FROM Sc2Matches s WHERE s.date = :date"),
    @NamedQuery(name = "Sc2Matches.findByComment", query = "SELECT s FROM Sc2Matches s WHERE s.comment = :comment"),
    @NamedQuery(name = "Sc2Matches.findByBetCreated", query = "SELECT s FROM Sc2Matches s WHERE s.betCreated = :betCreated"),
    @NamedQuery(name = "Sc2Matches.findByFinished", query = "SELECT s FROM Sc2Matches s WHERE s.finished = :finished")})
public class Sc2Matches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "result")
    private Integer result;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 45)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bet_created")
    private int betCreated;
    @Column(name = "finished")
    private Integer finished;
    @JoinColumn(name = "type", referencedColumnName = "idGameTyp")
    @ManyToOne
    private Gametyp type;
    @JoinColumn(name = "player1", referencedColumnName = "id")
    @ManyToOne
    private Sc2Player player1;
    @JoinColumn(name = "player2", referencedColumnName = "id")
    @ManyToOne
    private Sc2Player player2;
    @JoinColumn(name = "tournament_id", referencedColumnName = "idtournament")
    @ManyToOne
    private Sc2Tournament tournamentId;

    public Sc2Matches() {
    }

    public Sc2Matches(Integer id) {
        this.id = id;
    }

    public Sc2Matches(Integer id, int betCreated) {
        this.id = id;
        this.betCreated = betCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBetCreated() {
        return betCreated;
    }

    public void setBetCreated(int betCreated) {
        this.betCreated = betCreated;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public Gametyp getType() {
        return type;
    }

    public void setType(Gametyp type) {
        this.type = type;
    }

    public Sc2Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Sc2Player player1) {
        this.player1 = player1;
    }

    public Sc2Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Sc2Player player2) {
        this.player2 = player2;
    }

    public Sc2Tournament getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Sc2Tournament tournamentId) {
        this.tournamentId = tournamentId;
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
        if (!(object instanceof Sc2Matches)) {
            return false;
        }
        Sc2Matches other = (Sc2Matches) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.Sc2Matches[ id=" + id + " ]";
    }
    
}
