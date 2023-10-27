import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza implements Iterable<Ingredient>{
    public final static double PRIX_BASE = 5;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description) {
        Util.checkString(titre);
        Util.checkString(description);
        this.titre = titre;
        this.description = description;
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);
        Util.checkObject(ingredients);
        ArrayList<Ingredient> nouvelle = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if(!nouvelle.contains(ingredient))
                nouvelle.add(ingredient);
            else
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");
        }
        this.ingredients = nouvelle;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }
    public boolean ajouter(Ingredient ingredient){
        Util.checkObject(ingredient);
        return ingredients.add(ingredient);
    }
    public boolean supprimer(Ingredient ingredient){
        Util.checkObject(ingredient);
        return ingredients.remove(ingredient);
    }
    public double calculerPrix(){
        double somme = PRIX_BASE;
        for (Ingredient ingredient : ingredients) {
            somme += ingredient.getPrix();
        }
        return somme;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }
    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza that = (Pizza) o;
        return Objects.equals(titre, that.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }
}
