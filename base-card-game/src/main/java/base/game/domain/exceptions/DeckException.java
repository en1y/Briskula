package base.game.domain.exceptions;

public class DeckException extends RuntimeException {
    public DeckException(String message, Object ... args) {
        super(String.format(message, args));
    }
}
