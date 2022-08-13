package co.uk.mrpineapple.toyguns.common.container;

import co.uk.mrpineapple.toyguns.common.blockentity.ToyWorkbenchBlockEntity;
import co.uk.mrpineapple.toyguns.core.registry.ContainerRegistry;
import com.mrcrayfish.guns.crafting.WorkbenchRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;

public class ToyWorkbenchContainer extends AbstractContainerMenu {
    private ToyWorkbenchBlockEntity workbench;
    private BlockPos pos;

    public ToyWorkbenchContainer(int windowId, Container playerInventory, ToyWorkbenchBlockEntity workbench) {
        super(ContainerRegistry.WORKBENCH.get(), windowId);
        this.workbench = workbench;
        this.pos = workbench.getBlockPos();

        int offset = WorkbenchRecipes.isEmpty(workbench.getLevel()) ? 0 : 28;

        this.addSlot(new Slot(workbench, 0, 174, 18) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof DyeItem;
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 102 + y * 18));
            }
        }

        for(int x = 0; x < 9; x++) {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 160));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return workbench.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if(slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();

            if(index == 0) {
                if(!this.moveItemStackTo(slotStack, 1, 36, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if(slotStack.getItem() instanceof DyeItem) {
                    if(!this.moveItemStackTo(slotStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if(index < 28) {
                    if(!this.moveItemStackTo(slotStack, 28, 36, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if(index <= 36 && !this.moveItemStackTo(slotStack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if(slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if(slotStack.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return stack;
    }

    public ToyWorkbenchBlockEntity getWorkbench() {
        return workbench;
    }

    public BlockPos getPos() {
        return pos;
    }
}
