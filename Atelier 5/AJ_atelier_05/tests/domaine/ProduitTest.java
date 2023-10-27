package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {
    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;
    Produit produit1;
    Produit produit2;
    Produit produit3;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        //prixAucune = new Prix(null, 10);
        //prixAucune = new Prix(TypePromo.SOLDE, -5);
        prixPub = new Prix(TypePromo.PUB, 10);
        prixSolde = new Prix(TypePromo.SOLDE, 20);
        //produit1 = new Produit("", "", "");
        //produit1 = new Produit(null , "Carrefour", "Legumes");
        produit1 = new Produit("Carottes", "Carrefour", "Legumes");
        produit2 = new Produit("Riz", "Auchan", "Feculent");
        produit3 = new Produit("Riz", "Auchan", "Feculent");

        produit2.ajouterPrix(LocalDate.now(), new Prix());
        produit2.ajouterPrix(LocalDate.ofYearDay(2023, 5), new Prix(TypePromo.SOLDE, 10));
        produit2.ajouterPrix(LocalDate.ofYearDay(2022, 16), new Prix(TypePromo.PUB, 5));


    }

    @Test
    void getMarque() {
        System.out.println(produit1.getMarque());
        System.out.println(produit2.getMarque());
    }

    @Test
    void getNom() {
        System.out.println(produit1.getNom());
        System.out.println(produit2.getNom());
    }

    @Test
    void getRayon() {
        System.out.println(produit1.getRayon());
        System.out.println(produit2.getRayon());
    }

    @Test
    void ajouterPrix() {
        //produit1.ajouterPrix(LocalDate.now(), null);
        produit1.ajouterPrix(LocalDate.now(), new Prix());
        //produit1.ajouterPrix(LocalDate.now(), new Prix());
        System.out.println(produit1.getPrix(LocalDate.now()));

    }

    @Test
    void getPrix() {
        //System.out.println(produit1.getPrix(LocalDate.now()));
        //System.out.println(produit1.getPrix(LocalDate.ofYearDay(2000, 5)));
        System.out.println(produit2.getPrix(LocalDate.ofYearDay(2022, 300)));
    }

    @Test
    void equals() {
        System.out.println(produit2.equals(produit3));

    }

    @Test
    void hashcode() {
        System.out.println(produit2.hashCode());
        System.out.println(produit3.hashCode());
    }
}