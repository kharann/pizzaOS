package api

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import models.pizza.Pizza

enum class AppRole : Role { ANYONE, LOGGED_IN, ADMIN }

fun apiRoutes(app: Javalin) {
    val pizzaDao = PizzaController()
    app.routes {
        path("/api") {
            path("/pizzas") {
                get { ctx -> ctx.json(pizzaDao.pizzas) }
                post { ctx ->
                    val pizza = ctx.body<Pizza>()
                    pizzaDao.save(pizza.name, pizza.sauce, pizza.cheese, pizza.toppings)
                    ctx.status(201)
                }
                path(":pizza-id") {
                    get { ctx -> pizzaDao.findById(ctx.pathParam("pizza-id").toInt()) }
                    patch { ctx ->
                        pizzaDao.update(ctx.pathParam("pizza-id").toInt(), ctx.body<Pizza>())
                        ctx.status(204)
                    }
                    delete { ctx ->
                        pizzaDao.delete(ctx.pathParam("pizza-id").toInt())
                        ctx.status(204)
                    }
                }

            }
        }
    }
}