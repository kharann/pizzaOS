package models.pizza

enum class Sauce {
    TOMATO, BBQ, TACO;

    override fun toString(): String {
        return super.toString() + "-SAUCE"
    }
}