package com.andersonjunior.calltick.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping(value = "/api/clientReport/")
public class ReportController {

    @Autowired
    private ClientService clientService;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<byte[]> generatePdf() throws FileNotFoundException, JRException {

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findAllOne());
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("C:/Calltick/reports/client_report.jrxml"));
       
        HashMap<String, Object> map = new HashMap<>();
        map.put("filter", "TODOS");
        map.put("filterValue", "NENHUM");
        map.put("registers", clientService.count());
        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=clientes.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }

    @CrossOrigin
    @GetMapping("city")
    public ResponseEntity<byte[]> clientReportByCity(@RequestParam(value = "value") String city) throws Exception, JRException {

        Long registers = (long) clientService.findByFilter(null, null, null, city).size();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findByFilter(null, null, null, city));
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("C:/Calltick/reports/client_report.jrxml"));
       
        HashMap<String, Object> map = new HashMap<>();
        map.put("filter", "Cidade");
        map.put("filterValue", city);
        map.put("registers", registers);
        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=clientes_de_"+city+".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }

    @CrossOrigin
    @GetMapping("contract")
    public ResponseEntity<byte[]> clientReportByContract(@RequestParam(value = "value") Contract contract) throws Exception, JRException {

        Long registers = (long) clientService.findByContract(contract).size();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findByContract(contract));
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("C:/Calltick/reports/client_report.jrxml"));
       
        HashMap<String, Object> map = new HashMap<>();
        map.put("filter", "Contrato");
        map.put("filterValue", contract.getDescription());
        map.put("registers", registers);
        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=clientes_"+contract.getDescription().toLowerCase()+".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }

    @CrossOrigin
    @GetMapping("filter")
    public ResponseEntity<byte[]> clientReportByCityAndContract(@RequestParam(value = "city") String city, @RequestParam(value = "contract") Contract contract) throws Exception, JRException {

        Long registers = (long) clientService.findByCityAndContract(city, contract).size();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findByCityAndContract(city, contract));
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("C:/Calltick/reports/client_report.jrxml"));
       
        HashMap<String, Object> map = new HashMap<>();
        map.put("filter", "Cidade e Contrato");
        map.put("filterValue", city + " e " + contract.getDescription());
        map.put("registers", registers);
        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=clientes_"+city+"_"+contract.getDescription().toLowerCase()+".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }
    
}
