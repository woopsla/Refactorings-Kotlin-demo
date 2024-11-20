package dip04.replace.sate.conditional.with.state.work

abstract class PermissionState {
    abstract fun claimedBy(systemPermission: SystemPermission, admin: SystemAdmin)
    abstract fun deniedBy(systemPermission: SystemPermission, admin: SystemAdmin)
    abstract fun grantedBy(systemPermission: SystemPermission, admin: SystemAdmin)

    companion object {
        val REQUESTED: PermissionState = RequestedState()
        val CLAIMED: PermissionState = ClaimedState()
        val GRANTED: PermissionState = GrantedState()
        val DENIED: PermissionState = DeniedState()
    }
}

class RequestedState : PermissionState() {
    override fun claimedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
        systemPermission.willBeHandledBy(admin)
        systemPermission.state = CLAIMED
    }

    override fun deniedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }

    override fun grantedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }
}

class ClaimedState : PermissionState() {
    override fun claimedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }

    override fun deniedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
        if (systemPermission.admin != admin) return
        systemPermission.isGranted = false
        systemPermission.state = DENIED
        systemPermission.notifyUserOfPermissionRequestResult()
    }

    override fun grantedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
        if (systemPermission.admin != admin) return
        systemPermission.state = GRANTED
        systemPermission.isGranted = true
        systemPermission.notifyUserOfPermissionRequestResult()
    }
}

class GrantedState : PermissionState() {
    override fun claimedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }

    override fun deniedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }

    override fun grantedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }
}

class DeniedState : PermissionState() {
    override fun claimedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }

    override fun deniedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }

    override fun grantedBy(systemPermission: SystemPermission, admin: SystemAdmin) {
    }
}