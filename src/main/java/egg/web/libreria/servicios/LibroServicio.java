/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.errores.ErrorServicio;
import egg.web.libreria.repositorios.AutorRepositorio;
import egg.web.libreria.repositorios.EditorialRepositorio;
import egg.web.libreria.repositorios.LibroRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo
 */
@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio; 
    
       @Autowired
    private AutorRepositorio autorRepositorio; 
       @Autowired
    private EditorialRepositorio editorialRepositorio; 
               
    @Transactional 
    public void registrar(String titulo,  Integer anio,  Long isbn,  Integer ejemplares, Integer ejemplaresPrestados, String idAutor,String idEditorial) throws Exception{
        
        Autor autor = autorRepositorio.getOne(idAutor);
        Editorial editorial = editorialRepositorio.getOne(idEditorial);
        
        validar(titulo, isbn, titulo, anio, ejemplares, ejemplaresPrestados,autor,editorial);
         
      
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
    libro.setAutor(autor);
    libro.setEditorial(editorial);
    libro.setAlta(true);
  libroRepositorio.save(libro);
    }
    
             @Transactional
 public Libro modificar(String id,String titulo,  Integer anio,  Long isbn,  Integer ejemplares, Integer ejemplaresPrestados, String idAutor,String idEditorial) throws Exception{
     Autor autor = autorRepositorio.getOne(idAutor);
        Editorial editorial = editorialRepositorio.getOne(idEditorial);
      validar(titulo, isbn, titulo, anio, ejemplares, ejemplaresPrestados,autor,editorial);   
      
     Libro l =libroRepositorio.getOne(id);
       if(l !=null){
         
           
                l.setIsbn(isbn);
                l.setTitulo(titulo);
                l.setAnio(anio);
                l.setEjemplares(ejemplares);
                l.setEjemplaresPrestados(ejemplaresPrestados);
                  return libroRepositorio.save(l);
       }else{throw new Exception("NO se encontro ese libro");
 }}
 
         @Transactional
    public void habilitar(String id) {
       
          Libro libro = libroRepositorio.getOne(id);
       
           libro.setAlta(true);
         libroRepositorio.save(libro);
        
    }
         @Transactional
    public void deshabilitar(String id) {
      Libro libro = libroRepositorio.getOne(id);
       
           libro.setAlta(false);
         libroRepositorio.save(libro);
        
    }
    
    private void validar(String id,Long isbn,String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,Autor autor, Editorial editorial) throws Exception , ErrorServicio{
    
         if(titulo == null|| titulo.isEmpty()){
                  
            throw new ErrorServicio("El nombre del libro no puede estar vacio");
        }
                 
        if(anio == null){
                  
            throw new ErrorServicio("El año del libro no puede estar vacio");
        }
//        
        if(isbn == null){

            throw new ErrorServicio("El isbn del libro no puede estar vacio");
        }
        
        if(ejemplares == null){
                
            throw new ErrorServicio("Los ejemplares del libro no pueden estar vacio");
        } 
        if(ejemplaresPrestados == null){
            throw new ErrorServicio("Los ejemplares del libro no pueden estar vacio");
        }
  if(autor == null|| titulo.isEmpty()){

            throw new ErrorServicio("El Autor del libro no puede estar vacio");
        }
    if(editorial == null|| titulo.isEmpty()){
 
            throw new ErrorServicio("La Editorial del libro no puede estar vacio");
        }
    }
        @Transactional
        public Libro getOne(String id){
            return libroRepositorio.getOne(id);
        }

    public List<Libro> listarTodos() {
    return libroRepositorio.findAll();
    }
    
    
    
    
    
    
    
     public void crear(Libro libro) throws ErrorServicio{
//      
//         Autor autor = autorRepositorio.findById(IdAutor).get();
//        Editorial editorial = editorialRepositorio.findById(IdEditorial).get();
//        
//          
//    if(libroRepositorio.searchByIsbn(libro.getIsbn())==null){
//        Autor autor = autorRepositorio.searchById(libro.getAutor().getId());
//        if(autor ==null){
//             throw new ErrorServicio("No se encontro el autor") ;
//        }
//         Editorial editorial = editorialRepositorio.searchById(libro.getEditorial().getId());
//         
//        if(editorial ==null){
//             throw new ErrorServicio("No se encontro la editorial") ;
//        }
//    }    else{
//             throw new ErrorServicio("El libro ya existe") ;
//        
//    }
//        libroRepositorio.save(libro);
    }
}
