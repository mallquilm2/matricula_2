
package edu.cibertec.matricula.service;

import edu.cibertec.matricula.dao.UsuarioDAO;
import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    public UsuarioEntity validarLogin(UsuarioEntity usuario){
        return usuarioDAO.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave());
    }
    
    public void insertarUsuario(UsuarioEntity usuario){
        usuarioDAO.save(usuario);
    }
    
    public List<UsuarioEntity> getListarUsuarios(Pageable pagina){
        return usuarioDAO.findAll(pagina).getContent();
    }
    
    public UsuarioEntity getUsuario(String codigo){
        UsuarioEntity rpta = null;
        Optional<UsuarioEntity> usuarioBusqueda = usuarioDAO.findById(codigo);
        if(usuarioBusqueda.isPresent())
            rpta = usuarioBusqueda.get();
        return rpta;
    }
    
    public void modificarUsuario(UsuarioEntity usuario){
        usuarioDAO.save(usuario);
    }
    
    public void eliminarUsuario(String codigo){
        usuarioDAO.deleteById(codigo);
    }
    
    public List<UsuarioEntity> listarTodosLosUsuarios(){
        return usuarioDAO.findAll();
    }
    
}
