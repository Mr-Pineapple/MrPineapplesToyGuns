package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.common.blockentity.ToyWorkbenchBlockEntity;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TileEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ToyGuns.ID);

    public static final RegistryObject<BlockEntityType<ToyWorkbenchBlockEntity>> TOY_WORKBENCH = register("workbench", ToyWorkbenchBlockEntity::new, () -> new Block[]{BlockRegistry.TOY_WORKBENCH.get()});

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String id, BlockEntityType.BlockEntitySupplier<T> factoryIn, Supplier<Block[]> validBlocksSupplier) {
        return REGISTER.register(id, () -> BlockEntityType.Builder.of(factoryIn, validBlocksSupplier.get()).build(null));
    }
}
