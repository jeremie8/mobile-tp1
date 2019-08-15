package ca.csf.mobile.tp1.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Représente le tableau périodique
 */
public enum TableauPeriodique
{
    INSTANCE;

    /**
     * Les éléments du tableau périodique
     */
    private TreeMap<String, ElementChimique> elements;

    private TableauPeriodique()
    {
        elements = new TreeMap<>();
    }

    /**
     * Charge le tableau périodique
     * @param fichier le fichier à utiliser pour charger le tableau
     * @return true si le fichier est valide, false sinon
     */
    public boolean chargerTableauPeriodique(InputStream fichier)
    {
        List<String> lignes = new ArrayList<>();
        elements = new TreeMap<>();
        try
        {
            BufferedReader r = new BufferedReader(new InputStreamReader(fichier));
            for (String line; (line = r.readLine()) != null; ) {
                lignes.add(line);
            }
        }
        catch (IOException e)
        {
            return false;
        }

        for (String ligne : lignes)
        {
            try
            {
                ElementChimique p = new ElementChimique();
                p.chargerElementChimique(ligne);
                elements.put(p.getSymbole(), p);
            }
            catch (IllegalArgumentException e)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * @param symbole Le symbole de l'élément
     * @return l'élément chimique
     */
    public ElementChimique getElementChimique(String symbole)
    {
        return elements.get(symbole);
    }

    /**
     * @param symbole le symbole de l'élément
     * @return true si le symbole est valide, false sinon
     */
    public boolean estSymbole(String symbole)
    {
        ElementChimique valeur = elements.get(symbole);
        return valeur != null;
    }
}
