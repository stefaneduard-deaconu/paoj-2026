package com.pao.laboratory05.audit;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AngajatService {
    private Angajat[] angajati = new Angajat[0];
    private AuditEntry[] auditLog = new AuditEntry[0];

    private AngajatService() {}

    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    private void logAction(String action, String target) {
        AuditEntry entry = new AuditEntry(action, target, LocalDateTime.now().toString());
        AuditEntry[] newLog = new AuditEntry[auditLog.length + 1];
        System.arraycopy(auditLog, 0, newLog, 0, auditLog.length);
        newLog[auditLog.length] = entry;
        this.auditLog = newLog;
    }

    public void addAngajat(Angajat a) {
        Angajat[] newArr = new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, newArr, 0, angajati.length);
        newArr[angajati.length] = a;
        this.angajati = newArr;

        logAction("ADD", a.getNume());
        System.out.println("Angajat adaugat: " + a.getNume());
    }

    public void findByDepartament(String numeDept) {
        logAction("FIND_BY_DEPT", numeDept);
        boolean found = false;
        for (Angajat a : angajati) {
            if (a.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(a);
                found = true;
            }
        }
        if (!found) System.out.println("Niciun angajat in " + numeDept);
    }

    public void listBySalary() {
        Angajat[] copy = angajati.clone();
        Arrays.sort(copy);
        for (Angajat a : copy) System.out.println(a);
    }

    public void printAuditLog() {
        System.out.println("--- Audit Log ---");
        for (AuditEntry e : auditLog) {
            System.out.println(e);
        }
    }
}