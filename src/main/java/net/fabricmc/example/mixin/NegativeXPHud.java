package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public abstract class NegativeXPHud extends DrawableHelper {

   protected NegativeXPHud(){
      this.client = MinecraftClient.getInstance();
      throw new AssertionError();
   }
    
    @Shadow private final MinecraftClient client;
    @Shadow private int scaledHeight;
    @Shadow private int scaledWidth;

    @Shadow public abstract TextRenderer getFontRenderer();

    @Overwrite public void renderExperienceBar(MatrixStack matrices, int x) {
        this.client.getProfiler().push("expBar");
        this.client.getTextureManager().bindTexture(DrawableHelper.GUI_ICONS_TEXTURE);
        int i = this.client.player.getNextLevelExperience();
        int m;
        int n;
        if (i > 0) {
           m = (int)(this.client.player.experienceProgress * 183.0F);
           n = this.scaledHeight - 32 + 3;
           this.drawTexture(matrices, x, n, 0, 64, 182, 5);
           if (m > -100) { // was 0
              this.drawTexture(matrices, x, n, 0, 69, m, 5);
           }
        }
        this.client.getProfiler().pop();
      if (this.client.player.experienceLevel > -1000000) { // Was 0
         this.client.getProfiler().push("expLevel");
         String string = "" + this.client.player.experienceLevel;
         m = (this.scaledWidth - this.getFontRenderer().getWidth(string)) / 2;
         n = this.scaledHeight - 31 - 4;
         this.getFontRenderer().draw(matrices, (String)string, (float)(m + 1), (float)n, 0);
         this.getFontRenderer().draw(matrices, (String)string, (float)(m - 1), (float)n, 0);
         this.getFontRenderer().draw(matrices, (String)string, (float)m, (float)(n + 1), 0);
         this.getFontRenderer().draw(matrices, (String)string, (float)m, (float)(n - 1), 0);
         this.getFontRenderer().draw(matrices, string, (float)m, (float)n, 8453920);
         this.client.getProfiler().pop();
      }

   }
}
