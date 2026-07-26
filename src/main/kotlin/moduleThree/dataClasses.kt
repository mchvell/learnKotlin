package moduleThree


data class ProductInfo(val id: Long, val name: String, val price: Int, val inStock: Boolean)

data class ApiResponse(val code: Int) {
    var body: String = ""
}