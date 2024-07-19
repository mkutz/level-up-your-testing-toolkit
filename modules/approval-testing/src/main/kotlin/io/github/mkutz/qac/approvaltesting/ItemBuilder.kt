package io.github.mkutz.qac.approvaltesting

class ItemBuilder {
    private var id: String? = null
    private var name: String? = null
    private var amount: Int? = null
    private var price: Price? = null

    fun id(id: String?): ItemBuilder {
        this.id = id
        return this
    }

    fun name(name: String?): ItemBuilder {
        this.name = name
        return this
    }

    fun amount(amount: Int): ItemBuilder {
        this.amount = amount
        return this
    }

    fun price(price: Price?): ItemBuilder {
        this.price = price
        return this
    }

    fun build(): Item {
        return Item(id!!, name!!, amount!!, price!!)
    }

    companion object {
        @JvmStatic
        fun anItem(): ItemBuilder {
            return ItemBuilder()
        }
    }
}
