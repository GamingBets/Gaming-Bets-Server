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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "ticket_messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketMessages.findAll", query = "SELECT t FROM TicketMessages t"),
    @NamedQuery(name = "TicketMessages.findById", query = "SELECT t FROM TicketMessages t WHERE t.id = :id ORDER by t.datetime"),
    @NamedQuery(name = "TicketMessages.findByDatetime", query = "SELECT t FROM TicketMessages t WHERE t.datetime = :datetime")})
public class TicketMessages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "datetime")
    private String datetime;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    @ManyToOne
    private Ticket ticketId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    

    public TicketMessages() {
    }

    public TicketMessages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Ticket getTicketId() {
        return ticketId;
    }

    public void setTicketId(Ticket ticketId) {
        this.ticketId = ticketId;
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
        if (!(object instanceof TicketMessages)) {
            return false;
        }
        TicketMessages other = (TicketMessages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gabmingbets.gamingbetrestserver.domain.TicketMessages[ id=" + id + " ]";
    }
    
}
