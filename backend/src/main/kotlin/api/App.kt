/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package api

import io.javalin.Javalin

fun main() {
    val app = Javalin.create { config -> config.enableWebjars() }.start(7070)
    apiRoutes(app)
    app.get("/") { ctx -> ctx.result("Hello World") }


}
