/**
 * *
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.caelum.vraptor.blank;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.CargoDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.Cargo;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Resource
public class IndexController {

    private final Result result;
    private final CargoDAO cargoDAO;

    public IndexController(Result result, CargoDAO cargoDAO) {
        this.result = result;
        this.cargoDAO = cargoDAO;
    }

    @Path("/")
    public List<Cargo> lista() {
        return cargoDAO.list();
    }

    @Path("/form")
    public void form() {
    }

    @Path("/salva")
    public void salva(Cargo cargo) {
        cargoDAO.save(cargo);
        result.redirectTo(IndexController.class).lista();
    }

    @Path("/edita")
    public Cargo edita(Long id) {
        return cargoDAO.get(id);
    }
    
    @Path("/altera")
    public void altera(Cargo cargo) {  
        cargoDAO.update(cargo);
        result.redirectTo(IndexController.class).lista();
    }

    @Path("/remove")
    public void remove(Long id) {
        cargoDAO.delete(cargoDAO.get(id));
        result.redirectTo(IndexController.class).lista();
    }
    
    @Path("/relatorio")
    public File relatorio(ServletContext context) throws IOException{
        HashMap map = new HashMap();
        List<Cargo> results = cargoDAO.list();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(results, false);
        
        JasperPrint rel = null;
        String caminho= context.getRealPath("/WEB-INF/relatorios/"); 
        File pdf = null;
        try {            
            map.put("cargos", ds);                        
            rel = JasperFillManager.fillReport(caminho + "/Cargos.jasper", map, ds);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, rel); 
            pdf = File.createTempFile("output.", ".pdf");
            JasperExportManager.exportReportToPdfStream(rel, new FileOutputStream(pdf));
        } catch (JRException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return pdf;                
    }

}
