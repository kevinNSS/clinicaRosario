/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tbl_factura_encabezado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblFacturaEncabezado.findAll", query = "SELECT t FROM TblFacturaEncabezado t")
    , @NamedQuery(name = "TblFacturaEncabezado.findByIdFactura", query = "SELECT t FROM TblFacturaEncabezado t WHERE t.idFactura = :idFactura")
    , @NamedQuery(name = "TblFacturaEncabezado.findByFechaFacturacion", query = "SELECT t FROM TblFacturaEncabezado t WHERE t.fechaFacturacion = :fechaFacturacion")
    , @NamedQuery(name = "TblFacturaEncabezado.findBySubTotal", query = "SELECT t FROM TblFacturaEncabezado t WHERE t.subTotal = :subTotal")
    , @NamedQuery(name = "TblFacturaEncabezado.findByIva", query = "SELECT t FROM TblFacturaEncabezado t WHERE t.iva = :iva")
    , @NamedQuery(name = "TblFacturaEncabezado.findByDescuentoTotal", query = "SELECT t FROM TblFacturaEncabezado t WHERE t.descuentoTotal = :descuentoTotal")
    , @NamedQuery(name = "TblFacturaEncabezado.findByTotal", query = "SELECT t FROM TblFacturaEncabezado t WHERE t.total = :total")})
public class TblFacturaEncabezado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FACTURA")
    private Integer idFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FACTURACION")
    private String fechaFacturacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUB_TOTAL")
    private double subTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA")
    private double iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTO_TOTAL")
    private double descuentoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private double total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacturaEncabezado")
    private Collection<TblFacturaDetalle> tblFacturaDetalleCollection;
    @JoinColumn(name = "ID_PACIENTE", referencedColumnName = "ID_PACIENTE")
    @ManyToOne(optional = false)
    private TblPacientes idPaciente;
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = false)
    private TblEstados estado;
    @JoinColumn(name = "ENCARGADO_FACTURACION", referencedColumnName = "ID_EMPLEADO")
    @ManyToOne(optional = false)
    private TblEmpleados encargadoFacturacion;

    public TblFacturaEncabezado() {
    }

    public TblFacturaEncabezado(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public TblFacturaEncabezado(Integer idFactura, String fechaFacturacion, double subTotal, double iva, double descuentoTotal, double total) {
        this.idFactura = idFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.subTotal = subTotal;
        this.iva = iva;
        this.descuentoTotal = descuentoTotal;
        this.total = total;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @XmlTransient
    public Collection<TblFacturaDetalle> getTblFacturaDetalleCollection() {
        return tblFacturaDetalleCollection;
    }

    public void setTblFacturaDetalleCollection(Collection<TblFacturaDetalle> tblFacturaDetalleCollection) {
        this.tblFacturaDetalleCollection = tblFacturaDetalleCollection;
    }

    public TblPacientes getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(TblPacientes idPaciente) {
        this.idPaciente = idPaciente;
    }

    public TblEstados getEstado() {
        return estado;
    }

    public void setEstado(TblEstados estado) {
        this.estado = estado;
    }

    public TblEmpleados getEncargadoFacturacion() {
        return encargadoFacturacion;
    }

    public void setEncargadoFacturacion(TblEmpleados encargadoFacturacion) {
        this.encargadoFacturacion = encargadoFacturacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblFacturaEncabezado)) {
            return false;
        }
        TblFacturaEncabezado other = (TblFacturaEncabezado) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblFacturaEncabezado[ idFactura=" + idFactura + " ]";
    }
    
}
