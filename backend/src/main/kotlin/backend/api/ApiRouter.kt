package backend.api

import backend.models.pizza.Pizza
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.delete
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.patch
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.core.security.Role

enum class AppRole : Role { ANYONE, LOGGED_IN, ADMIN }

fun apiRoutes(app: Javalin) {
    val pizzaController = PizzaController()
    app.routes {
        path("api") {
            path("/pizzas") {
                get { ctx -> ctx.json(pizzaController.getAll()) }
                post { ctx ->
                    val pizza = ctx.body<Pizza>()
                    pizzaController.create(pizza)
                    ctx.status(201)
                }
                path(":pizza-id") {
                    get { ctx -> pizzaController.getOne(ctx.pathParam("pizza-id").toInt())?.let { ctx.json(it) } }
                    patch { ctx ->
                        pizzaController.update(ctx.pathParam("pizza-id").toInt(), ctx.body<Pizza>())
                        ctx.status(204)
                    }
                    delete { ctx ->
                        pizzaController.delete(ctx.pathParam("pizza-id").toInt())
                        ctx.status(204)
                    }
                }
            }
        }
    }
}
