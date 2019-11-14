package com.dummy.myerp.business.testbusiness;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.model.bean.comptabilite.*;
import org.junit.Assert;
import org.junit.Test;
import com.dummy.myerp.technical.exception.FunctionalException;



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


    //on teste ici le bon format de la référence à savoir comme cet exemple XX-2019/00088
    @Test
    public void addReferenceCheckFormat() throws FunctionalException{
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achats"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle de test");
        manager.addReference(vEcritureComptable);
        boolean isReferenceOK = true;
        String regexp = "[A-Z]{2}\\-[0-9]{4}/[0-9]{5}";
        isReferenceOK = Pattern.matches(regexp, vEcritureComptable.getReference());
        Assert.assertTrue("Référence testée :" + vEcritureComptable.getReference(), isReferenceOK);
    }

    //on teste que la référence d'une Ecriture Comptable est unique : RG6
    //On teste 1 écriture qui possède la même référence qu'une écriture de la persistance
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableContextRG6() throws FunctionalException {
        //création de l'Ecriture, on sait que la base docker contient l'écriture avec la référence AC-2016/00001
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        Calendar date = new GregorianCalendar(2016, 1,3);
        vEcritureComptable.setDate(date.getTime());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2016/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
        manager.checkEcritureComptableContext(vEcritureComptable);
    }


    //test de la méthode checkEcritureComptable : échec avec référence prééxistante
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableFail() throws FunctionalException {
        //création de l'Ecriture, on sait que la base docker contient l'écriture avec la référence AC-2016/00001
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        Calendar date = new GregorianCalendar(2016, 1,3);
        vEcritureComptable.setDate(date.getTime());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2016/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));
        manager.checkEcritureComptable(vEcritureComptable);
    }

    //test de la méthode checkEcritureComptable : succes
    @Test
    public void checkEcritureComptableSuccess() throws FunctionalException {
        //création de l'Ecriture, on sait que la base docker contient l'écriture avec la référence AC-2016/00001
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        Calendar date = new GregorianCalendar(2017, 1,3);
        vEcritureComptable.setDate(date.getTime());
        vEcritureComptable.setLibelle("Libelle");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));
        manager.checkEcritureComptable(vEcritureComptable);
    }

    //enregistrement d'une EC dans la persistance
    @Test
    public void insertEcritureComptable() throws FunctionalException {

        // création de l'EC
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        Calendar date = new GregorianCalendar(2016, 1,3);
        vEcritureComptable.setDate(date.getTime());
        vEcritureComptable.setLibelle("Libelle de test");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
                null, null,
                new BigDecimal(123)));
        //enregistrement de l'EC
        manager.insertEcritureComptable(vEcritureComptable);

    }

    @Test
    public void updateEcritureComptable() throws FunctionalException {

        try {
        EcritureComptable vEcritureComptable = manager.getListEcritureComptable().get(0);
            vEcritureComptable.setLibelle("Nouveau libellé de test");
            manager.updateEcritureComptable(vEcritureComptable);}
        catch (NullPointerException exception){
            System.out.println("la table est vide");
        }


    }

    @Test
    public void deleteEcritureComptable() throws FunctionalException {
        try {
            EcritureComptable vEcritureComptable = manager.getListEcritureComptable().get(0);
            vEcritureComptable.setLibelle("Nouveau libellé de test");
            manager.deleteEcritureComptable(vEcritureComptable.getId());
        } catch (NullPointerException exception) {
            System.out.println("la table est vide");
        }
    }

        @Test
        public void getListCompteComptable() throws Exception {
        List<CompteComptable> ListeEcrituresComptables = manager.getListCompteComptable();


        }

        @Test
        public void getListSequenceEcritureComptable() {
        List<SequenceEcritureComptable> ListeSequenceEC = manager.getListSequenceEcritureComptable();


        }

        @Test
        public void getListJournalComptable() {
        List<JournalComptable> ListeJournauxComptables = manager.getListJournalComptable();
        }

        @Test
        public void getListEcritureComptable() {
        List<EcritureComptable> ListeEC = manager.getListEcritureComptable();

        }








}
