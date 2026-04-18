package com.pao.project.src;

public class CurrentAccount extends Account {
    private double limitaOverdraft;
    private double taxaAdministrare;

    public CurrentAccount(String iban, double sold, String moneda, double limitaOverdraft, double taxaAdministrare) {
        super(iban, sold, moneda);
        this.limitaOverdraft = limitaOverdraft;
        this.taxaAdministrare = taxaAdministrare;
    }

    public double getTaxaAdministrare() { return taxaAdministrare; }
    public void setTaxaAdministrare(double taxaAdministrare) { this.taxaAdministrare = taxaAdministrare; }

    public double getLimitaOverdraft() { return limitaOverdraft; }

    @Override
    public void afiseazaDetaliiSpecific() {
        System.out.println("CONT CURENT | Overdraft: " + limitaOverdraft + " | Taxa lunara: " + taxaAdministrare);
    }

    @Override
    public boolean retragere(double suma) {
        if (suma > 0 && (this.sold + limitaOverdraft) >= suma) {
            this.sold -= suma;
            Transaction t = new Transaction(suma, "RETRAGERE", "Retragere (include potential overdraft)");
            this.adaugaTranzactie(t);
            System.out.println("Succes: Retragere efectuata (Sold nou: " + this.sold + ")");
            return true;
        } else {
            System.out.println("Eroare: Ai depasit limita de overdraft de " + limitaOverdraft);
            return false;
        }
    }
}