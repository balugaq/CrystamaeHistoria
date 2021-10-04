package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.StaveInstance;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
        if (slimefunItem instanceof Stave) {
            StaveInstance staveInstance = new StaveInstance(itemStack);
        }

    }

}