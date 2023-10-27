import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande>{
    private static int numeroSuivant =  1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesCommande = new ArrayList<>();

    public Commande(Client client){
        Util.checkObject(client);
        if(client.getCommandeEnCours() != null) throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        numero = numeroSuivant;
        numeroSuivant++;
        this.client = client;
        date = LocalDateTime.now();
        client.enregistrer(this);
    }
    public int getNumero() {
        return numero;
    }
    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public boolean ajouter(Pizza pizza, int quantite){
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);
        if(this != client.getCommandeEnCours()) return false;
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            if(ligneDeCommande.getPizza().equals(pizza)){
                ligneDeCommande.setQuantite(ligneDeCommande.getQuantite() + quantite);
                return true;
            }
        }
        lignesCommande.add(new LigneDeCommande(pizza, quantite));
        return true;
    }
    public boolean ajouter(Pizza pizza){
        Util.checkObject(pizza);
        return ajouter(pizza, 1);
    }
    public double calculerMontantTotal(){
        double somme = 0;
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            somme += ligneDeCommande.calculerPrixtotal();
        }
        return somme;
    }
    public String detailler(){
        String aRenvoyer = "";
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            aRenvoyer+=ligneDeCommande.toString()+"\n";
        }
        return aRenvoyer;
    }
    public boolean retirer(Pizza pizza, int quantite){
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);
        if(client.getCommandeEnCours()!=this) return false;
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            if(ligneDeCommande.getPizza().equals(pizza)){
                if(ligneDeCommande.getQuantite()<quantite) return false;
                if(ligneDeCommande.getQuantite()==quantite) lignesCommande.remove(ligneDeCommande);
                ligneDeCommande.setQuantite(ligneDeCommande.getQuantite() - quantite);
                return true;
            }
        }
        return false;
    }
    public boolean retirer(Pizza pizza){
        Util.checkObject(pizza);
        return retirer(pizza, 1);
    }
    public boolean supprimer(Pizza pizza){
        Util.checkObject(pizza);
        if(client.getCommandeEnCours()==null) return false;
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            if(ligneDeCommande.getPizza().equals(pizza)) {
                lignesCommande.remove(ligneDeCommande);
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterator<LigneDeCommande> iterator() {
        return lignesCommande.iterator();
    }
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }
}
