/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.ireport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import pe.gob.fovipol.sifo.model.maestros.MaeArea;

/**
 *
 * @author eBuho
 */
public class MaeAreaCollection {

    public static Collection getMaeAreaCollection() {
        Collection areas = new ArrayList();
        try {
            MaeArea area = new MaeArea();
            area.setCodiAreaAre(BigDecimal.ZERO);
            area.setDescAreaAre("MyArea0");
            areas.add(area);
            area = new MaeArea();
            area.setCodiAreaAre(BigDecimal.ONE);
            area.setDescAreaAre("MyArea1");
            areas.add(area);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return areas;
    }
    /*
    public static void main(String[] args) throws Exception {
        EJBContainer container = EJBContainer.createEJBContainer();
        Context namingContext = container.getContext();
        TestBean testBean = (TestBean) namingContext.lookup("java:global/classes/TestBean");
        testBean.addEmployee(args);  //need to pass in employee names from command line
        container.close();
    }
    */
}
