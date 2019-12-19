package backend.api

interface CrudController<T> {
  fun getAll(): List<T>
  fun getOne(id: Int): T?
  fun update(id: Int, item: T)
  fun delete(id: Int)
  fun create(item: T)
}
