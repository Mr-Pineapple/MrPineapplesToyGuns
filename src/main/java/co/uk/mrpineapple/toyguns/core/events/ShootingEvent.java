package co.uk.mrpineapple.toyguns.core.events;

import co.uk.mrpineapple.toyguns.common.items.NerfGun;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.registry.SoundRegistry;
import com.mrcrayfish.guns.event.GunFireEvent.Pre;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ToyGuns.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShootingEvent {
    @SubscribeEvent
    public static void preShoot(Pre event) {
        if(!(event.getStack().getItem() instanceof NerfGun))
            return;
        int rand = new Random().nextInt(5);
        if(rand == 3) {
            event.getPlayer().playSound(SoundRegistry.ITEM_TOY_GUN_RELOAD.get(), 1F, 1F);
            event.getPlayer().getCooldownTracker().setCooldown(event.getStack().getItem(), 20*5);
            event.setCanceled(true);
        }

    }
}
