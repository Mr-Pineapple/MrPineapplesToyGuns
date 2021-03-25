package co.uk.mrpineapple.toyguns.common.items;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.item.Item;

/**
 * Author: Mr. Pineapple
 */
public class NerfGun extends GunItem {
    public NerfGun() {
        super(new Item.Properties().maxStackSize(1).group(ToyGuns.GROUP));
    }
}
