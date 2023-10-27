public class LigneDeCommande {
    private Pizza pizza;
    private int quantite;
    private double prixUnitaire;

    public LigneDeCommande(Pizza pizza, int quantite) {
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);
        this.pizza = pizza;
        this.quantite = quantite;
        prixUnitaire = pizza.calculerPrix();
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setQuantite(int quantite) {
        Util.checkStrictlyPositive(quantite);
        this.quantite = quantite;
    }
    public double calculerPrixtotal(){
        return prixUnitaire*quantite;
    }
    public String toString() {
        return  quantite + " " + pizza.getTitre() + "  à " + prixUnitaire ;
    }
}
