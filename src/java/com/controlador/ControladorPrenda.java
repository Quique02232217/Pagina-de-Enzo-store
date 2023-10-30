package com.controlador;

import com.conexion.Conexion;
import com.modelo.Prenda;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class ControladorPrenda {

    private Prenda prenda = new Prenda();
    private List<Prenda> listaprenda = new ArrayList<>();
    PreparedStatement ps;
    ResultSet rs;

    public ControladorPrenda() {
    }

    public void registrarPrendaBD() {
        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("INSERT INTO prendas (nombre,colores,tallas,descripcion,precio) VALUES(?,?,?,?,?)");

            ps.setString(1, prenda.getNombre());
            ps.setString(2, prenda.getColor());
            ps.setString(3, prenda.getTalla());
            ps.setString(4, prenda.getDescripcion());
            ps.setString(5, prenda.getPrecio());

            int rss = ps.executeUpdate();
            if (rss > 0) {
                FaceUtil.addErrorMessage("Registro de prendas exitoso");
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect("CatalogoVirtual.xhtml");
            } else {
                FaceUtil.addErrorMessage("Error al registrar la prenda");
            }
            con.close();
        } catch (SQLException | IOException e) {
            FaceUtil.addErrorMessage("ERROR AL GUARDAR ---> " + e);
        }

    }

    public Prenda mostrarPrendas() {

        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("SELECT * FROM prendas WHERE nombre = ? AND precio = ?");
            ps.setString(1, prenda.getNombre());
            ps.setString(2, prenda.getPrecio());

            rs = ps.executeQuery();

            while (rs.next()) {
                prenda = new Prenda(rs.getString("nombre"), rs.getString("precio"));
                listaprenda.add(prenda);
                return prenda = new Prenda();
            }
            con.close();

        } catch (SQLException s) {
            FaceUtil.addErrorMessage("error en la base de datos al iniciar sesion " + s);

        }

        return null;
    }
    
    
    
    public void pasarARegistrodePrendas() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("registroDePrendas.xhtml");

    }

    public void pasarARegistroDeUsuario() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("RegistroDeUsuario.xhtml");

    }

    public void pasarAInicioDeSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("InicioDeSesion.xhtml");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public List<Prenda> getListaprenda() {
        return listaprenda;
    }

    public void setListaprenda(List<Prenda> listaprenda) {
        this.listaprenda = listaprenda;
    }

}
