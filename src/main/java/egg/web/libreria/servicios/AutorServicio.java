/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.errores.ErrorServicio;
import egg.web.libreria.repositorios.AutorRepositorio;
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
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio; 
 
    @Transactional
    public Autor registrar(String nombre) throws ErrorServicio{
        if(nombre == null|| nombre.isEmpty()){
            throw new ErrorServicio("El nombre del usuario no puede estar vacio");
        }
        
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
       return autorRepositorio.save(autor);
    }
    
    
    @Transactional
    public void modificar(String id,String nombre) throws ErrorServicio{
      if(nombre == null|| nombre.isEmpty()){
            throw new ErrorServicio("El nombre no puede estar vacio");
        }
       Optional <Autor> respuesta = autorRepositorio.findAllById(id);
       if(respuesta.isPresent()){
          Autor autor = respuesta.get();
            
       
        autor.setNombre(nombre);
      
        autorRepositorio.save(autor);
       }else{
          throw new ErrorServicio("No se encontro el autor") ;
       }
       
    }
    @Transactional
    public void habilitar(String id) throws ErrorServicio {
        Optional <Autor> respuesta = autorRepositorio.findAllById(id);
         if(respuesta.isPresent()){
           Autor autor = respuesta.get();
        autor.setAlta(true);
               autorRepositorio.save(autor);
         }else {
          throw new ErrorServicio("No se encontro el autor") ;
         }
    }
    @Transactional
    public void deshabilitar(String id) throws ErrorServicio {
        Optional <Autor> respuesta = autorRepositorio.findAllById(id);
         if(respuesta.isPresent()){
           Autor autor = respuesta.get();
         autor.setAlta(false);
                autorRepositorio.save(autor);
         }else {
          throw new ErrorServicio("No se encontro el autor") ;
         }
    }
    @Transactional
        public Autor getOne(String id){
            return autorRepositorio.getOne(id);
        }
         public List<Autor> listarTodos() {
    return autorRepositorio.findAll();
    }
}
