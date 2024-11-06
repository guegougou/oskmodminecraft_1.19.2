package oskmod.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This mixin class modifies the InventoryScreen class to add a new button that opens
 * an on-screen keyboard (OSK) when clicked.
 */
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin {
    private Button oskButton;

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        InventoryScreen screen = (InventoryScreen) (Object) this;
        int buttonX = (screen.width / 2) - 50;
        int buttonY = screen.height / 2 + 70;

        this.oskButton = new Button(buttonX, buttonY, 100, 20, Component.literal("Ouvrir OSK"), button -> openOSK());
        this.addRenderableWidget(oskButton);
    }

    private void openOSK() {
        Minecraft.getInstance().setScreen(null); // Ferme l'inventaire
        Minecraft.getInstance().setScreen(new OSKScreen()); // Affiche l'OSK
    }
}