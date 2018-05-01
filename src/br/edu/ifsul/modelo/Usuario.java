
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser branco")
    @Length(max = 40, message = "Onome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;
    
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ser branco")
    @Length(max = 40, message = "O email não pode ter mais que {max} caracteres")
    @Column(name = "email", length = 40, nullable = false)
    private String email;
    
    @NotNull(message = "O usuario não pode ser nulo")
    @NotBlank(message = "O usuario não pode ser branco")
    @Length(max = 40, message = "O usuario não pode ter mais que {max} caracteres")
    @Column(name = "usuario", length = 40, nullable = false, unique = true)
    private String usuario;
    
    @Length(max = 10, message = "A senha não pode ter mais que {max} caracteres")
    @NotNull(message = "A senha deve ser informado")
    @NotBlank(message = "A senha não pode ser em branco")
    @Column(name = "senha", length = 10, nullable = false)
    private String senha;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento")
    private Calendar nascimento;
    
    @ManyToOne
    @NotNull(message = "O setor deve ser informado")
    @JoinColumn(name = "setor", referencedColumnName = "id",  nullable = false)
    @ForeignKey(name = "fK_setor_id")
    private Setor setor;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permissoes",
            joinColumns = 
            @JoinColumn(name = "usuario", referencedColumnName = "usuario", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "permissao", referencedColumnName = "nome", nullable = false), 
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario","permissao"})})    
    private List<Permissao> permissoes = new ArrayList<>();
    

    public Usuario() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    
}
