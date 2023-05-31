import asteroidsystem.AsteroidController;
import common.services.IEntityProcessingService;
import common.services.IGamePluginService;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with asteroidsystem.AsteroidPlugin;
    provides IEntityProcessingService with AsteroidController;

}