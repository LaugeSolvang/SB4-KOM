import common.services.IEntityProcessingService;
import common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonBullet;
    uses bullet.common.IBullet;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
}