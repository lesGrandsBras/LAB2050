package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, LabINF2050Exception {

        if(args.length != 1){
            System.out.println("Le nombre d'arguments est invalide...");
        }


        JSONHash obj = new JSONHash(args[0]);
        obj.load();
        System.out.println("Le nombre d'albums : " + obj.countAlbum());
        System.out.println("");

        System.out.println("Le nombre de singles : " + obj.countSingle());
        System.out.println("");

        System.out.println("La liste des albums publiés depuis 2003: ");
        obj.getListeAlbumFrom2003();
        System.out.println("");

        System.out.println("La liste des albums avec une note de 5: ");
        obj.getListeAlbumNote5();
        System.out.println("");

        System.out.println("La liste de mes 3 albums préférés: ");
        obj.getListeAlbumPreferes();
    }


}
