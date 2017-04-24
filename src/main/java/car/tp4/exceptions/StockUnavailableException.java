package car.tp4.exceptions;

/**
 * Exception levée lorsque l'on essaie d'acheter un livre dans une quantité supérieure à son stock disponible
 *
 * @author Louis GUILBERT & Jonathan LECOINTE
 */
public class StockUnavailableException extends Exception {

    public StockUnavailableException(String title, int availableStock) {
        super("Le stock de livres pour le le livre '" + title + "' n'est plus que de " + availableStock + " livres. Veuillez réviser votre commande.");
    }
}
