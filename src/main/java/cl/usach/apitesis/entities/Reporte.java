package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="reporte")
public class Reporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_REPORTE")
    private long idReporte;
    @Column(name = "NOMBRE_PROFESOR")
    private String nombreProfesor;
    @Column(name = "DESCRIPCION_REPORTE")
    private String descripcionReporte;
    @Column(name = "ASUNTO")
    private String asunto;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ALUMNO")
    @JsonBackReference
    private Alumno alumno;

    public long getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(long idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getDescripcionReporte() {
        return descripcionReporte;
    }

    public void setDescripcionReporte(String descripcionReporte) {
        this.descripcionReporte = descripcionReporte;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
