# Food types

## Abstract

This represents different food nutrient calculation for 2 dimensions:
* Loose - discrete
  * Loose: Some foods can be of loose amount, e.g. rice, potatoes, etc.
  * Discrete: Some foods only come in set amounts, e.g. a liter of milk, 300g loaf of bread, etc.
* Singular - compound
  * Singular: Some foods have only themselves as the only ingredient, e.g. cucumbers, tomatoes, etc.
  * Compound: Some foods are a mix of ingredients, e.g. some salad that contains cucumbers and tomatoes.


## Interfaces

Each of the 4 dimensional-extremes have their own corresponding interface:
* `LooseFoodInterface`
* `DiscreteFoodInterface`
* `SingleFoodInterface`
* `CompoundFoodInterface`

All of them extend a common `FoodInterface`.


## Classes

Depending on the combination of the 2 dimensions, 4 food types arise:

|              | **Loose**         | **Discrete**         |
|:------------:|:------------------|:---------------------|
| **Singular** | SingleLooseFood   | SingleDiscreteFood   |
| **Compound** | CompoundLooseFood | CompoundDiscreteFood |

All of these types also extend a package-private `AbstractFood`.

