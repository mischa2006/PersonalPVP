package mischa.PersonalPVP.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnDamage implements Listener {
    private final FileConfiguration config;
    public OnDamage(FileConfiguration config){
        this.config = config;
    }

    @EventHandler
    public void OnplayerDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player defender){
            if(event.getDamager() instanceof Player attacker){
                Boolean defenderOff = false;
                Boolean attackerOff = false;
                if (config.get(defender.getUniqueId().toString() + ".pvpdisabled") != null) {
                    if (!config.getBoolean(defender.getUniqueId().toString() + ".pvpdisabled")) {
                        defenderOff = true;
                    }
                }
                if (config.get(attacker.getUniqueId().toString() + ".pvpdisabled") != null) {
                    if (!config.getBoolean(attacker.getUniqueId().toString() + ".pvpdisabled")) {
                        attackerOff = true;
                    }
                }
                if(!attackerOff){
                    attacker.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(net.md_5.bungee.api.ChatColor.RED + "PVP is disabled for you!"));
                    event.setCancelled(true);
                    return;
                }
                if(!defenderOff){
                    attacker.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(net.md_5.bungee.api.ChatColor.RED + "PVP is disabled for this Player!"));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
}
