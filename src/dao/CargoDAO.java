/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.caelum.vraptor.ioc.Component;
import model.Cargo;



/**
 *
 * @author Marcos
 */
@Component
public class CargoDAO extends AbstractDAOImpl<Cargo, Long> {

    @Override
    protected Class<Cargo> getDomainClass() {
        return Cargo.class;
    }
}
