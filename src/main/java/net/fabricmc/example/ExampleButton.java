package net.fabricmc.example;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class ExampleButton extends AbstractButtonBlock{ // Diamond button

   protected ExampleButton(AbstractBlock.Settings settings) {
      super(false, settings);
   }

   protected SoundEvent getClickSound(boolean powered) { // I was going to change this but then I realized I'm not a sound engineer
      return powered ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
   }

   protected int getType(){
   return 1;
   }

}
