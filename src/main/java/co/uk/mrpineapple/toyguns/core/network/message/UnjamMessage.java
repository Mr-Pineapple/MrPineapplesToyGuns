package co.uk.mrpineapple.toyguns.core.network.message;

import co.uk.mrpineapple.toyguns.core.network.Handler;
import com.mrcrayfish.framework.api.network.PlayMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Author: Mr. Pineapple
 */
public class UnjamMessage extends PlayMessage<UnjamMessage> {

    public UnjamMessage() {}

    @Override
    public void encode(UnjamMessage message, FriendlyByteBuf buffer) {}

    @Override
    public UnjamMessage decode(FriendlyByteBuf buffer) {
        return new UnjamMessage();
    }

    @Override
    public void handle(UnjamMessage message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if(player != null && !player.isSpectator()) {
                Handler.handleUnjam(player);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
