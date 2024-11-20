/*
 * SMELL: Switch Statement
 *   -- The conditional expressions that control an object’s 
 *      state transitions are complex. 
 *
 * TREATMENT: Replace State-Altering Conditionals with State
 *   -- Replace the conditionals with State classes that handle 
 *      specific states and transitions between them. 
 */
package dip04.replace.sate.conditional.with.state.work

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
    val admin: SystemAdmin
) {
    var isGranted: Boolean = false
    var state: PermissionState = PermissionState.REQUESTED

    init {
        notifyAdminOfPermissionRequest()
    }

    fun claimedBy(admin: SystemAdmin) {
        state.claimedBy(this, admin)
    }

    fun deniedBy(admin: SystemAdmin) {
        state.deniedBy(this, admin)
    }

    fun grantedBy(admin: SystemAdmin) {
        state.grantedBy(this, admin)
    }

    private fun notifyAdminOfPermissionRequest() {}
    fun willBeHandledBy(admin: SystemAdmin) {}
    fun notifyUserOfPermissionRequestResult() {}
}
