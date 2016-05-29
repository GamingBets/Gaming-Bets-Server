/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain.service;

import com.gabmingbets.gamingbetrestserver.domain.Sc2AvailableBets;
import com.gabmingbets.gamingbetrestserver.domain.Sc2Matches;
import com.gabmingbets.gamingbetrestserver.domain.Sc2Player;
import com.gabmingbets.gamingbetrestserver.domain.Sc2Tournament;
import com.gabmingbets.gamingbetrestserver.domain.User;
import com.gabmingbets.gamingbetrestserver.microservices.MicroserviceHandler;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("sc2availablebets")
public class Sc2AvailableBetsFacadeREST extends AbstractFacade<Sc2AvailableBets> {

    @PersistenceContext(unitName = "com.gabmingbets_gamingBetRestServer_war_1.0PU")
    private EntityManager em;

    public Sc2AvailableBetsFacadeREST() {
        super(Sc2AvailableBets.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Sc2AvailableBets entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Sc2AvailableBets entity) {
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
    public Sc2AvailableBets find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("notFinished/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sc2AvailableBets> findAllNotFinished(@PathParam("id") Integer id){
        MicroserviceHandler.createAvailableBetsSC2();
        TypedQuery<Sc2AvailableBets> query = getEntityManager().createNamedQuery("Sc2AvailableBets.findAllNotFinished", Sc2AvailableBets.class).setParameter("idtournament", id);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sc2AvailableBets> findAll() {
        return super.findAll();
    }
    @GET
    @Path("notFinished")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sc2AvailableBets> findAllNotFinished(){
        TypedQuery<Sc2AvailableBets> query = getEntityManager().createNamedQuery("Sc2AvailableBets.findAllNotFinished", Sc2AvailableBets.class);
        
        return query.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sc2AvailableBets> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
