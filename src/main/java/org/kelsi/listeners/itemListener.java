package org.kelsi.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

public class itemListener implements Listener {

    @EventHandler
    public void openCasino(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null) {

            if (item.getItemMeta().getDisplayName().equals("§fКазино")) {
                if (item.getType() == Material.PLAYER_HEAD) {
                    World world = player.getWorld();
                    Block clickedBlock = event.getClickedBlock();
                    double x = clickedBlock.getLocation().getX();
                    double y = clickedBlock.getLocation().getY();
                    double z = clickedBlock.getLocation().getZ();
                    Location loc = new Location(world, x, y, z);

                    spawnCasinoModel(loc, world);
                }
            }

        }

    }

    public void spawnCasinoModel(Location loc, World world) {

        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();

        BlockDisplay mainBlock = world.spawn(new Location(world, x, y+1, z), BlockDisplay.class, entity -> {
            entity.setBlock(Material.WHITE_CONCRETE.createBlockData());
            entity.setTransformation(
                    new Transformation(
                            new Vector3f(),
                            new AxisAngle4f(),
                            new Vector3f(1, 0.75F, 1),
                            new AxisAngle4f()
                    )
            );
        });

        BlockDisplay centerBlock = world.spawn(new Location(world, x, y+1.75, z), BlockDisplay.class, entity -> {
           entity.setBlock(Material.WHITE_CONCRETE.createBlockData());
           entity.setTransformation(
                   new Transformation(
                           new Vector3f(),
                           new AxisAngle4f(),
                           new Vector3f(0.8125f, 0.8125f, 1),
                           new AxisAngle4f()
                   )
           );
        });

        BlockDisplay upperBlock = world.spawn(new Location(world, x, y+2.5625, z), BlockDisplay.class, entity -> {
            entity.setBlock(Material.WHITE_CONCRETE.createBlockData());
            entity.setTransformation(
                    new Transformation(
                            new Vector3f(),
                            new AxisAngle4f(),
                            new Vector3f(1, 0.75f, 1),
                            new AxisAngle4f()
                    )
            );
        });

        BlockDisplay casinoScreen = world.spawn(new Location(world, x+0.8125, y+2.5625, z), BlockDisplay.class, entity -> {
            entity.setBlock(Material.BLACK_CONCRETE_POWDER.createBlockData());
            entity.setTransformation(
                    new Transformation(
                            new Vector3f(),
                            new AxisAngle4f((float) -Math.toRadians(90), 0f, 0f, 1f),
                            new Vector3f(0.8125f, 0.0625f, 1),
                            new AxisAngle4f()
                    )
            );
        });

        TextDisplay casinoText = world.spawn(new Location(world, x+1.0625, y+2.75, z+0.5), TextDisplay.class, entity -> {
            entity.text(Component.text("КАЗИНО", NamedTextColor.WHITE));
            entity.setBackgroundColor(Color.PURPLE);
            entity.setTransformation(
                    new Transformation(
                            new Vector3f(),
                            new AxisAngle4f((float) Math.toRadians(90), 0f, 1f, 0f),
                            new Vector3f(1.3125f, 2.375f, 1),
                            new AxisAngle4f()
                    )
            );
        });

        ItemDisplay diamondItem1 = world.spawn(new Location(world, x+0.875, y+2.17125, z+0.8125), ItemDisplay.class, entity -> {
           entity.setItemStack(ItemStack.of(Material.DIAMOND));

           entity.setTransformation(
                   new Transformation(
                           new Vector3f(),
                           new AxisAngle4f((float) Math.toRadians(90), 0f, 1f, 0f),
                           new Vector3f(0.3125f, 0.3125f, 0.3125f),
                           new AxisAngle4f()
                   )
           );
        });

        ItemDisplay diamondItem2 = world.spawn(new Location(world, x+0.865, y+2.03125, z+0.34375), ItemDisplay.class, entity -> {
            entity.setItemStack(ItemStack.of(Material.DIAMOND));

            entity.setTransformation(
                    new Transformation(
                            new Vector3f(),
                            new AxisAngle4f((float) Math.toRadians(90), 0f, 1f, 0f),
                            new Vector3f(0.3125f, 0.3125f, 0.3125f),
                            new AxisAngle4f()
                    )
            );
        });

        ItemDisplay diamondItem3 = world.spawn(new Location(world, x+0.8675, y+2.0625, z+0.0625), ItemDisplay.class, entity -> {
            entity.setItemStack(ItemStack.of(Material.DIAMOND));

            entity.setTransformation(
                    new Transformation(
                            new Vector3f(),
                            new AxisAngle4f((float) Math.toRadians(90), 0f, 1f, 0f),
                            new Vector3f(0.3125f, 0.3125f, 0.3125f),
                            new AxisAngle4f()
                    )
            );
        });

    }

    @EventHandler
    public void destroyCasinoModel(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction().isLeftClick()) {

            for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
                if (entity instanceof BlockDisplay || entity instanceof ItemDisplay || entity instanceof TextDisplay) {
                    entity.remove();
                }
            }

        }

    }

}
