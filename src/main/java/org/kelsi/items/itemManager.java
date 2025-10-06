package org.kelsi.items;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;
import java.util.UUID;

public class itemManager {

    public static ItemStack casinoItem;

    public static void registerItems(JavaPlugin plugin) {
        createCasinoItem(plugin);
        Bukkit.addRecipe(getCasinoRecipe(plugin));
    }

    private static void createCasinoItem(JavaPlugin plugin) {
        casinoItem = new ItemStack(Material.PLAYER_HEAD, 1);
        String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGUxYzJhNWM0ZDRjMzVmYzM3NTQyOTVmMzljMzMzZWY2NzhiZDJlZGFkOWM4OGYyZDE2ODBmMTQ5NjgyIn19fQ==";

        SkullMeta skull = (SkullMeta) casinoItem.getItemMeta();

        skull.setDisplayName("§fКазино");
        skull.setLore(Arrays.asList(
            "§dСтавится так же, как и обычные блоки"
        ));
        // Установка скина для головы
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID(), null);
        profile.getProperties().add(new ProfileProperty("textures", base64));
        skull.setPlayerProfile(profile);

        casinoItem.setItemMeta(skull);

    }

    private static ShapedRecipe getCasinoRecipe(JavaPlugin plugin) {

        NamespacedKey key = new NamespacedKey(plugin, "casino_item");
        ShapedRecipe recipe = new ShapedRecipe(key, casinoItem);

        recipe.shape("RRR", "RRR", "RRR");
        recipe.setIngredient('R', Material.DIRT);

        return recipe;
    }

}
