package org.kelsi;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.kelsi.items.itemManager;
import org.kelsi.listeners.itemListener;

public final class fVanilaCore extends JavaPlugin  implements Listener {

    @Override
    public void onEnable() {
        // Инициализация листенеров
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new itemListener(), this);


        // Инициализация менеджера предметов
        itemManager.registerItems(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
