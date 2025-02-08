package edu.cibertec.matricula.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    
    @Size(min = 3, max = 20)
    @Id
    private String usuario;
    
    @NotNull
    @NotBlank
    @Column(name="clave")
    private String clave;
    @Column(name="nomCompleto")
    private String nombreCompleto;
    @Column
    private byte[] foto;
    @Transient
    private String fotoBase64;
    
    public String getFotoBase64() {
        String rpta = null;
        if(foto != null && foto.length > 0)
            rpta = Base64.getEncoder().encodeToString(foto);
        return rpta;
    }
}
