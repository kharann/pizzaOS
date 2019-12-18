package api

import io.javalin.Javalin
import models.pizza.Pizza

fun main() {
    val pizzaDao = PizzaDao()
    val app = Javalin.create().start(7000)
    app.get("/api") { ctx -> ctx.result("Hello World") }

    app.get("/api/pizzas") { ctx ->
        ctx.json(pizzaDao.pizzas)
    }

    app.get("/api/pizzas/:pizza-id") { ctx -> pizzaDao.findById(ctx.pathParam("pizza-id").toInt()) }

    app.post("/api/pizzas") { ctx ->
        val pizza = ctx.body<Pizza>()
        pizzaDao.save(pizza.dough, pizza.size, pizza.toppings)
        ctx.status(201)
    }

    app.patch("/api/pizzas/:pizza-id") { ctx ->
        pizzaDao.update(ctx.pathParam("pizza-id").toInt(), ctx.body<Pizza>())
        ctx.status(204)
    }

    app.delete("/api/pizzas/:pizza-id") { ctx ->
        pizzaDao.delete(ctx.pathParam("pizza-id").toInt())
        ctx.status(204)
    }
}
