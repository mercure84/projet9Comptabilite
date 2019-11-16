package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import static org.junit.Assert.*;

public class SequenceEcritureComptableTest {


    @Test
    public  void TestSequenceEC(){

        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
        sequenceEcritureComptable.setAnnee(1999);
        sequenceEcritureComptable.setCode("ABCD");
        sequenceEcritureComptable.setDerniereValeur(1234);
        sequenceEcritureComptable.getAnnee();
        sequenceEcritureComptable.getDerniereValeur();
        sequenceEcritureComptable.getCode();
        sequenceEcritureComptable.toString();

    }

    @Test
    public void TestSequenceECConstructeurAvecParam(){

        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(2019, 1234);
        sequenceEcritureComptable.toString();
        SequenceEcritureComptable sequenceEcritureComptable1 = new SequenceEcritureComptable(2019, 1234, "ABCD");

    }



}