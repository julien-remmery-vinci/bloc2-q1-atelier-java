import java.util.ArrayList;

public class PizzaComposee extends Pizza{
    public static final int REMISE = 15;

    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);
    }

    @Override
    public boolean ajouter(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    @Override
    public boolean supprimer(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    @Override
    public double calculerPrix() {
        double prix = super.calculerPrix();
        return Math.ceil(prix-prix*REMISE/100);
    }
}
