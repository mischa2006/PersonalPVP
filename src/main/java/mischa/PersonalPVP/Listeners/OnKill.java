package mischa.PersonalPVP.Listeners;

import mischa.PersonalPVP.PersonalPVP;
import mischa.PersonalPVP.Tools.TimeConverter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class OnKill implements Listener {
    private final FileConfiguration config;
    public OnKill(FileConfiguration config){
        this.config = config;
    }

    @EventHandler
    public void Onkill(PlayerDeathEvent event){
        if(event.getEntity().getKiller() != null){
            Player killer = event.getEntity().getKiller();
            killer.sendMessage(ChatColor.DARK_RED + "You Killed a player!");


            Double milliseconds = (Double) config.get("CooldownTime");  // Example object



            StringBuilder time = new StringBuilder();
            List<Integer> timeList = TimeConverter.convertMilliseconds(milliseconds * 60 * 60 * 1000);
            System.out.println(timeList);
            if(timeList.get(0) != 0){
                time.append(timeList.get(0) + " hours");
                if(timeList.get(1) != 0){
                    time.append("and" + timeList.get(1) + " minutes");
                }
            }else{
                if(timeList.get(1) != 0){
                    time.append(timeList.get(1) + " minutes");
                }
            }

            if(config.get(killer.getUniqueId() + ".hasKilled") != null) {
                if (config.getBoolean(killer.getUniqueId() + ".hasKilled")) {
                    killer.sendMessage(ChatColor.DARK_RED + "By doing this you refreshed your cooldown time");
                }
                killer.sendMessage(ChatColor.DARK_RED + "You will now have to wait for " + time.toString() + " Before Disabling pvp");
            }else{
                killer.sendMessage(ChatColor.DARK_RED + "You will now have to wait " + time.toString() + " hours Before Disabling pvp");
            }
            config.set(killer.getUniqueId() + ".hasKilled", true);
            config.set(killer.getUniqueId() + ".startcooldowntime",  System.currentTimeMillis());
            PersonalPVP.getPlugin().saveDefaultConfig();
            PersonalPVP.getPlugin().saveConfig();
        }
    }
}
