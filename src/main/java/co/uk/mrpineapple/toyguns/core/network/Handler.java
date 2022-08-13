package co.uk.mrpineapple.toyguns.core.network;

import co.uk.mrpineapple.toyguns.common.blockentity.ToyWorkbenchBlockEntity;
import co.uk.mrpineapple.toyguns.common.container.ToyWorkbenchContainer;
import co.uk.mrpineapple.toyguns.common.items.NerfGunItem;
import co.uk.mrpineapple.toyguns.core.registry.SoundRegistry;
import com.mrcrayfish.guns.crafting.WorkbenchRecipe;
import com.mrcrayfish.guns.crafting.WorkbenchRecipes;
import com.mrcrayfish.guns.item.IColored;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Author: Mr. Pineapple
 */
public class Handler {
    /**
     *
     * @param player
     */
    public static void handleUnjam(ServerPlayer player) {
        ItemStack stack = player.getMainHandItem();
        if(stack.getItem() instanceof NerfGunItem) {
            if(stack.getTag().getBoolean("isJammed")) {
                stack.getTag().remove("isJammed");
                player.playSound(SoundRegistry.ITEM_TOY_GUN_RELOAD.get(), 1F, 1F);
            }
        }
    }

    public static void handleCraft(ServerPlayer player, ResourceLocation id, BlockPos pos) {
        Level world = player.level;

        if(player.containerMenu instanceof ToyWorkbenchContainer workbench) {
            if(workbench.getPos().equals(pos)) {
                WorkbenchRecipe recipe = WorkbenchRecipes.getRecipeById(world, id);
                if(recipe == null || !recipe.hasMaterials(player))
                    return;

                recipe.consumeMaterials(player);

                ToyWorkbenchBlockEntity workbenchBlockEntity = workbench.getWorkbench();

                /* Gets the color based on the dye */
                ItemStack stack = recipe.getItem();
                ItemStack dyeStack = workbenchBlockEntity.getInventory().get(0);
                if(dyeStack.getItem() instanceof DyeItem) {
                    DyeItem dyeItem = (DyeItem) dyeStack.getItem();
                    int color = dyeItem.getDyeColor().getTextColor();

                    if(IColored.isDyeable(stack)) {
                        IColored colored = (IColored) stack.getItem();
                        colored.setColor(stack, color);
                        workbenchBlockEntity.getInventory().set(0, ItemStack.EMPTY);
                    }
                }

                Containers.dropItemStack(world, pos.getX() + 0.5, pos.getY() + 1.125, pos.getZ() + 0.5, stack);
            }
        }
    }
}
