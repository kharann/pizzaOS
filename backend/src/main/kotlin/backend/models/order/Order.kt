package backend.models.order

import backend.models.pizza.Pizza

class Order(private val size: Size, private val dough: Dough, private val pizza: Pizza) {
    private val price: Int = size.value + pizza.getPrice()

    override fun toString(): String {
        return "size: $size dough: $dough pizza: $pizza price: $price"
    }
}
