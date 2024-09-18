package co.uk.mrpineapple.toyguns.core.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import com.mrcrayfish.guns.common.network.ServerPlayHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

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
    public void handle(CraftMessage craftMessage, MessageContext context) {
        context.execute(() -> {
            ServerPlayer player= context.getPlayer();
            if(player != null) {
                ServerPlayHandler.handleCraft(player, craftMessage.id, craftMessage.pos);
            }
        });
        context.setHandled(true);
    }
}
