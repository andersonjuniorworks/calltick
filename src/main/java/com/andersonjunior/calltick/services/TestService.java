package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.models.Paid;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.Comment;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.CalledStatus;
import com.andersonjunior.calltick.models.enums.ClientType;
import com.andersonjunior.calltick.models.enums.Profile;
import com.andersonjunior.calltick.repositories.CalledRepository;
import com.andersonjunior.calltick.repositories.ClientRepository;
import com.andersonjunior.calltick.repositories.ContractRepository;
import com.andersonjunior.calltick.repositories.PaidRepository;
import com.andersonjunior.calltick.repositories.SectorRepository;
import com.andersonjunior.calltick.repositories.CommentRepository;
import com.andersonjunior.calltick.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private ContractRepository contractRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void instantiateTestDatabase() throws ParseException {

        User u1 = new User(null, "Administrador", "admin@admin.com", encoder.encode("printf@javadev"),
                Profile.ADMINISTRADOR.getCode());
        User u2 = new User(null, "Ana Vitória", "vitoria@gmail.com", encoder.encode("123"), Profile.ATENDENTE.getCode());
        User u3 = new User(null, "Gonçalo Neto", "neto@gmail.com", encoder.encode("123"), Profile.TECNICO.getCode());
        userRepo.saveAll(Arrays.asList(u1, u2, u3));

        Sector s1 = new Sector(null, "Suporte Técnico");
        Sector s2 = new Sector(null, "Suporte Operacional");
        Sector s3 = new Sector(null, "Financeiro");
        sectorRepo.saveAll(Arrays.asList(s1, s2, s3));

        Contract ct1 = new Contract(null, "SGE", 100.00);
        Contract ct2 = new Contract(null, "SGE + MFE + NFE", 150.00);
        Contract ct3 = new Contract(null, "SGR", 100.00);
        Contract ct4 = new Contract(null, "SGR + MFE + NFE", 150.00);
        Contract ct5 = new Contract(null, "NFE", 65.00);
        Contract ct6 = new Contract(null, "Internet - Plano Rural", 50.00);
        contractRepo.saveAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5, ct6));

        Client c1 = new Client(null, ClientType.PESSOAFISICA.getCode(), "07115621306", "",
                "Antonio Anderson Vieira do Nascimento Júnior", "Anderson Júnior", "63702170", "Rua Manoel Balbino",
                "72", "Casa", "Centro", "Ceará", "Crateús", "88994354507", null, "andersonjunior.tech@gmail.com", ct1,
                new Date());
        Client c2 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "26729461000193", "",
                "S & A Automação Comercial", "Saraiva Automação", "63700000", "Rua Vereador José Veras", "1341", "Casa",
                "Centro", "Ceará", "Novo Oriente", "88994354507", null, "saraivaautomacao@gmail.com", ct2, new Date());
        Client c3 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "37616795000177", "", "Samuel de Souza Melo",
                "SA Informática", "63702170", "Rua Manoel Balbino", "72", "Casa", "Centro", "Ceará", "Crateús",
                "88994354507", null, "sainformatica@gmail.com", ct2, new Date());
        Client c4 = new Client(null, ClientType.PESSOAFISICA.getCode(), "50533038359", "", "Cauê Benedito Vieira",
                "Cauê Benedito", "63700977", "Rua Principal", "281", "Casa", "Centro", "Ceará", "Crateús",
                "88991978209", null, "cauebeneditovieira@azulcargo.com.br", ct2, new Date());
        Client c5 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "72613975000104", "",
                "Cauã e Lúcia Doces & Salgados ME", "Cantinho do Salgado", "61921485", "Rua Francisco Cassimiro", "930",
                "Casa", "Centro", "Ceará", "Maracanau", "85998949337", "8525869106",
                "sistema@cauaeluciadocessalgadosme.com.br", ct2, new Date());
        Client c6 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "90654821000134", "",
                "Amanda e Raquel Eletrônica Ltda", "Eletrônica Raquel", "63507295", "Rua 6", "829", "Casa", "Centro",
                "Ceará", "Iguatu", "88984369751", "8835975642", "fabricacao@amandaeraqueleletronicaltda.com.br", ct3,
                new Date());
        Client c7 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "17007309000106", "",
                "Osvaldo e Yago Adega Ltda", "Adega Só o Pito", "60530605", "Rua Arca de Noé", "378", "Casa", "Centro",
                "Ceará", "Fortaleza", "85995565205", "8526284638", "contato@osvaldoeyagoadegaltda.com.br", ct1,
                new Date());
        Client c8 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "58153247000128", "",
                "Tatiane e Vinicius Locações de Automóveis ME", "LocaMais", "61620190",
                "Rua Padre Domingos R. Vasconcelos", "782", "Casa", "Centro", "Ceará", "Caucaia", "85996100435",
                "8525558503", "cobranca@tatianeeviniciuslocacoesdeautomoveisme.com.br", ct2, new Date());
        Client c9 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "68958732000122", "",
                "Yago e Analu Limpeza ME", "Limpa Tudo", "60864314", "Rua B", "563", "Casa", "Centro", "Ceará",
                "Fortaleza", "85984029892", "8528555981", "producao@yagoeanalulimpezame.com.br", ct4, new Date());
        Client c10 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "24364757000122", "",
                "Enrico e Tiago Construções ME", "TM Material de Construções", "60526800", "Rua Nova Veneza", "521",
                "Casa", "Centro", "Ceará", "Fortaleza", "85981057432", "8528964286",
                "representantes@enricoetiagoconstrucoesme.com.br", ct1, new Date());
        Client c11 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "67847586000103", "",
                "Eloá e Alexandre Fotografias ME", "EA Fotografias", "60741208", "Vila São Bernardino", "119", "Casa",
                "Centro", "Ceará", "Fortaleza", "8536917728", "85984117966",
                "producao@eloaealexandrefotografiasme.com.br", ct2, new Date());
        Client c12 = new Client(null, ClientType.PESSOAJURIDICA.getCode(), "21915226000192", "",
                "Sophia e Anderson Comercio de Bebidas Ltda", "Alvorada Bebidas", "60545035", "Rua Raimundo Pinheiro",
                "713", "Casa", "Centro", "Ceará", "Fortaleza", "85998549172", "8526304854",
                "presidencia@sophiaegiovannicomerciodebebidasltda.com.br", ct1, new Date());
        clientRepo.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12));

        SimpleDateFormat format = new SimpleDateFormat();

        Called ca1 = new Called(null, c1, 3, s1, "ERRO DE IMPRESSÃO", "<font>VERIFICAR CONEX&#199;AO SGF</font><p>TEAMWIVER&#160;</p><p>ID: 1 508 367 736</p><p>SENHA: geu2792z</p>", u3, format.format(new Date()), format.format(new Date()), "Administrador", "Gonçalo Neto", CalledStatus.FINALIZADO.getCode(), "Foi trocado o cabo usb", 0, new Date());
        calledRepo.saveAll(Arrays.asList(ca1));

        Comment tr1 = new Comment(null, "Realizando teste 01", ca1, u1, new Date());
        Comment tr2 = new Comment(null, "Realizando teste 02", ca1, u2, new Date());
        commentRepo.saveAll(Arrays.asList(tr1, tr2));

        Paid p1 = new Paid(null, c1, new Date(), 150.00, new Date());
        Paid p2 = new Paid(null, c2, new Date(), 400.00, new Date());
        paidRepo.saveAll(Arrays.asList(p1, p2));

    }

}
