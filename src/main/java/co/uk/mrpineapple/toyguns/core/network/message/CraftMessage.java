package co.uk.mrpineapple.toyguns.core.network.message;

import co.uk.mrpineapple.toyguns.core.network.Handler;
import com.mrcrayfish.framework.api.network.PlayMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class CraftMessage extends PlayMessage<CraftMessage> {
    private ResourceLocation id;
    private BlockPos pos;

    public CraftMessage() {}

    public CraftMessage(ResourceLocation id, BlockPos pos) {
        this.id = id;
        this.pos = pos;
    }

    @Override
    public void encode(CraftMessage craftMessage, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeResourceLocation(craftMessage.id);
        friendlyByteBuf.writeBlockPos(craftMessage.pos);
    }

    @Override
    public CraftMessage decode(FriendlyByteBuf friendlyByteBuf) {
        return new CraftMessage(friendlyByteBuf.readResourceLocation(), friendlyByteBuf.readBlockPos());
    }

    @Override
    public void handle(CraftMessage craftMessage, Supplier<Context> supplier) {
        ((Context) supplier.get()).enqueueWork(() -> {
            ServerPlayer player = ((Context) supplier.get()).getSender();
            if(player != null) {
                Handler.handleCraft(player, craftMessage.id, craftMessage.pos);
            }
        });
        ((Context) supplier.get()).setPacketHandled(true);
    }
}
