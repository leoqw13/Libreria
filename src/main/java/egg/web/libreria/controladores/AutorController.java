/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;

import egg.web.libreria.errores.ErrorServicio;
import egg.web.libreria.servicios.AutorServicio;
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
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {

        return "form-autor";
    }

    @PostMapping("/registro")
    public String registrar(ModelMap modelo, @RequestParam String nombre) {
        try {
            autorServicio.registrar(nombre);
            modelo.put("exito", "Registro Exitoso");
            return "form-autor";

        } catch (ErrorServicio ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);

            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "form-autor";
        }

    }

    @GetMapping("/modificar/{id}")//Pathvariable
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorServicio.getOne(id));

        return "form-autor-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre) throws Exception {
        try {
            autorServicio.modificar(id, nombre);
            //modelo.put("exito", "Registro Exitoso");
            return "redirect:/autor/lista";

        } catch (ErrorServicio ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);

            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "form-autor-modif";
        }

    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarTodos();
        modelo.addAttribute("autores", autores);
        return "list-autores";
    }

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id) {
        try {
            autorServicio.deshabilitar(id);
            return "redirect:/autor/lista";
        } catch (ErrorServicio e) {
            return "redirect:/";
        }
    }

    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id) {
        try {
            autorServicio.habilitar(id);
            return "redirect:/autor/lista";
        } catch (ErrorServicio e) {
            return "redirect:/";
        }
    }
}
