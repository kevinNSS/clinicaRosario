package clinicaRosario.controller;

import clinicaRosario.entity.TblTipoExamenes;
import clinicaRosario.session.TblTipoExamenesFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("tipoExamenes")
@ViewScoped
public class TipoExamenes implements Serializable{
 
    @EJB
    private TblTipoExamenesFacade tblTipoExamenesFacade;
    private TblTipoExamenes tblTipoExamenes;
    
    public List<TblTipoExamenes> getAllTipoExamen(){
        return tblTipoExamenesFacade.findAll();
    }
}
