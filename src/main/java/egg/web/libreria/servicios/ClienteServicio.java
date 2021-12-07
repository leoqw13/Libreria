/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Cliente;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.errores.ErrorServicio;
import egg.web.libreria.repositorios.ClienteRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Leonardo
 */
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public void registrar(String nombre, String apellido, String telefono, Long documento) throws Exception {

validar(nombre, apellido, telefono, documento);       

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDocumento(documento);
        cliente.setTelefono(telefono);
        cliente.setAlta(true);
        clienteRepositorio.save(cliente);
    }
    
    @Transactional
 public Cliente modificar(String id,String nombre, String apellido, String telefono, Long documento) throws Exception{
    validar(nombre, apellido, telefono, documento);   
     
    Cliente l =clienteRepositorio.getOne(id);
       if(l !=null){
         
           
                l.setNombre(nombre);
                l.setApellido(apellido);
                l.setTelefono(telefono);
                l.setDocumento(documento);
                  return clienteRepositorio.save(l);
       }else{throw new Exception("NO se encontro ese cliente");
 }}
    
    
 @Transactional
    public void habilitar(String id) {
       
          Cliente cliente = clienteRepositorio.getOne(id);
       
           cliente.setAlta(true);
         clienteRepositorio.save(cliente);
        
    }
         @Transactional
    public void deshabilitar(String id) {
      Cliente cliente = clienteRepositorio.getOne(id);
       
           cliente.setAlta(false);
         clienteRepositorio.save(cliente);
        
    }
     @Transactional
        public Cliente getOne(String id){
            return clienteRepositorio.getOne(id);
        }
    public List<Cliente> listarTodos() {
    return clienteRepositorio.findAll();
    }
    public void validar(String nombre, String apellido, String telefono, Long documento) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del cliente no puede estar vacio");
        }
        if (apellido== null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del cliente no puede estar vacio");
        }
        if (documento== null) {
            throw new ErrorServicio("El documento del cliente no puede estar vacio");
        }

        if (telefono== null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono del cliente no puede estar vacio");
        }
        
    }
}
