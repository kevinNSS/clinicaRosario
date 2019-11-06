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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2016
 */
@Entity
@Table(name = "tbl_orina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrina.findAll", query = "SELECT t FROM TblOrina t")
    , @NamedQuery(name = "TblOrina.findByIdTblOrina", query = "SELECT t FROM TblOrina t WHERE t.idTblOrina = :idTblOrina")
    , @NamedQuery(name = "TblOrina.findByColor", query = "SELECT t FROM TblOrina t WHERE t.color = :color")
    , @NamedQuery(name = "TblOrina.findByAspecto", query = "SELECT t FROM TblOrina t WHERE t.aspecto = :aspecto")
    , @NamedQuery(name = "TblOrina.findByDensidad", query = "SELECT t FROM TblOrina t WHERE t.densidad = :densidad")
    , @NamedQuery(name = "TblOrina.findByPH", query = "SELECT t FROM TblOrina t WHERE t.pH = :pH")
    , @NamedQuery(name = "TblOrina.findByProteina", query = "SELECT t FROM TblOrina t WHERE t.proteina = :proteina")
    , @NamedQuery(name = "TblOrina.findByGlucosa", query = "SELECT t FROM TblOrina t WHERE t.glucosa = :glucosa")
    , @NamedQuery(name = "TblOrina.findBySangreoculta", query = "SELECT t FROM TblOrina t WHERE t.sangreoculta = :sangreoculta")
    , @NamedQuery(name = "TblOrina.findByCuerposcetonicos", query = "SELECT t FROM TblOrina t WHERE t.cuerposcetonicos = :cuerposcetonicos")
    , @NamedQuery(name = "TblOrina.findByUrobilinogeno", query = "SELECT t FROM TblOrina t WHERE t.urobilinogeno = :urobilinogeno")
    , @NamedQuery(name = "TblOrina.findByBilirrubina", query = "SELECT t FROM TblOrina t WHERE t.bilirrubina = :bilirrubina")
    , @NamedQuery(name = "TblOrina.findByNitritos", query = "SELECT t FROM TblOrina t WHERE t.nitritos = :nitritos")
    , @NamedQuery(name = "TblOrina.findByHemoglobina", query = "SELECT t FROM TblOrina t WHERE t.hemoglobina = :hemoglobina")
    , @NamedQuery(name = "TblOrina.findByEsterasaleucocitaria", query = "SELECT t FROM TblOrina t WHERE t.esterasaleucocitaria = :esterasaleucocitaria")
    , @NamedQuery(name = "TblOrina.findByGranulosos", query = "SELECT t FROM TblOrina t WHERE t.granulosos = :granulosos")
    , @NamedQuery(name = "TblOrina.findByLeucocitarios", query = "SELECT t FROM TblOrina t WHERE t.leucocitarios = :leucocitarios")
    , @NamedQuery(name = "TblOrina.findByHematicos", query = "SELECT t FROM TblOrina t WHERE t.hematicos = :hematicos")
    , @NamedQuery(name = "TblOrina.findByHialinos", query = "SELECT t FROM TblOrina t WHERE t.hialinos = :hialinos")
    , @NamedQuery(name = "TblOrina.findByOtros", query = "SELECT t FROM TblOrina t WHERE t.otros = :otros")
    , @NamedQuery(name = "TblOrina.findByHematies", query = "SELECT t FROM TblOrina t WHERE t.hematies = :hematies")
    , @NamedQuery(name = "TblOrina.findByLeucocitos", query = "SELECT t FROM TblOrina t WHERE t.leucocitos = :leucocitos")
    , @NamedQuery(name = "TblOrina.findByCelulasepteliales", query = "SELECT t FROM TblOrina t WHERE t.celulasepteliales = :celulasepteliales")
    , @NamedQuery(name = "TblOrina.findByCristales", query = "SELECT t FROM TblOrina t WHERE t.cristales = :cristales")
    , @NamedQuery(name = "TblOrina.findByParasitologico", query = "SELECT t FROM TblOrina t WHERE t.parasitologico = :parasitologico")
    , @NamedQuery(name = "TblOrina.findByObservaciones", query = "SELECT t FROM TblOrina t WHERE t.observaciones = :observaciones")
    , @NamedQuery(name = "TblOrina.findByFechaRegistro", query = "SELECT t FROM TblOrina t WHERE t.fechaRegistro = :fechaRegistro")})
