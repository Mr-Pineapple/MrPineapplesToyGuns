package co.uk.mrpineapple.toyguns.core.mixin;

import co.uk.mrpineapple.toyguns.common.items.NerfGunItem;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.registry.ItemRegistry;
import com.mrcrayfish.guns.common.network.ServerPlayHandler;
import com.mrcrayfish.guns.network.message.C2SMessageShoot;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;

/**
 * Author: Mr. Pineapple
 */

@Mixin(ServerPlayHandler.class)
public abstract class ServerPlayHandlerMixin {

    @Inject(at = @At("HEAD"), method = "handleShoot", cancellable = true, remap = false)
    private static void handleShootHead(C2SMessageShoot message, ServerPlayer player, CallbackInfo ci) {
        ItemStack stackInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        CompoundTag tag = stackInHand.getOrCreateTag();
        int ammoCount = tag.getInt("AmmoCount");
        if(stackInHand.is(ItemRegistry.TRI_SHOT.get()) && ammoCount <= 2) {
            player.displayClientMessage(Component.translatable("info." + ToyGuns.ID + ".gun_limited_ammo").withStyle(ChatFormatting.YELLOW), true);
            ci.cancel();
        }
    }

    @Inject(at = @At("RETURN"), method = "handleShoot", cancellable = true, remap = false)
    private static void handleShootReturn(C2SMessageShoot message, ServerPlayer player, CallbackInfo ci) {
        System.out.println("Started Return Injection");
        ItemStack stackInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        CompoundTag tag = stackInHand.getOrCreateTag();
        int ammoCount = tag.getInt("AmmoCount");
        if(stackInHand.is(ItemRegistry.TRI_SHOT.get())) {
            System.out.println("Holding Tri-Shot (RETURN)");
            tag.putInt("AmmoCount", Math.max(0, tag.getInt("AmmoCount") - 2));
        }
        System.out.println("Finished Return Injection");
    }
}
