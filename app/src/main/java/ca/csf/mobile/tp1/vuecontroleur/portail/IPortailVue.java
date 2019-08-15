package ca.csf.mobile.tp1.vuecontroleur.portail;

import ca.csf.mobile.tp1.modele.portail.IPortailModele;

/**
 * Portail vers la vue
 */
public interface IPortailVue
{
    /**
     * @param portailModele Est notifié lorsque la vue doit récupérer les informations de la composition chimique
     */
    public abstract void notifier(IPortailModele portailModele);
}
