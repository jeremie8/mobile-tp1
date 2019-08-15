package ca.csf.mobile.tp1.web;

public class WebBuilderTexte implements WebBuilder {
    @Override
    public String getHTML(String contenu) {
        StringBuilder result = new StringBuilder();
        String[] lignes = contenu.split("\n");
        String[] formulePoids = contenu.split(" = ");
        result.append("Formule : " + formulePoids[0] + "<hr>");
        result.append("Poids : " + formulePoids[1] + "<hr>");

        for(int i = 1; i <lignes.length; i++){
            result.append(lignes[i] + "<br>");
        }
        return result.toString();
    }
}
