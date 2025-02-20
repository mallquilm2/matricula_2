
package edu.cibertec.matricula.controller;

import edu.cibertec.matricula.dao.entity.CursoEntity;
import edu.cibertec.matricula.service.CursoService;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CursoController {
    
    @Autowired
    private CursoService cursoService;
    
    Logger logger = LoggerFactory.getLogger(CursoController.class);
    
    @RequestMapping("")
    public String login(){
        System.out.println("");
        logger.info("Mostrando menu View");
        return "menu";
    }
    
    @RequestMapping("cursoMostrar.do")
    public String cursoMostrar(){
        logger.info("Mostrando cursoBusqueda View");
        return "cursoBusqueda";
    }
    
    @RequestMapping("cursoBusqueda.do")
    public ModelAndView menuConsultas(HttpServletRequest request){
        logger.info("Inicia Atendiendo menu consulta");
        ModelAndView mv = new ModelAndView("cursoBusqueda");
        try {
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
        
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Finaliza Atendiendo menu consulta");
        return mv;
    }
    
    @RequestMapping("cursoListar")
    public ModelAndView cursoListar(){
        ModelAndView mv = new ModelAndView("curso","lista", cursoService.listarTodos());
        mv.addObject("cursoBean", new CursoEntity());
        return mv;
    }
    
    @RequestMapping("cursoGrabar")
    public ModelAndView cursoGrabar(@ModelAttribute("cursoBean") CursoEntity curso){
        ModelAndView mv = new ModelAndView("curso");
        cursoService.insertar(curso);
        mv.addObject("lista", cursoService.listarTodos());
        mv.addObject("cursoBean", new CursoEntity());
        return mv;
    }
    
    @RequestMapping("cursoEliminar")
    public ModelAndView cursoEliminar(@RequestParam("codigo") int codigo){
        cursoService.eliminar(codigo);
        return new ModelAndView("redirect:cursoListar");
    }
    
}
