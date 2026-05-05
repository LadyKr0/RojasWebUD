// Lady Katherine Rojas Mendivelso
package asp.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Aspirante extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime fecha_reg;
    private ProgAcad pro_acad;

    public Aspirante() {
        super();
        this.pro_acad = new ProgAcad();
    }

    public Aspirante(String id_p, String nombres, String apellidos, String correo, String telefono, LocalDateTime fecha_reg,
            ProgAcad pro_acad) {
        super(id_p, nombres, apellidos, correo, telefono);
        this.fecha_reg = fecha_reg;
        this.pro_acad = pro_acad;
    }

    public LocalDateTime getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(LocalDateTime fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public ProgAcad getPro_acad() {
        return pro_acad;
    }

    public void setPro_acad(ProgAcad pro_acad) {
        this.pro_acad = pro_acad;
    }
}
