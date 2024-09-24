package mischa.PersonalPVP.Commands;

import mischa.PersonalPVP.Tools.TimeConverter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class helppvp implements CommandExecutor {

    private final FileConfiguration config;
    public helppvp(FileConfiguration config){
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player player){
            Double milliseconds = (Double) config.get("CooldownTime");  // Example object
            List<Integer> timeList = TimeConverter.convertMilliseconds(milliseconds * 60 * 60 * 1000);
            StringBuilder time1 = new StringBuilder();
            System.out.println(timeList);
            if(timeList.get(0) != 0){
                time1.append(timeList.get(0) + " hours");
                if(timeList.get(1) != 0){
                    time1.append("and" + timeList.get(1) + " minutes");
                }
            }else{
                if(timeList.get(1) != 0){
                    time1.append(timeList.get(1) + " minutes");
                }else{
                    time1.append("1 minute");
                }
            }

            player.sendMessage(ChatColor.YELLOW + " " + org.bukkit.ChatColor.BOLD + "HELP MENU FOR PVP DISABLER");
            player.sendMessage(ChatColor.YELLOW + "-------------------------------------------------------------");
            player.sendMessage(ChatColor.YELLOW + "Commands:");

            player.sendMessage(ChatColor.YELLOW + "/togglepvp on");
            player.sendMessage(ChatColor.YELLOW + "enables pvp, this means you can attack players who also have pvp enabled");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.YELLOW + "/togglepvp off:");
            player.sendMessage(ChatColor.YELLOW + "disables pvp if the player did not kill an other player in the past " + time1);
            player.sendMessage(" ");
            player.sendMessage(ChatColor.YELLOW + "/pvp <player name>:");
            player.sendMessage(ChatColor.YELLOW + "find out if a player has pvp enabled or not");








            player.sendMessage(ChatColor.YELLOW + "On this SMP, you will have custom Ranks which can be used for all kinds of stuff.");




        }


        return true;
    }
}
