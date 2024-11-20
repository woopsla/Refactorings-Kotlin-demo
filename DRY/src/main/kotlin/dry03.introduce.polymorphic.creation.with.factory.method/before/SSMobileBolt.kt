package dry03.introduce.polymorphic.creation.with.factory.method.before

class SSMobileBolt : SSMobileProduct() {
    override fun prepare() {
        println("SSMobileBolt prepare ...")
    }

    override fun bundle() {
        println("SSMobileBolt bundle ...")
    }

    override fun label() {
        println("SSMobileBolt label ...")
    }
}
