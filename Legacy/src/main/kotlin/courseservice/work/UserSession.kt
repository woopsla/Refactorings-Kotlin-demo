package courseservice.work

import courseservice.CollaboratorCallException

class UserSession private constructor() {
    fun getLoggedUser(): User? {
        throw CollaboratorCallException(
            "UserSession.getLoggedUser() should not be called in an unit test"
        )
    }

    companion object {
        private var INSTANCE: UserSession? = null

        fun getInstance(): UserSession {
            return INSTANCE ?: UserSession().also { INSTANCE = it }
        }
    }
}

