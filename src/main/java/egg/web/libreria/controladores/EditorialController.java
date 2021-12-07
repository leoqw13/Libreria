/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.errores.ErrorServicio;
import egg.web.libreria.servicios.EditorialServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Leonardo
 */
@Controller
@RequestMapping("/editorial")
public class EditorialController {
      @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        
        return "form-editorial";
    }

    @PostMapping("/registro")
    public String registrar(ModelMap modelo, @RequestParam String nombre) {
        try {
            editorialServicio.registrar(nombre);
            modelo.put("exito", "Registro Exitoso");
            return "form-editorial";

        } catch (ErrorServicio ex) {
           
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);

            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "form-editorial";
        }

    }

    @GetMapping("/modificar/{id}")//Pathvariable
    public String modificar(@PathVariable String id, ModelMap modelo) {
    modelo.put("editorial", editorialServicio.getOne(id));

       
        return "redirect:/editorial/lista";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre) throws Exception {
        try {
            editorialServicio.modificar(id, nombre);
            //modelo.put("exito", "Registro Exitoso");
            return "list-editoriales";

        } catch (ErrorServicio ex) {
           
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "form-editorial-modif";
        }

    }
@GetMapping("/lista")
public String lista(ModelMap modelo){
    List<Editorial> editoriales = editorialServicio.listarTodos();
    modelo.addAttribute("editoriales", editoriales);
    return "list-editoriales";
}

@GetMapping("/baja/{id}")
public String baja(@PathVariable String id){
    try{
        editorialServicio.deshabilitar(id);
        return "redirect:/editorial/lista";
    }catch(ErrorServicio e){
        return "redirect:/";
    }
}

@GetMapping("/alta/{id}")
public String alta(@PathVariable String id){
    try{
        editorialServicio.habilitar(id);
        return "redirect:/editorial/lista";
    }catch(ErrorServicio e){
        return "redirect:/";
    }
}
}
