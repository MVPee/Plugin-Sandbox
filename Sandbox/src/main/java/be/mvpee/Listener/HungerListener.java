package be.mvpee.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerListener implements Listener {

    @EventHandler
    public void OnFoodLevelChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
           if (e.getFoodLevel() < player.getFoodLevel())
                e.setCancelled(true);
        }
    }

}
