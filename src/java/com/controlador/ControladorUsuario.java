package com.controlador;

import com.conexion.Conexion;
import com.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ControladorUsuario {

   private Usuario usuario = new Usuario();
    private Usuario usuarioInicioSesion = new Usuario();
    
    PreparedStatement ps;
        ResultSet rs;

    public ControladorUsuario() {
    }

    public void guardar() {
        
        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("INSERT INTO usuarios (nombre, apellido, correo, contraseña, tipo_contacto) VALUES (?,?,?,?,?)");

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getTipo_contacto());
            
            
            int rss = ps.executeUpdate();

            if (rss > 0) {
                FaceUtil.addInfoMessage("registro exitoso");
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect("CatalogoVirtual.xhtml");
            } else {
                FaceUtil.addErrorMessage("error al registrarse");
            }
            con.close();

        } catch (SQLException|IOException s) {
            FaceUtil.addErrorMessage("error en la base de datos al guardar registro " + s);
        }

    }
    
        public String  iniciosesion(){
        
        Connection con = null;
        
        try{
            con = Conexion.getConection();
            ps = con.prepareStatement("SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?");
            ps.setString(1, usuarioInicioSesion.getCorreo());
            ps.setString(2, usuarioInicioSesion.getContraseña());
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect("CatalogoVirtual.xhtml");
            } else {
                FaceUtil.addErrorMessage("contraseña o correo invalidos");
            }
            con.close();
            
        }catch(SQLException | IOException s){
            FaceUtil.addErrorMessage("error en la base de datos al iniciar sesion " + s);

        }
        
        
        return null;
    }
    

    


    public Usuario getUsuarioInicioSesion() {
        return usuarioInicioSesion;
    }

    public void setUsuarioInicioSesion(Usuario usuarioInicioSesion) {
        this.usuarioInicioSesion = usuarioInicioSesion;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
