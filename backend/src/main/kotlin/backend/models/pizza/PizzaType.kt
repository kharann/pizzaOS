package backend.models.pizza

data class PizzaType(
  val name: String? = null,
  val sauce: Sauce = Sauce.TOMATO,
  val cheese: Cheese = Cheese.MOZERELLA,
  val toppings: List<Topping> = listOf(),
  val id: Int = 0
) {

  val value: Int = sauce.value + cheese.value +
    toppings.fold(0) { sum, topping -> sum + topping.value }

  override fun toString(): String {
    return "name: $name, sauce: $sauce, cheese: $cheese, toppings: $toppings"
  }
}
