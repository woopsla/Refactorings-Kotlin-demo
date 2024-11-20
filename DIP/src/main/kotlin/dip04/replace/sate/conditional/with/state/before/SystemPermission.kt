/*
 * SMELL: Switch Statement
 *   -- The conditional expressions that control an object’s 
 *      state transitions are complex. 
 *
 * TREATMENT: Replace State-Altering Conditionals with State
 *   -- Replace the conditionals with State classes that handle 
 *      specific states and transitions between them. 
 */
package dip04.replace.sate.conditional.with.state.before
//
//              claimedBy              grantedBy
//  Requested -------------> Claimed -----------> Granted
//                              |
//                              +----------------> Denied
//                                   deniedBy
//

class SystemUser
class SystemProfile
class SystemAdmin

class SystemPermission(
    private val requester: SystemUser,
    private val profile: SystemProfile,
    private val admin: SystemAdmin
) {
    var isGranted: Boolean = false
    var state: String

    init {
        state = REQUESTED
        notifyAdminOfPermissionRequest()
    }

    fun claimedBy(admin: SystemAdmin) {
        if (state != REQUESTED) return
        willBeHandledBy(admin)
        state = CLAIMED
    }

    fun deniedBy(admin: SystemAdmin) {
        if (state != CLAIMED) return
        if (this.admin != admin) return
        isGranted = false
        state = DENIED
        notifyUserOfPermissionRequestResult()
    }

    fun grantedBy(admin: SystemAdmin) {
        if (state != CLAIMED) return
        if (this.admin != admin) return
        state = GRANTED
        isGranted = true
        notifyUserOfPermissionRequestResult()
    }

    private fun willBeHandledBy(admin: SystemAdmin) {}

    private fun notifyAdminOfPermissionRequest() {}

    private fun notifyUserOfPermissionRequestResult() {}

    companion object {
        const val REQUESTED: String = "REQUESTED"
        const val CLAIMED: String = "CLAIMED"
        const val GRANTED: String = "GRANTED"
        const val DENIED: String = "DENIED"
    }
}
