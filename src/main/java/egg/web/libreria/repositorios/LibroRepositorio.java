
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Libro;
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
public interface LibroRepositorio extends JpaRepository<Libro, String> {
//@Query("SELECT c FROM Libro c WHERE c.nombre = :nombre")
//    public Libro buscarPorNombre(@Param("nombre")String nombre);
//@Query("SELECT c FROM Libro c WHERE c.isbn= :isbn")
//    public Libro searchByIsbn(@Param("isbn")Long isbn);
//      
    @Query("SELECT c FROM Libro c")
    public List<Libro> listarLibros();
    public Optional<Libro> findAllById(String id);
    
}
