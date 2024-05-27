package com.mygdx.vampiresurvivors.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.mygdx.vampiresurvivors.ecs.Mappers;
import com.mygdx.vampiresurvivors.ecs.component.BoundsComponent;
import com.mygdx.vampiresurvivors.ecs.component.PositionComponent;
import com.mygdx.vampiresurvivors.ecs.component.VelocityComponent;

public class BounceInBoundsSystem extends IteratingSystem {

  private final float screenWidth;
  private final float screenHeight;

  public BounceInBoundsSystem(final float screenWidth, final float screenHeight) {
    super(Family.all(BoundsComponent.class, PositionComponent.class, VelocityComponent.class).get());

    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;

    Gdx.app.log("BounceInBoundsSystem", "screenWidth: " + screenWidth + ", screenHeight: " + screenHeight);
  }

  @Override
  public void processEntity(final Entity entity, final float deltaTime) {
    final BoundsComponent boundsComponent = Mappers.bounds.get(entity);
    final PositionComponent positionComponent = Mappers.position.get(entity);
    final VelocityComponent velocityComponent = Mappers.velocity.get(entity);

    if (positionComponent.position.x < 0 || positionComponent.position.x + boundsComponent.width > screenWidth) {
      velocityComponent.velocity.x = velocityComponent.velocity.x * -1;
    }

    if (positionComponent.position.y < 0 || positionComponent.position.y + boundsComponent.height > screenHeight) {
      velocityComponent.velocity.y = velocityComponent.velocity.y * -1;
    }
  }
}
