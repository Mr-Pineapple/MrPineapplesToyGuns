package co.uk.mrpineapple.toyguns.core.events;

import co.uk.mrpineapple.toyguns.common.items.NerfGunItem;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.registry.SoundRegistry;
import com.mrcrayfish.guns.event.GunFireEvent.Pre;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
    /**
     * @param event the event. In this case, the Pre shoot event {@link com.mrcrayfish.guns.event.GunFireEvent.Pre} which is fired when a player is about to shoot a bullet
     */
    @SubscribeEvent
    public static void preShoot(Pre event) {
        if(!(event.getStack().getItem() instanceof NerfGunItem))
            return;
        int rand = new Random().nextInt(10);
        if(rand == 3) {
            event.getStack().getTag().putBoolean("isJammed", true);
            event.getStack().setDisplayName(new TranslationTextComponent(event.getStack().getDisplayName().getString() + " - Jammed"));
            event.setCanceled(true);
        }
        if(event.getStack().getTag().getBoolean("isJammed")) {
            event.getPlayer().playSound(SoundRegistry.ITEM_TOY_GUN_RELOAD.get(), 1F, 1F);
            event.getPlayer().getCooldownTracker().setCooldown(event.getStack().getItem(), 60);
            event.setCanceled(true);
        }
    }
}
