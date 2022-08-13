package co.uk.mrpineapple.toyguns.core.network;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.network.message.CraftMessage;
import co.uk.mrpineapple.toyguns.core.network.message.UnjamMessage;
import com.mrcrayfish.framework.api.network.FrameworkChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * Author: Mr. Pineapple
 */
public class PacketHandler {
    private static final SimpleChannel PLAY_CHANNEL = FrameworkChannelBuilder
            .create(ToyGuns.ID, "play", 1)
            .registerPlayMessage(UnjamMessage.class, NetworkDirection.PLAY_TO_SERVER)
            .registerPlayMessage(CraftMessage.class, NetworkDirection.PLAY_TO_SERVER)
            .build();

    //Gets the play network channel for the mod
    public static SimpleChannel getPlayChannel() {
        return PLAY_CHANNEL;
    }

    public static void init() {}

}