package ca.csf.mobile.tp1.modele;

/**
 * Représente un élément chimique.
 * @author Jérémie Vézina
 */
public class ElementChimique
{
    /**
     * Le nom de l'élément
     */
    private String nom;

    /**
     * Le symbole de l'élément
     */
    private String symbole;

    /**
     * Le numéro de l'élément
     */
    private int noElement;

    /**
     * Le poids atomique de l'élément
     */
    private double poidsAtomique;

    public static final String ERREUR1 = "Erreur de forme: il n'y a pas trois virgules qui font office de séparateur.";
    public static final String ERREUR2 = "Erreur de forme: Au moins un des quatre arguments est de longueur 0.";
    public static final String ERREUR3 = "Erreur de conversion: l'argument pour la position dans le tableau n'est pas numérique.";
    public static final String ERREUR4 = "Erreur de conversion: l'argument pour le poids n'est pas numérique.";

    public ElementChimique()
    {
    }

    /**
     * Charge les informations d'un élément selon une ligne
     * @param element La ligne représentant l'élément
     * @throws IllegalArgumentException
     */
    public void chargerElementChimique(String element) throws IllegalArgumentException
    {
        String[] elements = element.split(",");
        if (elements.length != 4)
            throw new IllegalArgumentException(ERREUR1);
        for (int i = 0; i < 4; i++)
            if (elements[i].length() <= 0)
                throw new IllegalArgumentException(ERREUR2);
        try
        {
            noElement = Integer.parseInt(elements[2]);
        } catch (NumberFormatException e)
        {
            throw new IllegalArgumentException(ERREUR3);
        }
        try
        {
            poidsAtomique = Double.parseDouble(elements[3]);
        } catch (NumberFormatException e)
        {
            throw new IllegalArgumentException(ERREUR4);
        }
        this.nom = elements[0];
        this.symbole = elements[1];
    }

    @Override
    public String toString()
    {
        return "ElementChimique{" +
                "nom='" + nom + '\'' +
                ", symbole='" + symbole + '\'' +
                ", noElement=" + noElement +
                ", poidsAtomique=" + poidsAtomique +
                '}';
    }

    /**
     * @return le nom de l'élément
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return le symbole de l'élément
     */
    public String getSymbole()
    {
        return symbole;
    }

    /**
     * @return le numéro de l'élément
     */
    public int getNoElement()
    {
        return noElement;
    }

    /**
     * @return le poids atomique de l'élément
     */
    public double getPoidsAtomique()
    {
        return poidsAtomique;
    }
}
