package co.uk.mrpineapple.toyguns.core.events;

import co.uk.mrpineapple.toyguns.common.items.NerfGunItem;
import co.uk.mrpineapple.toyguns.core.Config;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.registry.SoundRegistry;
import com.mrcrayfish.guns.event.GunFireEvent.Pre;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;
import java.util.Random;

/**
 * This class will be used for all shooting events that I will utilise.
 * The gun mod provides 3 events for firing guns check out {@link com.mrcrayfish.guns.event.GunFireEvent} for what they are
 */

/**
 * Author: Mr. Pineapple
 */
@Mod.EventBusSubscriber(modid = ToyGuns.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShootingEvent {
    static final Random rand = new Random();
    /**
     * @param event the event. In this case, the Pre shoot event {@link com.mrcrayfish.guns.event.GunFireEvent.Pre} which is fired when a player is about to shoot a bullet
     */
    @SubscribeEvent
    public static void preShoot(Pre event) {
        if(Config.COMMON.gunJams.get()) {
            //Make sure we are only doing this to nerf guns
            if(!(event.getStack().getItem() instanceof NerfGunItem))
                return;
            if(rand.nextInt(Config.COMMON.dartJamChance.get() - 1) <= 1) {
            /*
               Add NBT data to the ItemStack
               isJammed - for checking if a gun is jammed
             */
                event.getStack().getTag().putBoolean("isJammed", true);
            }
            //Check if the gun is jammed from the NBT data
            if(event.getStack().getTag().getBoolean("isJammed")) {
                //Play sound
                event.getPlayer().playSound(SoundRegistry.ITEM_TOY_GUN_RELOAD.get(), 1F, 1F);
                //Set cooldown tracker for the stack
                event.getPlayer().getCooldownTracker().setCooldown(event.getStack().getItem(), 60);
                //Inform the player
                if(Config.COMMON.showJamStatusMessage.get()) {
                    event.getPlayer().sendStatusMessage(new TranslationTextComponent("info." + ToyGuns.ID + ".gun_jammed", (new KeybindTextComponent("key." + ToyGuns.ID + ".unjam_gun")).getString().toUpperCase(Locale.ENGLISH)).mergeStyle(TextFormatting.RED), true);
                }
                //Cancel the event, ultimately, not firing the gun
                event.setCanceled(true);
            }
        }
    }
}
