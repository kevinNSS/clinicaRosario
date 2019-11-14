/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblFacturaEncabezado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevin
 */
@Stateless
public class TblFacturaEncabezadoFacade extends AbstractFacade<TblFacturaEncabezado> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblFacturaEncabezadoFacade() {
        super(TblFacturaEncabezado.class);
    }
    
    public List<TblFacturaEncabezado> findAllFacturaDiario(String fechaHoy) {
        String consulta;
        consulta = "FROM TblFacturaEncabezado fe WHERE fe.fechaFacturacion = ?1";
        Query query = em.createQuery(consulta);
        query.setParameter(1, fechaHoy);
        List<TblFacturaEncabezado> lista = query.getResultList();
        return lista;
    }
}
