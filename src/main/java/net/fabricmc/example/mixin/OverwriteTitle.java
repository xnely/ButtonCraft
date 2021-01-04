package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.util.Identifier;

@Mixin(TitleScreen.class)
public class OverwriteTitle {
    @SuppressWarnings("unused")
    private static final Identifier MINECRAFT_TITLE_TEXTURE = new Identifier("modid:textures/gui/title/minecraftbutton.png");
}
