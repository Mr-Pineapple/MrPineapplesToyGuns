package co.uk.mrpineapple.toyguns.core;

import co.uk.mrpineapple.toyguns.core.registry.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
     * We pass in our ID to this so we can name it in the assets.
     */
    public static final ItemGroup GROUP = new ItemGroup(ID) {
        //Here we create the icon for the tab
        //If you wanted a normal item here then you can just return an ItemStack
        @Override
        public ItemStack createIcon() {
            //Get the Item in a new ItemStack
            return new ItemStack(Items.APPLE);
        }
    };

    //What needs to be called the the event bus
    public ToyGuns() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        //Register the Deferred Register from our ItemRegistry class
        ItemRegistry.ITEM_REGISTRY.register(bus);
    }

}
