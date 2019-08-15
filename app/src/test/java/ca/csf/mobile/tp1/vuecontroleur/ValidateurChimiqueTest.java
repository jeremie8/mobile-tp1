package ca.csf.mobile.tp1.vuecontroleur;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;
import ca.csf.mobile.tp1.modele.TableauPeriodique;

public class ValidateurChimiqueTest
{
    private void chargerTableauPeriodique()throws Exception
    {
        TableauPeriodique.INSTANCE.chargerTableauPeriodique(new FileInputStream(new File(".\\src\\main\\res\\raw\\tableau_periodique.txt")));
    }

    @org.junit.Test
    public void A_initialiser_AvecComposeChimiqueValideSansParenthese_DevraitRetournerVrai() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper messageUtilisateur = new StringWrapper();

        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("H"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("H2O"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("NaCl"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("O2"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("C11H23COOH"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("C12H22O11"), messageUtilisateur));
    }

    @org.junit.Test
    public void B_initialiser_AvecComposeChimiqueValideContenantParenthesesNonImbriquees_DevraitRetournerVrai() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper messageUtilisateur = new StringWrapper();

        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H)"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(NaCl)4"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(O2)3"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(CN)4(O2)3"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("CO(CH2OH)2"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("C18H24N2O6"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("NaCl(O3H5)2H4"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(CH3)2CO"), messageUtilisateur));
    }

    @org.junit.Test
    public void C_initialiser_AvecComposeChimiqueValideContenantParenthesesImbriquees_DevraitRetournerVrai() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper messageUtilisateur = new StringWrapper();

        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H(CN)4)5"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("B(Ar(CF3)2)4"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("B(Ar(CF3)2)4"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("((MgFe)7Si8(OH)22)"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(Al2Si2O5(OH)4)"), messageUtilisateur));
        assertTrue(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2SO4(Be)3(H2O))2"), messageUtilisateur));
    }

    @org.junit.Test
    public void D_initialiser_AvecMauvaiseUtilisationParentheses_DevraitRetournerFaux() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper messageUtilisateur = new StringWrapper();

        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique((")("), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H2("), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H)O("), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(NaCl))"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("((NaCl)"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique((")(H2N2)4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("()H2N2)4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2)N2)4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2N2))4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2N2)4)"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("((H2N2)4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2(N2)4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2N2()4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2N2)(4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(H2N2)4("), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H2(2H)2"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("()"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(())"), messageUtilisateur));
    }

    @org.junit.Test
    public void E_initialiser_AvecComposesChimiquesInvalides_DevraitRetournerFaux() throws Exception
    {
        chargerTableauPeriodique();
        StringWrapper messageUtilisateur = new StringWrapper();

        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("h"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("nacl"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("Few"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("CH1"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("CH01"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("CH02"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("Al0"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("BONDs007"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H002O"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H2SO0"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H2SO1"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("al"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("fe"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("Hu"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("abc"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique((" "), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("!"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H 2"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("Na+"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("3Al"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("3(Al)4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(3Al)4"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(3Al)"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("((3Al))"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("9"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("(3)2"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("2(3)"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique(("H2 O2"), messageUtilisateur));
        assertFalse(ValidateurChimique.INSTANCE.validerFormuleChimique((""), messageUtilisateur));
    }
}