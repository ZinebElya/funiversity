package com.example.api;

import com.example.domain.Professor;
import com.example.dtos.CreateProfessorDto;
import com.example.dtos.ProfessorDto;
import com.example.dtos.UpdateProfessorDto;
import com.example.service.ProfessorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.BAD_REQUEST;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.CREATED;

@Path("/professors")
public class ProfessorController {
    private ProfessorService professorService;
    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);


    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(CREATED)


    public Response createProfessor(CreateProfessorDto createProfessorDto) {
        try {
            Professor newProfessor = professorService.createProfessor(createProfessorDto);
            String id = newProfessor.getId();
            logger.info("Professor created.");
            return Response.status(Response.Status.CREATED).entity("Professor id: " + id).build();
        } catch (Exception e) {
            logger.error("Error creating professor.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
        }
    }


    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllProfessors() {
        try {
            Collection<ProfessorDto> professors = professorService.getAllProfessors();
            if (professors.isEmpty()) {
                logger.info("No Professors Found");
                return Response.status(Response.Status.NOT_FOUND).entity("No Professors Found").build();
            } else {
                return Response.ok(professors).build();
            }
        } catch (Exception e) {
            logger.error("Error getting professors", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
        }
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProfessorDto getProfessorById(String id) {
        ProfessorDto professorDto = professorService.getProfessorById(id);
        if (professorDto == null) {
            logger.error("Professor not found with id: " + id);
            throw new WebApplicationException("Professor not found", Response.Status.NOT_FOUND);
        }
        return professorDto;

    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ProfessorDto updateProfessor(@PathParam("id") String id, UpdateProfessorDto updateProfessorDto) {
        ProfessorDto professorDto = professorService.getProfessorById(id);
        try {
            if (professorDto == null) {
                logger.error("Professor not found with id: " + id);
                throw new WebApplicationException("Professor not found", Response.Status.NOT_FOUND);
            }
            logger.info("Professor updated");
            return professorService.updateProfessor(id, updateProfessorDto);

        } catch (WebApplicationException e) {
            logger.error("Error updating professor with id: " + id, e);
            throw new WebApplicationException(BAD_REQUEST);

        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfessor(@PathParam("id") String id) {
        ProfessorDto professorDto = professorService.getProfessorById(id);
        try {
            if (professorDto == null) {
                logger.error("Professor not found with id: " + id);
                throw new WebApplicationException("Professor not found", Response.Status.NOT_FOUND);
            }
            professorService.deleteProfessor(id);
            logger.info("Professor with id: " + id + " deleted.");
            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            logger.error("Error deleting professor with id: " + id);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();

        }
    }


}
