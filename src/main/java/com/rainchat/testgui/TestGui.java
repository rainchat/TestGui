package com.rainchat.testgui;

import com.rainchat.raingui.menus.ClickItem;
import com.rainchat.raingui.menus.LiteMenu;
import com.rainchat.raingui.menus.PaginationMenu;
import com.rainchat.raingui.replacers.PlayerReplacements;
import com.rainchat.raingui.utils.general.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TestGui extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();

        PaginationMenu paginationMenu = new PaginationMenu(this,"WorldStats", 4);

        paginationMenu.setItemSlots(Arrays.asList(
                10,11,12,13,14,15,16,
                19,20,21,22,23,24,25
        ));

        paginationMenu.guiFill(new ClickItem(new Item()
                .material(Material.GRAY_STAINED_GLASS_PANE),  inventoryClickEvent -> {}));


        
        List<ClickItem> clickItems = new ArrayList<>();

        for (World world: Bukkit.getWorlds()) {
            ClickItem clickItem =  new ClickItem(new Item()
                    .material(Material.PAPER)
                    .name("&7Stats &e%world_name%")
                    .lore(Arrays.asList(
                            "&7Difficulty: %world_difficulty%",
                            "&7Time: %world_time%"
                            ))
                    .setPlaceholders(new WorldReplacer(world)),

                    inventoryClickEvent -> {
                        player.teleport(world.getSpawnLocation());
                    }
            );
            clickItems.add(clickItem);
        }
        paginationMenu.setItems(clickItems);
        paginationMenu.open(player);
    }
}
