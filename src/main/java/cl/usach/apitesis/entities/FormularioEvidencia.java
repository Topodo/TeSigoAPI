package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="formulario_evidencia")
public class FormularioEvidencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVIDENCIA")
    private Long idEvidencia;

    @Column(name = "NOMBRE_EVIDENCIA")
    private String nombreEvidencia;

    @Column(name = "CONTEXTO_EVIDENCIA")
    private String contextoEvidencia;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    @Column(name = "FECHA_EVIDENCIA")
    private Date fechaEvidencia;

    @Column(name = "FIREBASE_ID")
    private String firebaseID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ALUMNO")
    @JsonBackReference(value = "alumno-evidencia")
    private Alumno alumno;

    public FormularioEvidencia(){
        super();
    }

    public Long getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(Long idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getNombreEvidencia() {
        return nombreEvidencia;
    }

    public void setNombreEvidencia(String nombreEvidencia) {
        this.nombreEvidencia = nombreEvidencia;
    }

    public String getContextoEvidencia() {
        return contextoEvidencia;
    }

    public void setContextoEvidencia(String contextoEvidencia) {
        this.contextoEvidencia = contextoEvidencia;
    }

    public Date getFechaEvidencia() {
        return fechaEvidencia;
    }

    public void setFechaEvidencia(Date fechaEvidencia) {
        this.fechaEvidencia = fechaEvidencia;
    }

    public String getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
