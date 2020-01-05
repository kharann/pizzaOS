package backend.api

import backend.models.pizza.PizzaType
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.patch
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing

fun Application.apiRoutes() {
  install(ContentNegotiation) {
    gson {
      setPrettyPrinting()
    }
  }
  routing {
    route("/") {
      get {
        call.respondText(
          "<a href='/pizzas'>pizzas</a><br>" +
            "<a href='/orders'>orders</a>", ContentType.Text.Html
        )
      }
    }
    pizzaRoutes()
  }
}

fun Routing.pizzaRoutes() {
  val pizzaController = PizzaController()
  route("/pizzas") {
    get { call.respond(pizzaController.getAll()) }
    post {
      val pizza = call.receive<PizzaType>()
      pizzaController.create(pizza)
      call.respond(201)
    }
    get("/{id}") {
      val pizza: PizzaType? = pizzaController.getOne(call.parameters["id"]!!.toInt())
      call.respond(pizza ?: 404)

    }
    patch("/{id}") {
      pizzaController.update(call.parameters["id"]!!.toInt(), call.receive<PizzaType>())
      call.respond(204)
    }
    delete("/{id}") {
      pizzaController.delete(call.parameters["id"]!!.toInt())
      call.respond(204)
    }
  }
}
