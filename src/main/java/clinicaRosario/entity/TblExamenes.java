/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author kevin
 */
@Entity
@Table(name = "tbl_examenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblExamenes.findAll", query = "SELECT t FROM TblExamenes t")
    , @NamedQuery(name = "TblExamenes.findByIdExamen", query = "SELECT t FROM TblExamenes t WHERE t.idExamen = :idExamen")
    , @NamedQuery(name = "TblExamenes.findByNombreExamen", query = "SELECT t FROM TblExamenes t WHERE t.nombreExamen = :nombreExamen")
    , @NamedQuery(name = "TblExamenes.findByDescripcionExamen", query = "SELECT t FROM TblExamenes t WHERE t.descripcionExamen = :descripcionExamen")
    , @NamedQuery(name = "TblExamenes.findByPrecioExamen", query = "SELECT t FROM TblExamenes t WHERE t.precioExamen = :precioExamen")})
public class TblExamenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ID_EXAMEN")
    private String idExamen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_EXAMEN")
    private String nombreExamen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION_EXAMEN")
    private String descripcionExamen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_EXAMEN")
    private double precioExamen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen")
    private Collection<TblFacturaDetalle> tblFacturaDetalleCollection;
    @JoinColumn(name = "ESTADO_EXAMEN", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = true)
    private TblEstados estadoExamen;
    @JoinColumn(name = "TIPO_EXAMEN", referencedColumnName = "ID_TIPO_EXAMEN")
    @ManyToOne(optional = true)
    private TblTipoExamenes tipoExamen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen")
    private Collection<TblExpedientes> tblExpedientesCollection;

    public TblExamenes() {
    }

    public TblExamenes(String idExamen) {
        this.idExamen = idExamen;
    }

    public TblExamenes(String idExamen, String nombreExamen, String descripcionExamen, double precioExamen) {
        this.idExamen = idExamen;
        this.nombreExamen = nombreExamen;
        this.descripcionExamen = descripcionExamen;
        this.precioExamen = precioExamen;
    }

    public String getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(String idExamen) {
        this.idExamen = idExamen;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public String getDescripcionExamen() {
        return descripcionExamen;
    }

    public void setDescripcionExamen(String descripcionExamen) {
        this.descripcionExamen = descripcionExamen;
    }

    public double getPrecioExamen() {
        return precioExamen;
    }

    public void setPrecioExamen(double precioExamen) {
        this.precioExamen = precioExamen;
    }

    @XmlTransient
    public Collection<TblFacturaDetalle> getTblFacturaDetalleCollection() {
        return tblFacturaDetalleCollection;
    }

    public void setTblFacturaDetalleCollection(Collection<TblFacturaDetalle> tblFacturaDetalleCollection) {
        this.tblFacturaDetalleCollection = tblFacturaDetalleCollection;
    }

    public TblEstados getEstadoExamen() {
        return estadoExamen;
    }

    public void setEstadoExamen(TblEstados estadoExamen) {
        this.estadoExamen = estadoExamen;
    }

    public TblTipoExamenes getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(TblTipoExamenes tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    @XmlTransient
    public Collection<TblExpedientes> getTblExpedientesCollection() {
        return tblExpedientesCollection;
    }

    public void setTblExpedientesCollection(Collection<TblExpedientes> tblExpedientesCollection) {
        this.tblExpedientesCollection = tblExpedientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamen != null ? idExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblExamenes)) {
            return false;
        }
        TblExamenes other = (TblExamenes) object;
        if ((this.idExamen == null && other.idExamen != null) || (this.idExamen != null && !this.idExamen.equals(other.idExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblExamenes[ idExamen=" + idExamen + " ]";
    }
    
}
