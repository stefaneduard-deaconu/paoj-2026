package com.pao.project.platforma_elearning.service;

import com.pao.project.platforma_elearning.exception.EntitateExistentaException;
import com.pao.project.platforma_elearning.exception.UtilizatorNegasitException;
import com.pao.project.platforma_elearning.model.Cursant;
import com.pao.project.platforma_elearning.model.Utilizator;

import java.util.HashMap;
import java.util.Map;

public class UtilizatorService {
    private static UtilizatorService instance;
    private Map<String, Utilizator> utilizatori = new HashMap<>();

    private UtilizatorService() {}

    public static UtilizatorService getInstance() {
        if (instance == null) {
            instance = new UtilizatorService();
        }

        return instance;
    }

    public void inregistrare(Utilizator u) throws EntitateExistentaException {
        if (utilizatori.containsKey(u.getEmail())) {
            throw new EntitateExistentaException("Email-ul " + u.getEmail() + " este deja folosit");
        }

        utilizatori.put(u.getEmail(), u);
        System.out.println("Utilizator inregistrat cu succes: " + u.getNume());
    }

    public Utilizator login(String email, String parola) throws UtilizatorNegasitException {
        Utilizator u = utilizatori.get(email);

        if (u == null || !u.getParola().equals(parola)) {
            throw new UtilizatorNegasitException("Email sau parola incorecta");
        }

        return u;
    }

    public void afiseazaTotiUtilizatorii() {
        utilizatori.values().forEach(System.out::println);
    }

    public void alimenteazaPortofel(String email, double suma) throws UtilizatorNegasitException {
        Utilizator u = utilizatori.get(email);
        if (u instanceof Cursant) {
            Cursant c = (Cursant) u;
            c.setPortofelVirtual(c.getPortofelVirtual() + suma);
            System.out.println("Portofel actualizat. Sold nou: " + c.getPortofelVirtual());
        }

        else {
            throw new UtilizatorNegasitException("Utilizatorul nu este cursant sau nu a fost gasit");
        }
    }
}