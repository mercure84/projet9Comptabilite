package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompteComptableTest {

    @Test
    public void testCompteComptable() {

        CompteComptable compteComptable = new CompteComptable();
        compteComptable.setLibelle("Test libellé");
        compteComptable.setNumero(111);;
        compteComptable.getNumero();
        compteComptable.getLibelle();
        compteComptable.toString();

    }
// test du constructeur avec param
    @Test
    public void testCompteComptableBis() {
        CompteComptable compteComptable = new CompteComptable(111,"Test de libellé");
        compteComptable.toString();
    }

}