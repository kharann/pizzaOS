import backend.models.order.Dough
import backend.models.order.Size
import backend.models.pizza.Cheese
import backend.models.pizza.Pizza
import backend.models.pizza.PizzaType
import backend.models.pizza.Sauce
import backend.models.pizza.Topping
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PizzaTests {
  private val pizzaType = PizzaType("TestPizza", toppings = listOf(Topping.BACON, Topping.MARINATED_CHICKEN))
  private val pizza = Pizza(pizzaType, Dough.ITALIAN, Size.LARGE)

  @Test
  fun defaultPizzaTypeConstructorTest() {
    assertEquals(pizzaType.sauce, Sauce.TOMATO, "Sauce should be tomato")
    assertEquals(pizzaType.cheese, Cheese.MOZERELLA, "Cheese should be mozerella")
  }

  @Test
  fun pizzaPriceTest() {
    val expectedPizzaTypeValue: Int =
      Topping.BACON.value + Topping.MARINATED_CHICKEN.value + Sauce.TOMATO.value + Cheese.MOZERELLA.value
    assertEquals(pizzaType.value, expectedPizzaTypeValue, "PizzaType value should be $expectedPizzaTypeValue")

    val expectedPizzaPrice: Int = expectedPizzaTypeValue + pizza.size.value
    assertEquals(pizza.getPrice(), expectedPizzaPrice, "Price should be $expectedPizzaPrice")
  }
}
