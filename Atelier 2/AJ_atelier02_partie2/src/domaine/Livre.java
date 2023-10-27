package domaine;

import java.util.*;

import domaine.Plat.Type;

public class Livre {
    Map<Type, SortedSet<Plat>> plats = new HashMap<>();

    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {
        if(!plats.containsKey(plat.getType())){
            SortedSet<Plat> set = new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    return 0;
                }
            });
            set.add(plat);
            plats.put(plat.getType(), set);
            return true;
        }else{
            if(!plats.get(plat.getType()).contains(plat)){
                plats.get(plat.getType()).add(plat);
                return true;
            }
        }
        return false;
    }
    /**
     * Supprime un plat du livre, s'il est dedans.
     4
     * Si le plat supprimé est le dernier de ce type de plat, il faut supprimer
     ce type de plat de la Map.
     * @param plat le plat à supprimer
     * @return true si le plat a été supprimé, false sinon.
     */
    public boolean supprimerPlat (Plat plat) {
        if(plats.containsKey(plat.getType())){
            if(plats.get(plat.getType()).size()==1)
                plats.remove(plat.getType());
            else{
                plats.get(plat.getType()).remove(plat);
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String aRenvoyer = "";
        for (Type type : plats.keySet()) {
            aRenvoyer += type.toString()+"\n=====";
            for (SortedSet<Plat> value : plats.values()) {
                for (Plat plat : value) {
                    aRenvoyer += "\n"+plat.getNom();
                }
            }
        }
        return aRenvoyer;
    }
}
