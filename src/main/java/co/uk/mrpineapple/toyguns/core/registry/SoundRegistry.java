package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * This class will register any sounds we want to add - in this case I am adding a shooting sound
 * for my toy gun.
 */

/**
 * Author: Mr. Pineapple
 */
public class SoundRegistry {
    //A Deferred Register which will be called in our main class to register all of the sounds
    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ToyGuns.ID);

    /*
     * Just like we have in our other registries we create static instances of the RegistryObject's
     * Here I am creating 2 new sound events that I will call in the gun's JSON data file when needed.
     * The key is the name of the sound - and this is the standard way they are named - category.name
     *
     * For instance this is for an item so it would be `item.fire` (however, I have added toy_gun so I know what to call).
     * If it were or a block, we would generally do something like `block.fire`
     */
    public static final RegistryObject<SoundEvent> ITEM_TOY_GUN_SHOOT = register("item.toy_gun.fire");
    public static final RegistryObject<SoundEvent> ITEM_TOY_GUN_RELOAD = register("item.toy_gun.reload");

    /*
     * A helper method to register sounds.
     * This can be written for each sound, but it's easier to use this method.
     */
    private static RegistryObject<SoundEvent> register(String key) {
        return SOUND_REGISTRY.register(key, () -> new SoundEvent(new ResourceLocation(ToyGuns.ID, key)));
    }
}
