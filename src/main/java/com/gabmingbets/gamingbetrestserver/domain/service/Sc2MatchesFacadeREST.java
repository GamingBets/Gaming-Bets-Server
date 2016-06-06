/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain.service;

import com.gabmingbets.gamingbetrestserver.domain.Sc2Bet;
import com.gabmingbets.gamingbetrestserver.domain.Sc2Matches;
import com.gabmingbets.gamingbetrestserver.microservices.MicroserviceHandler;

import java.util.Iterator;
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
@Path("sc2matches")
public class Sc2MatchesFacadeREST extends AbstractFacade<Sc2Matches> {

    @PersistenceContext(unitName = "com.gabmingbets_gamingBetRestServer_war_1.0PU")
    private EntityManager em;

    public Sc2MatchesFacadeREST() {
        super(Sc2Matches.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Sc2Matches entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Sc2Matches entity) {
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
    public Sc2Matches find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("newsFeed")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sc2Matches> getNewsFeed(){
    	TypedQuery<Sc2Matches> query = getEntityManager().createNamedQuery("Sc2Matches.findNewsFeed", Sc2Matches.class);
        return query.getResultList();
    	
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sc2Matches> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sc2Matches> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    
    
    @GET
    @Path("createBets")
    @Produces(MediaType.TEXT_PLAIN)
    public String createBets(){
    	
       	StringBuilder temp = new StringBuilder("Create Bets!:\n");
    	temp.append("Before:\n");
    	
    	TypedQuery<Sc2Matches> query = getEntityManager().createNamedQuery("Sc2Matches.findByBetCreated", Sc2Matches.class).setParameter("betCreated", 0);
    	List<Sc2Matches> list = query.getResultList();
    	
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Sc2Matches sc2Matches = (Sc2Matches) iterator.next();
			temp.append(sc2Matches.toString() + "\n");
		}
    	
    	MicroserviceHandler.createAvailableBetsSC2();
    	
    	temp.append("After:\n");
    	query = getEntityManager().createNamedQuery("Sc2Matches.findByBetCreated", Sc2Matches.class).setParameter("betCreated", 0);
    	list = query.getResultList();
    	
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Sc2Matches sc2Matches = (Sc2Matches) iterator.next();
			temp.append(sc2Matches.toString() + "\n");
		}
    	
    	return temp.toString();
    	
    }
    
    @GET
    @Path("evaluateBets")
    @Produces(MediaType.TEXT_PLAIN)
    public String evaluateBets(){
    	
       	StringBuilder temp = new StringBuilder("Evaluate Bets!:\n");
    	temp.append("Before:\n");
    	
    	//TypedQuery<Sc2Matches> query = getEntityManager().createNamedQuery("Sc2Matches.findByFinished", Sc2Matches.class).setParameter("finished", 1);
    	TypedQuery<Sc2Bet> query = getEntityManager().createNamedQuery("Sc2Bet.findAllMatchEndedNotEvaluated", Sc2Bet.class); 
    	List<Sc2Bet> list = query.getResultList();
    	
    	for (Sc2Bet sc2Bet : list) {
			temp.append(sc2Bet.toString()+"\n");
		}
    	
    	MicroserviceHandler.evaluateBetsSC2();
    	
    	temp.append("After:\n");
    	query = getEntityManager().createNamedQuery("Sc2Bet.findAllMatchEndedNotEvaluated", Sc2Bet.class); 
    	list = query.getResultList();
    	
    	for (Sc2Bet sc2Bet : list) {
			temp.append(sc2Bet.toString()+"\n");
		}
    	
    	return temp.toString();
    	
    }
    
    @GET
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public String executeMicroservices(){
    	

    	MicroserviceHandler.createAvailableBetsSC2();
    	MicroserviceHandler.evaluateBetsSC2();
    	MicroserviceHandler.adjustScoreSC2();
    	return "Done";
    	
    }
    
  

	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
