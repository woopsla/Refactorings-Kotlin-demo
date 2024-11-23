package dry03.introduce.polymorphic.creation.with.factory.method.before

abstract class SSMobileProduct(var price: Double = 0.0) {
    abstract fun prepare()
    abstract fun bundle()
    abstract fun label()
}

class SSMobileBolt : SSMobileProduct() {
    override fun prepare() = println("SSMobileBolt prepare ...")
    override fun bundle() = println("SSMobileBolt bundle ...")
    override fun label() = println("SSMobileBolt label ...")
}

class SSMobileThunder : SSMobileProduct() {
    override fun prepare() = println("SSMobileThunder prepare ...")
    override fun bundle() = println("SSMobileThunder bundle ...")
    override fun label() = println("SSMobileThunder label ...")
}

class SSMobilePlus : SSMobileProduct() {
    override fun prepare() = println("SSMobileBolt prepare ...")
    override fun bundle() = println("SSMobileBolt bundle ...")
    override fun label() = println("SSMobileBolt label ...")
}




