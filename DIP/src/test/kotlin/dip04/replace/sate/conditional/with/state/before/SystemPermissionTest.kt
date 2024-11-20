package dip04.replace.sate.conditional.with.state.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StateTests {
    private lateinit var admin: SystemAdmin
    private lateinit var permission: SystemPermission

    @BeforeEach
    fun setup() {
        admin = SystemAdmin()
        permission = SystemPermission(SystemUser(), SystemProfile(), admin)
    }

    @Test
    fun `claimedBy on requested state should change state`() {
        permission.claimedBy(admin)
        assertEquals(SystemPermission.CLAIMED, permission.state, "claimed")
        assertEquals(false, permission.isGranted, "not granted")
    }

    @Test
    fun `grantedBy on requested state should not change state`() {
        permission.grantedBy(admin)
        assertEquals(SystemPermission.REQUESTED, permission.state, "requested")
        assertEquals(false, permission.isGranted, "not granted")
    }

    @Test
    fun `grantedBy on claimed state should change state`() {
        permission.claimedBy(admin)
        permission.grantedBy(admin)
        assertEquals(SystemPermission.GRANTED, permission.state, "granted")
        assertEquals(true, permission.isGranted, "granted")
    }

    @Test
    fun `deniedBy on claimed state should change state`() {
        permission.claimedBy(admin)
        permission.deniedBy(admin)
        assertEquals(SystemPermission.DENIED, permission.state, "denied")
        assertEquals(false, permission.isGranted, "not granted")
    }

    @Test
    fun `grantedBy on claimed state with different admin should not change state`() {
        val admin = SystemAdmin()
        permission.claimedBy(admin)
        permission.grantedBy(admin)
        assertEquals(SystemPermission.CLAIMED, permission.state, "claimed")
        assertEquals(false, permission.isGranted, "not granted")
    }
}
