# ButtonCraft

## Description

Do you have fun playing Minecraft? Maybe a little too much? ButtonCraft aims to fix this problem.

ButtonCraft exploits the human psyche to make leveling an exceptionally frustrating grind. Firstly, it removes XP orbs normally obtained by killing mobs, mining, smelting, etc. The only way to obtain XP in ButtonCraft is to press any physical button in the game. This includes any wood button, the stone button, and the newly added diamond and netherite buttons. In general, buttons will most often do nothing, less often give a small amount of XP, and least commonly, remove a large amount of XP (possibly going into the negative). This is done to make losing XP more frustrating. The behavior of these different buttons can be seen below.

- Wood: 20 tick press time, expected value of -.03 (bad idea to press too much)</br>
      20% chance to lose 5 XP</br>
      10% chance to gain 7 XP</br>
      70% chance to do nothing
- Stone: 40 tick press time, expected value of 0</br>
      1% chance to lose 100 XP</br>
      10% chance to gain 10 XP</br>
      89% chance to do nothing
- Diamond: 80 tick press time, expected value of 5</br>
      1% chance to lose 1000 XP</br>
      15% chance to gain 100 XP</br>
      84% chance to do nothing
- Netherite: 160 tick press time, expected value of 25</br>
      1% chance to lose 10000 XP</br>
      15% chance to gain 250 XP</br>
      84% chance to do nothing

## Instalation

Download the <a src="https://github.com/xnely/mods/releases/download/1.0.0/fabric-example-mod-1.0.0.jar">.jar</a> and place it into the /mods folder of your Minecraft installation. Make sure to have Fabric for 1.16 installed, and that you download and place the <a src="https://www.curseforge.com/minecraft/mc-mods/fabric-api/files">Fabric API</a> in your mods folder.
