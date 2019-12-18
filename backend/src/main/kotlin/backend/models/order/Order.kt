package backend.models.order

import backend.models.pizza.Pizza

class Order(val size: Size, val dough: Dough, pizza: Pizza)
