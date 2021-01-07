package net.fabricmc.example;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class NetheriteButton extends AbstractButtonBlock{

    protected NetheriteButton(AbstractBlock.Settings settings) {
        super(false, settings);
     }
  
     protected SoundEvent getClickSound(boolean powered) {
        return powered ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
     }
  
     protected int getType(){
     return 2;
     }

     protected int getPressTicks() {
      return 120;
   }
}
