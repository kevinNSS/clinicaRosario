/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblEstados;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2016
 */
@Stateless
public class TblEstadosFacade extends AbstractFacade<TblEstados> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblEstadosFacade() {
        super(TblEstados.class);
    }
    
}
