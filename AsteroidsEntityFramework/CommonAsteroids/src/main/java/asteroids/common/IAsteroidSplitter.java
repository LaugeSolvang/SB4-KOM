package asteroids.common;

import common.data.Entity;
import common.data.World;

public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w);
}
