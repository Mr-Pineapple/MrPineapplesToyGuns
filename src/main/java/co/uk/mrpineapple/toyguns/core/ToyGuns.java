package co.uk.mrpineapple.toyguns.core;

import co.uk.mrpineapple.toyguns.client.KeyBinds;
import co.uk.mrpineapple.toyguns.client.SpecialModels;
import co.uk.mrpineapple.toyguns.client.render.gun.model.ToyRevolverModel;
import co.uk.mrpineapple.toyguns.common.entity.DartEntity;
import co.uk.mrpineapple.toyguns.core.network.PacketHandler;
import co.uk.mrpineapple.toyguns.core.registry.EntityRegistry;
import co.uk.mrpineapple.toyguns.core.registry.ItemRegistry;
import co.uk.mrpineapple.toyguns.core.registry.SoundRegistry;
import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
import com.mrcrayfish.guns.common.ProjectileManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/*
 * This is our main class in here we will make sure everything is registered correctly and the mod runs
 * Remember to add the @Mod annotation to the top of the class and include your mod id - I have it stored in a variable below
 */

/**
 * Author: Mr. Pineapple
 */
@Mod(ToyGuns.ID)
public class ToyGuns {
    //This variable is our mods ID - this must be coherent across the project
    public static final String ID = "toyguns";

    /*
     * This is our creative tab that we will add our items to.
     * If you wanted, you could just add them to the Gun Mods tab.
     * We pass in our ID to this so we can name it in the lang file.
     */
    public static final CreativeModeTab GROUP = new CreativeModeTab(ID) {
        //Here we create the icon for the tab
        //If you wanted a normal item here then you can just return an ItemStack
        @Override
        public ItemStack makeIcon() {
            //Get the Item in a new ItemStack
            ItemStack stack = new ItemStack(ItemRegistry.TOY_GUN.get());
            //Here we add ammunition to the gun so it doesn't have the re-fill bar under the item
            stack.getOrCreateTag().putInt("AmmoCount", ItemRegistry.TOY_GUN.get().getGun().getGeneral().getMaxAmmo());
            //We now return the stack which has added ammunition
            return stack;
        }
    };

    //What needs to be called the the event bus
    public ToyGuns() {
        //Here we add the config to the mod - remember to do this for the server and client if you have them
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        //Register the Deferred Register from our Registry classes
        EntityRegistry.ENTITY_REGISTRY.register(bus);
        ItemRegistry.ITEM_REGISTRY.register(bus);
        SoundRegistry.SOUND_REGISTRY.register(bus);
        //Call the setup methods from below and add them to the bus
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        //Register our Keybinds
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
           bus.addListener(KeyBinds::register);
            bus.addListener(SpecialModels::registerAdditional);
        });
    }

    //This is the common setup event
    void commonSetup(FMLCommonSetupEvent event) {
        //Here is where we register the dart to the gun
        PacketHandler.init();
        ProjectileManager.getInstance().registerFactory(ItemRegistry.DART.get(), ((world, livingEntity, itemStack, gunItem, gun) -> new DartEntity(EntityRegistry.DART.get(), world, livingEntity, itemStack, gunItem, gun)));
    }

    //This is the client setup event
    void clientSetup(FMLClientSetupEvent event) {
        //Register the revolver model
        ModelOverrides.register(ItemRegistry.TOY_REVOLVER.get(), new ToyRevolverModel());
    }
}
