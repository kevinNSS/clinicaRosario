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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2016
 */
@Entity
@Table(name = "tbl_hemograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblHemograma.findAll", query = "SELECT t FROM TblHemograma t")
    , @NamedQuery(name = "TblHemograma.findByIdTblHemograma", query = "SELECT t FROM TblHemograma t WHERE t.idTblHemograma = :idTblHemograma")
    , @NamedQuery(name = "TblHemograma.findByGlobulosrojos", query = "SELECT t FROM TblHemograma t WHERE t.globulosrojos = :globulosrojos")
    , @NamedQuery(name = "TblHemograma.findByHemoglobina", query = "SELECT t FROM TblHemograma t WHERE t.hemoglobina = :hemoglobina")
    , @NamedQuery(name = "TblHemograma.findByHematocrito", query = "SELECT t FROM TblHemograma t WHERE t.hematocrito = :hematocrito")
    , @NamedQuery(name = "TblHemograma.findByVcm", query = "SELECT t FROM TblHemograma t WHERE t.vcm = :vcm")
    , @NamedQuery(name = "TblHemograma.findByHcm", query = "SELECT t FROM TblHemograma t WHERE t.hcm = :hcm")
    , @NamedQuery(name = "TblHemograma.findByChcm", query = "SELECT t FROM TblHemograma t WHERE t.chcm = :chcm")
    , @NamedQuery(name = "TblHemograma.findByLeucocitos", query = "SELECT t FROM TblHemograma t WHERE t.leucocitos = :leucocitos")
    , @NamedQuery(name = "TblHemograma.findByNeutrofilossegmentados", query = "SELECT t FROM TblHemograma t WHERE t.neutrofilossegmentados = :neutrofilossegmentados")
    , @NamedQuery(name = "TblHemograma.findByNeutrofilosenbanda", query = "SELECT t FROM TblHemograma t WHERE t.neutrofilosenbanda = :neutrofilosenbanda")
    , @NamedQuery(name = "TblHemograma.findByLinfocitos", query = "SELECT t FROM TblHemograma t WHERE t.linfocitos = :linfocitos")
    , @NamedQuery(name = "TblHemograma.findByMonocitos", query = "SELECT t FROM TblHemograma t WHERE t.monocitos = :monocitos")
    , @NamedQuery(name = "TblHemograma.findByEosinofilos", query = "SELECT t FROM TblHemograma t WHERE t.eosinofilos = :eosinofilos")
    , @NamedQuery(name = "TblHemograma.findByBasofilos", query = "SELECT t FROM TblHemograma t WHERE t.basofilos = :basofilos")
    , @NamedQuery(name = "TblHemograma.findByRecuentoplaquetas", query = "SELECT t FROM TblHemograma t WHERE t.recuentoplaquetas = :recuentoplaquetas")
    , @NamedQuery(name = "TblHemograma.findByRecuentoreticulocitos", query = "SELECT t FROM TblHemograma t WHERE t.recuentoreticulocitos = :recuentoreticulocitos")
    , @NamedQuery(name = "TblHemograma.findByEritrosedimentacion", query = "SELECT t FROM TblHemograma t WHERE t.eritrosedimentacion = :eritrosedimentacion")
    , @NamedQuery(name = "TblHemograma.findByGotagruesa", query = "SELECT t FROM TblHemograma t WHERE t.gotagruesa = :gotagruesa")
    , @NamedQuery(name = "TblHemograma.findByOtrosHemogramas", query = "SELECT t FROM TblHemograma t WHERE t.otrosHemogramas = :otrosHemogramas")
    , @NamedQuery(name = "TblHemograma.findByOservaciones", query = "SELECT t FROM TblHemograma t WHERE t.oservaciones = :oservaciones")})
