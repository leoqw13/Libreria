/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.errores.ErrorServicio;
import egg.web.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo
 */
@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio; 
    
    
    
     public void registrar(String nombre) throws ErrorServicio{
         
        if(nombre == null|| nombre.isEmpty()){
            throw new ErrorServicio("El nombre no puede estar vacio");
        }
        
       Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
       editorial.setAlta(true);
       editorialRepositorio.save(editorial);
    }
    public void modificar(String id,String nombre) throws ErrorServicio{
      if(nombre == null|| nombre.isEmpty()){
            throw new ErrorServicio("El nombre no puede estar vacio");
        }
       Optional <Editorial> respuesta = editorialRepositorio.findAllById(id);
       if(respuesta.isPresent()){
          Editorial editorial = respuesta.get();
            
       
        editorial.setNombre(nombre);
      
        editorialRepositorio.save(editorial);
       }else{
          throw new ErrorServicio("No se encontro la editorial") ;
       }
       
    }
    
    public void habilitar(String id) throws ErrorServicio {
        Optional <Editorial> respuesta = editorialRepositorio.findAllById(id);
         if(respuesta.isPresent()){
           Editorial editorial = respuesta.get();
        editorial.setAlta(true);
          editorialRepositorio.save(editorial);
         }else {
          throw new ErrorServicio("No se encontro la editorial") ;
         }
    }
    public void deshabilitar(String id) throws ErrorServicio {
        Optional <Editorial> respuesta = editorialRepositorio.findAllById(id);
         if(respuesta.isPresent()){
           Editorial editorial = respuesta.get();
         editorial.setAlta(false);
           editorialRepositorio.save(editorial);
         }else {
          throw new ErrorServicio("No se encontro la editorial") ;
         }
    }
    
 @Transactional
        public Editorial getOne(String id){
            return editorialRepositorio.getOne(id);
        }
         public List<Editorial> listarTodos() {
    return editorialRepositorio.findAll();
    }
             
}
