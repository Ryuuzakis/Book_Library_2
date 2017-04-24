package car.tp4.exceptions;

/**
 * Exception levée lorsque l'on essaie d'ajouter une quantité négative ou nulle de livres au stock
 *
 * @author Louis GUILBERT & Jonathan LECOINTE
 *
 */
public class NegativeQuantityException extends Exception {


    public NegativeQuantityException() {
        super("Veuillez ajouter une quantité de stock strictement positive uniquement.");
    }
}
