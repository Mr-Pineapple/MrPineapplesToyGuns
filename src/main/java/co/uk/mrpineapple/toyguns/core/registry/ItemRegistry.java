package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.common.items.NerfGunItem;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import com.mrcrayfish.guns.item.AmmoItem;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
     * Register a new instance of GunItem into the Deferred Register above.
     * We can just create a new instance of the Gun Item as the API allows us to edit the properties in the JSON data file.
     * In that JSON we can add and remove fields to suit our needs, take a look at the existing guns if you would like to see them
     *      https://github.com/MrCrayfish/MrCrayfishGunMod/tree/1.16.X/src/main/resources/data/cgm/guns
     * I would say, if you wanted to add something to this then make sure you know what you're doing :P
     *
     * I am using NerfGun as a class here, you can just use GunItem, as I am doing something for all of my nerf guns.
     */
    public static final RegistryObject<GunItem> TOY_GUN = ITEM_REGISTRY.register("hand_gun", NerfGunItem::new);
    public static final RegistryObject<GunItem> DEFENDER = ITEM_REGISTRY.register("defender", NerfGunItem::new);

    /*
     * This is the toy revolver, it is the same as above, as it is a gun. Even though we override the model,
     * we still need to register it in the same way.
     */
    public static final RegistryObject<GunItem> TOY_REVOLVER = ITEM_REGISTRY.register("revolver", NerfGunItem::new);
    public static final RegistryObject<GunItem> TRI_SHOT = ITEM_REGISTRY.register("tri_shot", NerfGunItem::new);
    public static final RegistryObject<GunItem> ONE_SHOT = ITEM_REGISTRY.register("one_shot", NerfGunItem::new);
    public static final RegistryObject<GunItem> FIRE_STORM = ITEM_REGISTRY.register("fire_storm", NerfGunItem::new);

    /*
     * You don't need to add new ammunition as you can use the existing ones in the mod by adding them to the gun's JSON file
     * However, for my mod, I want to create my own dart.
     */
    public static final RegistryObject<Item> DART = ITEM_REGISTRY.register("dart", () -> new AmmoItem(new Item.Properties().tab(ToyGuns.GROUP)));
}
