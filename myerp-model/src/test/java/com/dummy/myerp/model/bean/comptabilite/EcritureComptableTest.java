package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class EcritureComptableTest {

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
    }

    @Test
    public void getTotalDebit() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Test Total Débit ");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "1", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "2", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "1.7"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "3", "4"));
        Assert.assertEquals(vEcriture.getTotalDebit(), new BigDecimal("6" ));

        // test si aucun montant au débit :

        EcritureComptable vEcritureNull;
        vEcritureNull = new EcritureComptable();
        vEcritureNull.setLibelle("Test Total Débit ");
        vEcritureNull.getListLigneEcriture().add(this.createLigne(1, null, "4.32"));
        vEcritureNull.getListLigneEcriture().add(this.createLigne(1, null, "1"));
        vEcritureNull.getListLigneEcriture().add(this.createLigne(2, null, "1.7"));
        vEcritureNull.getListLigneEcriture().add(this.createLigne(2, null, "4"));
        Assert.assertEquals(vEcritureNull.getTotalDebit(), new BigDecimal("0" ));


    }

    @Test
    public void getTotalCredit() {

        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Test Total Credit ");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "15.82", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "16.7", "16"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "19.48"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "4", "4.21"));
        Assert.assertEquals(vEcriture.getTotalCredit(), new BigDecimal("39.69" ));

        // test si aucun montant au débit :


        EcritureComptable vEcritureNull;
        vEcritureNull = new EcritureComptable();
        vEcritureNull.setLibelle("Test Total Débit ");
        vEcritureNull.getListLigneEcriture().add(this.createLigne(1, "5", null));
        vEcritureNull.getListLigneEcriture().add(this.createLigne(1, "52.63", null));
        vEcritureNull.getListLigneEcriture().add(this.createLigne(2, "963.2", null));
        vEcritureNull.getListLigneEcriture().add(this.createLigne(2, "59", null));
        Assert.assertEquals(vEcritureNull.getTotalCredit(), new BigDecimal("0" ));

    }

    @Test
    public void toStringTest(){
        EcritureComptable vEcriture = new EcritureComptable();
        vEcriture.toString();
    }

}
