package me.iamyuuk.nightvision;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class NightVision extends JavaPlugin {
    public int time;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("""
                  
                  _  _ _      _   _ __   ___    _         \s
                 | \\| (_)__ _| |_| |\\ \\ / (_)__(_)___ _ _ \s
                 | .` | / _` | ' \\  _\\ V /| (_-< / _ \\ ' \\\s
                 |_|\\_|_\\__, |_||_\\__|\\_/ |_/__/_\\___/_||_|
                        |___/                             \s
                开发者:宇宇YuuK
                
                """);
        if (Bukkit.getPluginCommand("nightvision") != null) {
            Objects.requireNonNull(Bukkit.getPluginCommand("nightvision")).setExecutor(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("插件已关闭");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("请输入想获取的时长(单位:秒，请输入整数数字 | 1000000为永久时长 -1清除效果)");
        }
        else if (args.length == 1) {
                time = Integer.parseInt(args[0]);
                if (sender instanceof Player player) {
                    if (!player.hasPermission("nightvision.use")) {
                        sender.sendMessage("你没有权限");
                        return true;
                    }
                    if (time == -1) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        sender.sendMessage("已清除夜视效果");
                    }
                    else {
                        PotionEffect potion = getNightVision();
                        player.addPotionEffect(potion);
                        sender.sendMessage("已获取夜视效果");
                    }
                }
                else { sender.sendMessage("你不是玩家"); }



        }
        return true;
    }
    public PotionEffect getNightVision() {
        return PotionEffectType.NIGHT_VISION.createEffect(20 * time,0);
    }

}
