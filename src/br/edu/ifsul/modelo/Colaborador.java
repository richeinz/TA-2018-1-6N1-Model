package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "colaborador")
public class Colaborador implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_colaborador", sequenceName = "seq_colaborador_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_colaborador", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "A cargaHoraria de parcelas deve ser informada")
    @Column(name = "cargaHoraria", nullable = false)
    private Integer cargaHoraria;

    @Column(name = "gestor", nullable = false)
    private Boolean gestor;
    
    @ManyToOne
    @NotNull(message = "O usuario deve ser informado")
    @JoinColumn(name = "usuario", referencedColumnName = "id",  nullable = false)
    @ForeignKey(name = "fK_usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_colaborador_id")
    private Colaborador colaborador;

    public Colaborador() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Boolean getGestor() {
        return gestor;
    }

    public void setGestor(Boolean gestor) {
        this.gestor = gestor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    
}
