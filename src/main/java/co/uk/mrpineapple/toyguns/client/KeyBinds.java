package co.uk.mrpineapple.toyguns.client;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;


public class KeyBinds {
    public static final KeyMapping KEY_UNJAM = new KeyMapping("key." + ToyGuns.ID + ".unjam_gun", GLFW.GLFW_KEY_V, "key.categories." + ToyGuns.ID);

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(KEY_UNJAM);
    }
}
