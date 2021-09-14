package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Paid;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.CalledStatus;
import com.andersonjunior.calltick.models.enums.ClientType;
import com.andersonjunior.calltick.models.enums.Profile;
import com.andersonjunior.calltick.repositories.CalledRepository;
import com.andersonjunior.calltick.repositories.ClientRepository;
import com.andersonjunior.calltick.repositories.PaidRepository;
import com.andersonjunior.calltick.repositories.SectorRepository;
import com.andersonjunior.calltick.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private SectorRepository sectorRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private CalledRepository calledRepo;

    @Autowired
    private PaidRepository paidRepo;

    public void instantiateTestDatabase() throws ParseException {

        SimpleDateFormat formatDate = new SimpleDateFormat();

        User u1 = new User(null, "ADMINISTRADOR", "admin@admin.com", "printf@javadev", Profile.ADMINISTRADOR.getCode());
        User u2 = new User(null, "ATENDIMENTO", "atendimento@admin.com", "printf@javadev", Profile.ADMINISTRADOR.getCode());
        userRepo.saveAll(Arrays.asList(u1, u2));
        
        Sector s1 = new Sector(null, "Suporte Técnico");
        Sector s2 = new Sector(null, "Suporte Operacional");
        Sector s3 = new Sector(null, "Financeiro");
        sectorRepo.saveAll(Arrays.asList(s1,s2,s3));

        Client c1 = new Client(null, ClientType.PESSOAFISICA.getCode(), "07115621306", "Antonio Anderson Vieira do Nascimento Júnior", "Anderson Júnior", "63702-170", "Rua Manoel Balbino", "72", "Casa", "CE", "Crateús", "88994354507", "", "andersonjunior.tech@gmail.com", new Date());
        Client c2 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "26729461000193", "S & A Automação Comercial", "Saraiva Automação", "63700-000", "Rua Vereador José Veras", "1341", "Casa", "CE", "Novo Oriente", "88994354507", "", "saraivaautomacao@gmail.com", new Date());
        Client c3 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "37616795000177", "Samuel de Souza Melo", "SA Informática", "63702-170", "Rua Manoel Balbino", "72", "Casa", "CE", "Crateús", "88994354507", "", "sainformatica@gmail.com", new Date());
        Client c4 = new Client(null, ClientType.PESSOAFISICA.getCode(), "07115621307", "Antonio Anderson Vieira do Nascimento Júnior", "Anderson Júnior", "63702-170", "Rua Manoel Balbino", "72", "Casa", "CE", "Crateús", "88994354507", "", "andersonjunior2.tech@gmail.com", new Date());
        Client c5 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "26729461000190", "S & A Automação Comercial", "Saraiva Automação", "63700-000", "Rua Vereador José Veras", "1341", "Casa", "CE", "Novo Oriente", "88994354507", "", "saraivaautomacao2@gmail.com", new Date());
        Client c6 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "37616795000175", "Samuel de Souza Melo", "SA Informática", "63702-170", "Rua Manoel Balbino", "72", "Casa", "CE", "Crateús", "88994354507", "", "sainformatica2@gmail.com", new Date());
        clientRepo.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

        Called ca1 = new Called(null, c1, s1, "Erro ao finalizar a venda fiscal", "Cliente relatou erro ao finalizar venda com forma de pagamento Cartão de Crédito",u2, formatDate.format(new Date()), null, u1.getFullname(), null, null, CalledStatus.ABERTO.getCode(), 0);
        Called ca2 = new Called(null, c2, s2, "Dúvida na entrada de notas", "Cliente solicitou ajuda na entrada de notas manual", u2, formatDate.format(new Date()), null, u1.getFullname(), null, null, CalledStatus.ABERTO.getCode(), 0);
        Called ca3 = new Called(null, c2, s3, "Atualização de Boleto", "Atualizar boleto do cliente para nova data", u2, formatDate.format(new Date()), null, u1.getFullname(), null, null, CalledStatus.ABERTO.getCode(), 0);
        calledRepo.saveAll(Arrays.asList(ca1, ca2, ca3));

        Paid p1 = new Paid(null, c1, new Date(), 150.00, new Date());
        Paid p2 = new Paid(null, c2, new Date(), 400.00, new Date());
        paidRepo.saveAll(Arrays.asList(p1, p2));

    }
    
}