public class TblHemograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tbl_hemograma")
    private Integer idTblHemograma;
    @Size(max = 25)
    @Column(name = "Globulos_rojos")
    private String globulosrojos;
    @Size(max = 25)
    @Column(name = "Hemoglobina")
    private String hemoglobina;
    @Size(max = 25)
    @Column(name = "Hematocrito")
    private String hematocrito;
    @Size(max = 25)
    @Column(name = "VCM")
    private String vcm;
    @Size(max = 25)
    @Column(name = "HCM")
    private String hcm;
    @Size(max = 25)
    @Column(name = "CHCM")
    private String chcm;
    @Size(max = 25)
    @Column(name = "Leucocitos")
    private String leucocitos;
    @Size(max = 25)
    @Column(name = "Neutrofilos_segmentados")
    private String neutrofilossegmentados;
    @Size(max = 25)
    @Column(name = "Neutrofilos_en_banda")
    private String neutrofilosenbanda;
    @Size(max = 25)
    @Column(name = "Linfocitos")
    private String linfocitos;
    @Size(max = 25)
    @Column(name = "Monocitos")
    private String monocitos;
    @Size(max = 25)
    @Column(name = "Eosinofilos")
    private String eosinofilos;
    @Size(max = 25)
    @Column(name = "Basofilos")
    private String basofilos;
    @Size(max = 25)
    @Column(name = "Recuento_plaquetas")
    private String recuentoplaquetas;
    @Size(max = 25)
    @Column(name = "Recuento_reticulocitos")
    private String recuentoreticulocitos;
    @Size(max = 25)
    @Column(name = "Eritrosedimentacion")
    private String eritrosedimentacion;
    @Size(max = 25)
    @Column(name = "Gota_gruesa")
    private String gotagruesa;
    @Size(max = 25)
    @Column(name = "otros_hemogramas")
    private String otrosHemogramas;
    @Size(max = 50)
    @Column(name = "oservaciones")
    private String oservaciones;
    @OneToMany(mappedBy = "idTblHemograma")
    private Collection<TblExpedientes> tblExpedientesCollection;

    public TblHemograma() {
    }

    public TblHemograma(Integer idTblHemograma) {
        this.idTblHemograma = idTblHemograma;
    }

    public Integer getIdTblHemograma() {
        return idTblHemograma;
    }

    public void setIdTblHemograma(Integer idTblHemograma) {
        this.idTblHemograma = idTblHemograma;
    }

    public String getGlobulosrojos() {
        return globulosrojos;
    }

    public void setGlobulosrojos(String globulosrojos) {
        this.globulosrojos = globulosrojos;
    }

    public String getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(String hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public String getHematocrito() {
        return hematocrito;
    }

    public void setHematocrito(String hematocrito) {
        this.hematocrito = hematocrito;
    }

    public String getVcm() {
        return vcm;
    }

    public void setVcm(String vcm) {
        this.vcm = vcm;
    }

    public String getHcm() {
        return hcm;
    }

    public void setHcm(String hcm) {
        this.hcm = hcm;
    }

    public String getChcm() {
        return chcm;
    }

    public void setChcm(String chcm) {
        this.chcm = chcm;
    }

    public String getLeucocitos() {
        return leucocitos;
    }

    public void setLeucocitos(String leucocitos) {
        this.leucocitos = leucocitos;
    }

    public String getNeutrofilossegmentados() {
        return neutrofilossegmentados;
    }

    public void setNeutrofilossegmentados(String neutrofilossegmentados) {
        this.neutrofilossegmentados = neutrofilossegmentados;
    }

    public String getNeutrofilosenbanda() {
        return neutrofilosenbanda;
    }

    public void setNeutrofilosenbanda(String neutrofilosenbanda) {
        this.neutrofilosenbanda = neutrofilosenbanda;
    }

    public String getLinfocitos() {
        return linfocitos;
    }

    public void setLinfocitos(String linfocitos) {
        this.linfocitos = linfocitos;
    }

    public String getMonocitos() {
        return monocitos;
    }

    public void setMonocitos(String monocitos) {
        this.monocitos = monocitos;
    }

    public String getEosinofilos() {
        return eosinofilos;
    }

    public void setEosinofilos(String eosinofilos) {
        this.eosinofilos = eosinofilos;
    }

    public String getBasofilos() {
        return basofilos;
    }

    public void setBasofilos(String basofilos) {
        this.basofilos = basofilos;
    }

    public String getRecuentoplaquetas() {
        return recuentoplaquetas;
    }

    public void setRecuentoplaquetas(String recuentoplaquetas) {
        this.recuentoplaquetas = recuentoplaquetas;
    }

    public String getRecuentoreticulocitos() {
        return recuentoreticulocitos;
    }

    public void setRecuentoreticulocitos(String recuentoreticulocitos) {
        this.recuentoreticulocitos = recuentoreticulocitos;
    }

    public String getEritrosedimentacion() {
        return eritrosedimentacion;
    }

    public void setEritrosedimentacion(String eritrosedimentacion) {
        this.eritrosedimentacion = eritrosedimentacion;
    }

    public String getGotagruesa() {
        return gotagruesa;
    }

    public void setGotagruesa(String gotagruesa) {
        this.gotagruesa = gotagruesa;
    }

    public String getOtrosHemogramas() {
        return otrosHemogramas;
    }

    public void setOtrosHemogramas(String otrosHemogramas) {
        this.otrosHemogramas = otrosHemogramas;
    }

    public String getOservaciones() {
        return oservaciones;
    }

    public void setOservaciones(String oservaciones) {
        this.oservaciones = oservaciones;
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
        hash += (idTblHemograma != null ? idTblHemograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblHemograma)) {
            return false;
        }
        TblHemograma other = (TblHemograma) object;
        if ((this.idTblHemograma == null && other.idTblHemograma != null) || (this.idTblHemograma != null && !this.idTblHemograma.equals(other.idTblHemograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblHemograma[ idTblHemograma=" + idTblHemograma + " ]";
    }
    
}
