package bullet.common;

import common.data.Entity;
import common.data.GameData;

public interface IBullet {
    Entity createBullet(Entity e, GameData gameData);
}
