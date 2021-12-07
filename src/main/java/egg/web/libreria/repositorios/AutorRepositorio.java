
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Autor;
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
public interface AutorRepositorio extends JpaRepository<Autor, String> {
//    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombre")
//    public Autor buscarPorNombre(@Param("nombre")String nombre);
//    @Query("SELECT c FROM Autor c WHERE c.id = :id")
//    public Autor searchById(@Param("id")String id);
//   
    @Query("SELECT c FROM Autor c")
    public List<Autor> listarAutores();

    
    public Optional<Autor> findAllById(String id);
    
}
