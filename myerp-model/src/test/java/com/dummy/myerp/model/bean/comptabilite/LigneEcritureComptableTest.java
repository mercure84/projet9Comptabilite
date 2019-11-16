package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class LigneEcritureComptableTest {



    @Test
    public void TestLigneEcriture(){

        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();
        ligneEcritureComptable.setCompteComptable(new CompteComptable(555, "Libellé de test"));
        ligneEcritureComptable.setCredit(new BigDecimal("100.22"));
        ligneEcritureComptable.setDebit(new BigDecimal("100.22"));
        ligneEcritureComptable.setLibelle("Test de libellé");
        ligneEcritureComptable.toString();
        ligneEcritureComptable.getCredit();
        ligneEcritureComptable.getCompteComptable();
        ligneEcritureComptable.getDebit();
        ligneEcritureComptable.getLibelle();


    }



}