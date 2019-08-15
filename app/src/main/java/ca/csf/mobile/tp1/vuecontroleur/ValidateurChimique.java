package ca.csf.mobile.tp1.vuecontroleur;

import android.content.Context;

import ca.csf.mobile.tp1.R;
import ca.csf.mobile.tp1.modele.Jeton;
import ca.csf.mobile.tp1.modele.TableauPeriodique;
import java.util.ArrayList;

/**
 * Valide une formule chimique
 */
public enum ValidateurChimique
{
    INSTANCE;
    private String[] messagesErreur;
    /**
     * Les jetons de la formule
     * @see Jeton
     */
    private ArrayList<Jeton> jetonsDeFormule;

    /**
     * Compte les parenthèses pour s'assurer qu'elles balancent
     */
    private int parenthesesCounter;

    private ArrayList<Integer> messagesIndexes;
    /**
     * Le message à afficher
     */
    private StringWrapper message;

    private Context contexte;

    ValidateurChimique()
    {
        messagesErreur = new String[10];
        messagesErreur[0] = "Erreur: formule de longueur nulle.";
        messagesErreur[1] = "Erreur: la formule commence par un chiffre.";
        messagesErreur[2] = "Erreur: ceci n'est pas un atome existant.";
        messagesErreur[3] = "Erreur: un multiplicateur doit suivre un élément ou une parenthèse fermante.";
        messagesErreur[4] = "Erreur: Un multiplicateur doit être au moins de 2.";
        messagesErreur[5] = "Erreur: les zéros en trop ne sont pas permis devant un nombre.";
        messagesErreur[6] = "Erreur: les parenthèses vides sont interdites.";
        messagesErreur[7] = "Erreur: Parenthèse fermante sans parenthèse ouvrante.";
        messagesErreur[8] = "Erreur: Parenthèse ouvrante sans parenthèse fermante.";
        messagesErreur[9] = "Erreur: caractère invalide (y compris une minuscule qui ne suit pas une majuscule).";
        jetonsDeFormule = new ArrayList<>();
        parenthesesCounter = 0;
    }

    public void initContexte(Context contexte){
        this.contexte = contexte;
    }

    /**
     * @return les jetons de la formule
     */
    public ArrayList<Jeton> getJetonsDeFormule()
    {
        return jetonsDeFormule;
    }

    /**
     * Valide la formule chimique
     * @param formuleChimique La formule à valider
     * @param message Le message a afficher
     * @return true si la formule est valide, false sinon
     */
    public boolean validerFormuleChimique(String formuleChimique, StringWrapper message){
        parenthesesCounter = 0;
        this.message = message;
        jetonsDeFormule = new ArrayList<>();
        messagesIndexes = new ArrayList<>();

        if(!validerLongueur(formuleChimique)) {
            setMessageErreur();
            return false;
        }

        if(Character.isDigit(formuleChimique.charAt(0))){
            addMessageErreur(R.string.erreur1);
        }

        creerJetonsRecursive(formuleChimique);
        validerParenthesesSontFermees();

        if(messagesIndexes.size() > 0){
            setMessageErreur();
            return false;
        }
        return true;
    }

    /**
     * Crée les jetons selon la formule chimique en validant qu'elle est valide
     * @param formule La formule chimique
     */
    private void creerJetonsRecursive(String formule){
        if(formule.length() == 0)
            return;

        Character premierChar = formule.charAt(0);
        if(premierChar.equals('(') || premierChar.equals(')')){
            validerParentheses(formule);

            jetonsDeFormule.add(new Jeton(premierChar.toString(), Jeton.TypeJeton.PARENTHESE));
            creerJetonsRecursive(formule.substring(1));
        }
        else if(Character.isDigit(premierChar)){
            StringWrapper nombre = new StringWrapper();
            int startIndex = validerNombre(formule, nombre);

            jetonsDeFormule.add(new Jeton(nombre.contenu, Jeton.TypeJeton.NOMBRE));
            creerJetonsRecursive(formule.substring(startIndex));
        }
        else if(Character.isUpperCase(premierChar)){
            StringWrapper symbole = new StringWrapper();
            int startIndex = validerSymbole(formule, symbole);

            jetonsDeFormule.add(new Jeton(symbole.contenu, Jeton.TypeJeton.SYNBOLE_ELEMENT_CHIMIQUE));
            creerJetonsRecursive(formule.substring(startIndex));
        }
        else
        {
            addMessageErreur(R.string.erreur9);
            creerJetonsRecursive(formule.substring(1));
        }
    }


