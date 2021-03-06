/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain.service;

import com.gabmingbets.gamingbetrestserver.domain.Ticket;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andre
 */
@Stateless
@Path("ticket")
public class TicketFacadeREST extends AbstractFacade<Ticket> {

    @PersistenceContext(unitName = "com.gabmingbets_gamingBetRestServer_war_1.0PU")
    private EntityManager em;

    public TicketFacadeREST() {
        super(Ticket.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Ticket entity) {
        super.create(entity);
    }
    
    @POST
    @Path("createticket")
    @Consumes(MediaType.APPLICATION_JSON)
    public Ticket createTicket(Ticket entity) {
        super.create(entity);
        
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Ticket entity) {
        Query q = em.createQuery("UPDATE Ticket t SET t.status="+entity.getStatus()+" WHERE t.id = " + entity.getId() +"");
        q.executeUpdate();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("userId/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> findByUserId(@PathParam("id") Integer id) {
        TypedQuery<Ticket> query = getEntityManager().createNamedQuery("Ticket.findByUserId", Ticket.class).setParameter("userId", id);
        return query.getResultList();
    }
    
    @GET
    @Path("open")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> findOpen() {
        TypedQuery<Ticket> query = getEntityManager().createNamedQuery("Ticket.findOpen", Ticket.class);
        return query.getResultList();
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
