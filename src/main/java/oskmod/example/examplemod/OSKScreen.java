package oskmod.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.vertex.PoseStack;

import java.util.ArrayList;
import java.util.List;

/**
 * OSKScreen represents an on-screen keyboard interface in the game. It extends the
 * Screen class from Minecraft and provides a graphical interface for users to
 * input keys.
 */
public class OSKScreen extends Screen {
    private static final int BUTTON_WIDTH = 20;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 3;

    private final List<String> selectedKeys = new ArrayList<>(); // Stocker les touches sélectionnées

    private static final String[] KEYS = {
            "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", " ", // Espace
            "Enter", "Backspace" // Ajout des touches spéciales
    };

    public OSKScreen() {
        super(Component.literal("Clavier Virtuel"));
    }

    @Override
    protected void init() {
        super.init();
        int startX = 10; // Position X de départ
        int startY = 30; // Position Y de départ
        int x = startX;
        int y = startY;

        for (String key : KEYS) {
            Button button = new Button(
                    x, y, BUTTON_WIDTH, BUTTON_HEIGHT, Component.literal(key),
                    btn -> keyPressed(key)
            );

            this.addRenderableWidget(button);
            x += BUTTON_WIDTH + BUTTON_SPACING;

            if (x > this.width - BUTTON_WIDTH) {
                x = startX;
                y += BUTTON_HEIGHT + BUTTON_SPACING;
            }
        }

        // Ajouter le bouton "Valider" pour valider les touches sélectionnées
        int validateButtonX = (this.width / 2) - 50;
        int validateButtonY = this.height - 30;

        Button validateButton = new Button(validateButtonX, validateButtonY, 100, 20, Component.literal("Valider"), button -> validateKeys());
        this.addRenderableWidget(validateButton);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 5, 0xFFFFFF);
    }

    private void keyPressed(String key) {
        selectedKeys.add(key); // Ajouter la touche sélectionnée à la liste
    }

    private void validateKeys() {
        // Activer la répétition des événements clavier
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(true);

        // Simuler les touches sélectionnées
        for (String key : selectedKeys) {
            simulateKeyPress(key);
        }

        // Désactiver si besoin après utilisation
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);

        // Fermer l'écran après la validation
        Minecraft.getInstance().setScreen(null);
    }

    private void simulateKeyPress(String key) {
        // Logique pour simuler les pressions des touches
        Minecraft mc = Minecraft.getInstance();
        // Implémenter la logique de simulation des entrées de touches
        // Exemple simple : mc.keyboardHandler.onKeyPress(…); (Utiliser les codes de touches appropriés pour votre jeu)
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}