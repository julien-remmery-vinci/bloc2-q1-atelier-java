package domaine;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {
    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        prixAucune = new Prix();
        //prixAucune = new Prix(null, 10);
        //prixAucune = new Prix(TypePromo.SOLDE, -5);
        prixPub = new Prix(TypePromo.PUB, 10);
        prixSolde = new Prix(TypePromo.SOLDE, 20);
        /*
        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);
        prixPub.definirPrix(3, 15);
         */
    }

    @org.junit.jupiter.api.Test
    void getTypePromo() {
        System.out.println(prixAucune.getTypePromo());
        System.out.println(prixPub.getTypePromo());
    }

    @org.junit.jupiter.api.Test
    void getValeurPromo() {
        System.out.println(prixAucune.getValeurPromo());
        System.out.println(prixPub.getValeurPromo());
    }

    @org.junit.jupiter.api.Test
    void definirPrix() {
        //prixPub.definirPrix(0 , 5);
        //prixPub.definirPrix(-5 , 5);
        prixAucune.definirPrix(10 , 6);
        System.out.println(prixAucune.getPrix(10));

    }

    @org.junit.jupiter.api.Test
    void getPrix() {
        /*System.out.println(prixAucune.getPrix(0));
        System.out.println(prixAucune.getPrix(-5));*/
        System.out.println(prixAucune.getPrix(1));
        System.out.println(prixAucune.getPrix(5));
        System.out.println(prixAucune.getPrix(9));
        System.out.println(prixAucune.getPrix(10));
        System.out.println(prixAucune.getPrix(15));
        System.out.println(prixAucune.getPrix(20));
        System.out.println(prixAucune.getPrix(25));
        System.out.println(prixPub.getPrix(2));
        System.out.println(prixSolde.getPrix(1));





    }
}