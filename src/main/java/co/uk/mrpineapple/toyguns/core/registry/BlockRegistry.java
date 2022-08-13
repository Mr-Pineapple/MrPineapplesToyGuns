package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.common.block.ToyWorkbenchBlock;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, ToyGuns.ID);

    public static final RegistryObject<Block> TOY_WORKBENCH = register("toy_workbench", () -> new ToyWorkbenchBlock(Block.Properties.of(Material.METAL).strength(1.5F)));

    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier) {
        return register(id, blockSupplier, block1 -> new BlockItem(block1, new Item.Properties().tab(ToyGuns.GROUP)));
    }

    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, @Nullable Function<T, BlockItem> supplier) {
        RegistryObject<T> registryObject = REGISTER.register(id, blockSupplier);
        if(supplier != null)
        {
            ItemRegistry.ITEM_REGISTRY.register(id, () -> supplier.apply(registryObject.get()));
        }
        return registryObject;
    }
}
