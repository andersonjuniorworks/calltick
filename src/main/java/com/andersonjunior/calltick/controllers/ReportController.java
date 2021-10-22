package com.andersonjunior.calltick.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.services.CalledService;
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
@RequestMapping(value = "/api")
public class ReportController {

    private final ClientService clientService;

    private final CalledService calledService;

    @Autowired
    public ReportController(ClientService clientService, CalledService calledService) {
        this.clientService = clientService;
        this.calledService = calledService;
    }

    @CrossOrigin
    @GetMapping(value = "/clientReport/")
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
    @GetMapping("/clientReport/city")
    public ResponseEntity<byte[]> clientReportByCity(@RequestParam(value = "value") String city) throws Exception, JRException {

        Long registers = (long) clientService.findByFilter(null, null, null, city, null).size();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findByFilter(null, null, null, city, null));
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
    @GetMapping("/clientReport/contract")
    public ResponseEntity<byte[]> clientReportByContract(@RequestParam(value = "value") Contract contract) throws Exception, JRException {

        Long registers = (long) clientService.countByFilter(null, null, null, null, contract);

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findByFilter(null, null, null, null, contract));
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
    @GetMapping("/clientReport/filter")
    public ResponseEntity<byte[]> clientReportByCityAndContract(@RequestParam(value = "city") String city, @RequestParam(value = "contract") Contract contract) throws Exception, JRException {

        Long registers = (long) clientService.countByFilter(null, null, null, city, contract);

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clientService.findByFilter(null, null, null, city, contract));
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

    @CrossOrigin
    @GetMapping("/ticketReport")
    public ResponseEntity<byte[]> ticketReport(@RequestParam(value = "id") Long id) throws Exception, JRException {

        List<Called> list = new ArrayList<>();
        list.add(calledService.findById(id));

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);

        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("C:/Calltick/reports/ticket_report.jrxml"));
       
        HashMap<String, Object> map = new HashMap<>();
        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=ticket.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }
    
}
