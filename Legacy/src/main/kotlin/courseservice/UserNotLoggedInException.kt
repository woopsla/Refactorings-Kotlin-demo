package courseservice

class UserNotLoggedInException : RuntimeException {
    constructor() : super()

    constructor(message: String?) : super(message)
}
