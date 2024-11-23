/*
   Symptom: Classes in a hierarchy implement a method similarly
            except for an object creation step.

   Treatment: Make a single superclass version `produceMobile`
            that calls a Factory Method to handle the instantiation.
 */
package dry03.introduce.polymorphic.creation.with.factory.method.before

/*
 * Produces "Thunder" and "Bolt"
 */
class SeoulMobileFactory {
    fun produceMobile(model: String): SSMobileProduct {
        lateinit var mobile: SSMobileProduct

        if (model.equals("Thunder", ignoreCase = true)) {
            mobile = SSMobileThunder()
            mobile.price = 500.0
        } else if (model.equals("Bolt", ignoreCase = true)) {
            mobile = SSMobileBolt()
            mobile.price = 300.0
        } else {
            throw IllegalArgumentException("Unknown model: $model")
        }

        mobile.prepare()
        mobile.bundle()
        mobile.label()

        return mobile
    }
}
