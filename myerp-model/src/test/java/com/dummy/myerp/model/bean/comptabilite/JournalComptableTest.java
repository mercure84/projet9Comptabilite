package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import static org.junit.Assert.*;

public class JournalComptableTest {

    @Test
    public void testJournalComptable() {


        JournalComptable journalComptable = new JournalComptable();
        journalComptable.setCode("XXX");
        journalComptable.setLibelle("Libellé de test");
        journalComptable.getCode();
        journalComptable.getLibelle();
        journalComptable.toString();
    }

}