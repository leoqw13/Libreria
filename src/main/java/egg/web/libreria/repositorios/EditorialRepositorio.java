package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Editorial;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo
 */
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
//    @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
//    public Editorial buscarPorNombre(@Param("nombre")String nombre);
      
    @Query("SELECT c FROM Editorial c")
    public List<Editorial> listarEditoriales();

    public Optional<Editorial> findAllById(String id);
@Query("SELECT e FROM Editorial e WHERE e.id = :id")
    public Editorial searchById(@Param("id")String id);
}
