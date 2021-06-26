package co.uk.mrpineapple.toyguns.core.network.message;

import co.uk.mrpineapple.toyguns.core.network.Handler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Author: Mr. Pineapple
 */
public class UnjamMessage implements Message {

    @Override
    public void encode(PacketBuffer buffer) {}

    @Override
    public void decode(PacketBuffer buffer) {}

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayerEntity player = supplier.get().getSender();
            if(player != null && !player.isSpectator()) {
                Handler.handleUnjam(player);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
