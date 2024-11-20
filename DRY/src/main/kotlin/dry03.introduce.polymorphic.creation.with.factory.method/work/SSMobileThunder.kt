package dry03.introduce.polymorphic.creation.with.factory.method.work

class SSMobileThunder : SSMobileProduct() {
    override fun prepare() {
        println("SSMobileThunder prepare ...")
    }

    override fun bundle() {
        println("SSMobileThunder bundle ...")
    }

    override fun label() {
        println("SSMobileThunder label ...")
    }
}
