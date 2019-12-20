package backend.models.pizza

import backend.models.order.Dough
import backend.models.order.Size

class Pizza(private val pizzaType: PizzaType, private val dough: Dough, val size: Size) {

  fun getPrice(): Int {
    return size.value + pizzaType.value
  }

  override fun toString(): String {
    return "size: $size dough: $dough pizza: $pizzaType price: ${getPrice()}"
  }
}
