package service.model.interfaces;

import com.AbstractCard;

public interface PlayerInterface <Card extends AbstractCard>{

    void playCard(Card card);

}
