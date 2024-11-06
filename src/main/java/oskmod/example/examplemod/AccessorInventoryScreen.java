package oskmod.example.examplemod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * This interface is a mixin for the Screen class that grants access to
 * the addRenderableWidget method, allowing for the addition of Button
 * widgets to the Screen.
 *
 * The invokeAddRenderableWidget method is an accessor method intended to
 * call the private/protected addRenderableWidget method from within the
 * Screen class.
 */
@Mixin(Screen.class)
public interface AccessorInventoryScreen {
    @Accessor("addRenderableWidget")
    void invokeAddRenderableWidget(Button button);
}