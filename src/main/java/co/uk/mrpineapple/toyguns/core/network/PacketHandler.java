package co.uk.mrpineapple.toyguns.core.network;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.network.message.Message;
import co.uk.mrpineapple.toyguns.core.network.message.UnjamMessage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Supplier;

/**
 * Author: Mr. Pineapple
 */
public class PacketHandler {
    public static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel handshakeChannel;
    private static SimpleChannel playChannel;
    private static int nextMessageId = 0;

    public static void init() {
        playChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ToyGuns.ID, "play"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();

        registerPlay(UnjamMessage.class, UnjamMessage::new, LogicalSide.SERVER);
    }

    /**
     *  Register a message to the play channel
     *
     * @param clazz class of the message
     * @param side side the message should be handled
     * @param <T> first param - to implement {@link Message}
     */
    private static <T extends Message> void registerPlay(Class<T> clazz, Supplier<T> messageSupplier, LogicalSide side) {
        playChannel.registerMessage(nextMessageId++, clazz, Message::encode, buffer -> {
            T t = messageSupplier.get();
            t.decode(buffer);
            return t;
        }, (t, supplier) -> {
            if(supplier.get().getDirection().getReceptionSide() != side)
                throw new RuntimeException("Attempted to handle message " + clazz.getSimpleName() + " on the wrong logical side!");
            t.handle(supplier);
        });
    }

    //Gets the play network channel for the mod
    public static SimpleChannel getPlayChannel() {
        return playChannel;
    }

    //Gets the handshake network channel for the mod
    public static SimpleChannel getHandshakeChannel() {
        return handshakeChannel;
    }
}
