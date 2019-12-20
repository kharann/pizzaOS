package backend.models.order

import backend.models.pizza.Pizza

class Order(private val pizza: Pizza, private val delieveryFee: Int = 0) {
  private val price: Int = pizza.getPrice() + delieveryFee
}
