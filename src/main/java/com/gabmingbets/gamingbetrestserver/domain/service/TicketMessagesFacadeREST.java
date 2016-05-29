/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabmingbets.gamingbetrestserver.domain.Ticket;
import com.gabmingbets.gamingbetrestserver.domain.TicketMessages;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author Andre
 */
@Stateless
@Path("ticketmessages")
public class TicketMessagesFacadeREST extends AbstractFacade<TicketMessages> {

    @PersistenceContext(unitName = "com.gabmingbets_gamingBetRestServer_war_1.0PU")
    private EntityManager em;

    public TicketMessagesFacadeREST() {
        super(TicketMessages.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(TicketMessages entity) {
        super.create(entity);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public String createMessage(TicketMessages entity) {
/*        
        TicketMessages message = new TicketMessages();
        JSONObject obj = new JSONObject(messagejson);
        
        message.setContent(obj.getString("content"));
        message.setDatetime(obj.getString("datetime"));
        
        JSONObject ticketobj = obj.getJSONObject("ticketId");
        Ticket ticket = new Ticket();
        ticket.setDate(ticketobj.getString("date"));
        ticket.setId(ticketobj.getInt("id"));
        ticket.setStatus(2);
        ticket.setUserId(ticketobj.getInt("userId"));
        
        message.setTicketId(ticket);
        message.setUserId(obj.getInt("userId"));
        
        getEntityManager().persist(message);
        */
        return entity.getDatetime();
                
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, TicketMessages entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public TicketMessages find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<TicketMessages> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TicketMessages> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
