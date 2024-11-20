package dry03.introduce.polymorphic.creation.with.factory.method.work

/*
 * Produces "Thunder" and "Plus"
 */
class TokyoMobileFactory {
    fun of(model: String): SSMobileProduct {
        lateinit var mobile: SSMobileProduct

        if (model.equals("Thunder", ignoreCase = true)) {
            mobile = SSMobileThunder()
            mobile.price = 500.0
        } else if (model.equals("Plus", ignoreCase = true)) {
            mobile = SSMobilePlus()
            mobile.price = 250.0
        }

        mobile.prepare()
        mobile.bundle()
        mobile.label()

        return mobile
    }
}
