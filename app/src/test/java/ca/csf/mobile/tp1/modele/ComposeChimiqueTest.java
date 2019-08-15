package ca.csf.mobile.tp1.modele;

import ca.csf.mobile.tp1.modele.TableauPeriodique;
import ca.csf.mobile.tp1.vuecontroleur.StringWrapper;
import ca.csf.mobile.tp1.vuecontroleur.ValidateurChimique;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class ComposeChimiqueTest
{
    private final double PRECISION = 0.005;

    private void chargerTableauPeriodique()throws Exception
    {
        TableauPeriodique.INSTANCE.chargerTableauPeriodique(new FileInputStream(new File(".\\src\\main\\res\\raw\\tableau_periodique.txt")));
    }

    @org.junit.Test
    public void F_getPoids_AvecComposeChimiqueSansParenthese_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper sw = new StringWrapper();
        ComposeChimique composeChimique = new ComposeChimique();

        ValidateurChimique.INSTANCE.validerFormuleChimique("H", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 1.007947, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("H2O", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 18.01532, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("NaCl", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 58.4430, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("O2", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 31.99886, PRECISION);
    }

    @org.junit.Test
    public void G_getPoids_AvecComposeChimiqueContenantParenthesesNonImbriquees_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper sw = new StringWrapper();
        ComposeChimique composeChimique = new ComposeChimique();

        ValidateurChimique.INSTANCE.validerFormuleChimique("(NaCl)4", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 233.7719, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("(O2)3", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 95.99658, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("CO(CH2OH)2", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 90.07831, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("(CH3)2CO", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 58.07945, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("NaCl(O3H5)2H4", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 168.5508, PRECISION);
    }

    @org.junit.Test
    public void H_getPoids_AvecComposeChimiqueContenantParenthesesImbriquées_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper sw = new StringWrapper();
        ComposeChimique composeChimique = new ComposeChimique();

        ValidateurChimique.INSTANCE.validerFormuleChimique("(H(CN)4)5", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 525.3897, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("(CN)4(O2)3", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 200.0666, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("B(Ar(CF3)2)4", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 722.6520, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("(Al2Si2O5(OH)4)", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 258.1608, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("(Ca4Si2O6(CO3)(OHF))2", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 816.9922, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("(H2SO4(Be)3(H2O))2", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 286.2620, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("B(Ar(CF3)2)4", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 722.6520, PRECISION);
    }

    @org.junit.Test
    public void I_getPoids_AvecComposeChimiqueContenantNombrePlusGrandQue9_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper sw = new StringWrapper();
        ComposeChimique composeChimique = new ComposeChimique();

        ValidateurChimique.INSTANCE.validerFormuleChimique("((MgFe)7Si8(OH)22)", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 1159.8984, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("(C18H24N2O6)", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 364.3948, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("C11H23COOH", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 200.3189, PRECISION);

        ValidateurChimique.INSTANCE.validerFormuleChimique("C12H22O11", sw);
        composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
        assertEquals(composeChimique.getPoids(), 342.2979, PRECISION);
    }
}