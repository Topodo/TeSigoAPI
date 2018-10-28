package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="a_completado")
public class ACompletado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "INDICADOR_COMPLETADO")
    private Boolean indicadorCompletado;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ALUMNO")
    @JsonBackReference
    private Alumno alumno;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_INDICADOR")
    @JsonBackReference
    private IndicadorEvaluacion indicadorEvaluacion;

    public Boolean getIndicadorCompletado() {
        return indicadorCompletado;
    }

    public void setIndicadorCompletado(Boolean indicadorCompletado) {
        this.indicadorCompletado = indicadorCompletado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public IndicadorEvaluacion getIndicadorEvaluacion() {
        return indicadorEvaluacion;
    }

    public void setIndicadorEvaluacion(IndicadorEvaluacion indicadorEvaluacion) {
        this.indicadorEvaluacion = indicadorEvaluacion;
    }
}
