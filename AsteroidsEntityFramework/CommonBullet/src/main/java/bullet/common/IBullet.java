package bullet.common;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface IBullet {
    Entity createBullet(Entity e, GameData gameData);
}
