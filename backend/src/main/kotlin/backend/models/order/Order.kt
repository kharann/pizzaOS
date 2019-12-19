package backend.models.order

import backend.models.pizza.Pizza

class Order(val size: Size, val dough: Dough, val pizza: Pizza) {
    override fun toString(): String {
        return "$size $dough $pizza"
    }
}
