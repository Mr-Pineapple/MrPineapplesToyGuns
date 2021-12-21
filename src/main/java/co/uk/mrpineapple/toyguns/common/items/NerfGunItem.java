package co.uk.mrpineapple.toyguns.common.items;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.KeybindComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

/**
 * Author: Mr. Pineapple
 */
public class NerfGunItem extends GunItem {
    public NerfGunItem() {
        super(new Item.Properties().stacksTo(1).tab(ToyGuns.GROUP));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flag) {
        //Add everything from the extended GunItem class
        super.appendHoverText(stack, worldIn, tooltip, flag);
        //Check if the gun is jammed from the isJammed NBT property
        if(stack.getTag().getBoolean("isJammed")) {
            //Add a tooltip on how to unjam the gun from the keybind
            tooltip.add(new TranslatableComponent("info." + ToyGuns.ID + ".gun_jammed", (new KeybindComponent("key." + ToyGuns.ID + ".unjam_gun")).getString().toUpperCase(Locale.ENGLISH)).withStyle(ChatFormatting.RED));
        }
    }
}
