package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.andersonjunior.calltick.component.DataConverter;
import com.andersonjunior.calltick.models.TicketByUser;
import com.andersonjunior.calltick.repositories.TicketByUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketByUserService {

    @Autowired
    private TicketByUserRepository ticketByUserRepository;

    public List<TicketByUser> findAll(String startDate, String endDate) throws ParseException {
        
        return ticketByUserRepository.listTicketByUsers(new DataConverter().parseDate(startDate), new DataConverter().parseDate(endDate));
    }
    
}
