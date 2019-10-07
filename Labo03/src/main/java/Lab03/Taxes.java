package Lab03;

public class Taxes {

    private static final double TPS = 0.05;
    private static final double TVQ = 0.09975;



    public static long ajouterTaxes(long montant, boolean tps, boolean tvq) throws java.lang.IllegalArgumentException {
        double taxes = 0;

        if(montant < 0 || montant == 0){
            throw new java.lang.IllegalArgumentException("Illegal argument exception");
        }

        double taxe = 0;
        if(tps && tvq){
            taxe = (montant * TPS) + (montant * TVQ);
        } else if (tps){
            taxe = montant * TPS;
        } else if (tvq){
            taxe = montant * TVQ;
        }

        return (long) (montant + taxe);
    }
}
