module Core {
    requires Common;
    requires CommonEnemy;
    requires CommonBullet;
    requires CommonAsteroids;
    requires java.desktop;
    requires com.badlogic.gdx;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    exports dk.sdu.mmmi.cbse.main;
    opens dk.sdu.mmmi.cbse.main to spring.core;
    uses common.services.IGamePluginService;
    uses common.services.IEntityProcessingService;
    uses common.services.IPostEntityProcessingService;

}