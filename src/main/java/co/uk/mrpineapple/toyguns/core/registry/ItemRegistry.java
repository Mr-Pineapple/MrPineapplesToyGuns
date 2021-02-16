package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * In this class we will register all of our items (guns, bullets, attachments etc.)
 */

/**
 * Author: Mr. Pineapple
 */
public class ItemRegistry {
    /*
     * Create a Deferred Register to register the items to our mod.
     * This is called in the main mod file, where we will add it to the event bus.
     */
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ToyGuns.ID);

}
