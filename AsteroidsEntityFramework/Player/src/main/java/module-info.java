import common.services.IEntityProcessingService;
import common.services.IGamePluginService;

import playersystem.PlayerController;
import playersystem.PlayerPlugin;

module Player {
    requires Common;
    requires CommonBullet;
    uses bullet.common.IBullet;
    provides IEntityProcessingService with PlayerController;
    provides IGamePluginService with PlayerPlugin;
}