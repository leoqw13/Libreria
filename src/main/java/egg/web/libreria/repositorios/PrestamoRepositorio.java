/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo
 */
@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String>{
    
    @Query("SELECT c FROM Prestamo c")
    public List<Prestamo> listarPrestamos();
}
