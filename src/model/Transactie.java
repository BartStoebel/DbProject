/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Bart.Stoebel
 */
public class Transactie implements Serializable {
    private int vanMagazijn;
    private int naarMagazijn;
    private int artikelId;
    private int aantal;

    public Transactie() {
        
    }
    public Transactie(int vanMagazijn, int naarMagazijn, int artikelId, int aantal) {
        setVanMagazijn(vanMagazijn);
        setNaarMagazijn(naarMagazijn);
        setArtikelId(artikelId);
        setAantal(aantal);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.vanMagazijn;
        hash = 79 * hash + this.naarMagazijn;
        hash = 79 * hash + this.artikelId;
        hash = 79 * hash + this.aantal;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transactie other = (Transactie) obj;
        return true;
    }

    @Override
    public String toString() {
        return "transactie{" + "vanMagazijn=" + vanMagazijn + ", naarMagazijn=" + naarMagazijn + ", artikelId=" + artikelId + ", aantal=" + aantal + '}';
    }
    
    public int getVanMagazijn() {
        return vanMagazijn;
    }

    public int getNaarMagazijn() {
        return naarMagazijn;
    }

    public int getArtikelId() {
        return artikelId;
    }

    public int getAantal() {
        return aantal;
    }

    public void setVanMagazijn(int vanMagazijn) {
        if (vanMagazijn>0){
            this.vanMagazijn = vanMagazijn;
        } else {
            throw new NumberFormatException();
        }
    }

    public void setNaarMagazijn(int naarMagazijn) {
        if (naarMagazijn>0){
            this.naarMagazijn = naarMagazijn;
        } else {
            throw new NumberFormatException();
        }
    }

    public void setArtikelId(int artikelId) {
        if (artikelId > 0){
            this.artikelId = artikelId;
        } else {
            throw new NumberFormatException();
        }
    }

    public void setAantal(int aantal) {
        if (aantal > 0){
            this.aantal = aantal;
        } else {
            throw new NumberFormatException();
        }
    }
    
}
