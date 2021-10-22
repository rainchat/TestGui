package com.rainchat.testgui;

import com.rainchat.raingui.utils.placeholder.BaseReplacements;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WorldReplacer  extends BaseReplacements<Player> {
    private final World world;


    public WorldReplacer(World world) {
        super("world_");

        this.world = world;
    }



    @Override
    public Class<Player> forClass() {
        return Player.class;
    }

    @Override
    public String getReplacement(String base, String fullKey) {


        switch (base) {
            case "name":
                return world.getName();
            case "time":
                return String.valueOf(world.getTime());
            case "difficulty":
                return world.getDifficulty().name();

        }

        return "";
    }
}
