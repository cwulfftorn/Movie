package facades;

import entities.Movies;
import entities.MoviesDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MoviesFacade {

    private static MoviesFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MoviesFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MoviesFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MoviesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MoviesDTO findMovieByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            MoviesDTO movie = em.find(MoviesDTO.class, id);
            return movie;
        } finally {
            em.close();
        }
    }

    public List<MoviesDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MoviesDTO> query
                    = em.createQuery("Select movie from Movies movie", MoviesDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public long getMovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long countMovies = (long) em.createQuery("SELECT COUNT(r) FROM Movies r").getSingleResult();
            return countMovies;
        } finally {
            em.close();
        }
    }

    public MoviesDTO getMovieDTOByName(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT new dto.MoviesDTO(m) FROM Movies m WHERE m.name = :name", MoviesDTO.class)
                    .setParameter("name", name).getSingleResult();
        } finally {
            em.close();
        }
    }


}
