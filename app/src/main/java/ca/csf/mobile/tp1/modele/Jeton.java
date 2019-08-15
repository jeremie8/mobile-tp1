package ca.csf.mobile.tp1.modele;

/**
 * Représente un jeton de la formule chimique
 */
public class Jeton
{

    public enum TypeJeton{
        SYNBOLE_ELEMENT_CHIMIQUE,
        NOMBRE,
        PARENTHESE;
    }

    public Jeton(String contenuJeton, TypeJeton typeJeton)
    {
        this.contenuJeton = contenuJeton;
        this.typeJeton = typeJeton;
    }

    /**
     * Le contenu du jeton
     */
    private String contenuJeton;

    /**
     * Le type du jeton
     */
    private TypeJeton typeJeton;

    /**
     * @return le contenu du jeton
     */
    public String getContenuJeton()
    {
        return contenuJeton;
    }

    /**
     * @return le type du jeton
     */
    public TypeJeton getTypeJeton()
    {
        return typeJeton;
    }

}
