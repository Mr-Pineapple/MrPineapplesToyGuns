package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import com.mrcrayfish.guns.item.AmmoItem;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
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

    /*
     * Register a new GunItem into the Deferred Register above.
     * We can just create a new instance of the Gun Item as the API allows us to edit the properties in the JSON data file.
     * In that JSON we can add and remove fields to suit our needs, take a look at the existing guns if you would like to see them
     *      https://github.com/MrCrayfish/MrCrayfishGunMod/tree/1.16.X/src/main/resources/data/cgm/guns
     * I would say, if you wanted to add something to this then make sure you know what you're doing :P
     */
    public static final RegistryObject<GunItem> TOY_GUN = ITEM_REGISTRY.register("hand_gun", () -> new GunItem(new Item.Properties().maxStackSize(1).group(ToyGuns.GROUP)));

    /*
     * You don't need to add new ammunition as you can use the existing ones in the mod by adding them to the gun's JSON file
     * However, for my mod, I want to create my own dart.
     */
    public static final RegistryObject<Item> DART = ITEM_REGISTRY.register("dart", () -> new AmmoItem(new Item.Properties().group(ToyGuns.GROUP)));
}
