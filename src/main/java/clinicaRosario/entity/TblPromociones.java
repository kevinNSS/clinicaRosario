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
 * @author 2016
 */
@Entity
@Table(name = "tbl_promociones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPromociones.findAll", query = "SELECT t FROM TblPromociones t")
    , @NamedQuery(name = "TblPromociones.findByIdPromocion", query = "SELECT t FROM TblPromociones t WHERE t.idPromocion = :idPromocion")
    , @NamedQuery(name = "TblPromociones.findByNombrePromocion", query = "SELECT t FROM TblPromociones t WHERE t.nombrePromocion = :nombrePromocion")
    , @NamedQuery(name = "TblPromociones.findByFechaInicio", query = "SELECT t FROM TblPromociones t WHERE t.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "TblPromociones.findByFechaFinalizacion", query = "SELECT t FROM TblPromociones t WHERE t.fechaFinalizacion = :fechaFinalizacion")
    , @NamedQuery(name = "TblPromociones.findByTotalPagar", query = "SELECT t FROM TblPromociones t WHERE t.totalPagar = :totalPagar")})
public class TblPromociones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROMOCION")
    private Integer idPromocion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_PROMOCION")
    private String nombrePromocion;
    @Size(max = 10)
    @Column(name = "FECHA_INICIO")
    private String fechaInicio;
    @Size(max = 10)
    @Column(name = "FECHA_FINALIZACION")
    private String fechaFinalizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PAGAR")
    private double totalPagar;
    @OneToMany(mappedBy = "idPromocion")
    private Collection<TblFacturaDetalle> tblFacturaDetalleCollection;
    @JoinColumn(name = "EXAMEN1", referencedColumnName = "ID_EXAMEN")
    @ManyToOne(optional = false)
    private TblExamenes examen1;
    @JoinColumn(name = "EXAMEN2", referencedColumnName = "ID_EXAMEN")
    @ManyToOne
    private TblExamenes examen2;
    @JoinColumn(name = "EXAMEN3", referencedColumnName = "ID_EXAMEN")
    @ManyToOne
    private TblExamenes examen3;
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = false)
    private TblEstados estado;

    public TblPromociones() {
    }

    public TblPromociones(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public TblPromociones(Integer idPromocion, String nombrePromocion, double totalPagar) {
        this.idPromocion = idPromocion;
        this.nombrePromocion = nombrePromocion;
        this.totalPagar = totalPagar;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    @XmlTransient
    public Collection<TblFacturaDetalle> getTblFacturaDetalleCollection() {
        return tblFacturaDetalleCollection;
    }

    public void setTblFacturaDetalleCollection(Collection<TblFacturaDetalle> tblFacturaDetalleCollection) {
        this.tblFacturaDetalleCollection = tblFacturaDetalleCollection;
    }

    public TblExamenes getExamen1() {
        return examen1;
    }

    public void setExamen1(TblExamenes examen1) {
        this.examen1 = examen1;
    }

    public TblExamenes getExamen2() {
        return examen2;
    }

    public void setExamen2(TblExamenes examen2) {
        this.examen2 = examen2;
    }

    public TblExamenes getExamen3() {
        return examen3;
    }

    public void setExamen3(TblExamenes examen3) {
        this.examen3 = examen3;
    }

    public TblEstados getEstado() {
        return estado;
    }

    public void setEstado(TblEstados estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromocion != null ? idPromocion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPromociones)) {
            return false;
        }
        TblPromociones other = (TblPromociones) object;
        if ((this.idPromocion == null && other.idPromocion != null) || (this.idPromocion != null && !this.idPromocion.equals(other.idPromocion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblPromociones[ idPromocion=" + idPromocion + " ]";
    }
    
}
