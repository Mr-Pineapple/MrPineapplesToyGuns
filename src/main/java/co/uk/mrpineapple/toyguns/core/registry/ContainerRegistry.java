package co.uk.mrpineapple.toyguns.core.registry;

import co.uk.mrpineapple.toyguns.common.blockentity.ToyWorkbenchBlockEntity;
import co.uk.mrpineapple.toyguns.common.container.ToyWorkbenchContainer;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerRegistry {
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ToyGuns.ID);

    public static final RegistryObject<MenuType<ToyWorkbenchContainer>> WORKBENCH = register("toy_workbench", (IContainerFactory<ToyWorkbenchContainer>) (windowId, playerInventory, data) -> {
        ToyWorkbenchBlockEntity workstation = (ToyWorkbenchBlockEntity) playerInventory.player.level.getBlockEntity(data.readBlockPos());
        return new ToyWorkbenchContainer(windowId, playerInventory, workstation);
    });

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String id, MenuType.MenuSupplier<T> factory) {
        return REGISTER.register(id, () -> new MenuType<>(factory));
    }
}
