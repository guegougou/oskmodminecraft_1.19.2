package oskmod.example.examplemod;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * ExempleMixin is designed to inject behavior into the Minecraft class.
 * Specifically, it intercepts the execution of the run method to provide
 * custom behavior or logging at the beginning of the method.
 *
 * The onRun method is injected at the "HEAD" of the run method, meaning it
 * will execute before any original code in the run method. The primary behavior
 * added in this mixin is a print statement to notify when the game has started.
 *
 * This can be useful for debugging or extending game behavior without modifying
 * the original source code directly.
 */
@Mixin(Minecraft.class)
public class ExempleMixin {

    @Inject(method = "run", at = @At("HEAD"))
    private void onRun(CallbackInfo info) {
        System.out.println("Le jeu a démarré !");
    }
}