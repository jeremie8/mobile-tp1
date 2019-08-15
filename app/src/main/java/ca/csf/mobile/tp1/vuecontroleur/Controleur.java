package ca.csf.mobile.tp1.vuecontroleur;

import java.io.InputStream;

import ca.csf.mobile.tp1.R;
import ca.csf.mobile.tp1.Vue;
import ca.csf.mobile.tp1.modele.ComposeChimique;
import ca.csf.mobile.tp1.modele.TableauPeriodique;

/**
 * Valide les informations entrées par l'utilisateur et gère la communication entre la vue et le modèle
 */
public enum Controleur
{
    INSTANCE;

    /**
     * La vue
     * @see Vue
     */
    private Vue vue;

    /**
     * Le composé chimique
     * @see ComposeChimique
     */
    private ComposeChimique composeChimique;

    /**
     * true si le fichier est chargé correctement, false sinon
     */
    private boolean validationChargementFichier;

    /**
     * Initialise le controleur
     * @param vue lien vers la vue
     * @param fichier Le fichier vers l'information du tableau périodique
     */
    public void Initialiser(Vue vue, InputStream fichier)
    {
        this.vue = vue;
        composeChimique = new ComposeChimique(vue);
        validationChargementFichier = TableauPeriodique.INSTANCE.chargerTableauPeriodique(fichier);
        ValidateurChimique.INSTANCE.initContexte(vue.getApplicationContext());
    }

    /**
     * Gère l'entrée de l'utilisateur
     * @param formuleChimique La formule entrée par l'utilisateur
     * @return true si la formule est valide, false sinon
     */
    public boolean traiterEntreeUtilisateur(String formuleChimique){
        StringWrapper message = new StringWrapper();
        if(ValidateurChimique.INSTANCE.validerFormuleChimique(formuleChimique, message))
        {
            composeChimique.initialiser(ValidateurChimique.INSTANCE.getJetonsDeFormule());
            vue.setMessageAffichage(vue.getApplicationContext().getString(R.string.msgOK));
            return true;
        }
        else
        {
            vue.setMessageAffichage(message.contenu);
            return false;
        }
    }
}
