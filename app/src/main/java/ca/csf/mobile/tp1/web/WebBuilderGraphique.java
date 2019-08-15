package ca.csf.mobile.tp1.web;

import java.util.ArrayList;

public class WebBuilderGraphique implements WebBuilder {

    private final String debut = "<html><head><style>table.blueTable{background-color:#EEE;width:100%}table.blueTable td,table.blueTable th{border:2px solid #FFF}table.blueTable tr:nth-child(even){background:#D0E4F5}span.blue{color:#06f; font-size:small}span.green{color:#0c6}span.red{color:#900}</style></head><body>";
    private final String fin = "</tbody></table></body></html>";


    @Override
    public String getHTML(String contenu) {
        StringBuilder result = new StringBuilder();
        result.append(debut);
        String[] elements = contenu.split("\n");

        //La formule et son poids
        String[] formulePoids = elements[0].split(" = ");
        result.append("<h3>");
        for(int i = 0; i < formulePoids[0].length(); i++){
            result.append("<span class=\"");
            if(Character.isDigit(formulePoids[0].charAt(i))){
                result.append("blue");
            }else if(formulePoids[0].charAt(i) == '(' || formulePoids[0].charAt(i) == ')'){
                result.append("green");
            }
            result.append("\">" + formulePoids[0].charAt(i) + "</span>");
        }

        result.append(" = " + "<span class=\"red\">" + formulePoids[1] + "</span>");
        result.append("</h3>");

        //Ajoute le tableau
        result.append("<table class=\"blueTable\"><tbody>");
        for(int i = 1; i < elements.length; i++){
            String[] e = elements[i].split(": ");
            result.append("<tr><td>" + e[0] + "</td><td>" + e[1] + "</td></tr>");
        }

        result.append(fin);
        return result.toString();
    }
}
