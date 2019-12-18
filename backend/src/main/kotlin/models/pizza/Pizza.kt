package models.pizza

data class Pizza(val name: String? = null, val sauce: Sauce, val cheese: Cheese, val toppings: List<Topping>, val id: Int? = null) {


    fun getPrice(): Int {
        return toppings.fold(0) { sum, topping -> sum + topping.value }
    }

    override fun toString(): String {
        return "Pizza: $name"
    }
}

fun main() {
}

