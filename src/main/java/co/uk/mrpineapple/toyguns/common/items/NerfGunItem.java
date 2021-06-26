package co.uk.mrpineapple.toyguns.common.items;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

/**
 * Author: Mr. Pineapple
 */
public class NerfGunItem extends GunItem {
    public NerfGunItem() {
        super(new Item.Properties().maxStackSize(1).group(ToyGuns.GROUP));
    }
}
