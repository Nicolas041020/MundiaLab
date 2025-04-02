/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Competitor;
import com.example.models.CompetitorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONObject;
import javax.ws.rs.NotAuthorizedException;


/**
 *
 * @author Mauricio
 */
@Path("/competitors")
@Produces(MediaType.APPLICATION_JSON)
public class CompetitorService {

    @PersistenceContext(unitName = "CompetitorsPU")
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            entityManager
                    = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAll() {
//
//        List<Competitor> competitors = new ArrayList<Competitor>();
//        Competitor competitorTmp = new Competitor("Carlos", "Alvarez", 35, "7658463", "3206574839 ", "carlos.alvarez@gmail.com", "Bogota", "Colombia", false);
//        Competitor competitorTmp2 = new Competitor("Gustavo", "Ruiz", 55, "2435231", "3101325467", "gustavo.ruiz@gmail.com", "Buenos Aires", "Argentina", false);
//        competitors.add(competitorTmp);
//        competitors.add(competitorTmp2);
//        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitors).build();
//    }
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Query q = entityManager.createQuery("select u from Competitor u order by u.surname ASC");
        List<Competitor> competitors = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(competitors).build();
    }

//    @POST
//    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createCompetitor(CompetitorDTO competitor) {
//
//        Competitor competitorTmp = new Competitor(competitor.getName(), competitor.getSurname(), competitor.getAge(), competitor.getTelephone(), competitor.getCellphone(), competitor.getAddress(), competitor.getCity(), competitor.getCountry(), false);
//        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitorTmp).build();
//    }
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetitor(CompetitorDTO competitor) {
        JSONObject rta = new JSONObject();
        Competitor competitorTmp = new Competitor();
        competitorTmp.setAddress(competitor.getAddress());
        competitorTmp.setAge(competitor.getAge());
        competitorTmp.setCellphone(competitor.getCellphone());
        competitorTmp.setCity(competitor.getCity());
        competitorTmp.setCountry(competitor.getCountry());
        competitorTmp.setName(competitor.getName());
        competitorTmp.setSurname(competitor.getSurname());
        competitorTmp.setTelephone(competitor.getTelephone());
        competitorTmp.setEmail(competitor.getEmail());
        competitorTmp.setPassw(competitor.getPassw());

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(competitorTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(competitorTmp);
            rta.put("competitor_id", competitorTmp.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            competitorTmp = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(rta).build();
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(CompetitorDTO competitor){
        String em = competitor.getEmail();
        String pa = competitor.getPassw();
        
        try {
        Query query = entityManager.createQuery(
                "SELECT u FROM Competitor u WHERE u.email = :email AND u.passw = :passw");
        query.setParameter("email", em);
        query.setParameter("passw", pa);

        Competitor user = (Competitor) query.getSingleResult();

        return Response.ok(user).build();

    } catch (NoResultException e) {
        throw new NotAuthorizedException("Credenciales inválidas");
    }
    }
        
}
