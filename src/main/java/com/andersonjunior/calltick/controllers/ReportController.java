package com.andersonjunior.calltick.controllers;

import java.io.FileInputStream;
import java.util.HashMap;

import com.andersonjunior.calltick.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping(value = "/api")
public class ReportController {

    @Autowired
    private ClientService clientService;

    @CrossOrigin
    @GetMapping("/clientReport")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findAllOne());
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/static/reports/client_report.jrxml"));
       
        HashMap<String, Object> map = new HashMap<>();
        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
        //JasperExportManager.exportReportToPdfFile(report, "clientes.pdf");
        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=clientes.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }
    
}
