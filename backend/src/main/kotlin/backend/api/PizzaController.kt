package backend.api

import backend.models.pizza.Cheese
import backend.models.pizza.Pizza
import backend.models.pizza.Sauce
import backend.models.pizza.Topping
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.Query
import com.google.firebase.cloud.FirestoreClient
import java.util.concurrent.atomic.AtomicInteger

class PizzaController {
    private val collection = FirestoreClient.getFirestore().collection("pizzas")
    private var lastId: AtomicInteger = getLastId(collection)

    fun getAll(): List<Pizza> {
        val future = collection.get()
        return future.get().documents.map { docRef -> docRef.toObject(Pizza::class.java) }
            .also { pizzas -> println(pizzas) }
    }

    fun findById(id: Int): Pizza? {
        val future = collection.document("pizza$id").get()
        val docRef = future.get()
        return if (docRef.exists()) docRef.toObject(Pizza::class.java).also { pizza ->
            println(pizza)
        } else null
    }

    fun save(name: String?, sauce: Sauce, cheese: Cheese, toppings: List<Topping>) {
        val id = lastId.incrementAndGet()
        val pizza = hashMapOf(
            "name" to "$name",
            "sauce" to "$sauce",
            "cheese" to "$cheese",
            "toppings" to toppings.map { topping -> "$topping" },
            "id" to id
        )
        collection.document("pizza$id")
            .set(pizza)
            .also { println("Saved pizza with the id of $id") }
    }

    fun update(id: Int, pizza: Pizza) {
        val data = hashMapOf(
            "name" to "${pizza.name}",
            "sauce" to "${pizza.sauce}",
            "cheese" to "${pizza.cheese}",
            "toppings" to pizza.toppings.map { topping -> "$topping" }
        )
        collection.document("pizza$id")
            .set(data)
            .also { println("Updated pizza with the id of $id") }
    }

    fun delete(id: Int) {
        collection.document("pizza$id").delete()
            .also { println("Deleted pizza with the id of $id") }
    }
}

fun getLastId(collection: CollectionReference): AtomicInteger {
    val query = collection.orderBy("Time", Query.Direction.DESCENDING).limit(1)
    val docRef = query.get().get().documents
    val id = if (docRef.isNotEmpty()) {
        docRef[0].getString("id")?.toInt()
    } else null
    return AtomicInteger(id ?: 0)
}
