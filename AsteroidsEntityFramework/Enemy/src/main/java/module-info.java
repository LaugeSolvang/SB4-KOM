import common.services.IEntityProcessingService;
import common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonEnemy;
    provides IEntityProcessingService with enemysystem.EnemyControlSystem;
    provides IGamePluginService with enemysystem.EnemyPlugin;
}