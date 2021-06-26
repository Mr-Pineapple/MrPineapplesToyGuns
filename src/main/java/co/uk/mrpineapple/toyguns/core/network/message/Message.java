package co.uk.mrpineapple.toyguns.core.network.message;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface Message {
    void encode(PacketBuffer buffer);
    void decode(PacketBuffer buffer);
    void handle(Supplier<NetworkEvent.Context> supplier);
}
