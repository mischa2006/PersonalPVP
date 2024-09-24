package mischa.PersonalPVP.Commands;

import mischa.PersonalPVP.PersonalPVP;
import mischa.PersonalPVP.Tools.TimeConverter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class TogglePVP implements CommandExecutor {
    private final FileConfiguration config;
    public TogglePVP(FileConfiguration config){
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("off")){
                if(commandSender instanceof Player player){
                    if(config.get(player.getUniqueId().toString() + ".pvpdisabled") != null) {
                        if (config.getBoolean(player.getUniqueId().toString() + ".pvpdisabled")) {
                            player.sendMessage(ChatColor.RED + "PVP is already Enabled for you!");
                            return true;
                        }
                    }
                    if(config.get(player.getUniqueId().toString() + ".hasKilled") == null){
                        config.set(player.getUniqueId().toString() + ".hasKilled",false);
                        PersonalPVP.getPlugin().saveConfig();
                    }
                    System.out.println(config.getBoolean(player.getUniqueId().toString() + ".hasKilled"));
                    if(config.getBoolean(player.getUniqueId().toString() + ".hasKilled")){
                        long killtime = (long) config.get(player.getUniqueId().toString() + ".startcooldowntime");
                        double CooldownTime = (double) config.get("CooldownTime") * (60*60*1000);


                        // Example object
                        if(System.currentTimeMillis() - killtime  < CooldownTime){
                            double mil1 = CooldownTime - (System.currentTimeMillis() - killtime);
                            double mil2 = System.currentTimeMillis() - killtime;
                            StringBuilder time1 = new StringBuilder();
                            List<Integer> timeList = TimeConverter.convertMilliseconds(mil1);
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

                            StringBuilder time2 = new StringBuilder();
                            List<Integer> timeList1 = TimeConverter.convertMilliseconds(mil2);
                            if(timeList1.get(0) != 0){
                                time2.append(timeList1.get(0) + " hours");
                                if(timeList1.get(1) != 0){
                                    time2.append("and" + timeList1.get(1) + " minutes");
                                }
                            }else{
                                if(timeList1.get(1) != 0){
                                    time2.append(timeList1.get(1) + " minutes");
                                }
                            }

                            player.sendMessage(ChatColor.RED + "You cant use this since you killed a player " +time1 + " ago");
                            player.sendMessage(ChatColor.RED + "You can use this command again in " +time2 );
                            return true;
                        }else{
                            player.sendMessage(ChatColor.GREEN + "Your Cooldown was over");
                            config.set(player.getUniqueId().toString() + ".hasKilled",false);
                            PersonalPVP.getPlugin().saveConfig();
                        }
                    }
                    config.set(player.getUniqueId().toString() + ".pvpdisabled",true);
                    player.sendMessage(ChatColor.GREEN + "PVP is now Disabled!");
                    PersonalPVP.getPlugin().saveDefaultConfig();
                    PersonalPVP.getPlugin().saveConfig();

                }else{
                    System.out.println("go away config");
                }
            } else if (args[0].equalsIgnoreCase("on")) {
                if(commandSender instanceof Player player) {
                    if (config.get(player.getUniqueId().toString() + ".pvpdisabled") != null) {
                        if (!config.getBoolean(player.getUniqueId().toString() + ".pvpdisabled")) {
                            player.sendMessage(ChatColor.RED + "PVP is already Enabled for you!");
                            return true;
                        }
                    }
                    config.set(player.getUniqueId().toString() + ".pvpdisabled",false);
                    player.sendMessage(ChatColor.GREEN + "PVP is now Enabled!");
                    PersonalPVP.getPlugin().saveDefaultConfig();
                    PersonalPVP.getPlugin().saveConfig();
                }else{
                    System.out.println("go away config");
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
}
