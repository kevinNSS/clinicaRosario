/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2016
 */
@Entity
@Table(name = "tbl_tipo_examenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTipoExamenes.findAll", query = "SELECT t FROM TblTipoExamenes t")
    , @NamedQuery(name = "TblTipoExamenes.findByIdTipoExamen", query = "SELECT t FROM TblTipoExamenes t WHERE t.idTipoExamen = :idTipoExamen")
    , @NamedQuery(name = "TblTipoExamenes.findByNombreTipoExamen", query = "SELECT t FROM TblTipoExamenes t WHERE t.nombreTipoExamen = :nombreTipoExamen")
    , @NamedQuery(name = "TblTipoExamenes.findByDescripcionTipoExamen", query = "SELECT t FROM TblTipoExamenes t WHERE t.descripcionTipoExamen = :descripcionTipoExamen")})
public class TblTipoExamenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_EXAMEN")
    private Integer idTipoExamen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_TIPO_EXAMEN")
    private String nombreTipoExamen;
    @Size(max = 50)
    @Column(name = "DESCRIPCION_TIPO_EXAMEN")
    private String descripcionTipoExamen;
    @OneToMany(mappedBy = "tipoExamen")
    private Collection<TblExamenes> tblExamenesCollection;

    public TblTipoExamenes() {
    }

    public TblTipoExamenes(Integer idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
    }

    public TblTipoExamenes(Integer idTipoExamen, String nombreTipoExamen) {
        this.idTipoExamen = idTipoExamen;
        this.nombreTipoExamen = nombreTipoExamen;
    }

    public Integer getIdTipoExamen() {
        return idTipoExamen;
    }

    public void setIdTipoExamen(Integer idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
    }

    public String getNombreTipoExamen() {
        return nombreTipoExamen;
    }

    public void setNombreTipoExamen(String nombreTipoExamen) {
        this.nombreTipoExamen = nombreTipoExamen;
    }

    public String getDescripcionTipoExamen() {
        return descripcionTipoExamen;
    }

    public void setDescripcionTipoExamen(String descripcionTipoExamen) {
        this.descripcionTipoExamen = descripcionTipoExamen;
    }

    @XmlTransient
    public Collection<TblExamenes> getTblExamenesCollection() {
        return tblExamenesCollection;
    }

    public void setTblExamenesCollection(Collection<TblExamenes> tblExamenesCollection) {
        this.tblExamenesCollection = tblExamenesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoExamen != null ? idTipoExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTipoExamenes)) {
            return false;
        }
        TblTipoExamenes other = (TblTipoExamenes) object;
        if ((this.idTipoExamen == null && other.idTipoExamen != null) || (this.idTipoExamen != null && !this.idTipoExamen.equals(other.idTipoExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblTipoExamenes[ idTipoExamen=" + idTipoExamen + " ]";
    }
    
}
