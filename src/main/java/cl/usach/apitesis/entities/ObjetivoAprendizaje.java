package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="objetivo_aprendizaje")
public class ObjetivoAprendizaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_OBJETIVO")
    private long idObjetivo;
    @Column(name = "DESCRIPCION_OBJETIVO")
    private String descripcionObjetivo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_UNIDAD")
    @JsonBackReference(value = "unidad-oa")
    private Unidad unidad;
    @OneToMany(mappedBy = "objetivoAprendizaje", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "oa-indicador")
    private Set<IndicadorEvaluacion> indicadoresEvaluacion;

    public String getDescripcionObjetivo() {
        return descripcionObjetivo;
    }

    public void setDescripcionObjetivo(String descripcionObjetivo) {
        this.descripcionObjetivo = descripcionObjetivo;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Set<IndicadorEvaluacion> getIndicadoresEvaluacion() {
        return indicadoresEvaluacion;
    }

    public void setIndicadoresEvaluacion(Set<IndicadorEvaluacion> indicadoresEvaluacion) {
        this.indicadoresEvaluacion = indicadoresEvaluacion;
    }

    public long getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(long idObjetivo) {
        this.idObjetivo = idObjetivo;
    }
}
