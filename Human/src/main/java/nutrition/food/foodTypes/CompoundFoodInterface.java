package nutrition.food.foodTypes;

import nutrition.food.FoodInterface;

//
public interface CompoundFoodInterface extends FoodInterface {
    //recalculates base nutritional value & etc
    void update();
}