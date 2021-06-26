package co.uk.mrpineapple.toyguns.client;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;


public class KeyBinds {
    public static final KeyBinding KEY_UNJAM = new KeyBinding("key." + ToyGuns.ID + ".unjam_gun", GLFW.GLFW_KEY_V, "key.categories." + ToyGuns.ID);

    public static void register() {
        ClientRegistry.registerKeyBinding(KEY_UNJAM);
    }
}
