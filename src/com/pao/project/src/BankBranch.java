package com.pao.project.src;

public class BankBranch {
    private String numeSucursala;
    private String oras;
    private String adresa;
    private String codBic;

    public BankBranch(String numeSucursala, String oras, String adresa, String codBic) {
        this.numeSucursala = numeSucursala;
        this.oras = oras;
        this.adresa = adresa;
        this.codBic = codBic;
    }

    public String getNumeSucursala() { return numeSucursala; }
    public void setNumeSucursala(String numeSucursala) { this.numeSucursala = numeSucursala; }

    public String getOras() { return oras; }
    public String getCodBic() { return codBic; }

    @Override
    public String toString() {
        return "Sucursala: " + numeSucursala + " (" + codBic + ") - " + oras;
    }
}