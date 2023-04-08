package maxmeitner.signedit.utils;

import lombok.Getter;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SignEditEvent extends Event {
    @Getter
    private static final HandlerList handlerList = new HandlerList();
    @Getter
    private Sign sign;
    @Getter
    private Player player;

    public SignEditEvent(Sign sign, Player player) {
        this.sign = sign;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {return handlerList;}
}
