package com.dummy.myerp.business.testbusiness;

import java.util.Date;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import org.junit.Assert;
import org.junit.Test;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.technical.exception.FunctionalException;


public class ComptabiliteManagerImplIntegrationTest extends BusinessTestCase {


    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    @Test
    public void testConnexionBDD(){
        if (manager.getListCompteComptable() != null) {
            System.out.println(manager.getListCompteComptable());
            System.out.println("La connexion à la BDD de test est effective !");

        }
        else {

            System.out.println("Il y a un problème de connexion à la BDD !");
        }
    }





    @Test
    public void addReference() throws FunctionalException {
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achats"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle de test");
        manager.addReference(vEcritureComptable);
        Assert.assertTrue(vEcritureComptable.toString(), vEcritureComptable.getReference().equals("AC-2019/00001"));
    }

    @Test
    public void checkEcritureComptable() {
    }

    @Test
    public void insertEcritureComptable() {
    }

    @Test
    public void updateEcritureComptable() {
    }

    @Test
    public void deleteEcritureComptable() {
    }
}
