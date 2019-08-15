package ca.csf.mobile.tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import ca.csf.mobile.tp1.modele.portail.IPortailModele;
import ca.csf.mobile.tp1.vuecontroleur.Controleur;
import ca.csf.mobile.tp1.vuecontroleur.portail.IPortailVue;
import ca.csf.mobile.tp1.web.WebBuilder;
import ca.csf.mobile.tp1.web.WebBuilderGraphique;
import ca.csf.mobile.tp1.web.WebBuilderTexte;

public class Vue extends AppCompatActivity implements IPortailVue {

    WebBuilder builder = null;

    Button btnAfficher = null;
    Button btnNettoyer = null;
    EditText txtFormule = null;
    TextView lblMessage = null;
    WebView webView = null;
    RadioButton radioGraphique = null;
    RadioButton radioTexte = null;

    String informationFormule = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controleur.INSTANCE.Initialiser(this, this.getResources().openRawResource(R.raw.tableau_periodique));

        btnAfficher = findViewById(R.id.btnAfficher);
        btnNettoyer = findViewById(R.id.btnNettoyer);
        txtFormule = findViewById(R.id.txtInput);
        lblMessage = findViewById(R.id.lblMessage);
        lblMessage.setText("");
        webView = findViewById(R.id.webViewInfo);
        radioGraphique = findViewById(R.id.radioGraphique);
        radioTexte = findViewById(R.id.radioTexte);

        btnAfficher.setOnClickListener(btnAfficherListener);
        btnNettoyer.setOnClickListener(btnNettoyerListener);
    }

    private View.OnClickListener btnAfficherListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Controleur.INSTANCE.traiterEntreeUtilisateur(txtFormule.getText().toString());
        }
    };

    private View.OnClickListener btnNettoyerListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            webView.loadUrl("about:blank");
            informationFormule = "";
            txtFormule.setText("");
            lblMessage.setText("");
        }
    };

    public void setMessageAffichage(String messageAffichage){
        lblMessage.setText(messageAffichage);
    }

    @Override
    public void notifier(IPortailModele portailModele) {
        updateWebView(portailModele.getInformationSurComposeeChimique());
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            updateWebView(savedInstanceState.getString("WEB_VIEW_CONTENT"));
            this.txtFormule.setText(savedInstanceState.getString("FORMULE"));
            this.lblMessage.setText(savedInstanceState.getString("MESSAGES"));
        }

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("WEB_VIEW_CONTENT", informationFormule);
        outState.putString("FORMULE", txtFormule.getText().toString());
        outState.putString("MESSAGES", lblMessage.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void updateWebView(String content){
        this.informationFormule = content;
        if(radioGraphique.isChecked())
            builder = new WebBuilderGraphique();
        else
            builder = new WebBuilderTexte();

        webView.loadUrl("about:blank");
        webView.loadData(builder.getHTML(content), "text/html; charset=utf-8", "UTF-8");
    }
}