package dry03.introduce.polymorphic.creation.with.factory.method.before

abstract class SSMobileProduct {
    @JvmField
    var price: Double = 0.0

    abstract fun prepare()
    abstract fun bundle()
    abstract fun label()
}
