package edu.cibertec.matricula;

import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import edu.cibertec.matricula.domain.UsuarioDomain;
import edu.cibertec.matricula.mapper.UsuarioMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MatriculaApplication {

	public static void main(String[] args) {
            UsuarioDomain usuario = new UsuarioDomain();
            usuario.setId(1L);
            usuario.setContrasenia("12345");
            usuario.setNombre("Erick Paez");
            
            UsuarioEntity usuarioEntity = UsuarioMapper.INSTANCE.usuarioDomainToUsuarioEntity(usuario);
            System.out.println("ID:"+usuarioEntity.getUsuario());
            System.out.println("NOMBRE:"+usuarioEntity.getNombreCompleto());
            System.out.println("CONTRASENIA:"+usuarioEntity.getClave());
            
		SpringApplication.run(MatriculaApplication.class, args);
	}

}
