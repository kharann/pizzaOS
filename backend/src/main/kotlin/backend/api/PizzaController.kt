package backend.api

import backend.models.pizza.Pizza
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.Query
import com.google.firebase.cloud.FirestoreClient
import java.util.concurrent.atomic.AtomicInteger

class PizzaController : CrudController<Pizza> {
    private val collection = FirestoreClient.getFirestore().collection("pizzas")
    private var lastId: AtomicInteger = getLastId(collection)

    override fun getAll(): List<Pizza> {
        val future = collection.get()
        return future.get().documents.map { docRef -> docRef.toObject(Pizza::class.java) }
            .also { pizzas -> println(pizzas) }
    }

    override fun getOne(id: Int): Pizza? {
        val future = collection.document("pizza$id").get()
        val docRef = future.get()
        return if (docRef.exists()) docRef.toObject(Pizza::class.java).also { pizza ->
            println(pizza)
        } else null
    }

    override fun create(item: Pizza) {
        val id = lastId.incrementAndGet()
        val pizza = hashMapOf(
            "name" to "${item.name}",
            "sauce" to "${item.sauce}",
            "cheese" to "${item.cheese}",
            "toppings" to item.toppings.map { topping -> "$topping" },
            "id" to id
        )
        collection.document("pizza$id")
            .set(pizza)
            .also { println("Saved pizza with the id of $id") }
    }

    override fun update(id: Int, item: Pizza) {
        val data = hashMapOf(
            "name" to "${item.name}",
            "sauce" to "${item.sauce}",
            "cheese" to "${item.cheese}",
            "toppings" to item.toppings.map { topping -> "$topping" }
        )
        collection.document("pizza$id")
            .set(data)
            .also { println("Updated pizza with the id of $id") }
    }

    override fun delete(id: Int) {
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