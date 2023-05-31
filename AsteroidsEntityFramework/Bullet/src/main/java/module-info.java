import bullet.common.IBullet;
import common.services.IEntityProcessingService;
import common.services.IGamePluginService;

module Bullet {
    requires Common;
    requires CommonBullet;
    provides IBullet with bulletsystem.BulletController;
    provides IGamePluginService with bulletsystem.BulletPlugin;
    provides IEntityProcessingService with bulletsystem.BulletController;
}