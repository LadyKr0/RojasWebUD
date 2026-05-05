// Lady Katherine Rojas Mendivelso
package asp.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import asp.modelo.Aspirante;
import asp.modelo.AspiranteDAO;
import asp.modelo.ProgAcad;
import asp.modelo.ProgAcadDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("asp")
@ViewScoped
public class AspiranteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter LOG_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Aspirante dto = new Aspirante();
    private ArrayList<Aspirante> listaAs = AspiranteDAO.lista_A;
    private ArrayList<ProgAcad> listaPa = ProgAcadDAO.lista_P;
    private int codPrograma = -1;

    public AspiranteBean() {
        ProgAcadDAO.cargaDatos();
    }

    public Aspirante getDto() {
        return dto;
    }

    public void setDto(Aspirante dto) {
        this.dto = dto;
    }

    public ArrayList<Aspirante> getListaAs() {
        return listaAs;
    }

    public void setListaAs(ArrayList<Aspirante> listaAs) {
        this.listaAs = listaAs;
    }

    public ArrayList<ProgAcad> getListaPa() {
        return listaPa;
    }

    public void setListaPa(ArrayList<ProgAcad> listaPa) {
        this.listaPa = listaPa;
    }

    public int getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(int codPrograma) {
        this.codPrograma = codPrograma;
    }

    public void registrar() {
        for (Aspirante aspirante : listaAs) {
            if (aspirante.getId_p().equals(dto.getId_p())) {
                addError("La identificacion ya esta registrada.");
                return;
            }
            if (aspirante.getCorreo().equalsIgnoreCase(dto.getCorreo())) {
                addError("El correo ya esta registrado.");
                return;
            }
            if (aspirante.getTelefono().equals(dto.getTelefono())) {
                addError("El telefono ya esta registrado.");
                return;
            }
        }

        if (codPrograma < 0 || codPrograma >= listaPa.size()) {
            addError("Debe seleccionar un programa academico valido.");
            return;
        }

        Aspirante nuevo = new Aspirante();
        nuevo.setId_p(dto.getId_p().trim());
        nuevo.setNombres(dto.getNombres().trim());
        nuevo.setApellidos(dto.getApellidos().trim());
        nuevo.setCorreo(dto.getCorreo().trim().toLowerCase());
        nuevo.setTelefono(dto.getTelefono().trim());
        nuevo.setFecha_reg(LocalDateTime.now());
        nuevo.setPro_acad(listaPa.get(codPrograma));

        AspiranteDAO.agregar(nuevo);

        String momento = nuevo.getFecha_reg().format(LOG_FORMAT);
        System.out.println("[RojasWebUD] Registro exitoso: " + nuevo.getNombres() + " " + nuevo.getApellidos() + " - "
                + nuevo.getPro_acad().getNombre_prog() + " - " + momento);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Aspirante registrado correctamente."));

        dto = new Aspirante();
        codPrograma = -1;
    }

    private void addError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }
}
