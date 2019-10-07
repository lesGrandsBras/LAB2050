package Lab03;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException {

        long total = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez un montant en sous: ");
        long montant = sc.nextLong();

        System.out.println("Taxe fédérale? true/ false");
        boolean tps = sc.nextBoolean();

        System.out.println("Taxe provinciale? true/false");
        boolean tvq = sc.nextBoolean();

        try{
            total = Taxes.ajouterTaxes(montant, tps, tvq);
            System.out.println("Le montant avant taxes est: " + montant);
            System.out.println("Le montant après taxes est: " + total);
        } catch(java.lang.IllegalArgumentException e){
            System.out.println(e.toString());
        }

        Monnaie m1 = new Monnaie(12, 34);

        System.out.println(m1.convertir());


    }
}
