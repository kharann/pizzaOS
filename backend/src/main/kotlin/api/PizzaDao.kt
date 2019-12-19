package api

import java.util.concurrent.atomic.AtomicInteger
import models.pizza.Dough
import models.pizza.Pizza
import models.pizza.Size
import models.pizza.Topping

class PizzaDao {
    val pizzas = hashMapOf(
            0 to Pizza(Dough.AMERICAN, Size.MEDIUM, listOf(Topping.MOZERELLA, Topping.PAPRIKA, Topping.BACON)),
            1 to Pizza(Dough.AMERICAN, Size.MEDIUM, listOf(Topping.MOZERELLA, Topping.CHICKEN, Topping.HAM)),
            2 to Pizza(Dough.AMERICAN, Size.MEDIUM, listOf(Topping.CHEDDAR, Topping.CORN, Topping.BEEF))
    )

    var lastId: AtomicInteger = AtomicInteger(pizzas.size - 1)

    fun save(dough: Dough, size: Size, toppings: List<Topping>) {
        val id = lastId.incrementAndGet()
        pizzas.put(id, Pizza(dough, size, toppings, id))
    }

    fun findById(id: Int): Pizza? {
        return pizzas[id]
    }

    fun update(id: Int, pizza: Pizza) {
        pizzas.put(id, Pizza(pizza.dough, pizza.size, pizza.toppings, id))
    }

    fun delete(id: Int) {
        pizzas.remove(id)
    }
}
