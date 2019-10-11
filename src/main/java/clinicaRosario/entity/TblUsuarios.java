/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tbl_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUsuarios.findAll", query = "SELECT t FROM TblUsuarios t")
    , @NamedQuery(name = "TblUsuarios.findByIdUsuario", query = "SELECT t FROM TblUsuarios t WHERE t.idUsuario = :idUsuario")
    , @NamedQuery(name = "TblUsuarios.findByContrasena", query = "SELECT t FROM TblUsuarios t WHERE t.contrasena = :contrasena")})
public class TblUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @JoinColumn(name = "USUARIO_EMPLEADO", referencedColumnName = "ID_EMPLEADO")
    @ManyToOne
    private TblEmpleados usuarioEmpleado;

    public TblUsuarios() {
    }

    public TblUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TblUsuarios(Integer idUsuario, String contrasena) {
        this.idUsuario = idUsuario;
        this.contrasena = contrasena;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TblEmpleados getUsuarioEmpleado() {
        return usuarioEmpleado;
    }

    public void setUsuarioEmpleado(TblEmpleados usuarioEmpleado) {
        this.usuarioEmpleado = usuarioEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUsuarios)) {
            return false;
        }
        TblUsuarios other = (TblUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
