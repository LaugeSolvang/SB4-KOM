import common.services.IEntityProcessingService;
import common.services.IGamePluginService;

import enemysystem.EnemyController;
import enemysystem.EnemyPlugin;

module Enemy {
    requires Common;
    requires CommonEnemy;
    provides IEntityProcessingService with EnemyController;
    provides IGamePluginService with EnemyPlugin;
}