package com.dummy.myerp.business.testbusiness;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;


public class ComptabiliteManagerImplTest extends BusinessTestCase {


    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();



    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2019/00002");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }



    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2019/00002");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123.36)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2019/00002");

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    // cas de test de la RG3 où l'écriture n'a qu'une seule ligne où le débit vaut le crédit
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3bis() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2019/00002");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }



    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5() throws Exception {
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("TT", "Tests JM"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle de test");
        vEcritureComptable.setReference("XX-2018/00002");
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }


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
    //On teste 1 écriture qui possède la même référence qu'une autre écriture
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableContextRG6() throws FunctionalException {
        //création de la 1ère Ecriture
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        Calendar date = new GregorianCalendar(2016, 1,3);
        vEcritureComptable.setDate(date.getTime());
        vEcritureComptable.setLibelle("Libelle");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                null, null,
                new BigDecimal(123)));

        // création de la 2ème Ecriture
        EcritureComptable vEcritureComptableBis;
        vEcritureComptableBis = new EcritureComptable();
        vEcritureComptableBis.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptableBis.setDate(date.getTime());
        vEcritureComptableBis.setLibelle("Libelle");

        //on set la référence de la première écriture à l'écriture bis
        vEcritureComptableBis.setReference(vEcritureComptable.getReference());
        vEcritureComptableBis.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptableBis.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));

        //on enregistre en persistance la première écriture
        manager.insertEcritureComptable(vEcritureComptable);

        //on check EcritureComptableBis
        manager.checkEcritureComptableUnit(vEcritureComptableBis);
        manager.checkEcritureComptableContext(vEcritureComptableBis);
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

    // test des Getters de la classe : permet notamment de valider les requêtes SQL concernées

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
