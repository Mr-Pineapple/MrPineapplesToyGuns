package co.uk.mrpineapple.toyguns.core.network;

import co.uk.mrpineapple.toyguns.common.items.NerfGunItem;
import co.uk.mrpineapple.toyguns.core.registry.SoundRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;

public class Handler {
    /**
     *
     * @param player
     */
    public static void handleUnjam(ServerPlayerEntity player) {
        ItemStack stack = player.getHeldItemMainhand();
        if(stack.getItem() instanceof NerfGunItem) {
            if(stack.getTag().getBoolean("isJammed")) {
                stack.getTag().remove("isJammed");
                player.playSound(SoundRegistry.ITEM_TOY_GUN_RELOAD.get(), SoundCategory.PLAYERS, 1F, 1F);
            }
        }
    }
}
