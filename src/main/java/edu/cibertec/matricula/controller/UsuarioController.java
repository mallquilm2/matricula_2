package edu.cibertec.matricula.controller;

import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import edu.cibertec.matricula.security.SecurityConfig;
import edu.cibertec.matricula.service.UsuarioService;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("accionSesion")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("loginMostrar.do")
    public String loginMostrar() {
        return "login";
    }

    @RequestMapping("loginAccion.do")
    public ModelAndView loginAccion(UsuarioEntity usuarioValida) {
        ModelAndView mv = null;

        UsuarioEntity ue = usuarioService.validarLogin(usuarioValida);
        if (ue == null) {
            mv = new ModelAndView("login", "msgError", "Usuario y clave no existen.");
        } else {
            mv = new ModelAndView("menu", "mensaje", "Bienvenid@ " + ue.getNombreCompleto());
        }
        return mv;
    }

    @RequestMapping("usuarioCrear.do")
    public ModelAndView crearUsuario(Model model) {
        ModelAndView mv = new ModelAndView("usuarioDatos", "usuarioBean", new UsuarioEntity());
        mv.addObject("accion", "Insertar");
        model.addAttribute("accionSesion", "Insertar");
        return mv;
    }

    @RequestMapping("usuarioMod.do")
    public ModelAndView usuarioModificar(@RequestParam("codigoUsuario") String codigo, Model model) {
        ModelAndView mv = new ModelAndView("usuarioDatos", "usuarioBean", usuarioService.getUsuario(codigo));
        mv.addObject("accion", "Modificar");
        model.addAttribute("accionSesion", "Modificar");
        return mv;
    }

    @RequestMapping("grabarUsuario.do")
    public ModelAndView grabarUsuario(@Valid @ModelAttribute("usuarioBean") UsuarioEntity usuario, BindingResult result,
            @RequestParam("archivo") MultipartFile archivo, Model model) throws IOException {
        String accion = (String) model.getAttribute("accionSesion");
        ModelAndView mv = null;
        if (result.hasErrors()) {
            mv = new ModelAndView("usuarioDatos", "usuarioBean", usuario);
        } else {
            usuario.setFoto(archivo.getBytes());
            if (accion.equalsIgnoreCase("Insertar")) {
                usuarioService.insertarUsuario(usuario);
            } else {
                usuarioService.modificarUsuario(usuario);
            }
            mv = new ModelAndView("redirect:usuarioListar.do?pag=0");
        }
        return mv;
    }

    @RequestMapping("fotoMostrar.do")
    public String fotoMostrar(@RequestParam("codigoUsuario") String codigoUsuario, Model modelo) {
        UsuarioEntity user = usuarioService.getUsuario(codigoUsuario);
        modelo.addAttribute("usuario", user);
        modelo.addAttribute("foto64", user.getFotoBase64());
        return "fotoUsuario";
    }

    @RequestMapping("usuarioListar.do")
    public ModelAndView usuarioListar(@RequestParam(value = "pag", required = false, defaultValue = "0") int pag, @RequestParam(value = "orden", required = false, defaultValue = "usuario") String orden) {
        Pageable pagina = null;
        if (orden == null || orden.equalsIgnoreCase("null")) {
            pagina = PageRequest.of(pag, 5);
        } else {
            pagina = PageRequest.of(pag, 5, Sort.by(orden));
        }
        return new ModelAndView("usuarioLista", "lista", usuarioService.getListarUsuarios(pagina));
    }

    @RequestMapping("usuarioEli.do")
    public ModelAndView usuarioEliminar(@RequestParam("codigoUsuario") String codigo) {
        usuarioService.eliminarUsuario(codigo);
        return new ModelAndView("redirect:usuarioListar.do?pag=0");
    }

}
