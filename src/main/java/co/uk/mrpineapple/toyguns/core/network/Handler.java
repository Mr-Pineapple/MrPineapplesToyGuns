package co.uk.mrpineapple.toyguns.core.network;

import co.uk.mrpineapple.toyguns.common.items.NerfGunItem;
import co.uk.mrpineapple.toyguns.core.registry.SoundRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

/**
 * Author: Mr. Pineapple
 */
public class Handler {
    /**
     *
     * @param player
     */
    public static void handleUnjam(ServerPlayer player) {
        ItemStack stack = player.getMainHandItem();
        if(stack.getItem() instanceof NerfGunItem) {
            if(stack.getTag().getBoolean("isJammed")) {
                stack.getTag().remove("isJammed");
                player.playSound(SoundRegistry.ITEM_TOY_GUN_RELOAD.get(), 1F, 1F);
            }
        }
    }
}
