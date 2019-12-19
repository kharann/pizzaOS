package models.pizza

data class Pizza(val dough: Dough, val size: Size, val toppings: List<Topping>, val id: Int? = null) {

    fun getPrice(): Int {
        return size.value + toppings.fold(0) { sum, topping -> sum + topping.value }
    }

    override fun toString(): String {
        return "Pizza: $dough, $size, $toppings"
    }
}

fun main() {
    val pizza: Pizza = Pizza(Dough.ITALIAN, Size.SMALL, listOf(Topping.CHEDDAR), 1)
    println(pizza.getPrice())
    println(pizza)
}
