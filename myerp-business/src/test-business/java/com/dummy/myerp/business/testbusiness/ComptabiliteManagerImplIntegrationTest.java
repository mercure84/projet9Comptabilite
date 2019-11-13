package com.dummy.myerp.business.testbusiness;

import java.util.Date;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import org.junit.Assert;
import org.junit.Test;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.technical.exception.FunctionalException;

import static junit.framework.TestCase.assertTrue;


public class ComptabiliteManagerImplIntegrationTest extends BusinessTestCase {


    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    // test simple d'accès à la BDD : permet de tester que le programme de test accède à la base docker
    @Test
    public void testConnexionBDD(){
        Assert.assertFalse("La reqûete de test d'accès à la BDD a retourné un résultat null : cela peut indiquer un problème de connexion", manager.getListCompteComptable() == null);
    }

    //prince du test : on vérifie que la référence est correctement ajoutée, attention ce test ne vérifie pas le format de la référence
    @Test
    public void addReference() throws FunctionalException {
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achats"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle de test");
        manager.addReference(vEcritureComptable);
        Assert.assertTrue("Référence ajoutée: " + vEcritureComptable.getReference(), vEcritureComptable.getReference()!=null);
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
