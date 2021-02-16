package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.common.entity.DartEntity;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

/*
 * This is where we register our entities
 * This will be used to mainly register projectiles, such as throwables and ammunition (when needed)
 * If you are adding an ammunition that you know wont be displayed when shot, you can skip this as it is
 * completely unnecessary and is wasting some resources.
 */

/**
 * Author: Mr. Pineapple
 */
public class EntityRegistry {
    //Deferred Register for our Entities - called in our Main class
    public static final DeferredRegister<EntityType<?>> ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, ToyGuns.ID);

    //My Dart projectile being registered by the helper method below
    public static final RegistryObject<EntityType<DartEntity>> DART = registerBasic("dart", DartEntity::new);

    /*
     * This is a helper method when registering projectiles.
     * All of the stuff in this method can be written each time we create a new projectile - but that isn't needed.
     * With this we can register things with much more ease.
     */
    private static <T extends Entity> RegistryObject<EntityType<T>> registerBasic(String id, BiFunction<EntityType<T>, World, T> function) {
        EntityType<T> type = EntityType.Builder.create(function::apply, EntityClassification.MISC)
                .size(0.25F, 0.25F)
                .setTrackingRange(100)
                .setUpdateInterval(1)
                .disableSummoning()
                .immuneToFire()
                .setShouldReceiveVelocityUpdates(true)
                .build(id);
        return ENTITY_REGISTRY.register(id, () -> type);
    }
}
