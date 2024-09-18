package co.uk.mrpineapple.toyguns.core.network.message;

import co.uk.mrpineapple.toyguns.core.network.Handler;
import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import com.mrcrayfish.guns.common.network.ServerPlayHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

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
    public void handle(UnjamMessage message, MessageContext context) {
        context.execute(() -> {
            ServerPlayer player = context.getPlayer();
            if(player != null && !player.isSpectator()) {
                Handler.handleUnjam(player);
            }
        });
        context.setHandled(true);

    }
}
