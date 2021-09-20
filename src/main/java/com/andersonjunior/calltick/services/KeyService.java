package com.andersonjunior.calltick.services;

import com.andersonjunior.calltick.repositories.KeyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService {

    @Autowired
    private KeyRepository keyRepo;

    public String gerarChave(String cnpj, String ma) {
        String key = "";
        final String data = limparMa(ma);
        final String mes = descobrirMes(data);
        if (mes == null) {
            return null;
        }
        String cnpj2 = cCnpj(cnpj);
        key = cnpj2 + mes + data;
        System.out.println(key);
        while (key.length() < 20) {
            key = key + "0";
        }
        return gerar(key);
    }

    public String limparMa(String ma) {
        String nova = "";
        for (int i = 0; i < ma.length(); i++) {
            if (i == 2) {
                continue;
            }
            nova = nova + (char) ma.charAt(i);
        }
        System.out.println(nova);
        return nova;
    }

    public String descobrirMes(String ma) {
        String m = "" + (char) ma.charAt(0) + (char) ma.charAt(1);
        switch (m) {
            case "01":
                return "JANEIRO";
            case "02":
                return "FEVEREIRO";
            case "03":
                return "MARÇO";
            case "04":
                return "ABRIL";
            case "05":
                return "MAIO";
            case "06":
                return "JUNHO";
            case "07":
                return "JULHO";
            case "08":
                return "AGOSTO";
            case "09":
                return "SETEMBRO";
            case "10":
                return "OUTUBRO";
            case "11":
                return "NOVEMBRO";
            case "12":
                return "DEZEMBRO";
            default:
                System.out.println("Mês inválido!");
                break;
        }
        return null;
    }

    public String cCnpj(String cnpj) {
        String ccn = "";
        for (int i = 0; i < 5; i++) {
            ccn = ccn + cnpj.charAt(i);
            System.out.println(ccn);
        }
        return ccn;
    }

    public static String gerar(String senha) {

        String cnum = "92547816781692586965";
        String cstr = "1ABC2DEF3GHI4JKL5MN6PQR7STU8WXY9Z";
        int ntam = senha.length();
        String cRes = "";
        int p1 = 0;
        int p;

        for (int i = 0; i < ntam; i++) {
            String clet = "" + senha.charAt(i);
            p1 = cstr.indexOf(clet);
            int p2 = Integer.parseInt("" + cnum.charAt(i));
            p = p1 + p2;

            if (p >= ntam) {
                p -= ntam;
            }
            cRes = cRes + (char) cstr.charAt(p);
        }

        return cRes;
        
    }

}
