
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.errores.ErrorServicio;
import egg.web.libreria.repositorios.AutorRepositorio;
import egg.web.libreria.repositorios.EditorialRepositorio;
import egg.web.libreria.servicios.LibroServicio;
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



@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        
        List<Autor> autores = autorRepositorio.findAll();
        modelo.put("autores", autores);
        List<Editorial> editoriales = editorialRepositorio.findAll();
        modelo.put("editoriales", editoriales);

        return "form-libro";
    }

    @PostMapping("/registro")
    public String registrar(ModelMap modelo, @RequestParam String titulo, Integer anio,  Long isbn,  Integer ejemplares, Integer ejemplaresPrestados, @RequestParam String idAutor, @RequestParam String idEditorial) throws Exception {
        try {
            libroServicio.registrar(titulo, anio, isbn, ejemplares, ejemplaresPrestados, idAutor, idEditorial);
            modelo.put("exito", "Registro Exitoso");
            return "form-libro";

        } catch (ErrorServicio e) {
            List<Autor> autores = autorRepositorio.findAll();
            modelo.put("autores", autores);
            List<Editorial> editoriales = editorialRepositorio.findAll();
            modelo.put("editoriales", editoriales);

        
            modelo.put("titulo", titulo);
            modelo.put("anio", anio);
            modelo.put("isbn", isbn);
            modelo.put("ejemplares", ejemplares);
            modelo.put("ejemplaresPrestados", ejemplaresPrestados);
            
            modelo.put("error", e.getMessage());
            return "form-libro";
        }

    }

    @GetMapping("/modificar/{id}")//Pathvariable
    public String modificar(@PathVariable String id, ModelMap modelo) {
    modelo.put("libro", libroServicio.getOne(id));

        List<Autor> autores = autorRepositorio.findAll();
        modelo.put("autores", autores);
        List<Editorial> editoriales = editorialRepositorio.findAll();
        modelo.put("editoriales", editoriales);

        return "form-libro-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Long isbn, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam String idAutor, @RequestParam String idEditorial) throws Exception {
        try {
            libroServicio.modificar(id, titulo, anio, isbn, ejemplares, ejemplaresPrestados, idAutor, idEditorial);
            //modelo.put("exito", "Registro Exitoso");
            return "list-libros";

        } catch (ErrorServicio ex) {
            List<Autor> autores = autorRepositorio.findAll();
            modelo.put("autores", autores);
            List<Editorial> editoriales = editorialRepositorio.findAll();
            modelo.put("editoriales", editoriales);

            modelo.put("error", ex.getMessage());
            modelo.put("titulo", titulo);
            modelo.put("anio", anio);
            modelo.put("isbn", isbn);
            modelo.put("ejemplares", ejemplares);
            modelo.put("ejemplaresPrestados", ejemplaresPrestados);

            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "form-libro-modif";
        }

    }
@GetMapping("/lista")
public String lista(ModelMap modelo){
    List<Libro> libros = libroServicio.listarTodos();
    modelo.addAttribute("libros", libros);
    return "list-libros";
}

@GetMapping("/baja/{id}")
public String baja(@PathVariable String id){
    try{
        libroServicio.deshabilitar(id);
        return "redirect:/libro/lista";
    }catch(Exception e){
        return "redirect:/";
    }
}

@GetMapping("/alta/{id}")
public String alta(@PathVariable String id){
    try{
        libroServicio.habilitar(id);
        return "redirect:/libro/lista";
    }catch(Exception e){
        return "redirect:/";
    }
}
}
