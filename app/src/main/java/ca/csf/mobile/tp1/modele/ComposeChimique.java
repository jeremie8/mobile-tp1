package ca.csf.mobile.tp1.modele;

import ca.csf.mobile.tp1.modele.portail.IPortailModele;
import ca.csf.mobile.tp1.vuecontroleur.portail.IPortailVue;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Représente un composé chimique.
 * @author Jérémie Vézina
 */
public class ComposeChimique implements IPortailModele
{
    private class IntWrapper{
        public Integer contenu;
    }

    /**
     * Le poids du composé chimique
     */
    private double poids;

    /**
     * La composition
     */
    private TreeMap<String, Double> composition;

    /**
     * Le portail vers la vie
     * @see IPortailVue
     */
    private IPortailVue portailVue;

    /**
     * Les jetons de la formule
     * @see Jeton
     */
    private ArrayList<Jeton> jetonsDeFormule;

    /**
     * Controleur de base du composé chimique
     */
    public ComposeChimique()
    {
    }

    /**
     * Controleur du composé chimique
     * @param portailVue lien vers la vue
     */
    public ComposeChimique(IPortailVue portailVue)
    {
        this.portailVue = portailVue;
    }

    /**
     * Initialise le composé chimique
     * @param jetonsDeFormule La liste (valide) des jetons de la formule.
     */
    public void initialiser(ArrayList<Jeton> jetonsDeFormule)
    {
        this.jetonsDeFormule = new ArrayList<>();
        this.jetonsDeFormule.add(new Jeton("(", Jeton.TypeJeton.PARENTHESE));
        this.jetonsDeFormule.addAll(jetonsDeFormule);
        this.jetonsDeFormule.add(new Jeton(")", Jeton.TypeJeton.PARENTHESE));

        composition = new TreeMap<>();
        IntWrapper i = new IntWrapper();

        poids = calculerPoidsParenthese(0, i);
        if(portailVue != null)
            portailVue.notifier(this);
    }

    /**
     * Calcule le poids d'une parenthèse du composé chimique.
     * @param index Index du début de la parenthèse
     * @param indexEndParenthese Index de la fin de la parenthèse
     * @return Le poids de la parenthèse
     */
    private double calculerPoidsParenthese(int index, IntWrapper indexEndParenthese){
        double poidsParenthese = 0;
        if(index == jetonsDeFormule.size() - 1)
            return poidsParenthese;

        for(int i = index; i < jetonsDeFormule.size(); i++){
            Jeton jeton = jetonsDeFormule.get(i);
            if(jeton.getTypeJeton().equals(Jeton.TypeJeton.PARENTHESE)){
                //Gère les parenthèses
                if(jeton.getContenuJeton().equals("(")){
                    //Calcule le poids de la nouvelle parenthèse
                    poidsParenthese += calculerPoidsParenthese(i + 1, indexEndParenthese);
                    i = indexEndParenthese.contenu;
                }
                else{
                    //Parenthèse fermante : retourne son poids
                    if(prochainJetonEstNombre(i))
                    {
                        poidsParenthese *= Integer.parseInt(jetonsDeFormule.get(i + 1).getContenuJeton());
                    }
                    indexEndParenthese.contenu = i;
                    return poidsParenthese;
                }
            }
            else if(jeton.getTypeJeton().equals(Jeton.TypeJeton.SYNBOLE_ELEMENT_CHIMIQUE)){
                //Ajoute le poids de l'élément chimique
                double poidsElement = TableauPeriodique.INSTANCE.getElementChimique(jeton.getContenuJeton()).getPoidsAtomique();
                if(prochainJetonEstNombre(i))
                {
                    poidsElement *= Integer.parseInt(jetonsDeFormule.get(i + 1).getContenuJeton());
                }
                poidsParenthese += poidsElement;
                if(!composition.containsKey(jeton.getContenuJeton())){
                    ajouterMolecule(jeton.getContenuJeton());
                }
            }
        }
        return poidsParenthese;
    }

    /**
     * Ajoute une molécule à la composition du composé chimique.
     * @param symbole Le symbole à ajouter
     */
    private void ajouterMolecule(String symbole){
        composition.put(symbole, TableauPeriodique.INSTANCE.getElementChimique(symbole).getPoidsAtomique());
    }

    /**
     * Vérifie que le prochain jeton est un nombre
     * @param index index du jeton avant le nombre
     * @return true si le jeton après l'index est un nombre, false sinon
     */
    private boolean prochainJetonEstNombre(int index){
        return index + 1 < jetonsDeFormule.size()
                && jetonsDeFormule.get(index + 1).getTypeJeton().equals(Jeton.TypeJeton.NOMBRE);
    }

    @Override
    public String getInformationSurComposeeChimique()
    {
        StringBuilder information = new StringBuilder();
        for(int i = 1; i  <  jetonsDeFormule.size() - 1; i++){
            information.append(jetonsDeFormule.get(i).getContenuJeton());
        }
        information.append(" = " + poids);
        for(String element: composition.keySet()){
            information.append("\n" + element + ": " + composition.get(element));
        }
        return information.toString();
    }

    /**
     * @return Le poids du composé chimique.
     */
    public double getPoids()
    {
        return poids;
    }
}
