package co.uk.mrpineapple.toyguns.core.events;

import co.uk.mrpineapple.toyguns.client.KeyBinds;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.network.PacketHandler;
import co.uk.mrpineapple.toyguns.core.network.message.UnjamMessage;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import org.lwjgl.glfw.GLFW;

/**
 * Author: Mr. Pineapple
 */
@Mod.EventBusSubscriber(modid = ToyGuns.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InputEvent {
    @SubscribeEvent
    public static void onKeyPressed(KeyInputEvent event) {
        if(KeyBinds.KEY_UNJAM.isPressed() && event.getAction() == GLFW.GLFW_PRESS) {
            PacketHandler.getPlayChannel().sendToServer(new UnjamMessage());
        }
    }
}