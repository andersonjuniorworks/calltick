package com.andersonjunior.calltick.controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.TicketByUser;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.CalledStatus;
import com.andersonjunior.calltick.component.DataConverter;
import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.services.CalledService;
import com.andersonjunior.calltick.services.SectorService;
import com.andersonjunior.calltick.services.TicketByUserService;
import com.andersonjunior.calltick.services.UserService;
import com.andersonjunior.calltick.services.ClientService;
import com.andersonjunior.calltick.services.ContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/charts")
public class ChartController {

    private final CalledService calledService;
    private final UserService userService;
    private final SectorService sectorService;
    private final ClientService clientService;
    private final ContractService contractService;
    private final TicketByUserService ticketByUserService;

    @Autowired
    public ChartController(CalledService calledService, UserService userService, SectorService sectorService, ClientService clientService, ContractService contractService, TicketByUserService ticketByUserService) {
        this.calledService = calledService;
        this.userService = userService;
        this.sectorService = sectorService;
        this.clientService = clientService;
        this.contractService = contractService;
        this.ticketByUserService = ticketByUserService;
    }

    @CrossOrigin
    @GetMapping(value = "/ticketByUser")
    public ResponseEntity<Object> chartTicketByUser(@RequestParam(value = "startDate", required = false) String startDate, @RequestParam(value = "endDate", required = false) String endDate) throws ParseException {
       
        List<TicketByUser> tickets = ticketByUserService.findAll(startDate, endDate);

        HashMap<String, Integer> list = new HashMap<String, Integer>();

        for (int i = 0; i < tickets.size(); i++) {
            try {
                list.put(tickets.get(i).getDescription(), tickets.get(i).getQuantity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok().body(list);

    }

    @CrossOrigin
    @GetMapping(value = "/ticketBySector")
    public ResponseEntity<Object> chartTicketBySector(@RequestParam(value = "startDate") String startDate,
    @RequestParam(value = "endDate") String endDate) {
        List<Sector> sectors = sectorService.findAll(0, 1000000);
        HashMap<String, Integer> list = new HashMap<String, Integer>();
        for (int i = 0; i < sectors.size(); i++) {
            try {
                list.put(sectors.get(i).getDescription(), calledService.countByFilter(null, null, sectors.get(i), null, startDate, endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @GetMapping(value = "/ticketByStatus")
    public ResponseEntity<Object> chartTicketByStatus(@RequestParam(value = "startDate") String startDate,
    @RequestParam(value = "endDate") String endDate) throws ParseException {

        HashMap<Integer, String> status = new HashMap<Integer, String>();
        status.put(CalledStatus.ABERTO.getCode(), CalledStatus.ABERTO.getDescription());
        status.put(CalledStatus.FINALIZADO.getCode(), CalledStatus.FINALIZADO.getDescription());
        status.put(CalledStatus.CANCELADO.getCode(), CalledStatus.CANCELADO.getDescription());

        HashMap<String, Integer> list = new HashMap<String, Integer>();

        for (int i = 1; i <= 3; i++) {
            list.put(status.get(i), calledService.countByFilter(null, null, null, i, startDate, endDate));
        }
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @GetMapping(value = "/clientByContract")
    public ResponseEntity<Object> chartClientByContract() throws ParseException {
        List<Contract> contracts = contractService.findAll();
        HashMap<String, Integer> list = new HashMap<String, Integer>();
        for (int i = 0; i < contracts.size(); i++) {
            list.put(contracts.get(i).getDescription(), clientService.countByFilter(null, null, null, null, contracts.get(i)));
        }
        return ResponseEntity.ok().body(list);
    }

}