    /**
     * Valide la longueur de la formule
     * @param formuleChimique la formule chimique
     * @return true si la formule est assez longue, false sinon
     */
    private boolean validerLongueur(String formuleChimique){
        if(formuleChimique == null || formuleChimique.length() == 0){
            addMessageErreur(R.string.erreur0);
            return false;
        }
        return true;
    }

    /**
     * Valide que toutes les parenthèses sont fermées
     */
    private void validerParenthesesSontFermees(){
        if(parenthesesCounter > 0)
        {
            addMessageErreur(R.string.erreur8);
        }
    }

    /**
     * Valide les parenthèses
     * @param formule La formule chimique
     */
    private void validerParentheses(String formule){
        if(formule.charAt(0) == '('){
            if(formule.length() > 1)
                validerApresParentheseOuvrante(formule.charAt(1));
            parenthesesCounter++;
        }
        else if(formule.charAt(0) == ')'){
            parenthesesCounter--;
            if(parenthesesCounter < 0){
                addMessageErreur(R.string.erreur7);
                parenthesesCounter = 0;
            }
        }
    }

    /**
     * Valide qu'une parenthèse n'est pas vide et qu'elle ne commence pas par un chiffre
     * @param caractereApres caractère après une parenthèse ouvrante
     */
    private void validerApresParentheseOuvrante(char caractereApres){
        if(caractereApres == ')'){
            addMessageErreur(R.string.erreur6);
        }
        if(Character.isDigit(caractereApres)){
            addMessageErreur(R.string.erreur3);
        }
    }

    /**
     * Valide le nombre
     * @param formule la formule chimique commençant par le nombre
     * @param nombre le nombre
     * @return l'index suivant la fin du nombre
     */
    private int validerNombre(String formule, StringWrapper nombre){
        Character premierChar = formule.charAt(0);
        if(premierChar.equals('0')){
            addMessageErreur(R.string.erreur5);
        }
        nombre.contenu = premierChar.toString();
        int i;
        for(i = 1; i < formule.length(); i++){
            if(Character.isDigit(formule.charAt(i)))
                nombre.contenu += formule.charAt(i);
            else
                break;
        }
        if(nombre.contenu.equals("1")){
            addMessageErreur(R.string.erreur4);
        }
        return i;
    }

    /**
     * Valide un symbole
     * @param formule la formule, commençant par le symbole
     * @param symbole le symbole
     * @return l'index de début du reste de la formule.
     */
    private int validerSymbole(String formule, StringWrapper symbole){
        int startIndex = 1;
        symbole.contenu = "" + formule.charAt(0);
        if(formule.length() > 1 && Character.isLowerCase(formule.charAt(1))){
            symbole.contenu += formule.charAt(1);
            startIndex = 2;
        }
        if(!TableauPeriodique.INSTANCE.estSymbole(symbole.contenu)){
            addMessageErreur(R.string.erreur2);
        }
        return startIndex;
    }

    /**
     * Change le message d'erreur
     * @param indexMessage l'index du message d'erreur dans le tableau des messages d'erreur.
     */
    private void addMessageErreur(int indexMessage){
        if(!messagesIndexes.contains(indexMessage))
            messagesIndexes.add(indexMessage);
    }

    private void setMessageErreur(){
        message.contenu = "";
        for(Integer indexMessage : messagesIndexes){
            if(contexte != null)
                message.contenu += contexte.getString(indexMessage);
        }
    }

}