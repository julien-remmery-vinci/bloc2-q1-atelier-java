package domaine;

import java.time.Duration;
import java.util.*;

public class Plat {
    public enum Difficulte {
        X,XX,XXX,XXXX,XXXXX;

        @Override
        public String toString() {
            return super.toString()
                    .replace("X", "*")
                    .replace("XX", "**")
                    .replace("XXX", "***")
                    .replace("XXXX", "****")
                    .replace("XXXXX", "*****");
        }
    }
    public enum Cout{
        $,$$,$$$,$$$$,$$$$$;

        @Override
        public String toString() {
            /*for (Cout c : Cout.values()) {
                if(c.equals(this)){
                    String aRenvoyer = "";
                    for (int i = 0; i < this.ordinal()+1; i++) {
                        aRenvoyer += "€";
                    }
                    return aRenvoyer;
                }
            }
            return "";*/
            return super.toString()
                    .replace("$", "€")
                    .replace("$$", "€€")
                    .replace("$$$", "€€€")
                    .replace("$$$$", "€€€€")
                    .replace("$$$$$", "€€€€€");
        }
    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private List<Instruction> recette = new ArrayList<>();
    private Set<IngredientQuantifie> ingredients = new HashSet<>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }
    //insère l’instruction à la position précisée, position commençant à 1.
    public void insererInstruction(int position, Instruction instruction) {
        if(position <= 0 || position > recette.size()+1)
            throw new IllegalArgumentException();
        recette.add(position-1, instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnminutes());
    }
    //ajoute l’instruction en dernier.
    public void ajouterInstruction (Instruction instruction){
        recette.add(instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnminutes());
    }

    //remplace l’instruction à la position précisée par celle en paramètre.
    //renvoie l’instruction qui a été remplacée
    public Instruction remplacerInstruction (int position, Instruction instruction){
        if(position <= 0 || position > recette.size())
            throw new IllegalArgumentException();
        Instruction instruction1 = recette.set(position-1, instruction);
        dureeEnMinutes = dureeEnMinutes.minus(instruction1.getDureeEnminutes());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnminutes());
        return instruction1;
    }
    //supprimer l’instruction à la position précisée
    //renvoie l’instruction qui a été supprimée
    public Instruction supprimerInstruction (int position){
        if(position <= 0 || position > recette.size())
            throw new IllegalArgumentException();
        Instruction instruction = recette.remove(position-1);
        dureeEnMinutes = dureeEnMinutes.minus(instruction.getDureeEnminutes());
        return instruction;
    }
    //fournit un itérateur d’instructions ne permettant pas de supprimer des
    //instructions (la méthode remove de l’itérateur renvoyé doit lancer une UnsupportedOperationException)
    public Iterator<Instruction> instructions(){
        List<Instruction> recette2 = Collections.unmodifiableList(recette);
        return recette2.iterator();
    }
    //crée un IngrédientQuantifie et l’ajoute si l’ingrédient n’est pas encore
    //présent. Cela renvoie false si l’ingrédient est déjà présent.
    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        return ingredients.add(new IngredientQuantifie(ingredient, quantite, unite));
    }
    //idem précédente.
    //l’unité mise par défaut est NEANT
    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        return ingredients.add(new IngredientQuantifie(ingredient, quantite, Unite.NEANT));
    }
    //modifie l’unité et la quantité de l’ingrédient quantifié correpondant
    // à l’ingrédient passé en paramètre.
    //renvoie false si l’ingredient n’est pas présent.
    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if(ingredientQuantifie.getIngredient().equals(ingredient)){
                ingredientQuantifie.setQuantite(quantite);
                ingredientQuantifie.setUnite(unite);
                return true;
            }
        }
        return false;
    }
    //supprime l’ingrédient quantifié correspondant à l’ingrédient passé en
    //paramètre.
    //renvoie false si l’ingredient n’est pas présent
    public boolean supprimerIngredient(Ingredient ingredient){
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if(ingredientQuantifie.getIngredient().equals(ingredient)){
                ingredients.remove(ingredientQuantifie);
                return true;
            }
        }
        return false;
    }
    //renvoie l’ingrédient quantifié correspondant à l’ingrédient
    //renvoie null si l’ingredient n’est pas présent
    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if(ingredientQuantifie.getIngredient().equals(ingredient)){
                return ingredientQuantifie;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }
}
