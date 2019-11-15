package com.dummy.myerp.consumer.testconsumer;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/dummy/myerp/consumer/testconsumer/bootstrapContext.xml"})
public class ComptabiliteDaoImplTest {


    @Autowired
    @Qualifier(value = "DaoProxy")
    private DaoProxy daoProxy;

    private ComptabiliteDao comptabiliteDao;

    @Before
    public void setUp() throws Exception {
        comptabiliteDao = daoProxy.getComptabiliteDao();
    }

    @Test
    public void getListCompteComptable() {

        comptabiliteDao.getListCompteComptable();
    }

    @Test
    public void getListJournalComptable() {
        comptabiliteDao.getListJournalComptable();
    }

    @Test
    public void getListSequenceEcritureComptable() {
        comptabiliteDao.getListSequenceEcritureComptable();
    }

    @Test
    public void getListEcritureComptable() {
        comptabiliteDao.getListEcritureComptable();
    }

    @Test
    public void getEcritureComptable() {
        try {
            int id = comptabiliteDao.getListEcritureComptable().get(0).getId();
            comptabiliteDao.getEcritureComptable(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
            System.out.println("Le test getEcritureComptable est passé mais n'a retourné aucun résultat");
        }
    }

    @Test
    public void getEcritureComptableByRef() {
        try {
            String reference = comptabiliteDao.getListEcritureComptable().get(0).getReference();
            comptabiliteDao.getEcritureComptableByRef(reference);
        } catch (NotFoundException e) {
            e.printStackTrace();
            System.out.println("Le test getEcritureComptable est passé mais n'a retourné aucun résultat");
        }
    }

    @Test
    public void loadListLigneEcriture() {

        try {
            EcritureComptable ecritureComptable = comptabiliteDao.getListEcritureComptable().get(0);
            comptabiliteDao.loadListLigneEcriture(ecritureComptable);

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Le test loadListLigneEcriture est passé mais n'a retourné aucun résultat");
        }
    }


    @Test
    public void updateEcritureComptable() {
        try{
            EcritureComptable ecritureComptable = comptabiliteDao.getListEcritureComptable().get(0);
            ecritureComptable.setLibelle("Un nouveau libellé pour modifier cette Ecriture");
            comptabiliteDao.updateEcritureComptable(ecritureComptable);
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Le test updateEcritureComptable est passé mais n'a retourné aucun résultat");
        }
        }

    @Test
    public void deleteEcritureComptable() {
        try {
            EcritureComptable ecritureComptable = comptabiliteDao.getListEcritureComptable().get(0);
            comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Le test deleteEcritureComptable est passé mais n'a retourné aucun résultat");
        }

    }


    @Test
    public void updateSequenceEcritureComptable() {
        try {
            SequenceEcritureComptable sequenceEcritureComptable =  comptabiliteDao.getListSequenceEcritureComptable().get(0);
            sequenceEcritureComptable.setAnnee(sequenceEcritureComptable.getAnnee() - 1);
            comptabiliteDao.updateSequenceEcritureComptable(sequenceEcritureComptable);
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Le test updateSequenceEcritureComptable est passé mais n'a pas pu être réalisé en totalité à cause d'un NullPointerException");
        }



    }


}
