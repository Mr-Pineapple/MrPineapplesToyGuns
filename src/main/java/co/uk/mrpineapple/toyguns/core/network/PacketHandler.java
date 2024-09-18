package co.uk.mrpineapple.toyguns.core.network;

import co.uk.mrpineapple.toyguns.core.network.message.CraftMessage;
import co.uk.mrpineapple.toyguns.core.network.message.UnjamMessage;
import com.mrcrayfish.framework.api.FrameworkAPI;
import com.mrcrayfish.framework.api.network.FrameworkNetwork;
import com.mrcrayfish.framework.api.network.MessageDirection;
import net.minecraft.resources.ResourceLocation;

/**
 * Author: Mr. Pineapple
 */
public class PacketHandler {
    private static FrameworkNetwork playChannel;

    public PacketHandler() {}

    public static void init() {
        playChannel =
                FrameworkAPI.createNetworkBuilder(new ResourceLocation("toyguns", "play"), 1).registerPlayMessage(CraftMessage.class, MessageDirection.PLAY_SERVER_BOUND).registerPlayMessage(UnjamMessage.class, MessageDirection.PLAY_SERVER_BOUND).build();
    }

    public static FrameworkNetwork getPlayChannel() {
        return playChannel;
    }
}