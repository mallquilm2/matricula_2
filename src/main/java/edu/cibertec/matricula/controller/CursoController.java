
package edu.cibertec.matricula.controller;

import edu.cibertec.matricula.service.CursoService;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CursoController {
    
    @Autowired
    private CursoService cursoService;
    
    @RequestMapping("")
    public String login(){
        return "login";
    }
    
    @RequestMapping("cursoMostrar.do")
    public String cursoMostrar(){
        return "cursoBusqueda";
    }
    
    @RequestMapping("cursoBusqueda.do")
    public ModelAndView menuConsultas(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("cursoBusqueda");
        String tipo = request.getParameter("tipo");
        
        switch (tipo) {
            case "estado":
                mv.addObject("lista", cursoService.consultarPorEstado(Integer.parseInt(request.getParameter("estado"))));
                break;
            case "incompleto":
                mv.addObject("lista", cursoService.abiertoIncompleto());
                break;
            case "porfecha":
                mv.addObject("lista", cursoService.consultarPorFecha(Date.valueOf(request.getParameter("fecha"))));
                break;
            case "faltante":
                mv.addObject("lista", cursoService.consultarFaltantes(Integer.parseInt(request.getParameter("cantidad"))));
                break;
            case "nombre":
                mv.addObject("lista", cursoService.consultarPorNombre(request.getParameter("nombre")));
                break;
            default:
                throw new AssertionError();
        }
        
        return mv;
    }
    
}
