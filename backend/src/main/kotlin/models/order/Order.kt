package models.order

import models.pizza.Pizza

class Order(val size: Size, val dough: Dough, pizza: Pizza)