/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblInventario;
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
public class TblInventarioFacade extends AbstractFacade<TblInventario> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblInventarioFacade() {
        super(TblInventario.class);
    }

    public TblInventario inventarioSeleccionado(TblInventario codigoProducto) {
        String consulta;
        TblInventario tblInventario = null;
        consulta = "FROM TblInventario i WHERE i.idInventario = ?1";
        Query query = em.createQuery(consulta);
        query.setParameter(1, codigoProducto.getIdInventario());
        List<TblInventario> lista = query.getResultList();
        if (!lista.isEmpty()) {
            tblInventario = lista.get(0);
        }
        return tblInventario;
    }
}
