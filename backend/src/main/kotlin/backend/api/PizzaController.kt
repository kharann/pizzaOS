package backend.api

import backend.models.pizza.Cheese
import backend.models.pizza.Pizza
import backend.models.pizza.Sauce
import backend.models.pizza.Topping
import java.util.concurrent.atomic.AtomicInteger

class PizzaController {

    val pizzas = hashMapOf(
        1 to Pizza(
            "Margharita", Sauce.TOMATO, Cheese.MOZERELLA,
            listOf(), 1
        ),
        2 to Pizza(
            "Double Pepperoni", Sauce.TOMATO, Cheese.MOZERELLA,
            listOf(Topping.PEPPERONI, Topping.PEPPERONI), 2
        ),
        3 to Pizza(
            "Tennesee BBQ Chicken", Sauce.BBQ, Cheese.MOZERELLA,
            listOf(Topping.MARINATED_CHICKEN, Topping.ONION, Topping.HAM), 3
        ),
        4 to Pizza(
            "Kebaben", Sauce.TACO, Cheese.MOZERELLA,
            listOf(Topping.KEBAB_MEAT, Topping.CORN, Topping.JALEPENOS, Topping.PAPRIKA, Topping.TORTILLA_CHIPS), 4
        ),
        5 to Pizza(
            "Sopp Bacon Spesial", Sauce.TOMATO, Cheese.MOZERELLA,
            listOf(Topping.MARINATED_BEEF, Topping.MUSHROOM, Topping.BACON), 5
        )

    )
    private var lastId: AtomicInteger = AtomicInteger(pizzas.size - 1)

    fun save(name: String?, sauce: Sauce, cheese: Cheese, topping: List<Topping>) {
        val id = lastId.incrementAndGet()
        pizzas[id] = Pizza(name, sauce, cheese, topping)
    }

    fun findById(id: Int): Pizza? {
        return pizzas[id]
    }

    fun update(id: Int, pizza: Pizza) {
        pizzas[id] = Pizza(pizza.name, pizza.sauce, pizza.cheese, pizza.toppings, id)
    }

    fun delete(id: Int) {
        pizzas.remove(id)
    }
}