public class TblOrina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tbl_orina")
    private Integer idTblOrina;
    @Size(max = 25)
    @Column(name = "Color")
    private String color;
    @Size(max = 25)
    @Column(name = "Aspecto")
    private String aspecto;
    @Size(max = 25)
    @Column(name = "Densidad")
    private String densidad;
    @Size(max = 25)
    @Column(name = "pH")
    private String pH;
    @Size(max = 25)
    @Column(name = "Proteina")
    private String proteina;
    @Size(max = 25)
    @Column(name = "Glucosa")
    private String glucosa;
    @Size(max = 25)
    @Column(name = "Sangre_oculta")
    private String sangreoculta;
    @Size(max = 25)
    @Column(name = "Cuerpos_cetonicos")
    private String cuerposcetonicos;
    @Size(max = 25)
    @Column(name = "Urobilinogeno")
    private String urobilinogeno;
    @Size(max = 25)
    @Column(name = "Bilirrubina")
    private String bilirrubina;
    @Size(max = 25)
    @Column(name = "Nitritos")
    private String nitritos;
    @Size(max = 25)
    @Column(name = "Hemoglobina")
    private String hemoglobina;
    @Size(max = 25)
    @Column(name = "Esterasa_leucocitaria")
    private String esterasaleucocitaria;
    @Size(max = 25)
    @Column(name = "Granulosos")
    private String granulosos;
    @Size(max = 25)
    @Column(name = "Leucocitarios")
    private String leucocitarios;
    @Size(max = 25)
    @Column(name = "Hematicos")
    private String hematicos;
    @Size(max = 25)
    @Column(name = "Hialinos")
    private String hialinos;
    @Size(max = 25)
    @Column(name = "Otros")
    private String otros;
    @Size(max = 25)
    @Column(name = "Hematies")
    private String hematies;
    @Size(max = 25)
    @Column(name = "Leucocitos")
    private String leucocitos;
    @Size(max = 25)
    @Column(name = "Celulas_epteliales")
    private String celulasepteliales;
    @Size(max = 25)
    @Column(name = "Cristales")
    private String cristales;
    @Size(max = 25)
    @Column(name = "Parasitologico")
    private String parasitologico;
    @Size(max = 50)
    @Column(name = "Observaciones")
    private String observaciones;
    @Size(max = 10)
    @Column(name = "fecha_registro")
    private String fechaRegistro;
    @JoinColumn(name = "paciente", referencedColumnName = "ID_PACIENTE")
    @ManyToOne
    private TblPacientes paciente;
    @OneToMany(mappedBy = "idTblOrina")
    private Collection<TblExpedientes> tblExpedientesCollection;

    public TblOrina() {
    }

    public TblOrina(Integer idTblOrina) {
        this.idTblOrina = idTblOrina;
    }

    public Integer getIdTblOrina() {
        return idTblOrina;
    }

    public void setIdTblOrina(Integer idTblOrina) {
        this.idTblOrina = idTblOrina;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAspecto() {
        return aspecto;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public String getDensidad() {
        return densidad;
    }

    public void setDensidad(String densidad) {
        this.densidad = densidad;
    }

    public String getPH() {
        return pH;
    }

    public void setPH(String pH) {
        this.pH = pH;
    }

    public String getProteina() {
        return proteina;
    }

    public void setProteina(String proteina) {
        this.proteina = proteina;
    }

    public String getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(String glucosa) {
        this.glucosa = glucosa;
    }

    public String getSangreoculta() {
        return sangreoculta;
    }

    public void setSangreoculta(String sangreoculta) {
        this.sangreoculta = sangreoculta;
    }

    public String getCuerposcetonicos() {
        return cuerposcetonicos;
    }

    public void setCuerposcetonicos(String cuerposcetonicos) {
        this.cuerposcetonicos = cuerposcetonicos;
    }

    public String getUrobilinogeno() {
        return urobilinogeno;
    }

    public void setUrobilinogeno(String urobilinogeno) {
        this.urobilinogeno = urobilinogeno;
    }

    public String getBilirrubina() {
        return bilirrubina;
    }

    public void setBilirrubina(String bilirrubina) {
        this.bilirrubina = bilirrubina;
    }

    public String getNitritos() {
        return nitritos;
    }

    public void setNitritos(String nitritos) {
        this.nitritos = nitritos;
    }

    public String getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(String hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public String getEsterasaleucocitaria() {
        return esterasaleucocitaria;
    }

    public void setEsterasaleucocitaria(String esterasaleucocitaria) {
        this.esterasaleucocitaria = esterasaleucocitaria;
    }

    public String getGranulosos() {
        return granulosos;
    }

    public void setGranulosos(String granulosos) {
        this.granulosos = granulosos;
    }

    public String getLeucocitarios() {
        return leucocitarios;
    }

    public void setLeucocitarios(String leucocitarios) {
        this.leucocitarios = leucocitarios;
    }

    public String getHematicos() {
        return hematicos;
    }

    public void setHematicos(String hematicos) {
        this.hematicos = hematicos;
    }

    public String getHialinos() {
        return hialinos;
    }

    public void setHialinos(String hialinos) {
        this.hialinos = hialinos;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getHematies() {
        return hematies;
    }

    public void setHematies(String hematies) {
        this.hematies = hematies;
    }

    public String getLeucocitos() {
        return leucocitos;
    }

    public void setLeucocitos(String leucocitos) {
        this.leucocitos = leucocitos;
    }

    public String getCelulasepteliales() {
        return celulasepteliales;
    }

    public void setCelulasepteliales(String celulasepteliales) {
        this.celulasepteliales = celulasepteliales;
    }

    public String getCristales() {
        return cristales;
    }

    public void setCristales(String cristales) {
        this.cristales = cristales;
    }

    public String getParasitologico() {
        return parasitologico;
    }

    public void setParasitologico(String parasitologico) {
        this.parasitologico = parasitologico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TblPacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(TblPacientes paciente) {
        this.paciente = paciente;
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
        hash += (idTblOrina != null ? idTblOrina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrina)) {
            return false;
        }
        TblOrina other = (TblOrina) object;
        if ((this.idTblOrina == null && other.idTblOrina != null) || (this.idTblOrina != null && !this.idTblOrina.equals(other.idTblOrina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblOrina[ idTblOrina=" + idTblOrina + " ]";
    }
    
}
