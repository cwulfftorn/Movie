package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movies;
import entities.MoviesDTO;
import utils.EMF_Creator;
import facades.MoviesFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class MoviesResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/movies",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MoviesFacade FACADE =  MoviesFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
     @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<MoviesDTO> movie = FACADE.getAllMovies();
        return GSON.toJson(movie);
    }

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        EntityManager em = EMF.createEntityManager();
        try {
            Movies m2 = new Movies(1970, "Bram Stokers Dracula", "Dracula, duhh");
            Movies m1 = new Movies(2015, "Backdoorsluts 9", "Niels Bramsespor og Denise Klarskov");
            em.getTransaction().begin(); //begin transaction
            em.persist(m1);
            em.persist(m2);
            em.getTransaction().commit(); //commit transactions
        } finally {
            em.close();
        }
        return "Populated the Database successfully. Don't run this again.";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getMovieCount();
        return "{\"count\":" + count + "}";
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") long id) {
        return GSON.toJson(FACADE.findMovieByID(id));
    }

    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getByName(@PathParam("name") String name) {
        return GSON.toJson(FACADE.getMovieDTOByName(name));
    }

 
}
