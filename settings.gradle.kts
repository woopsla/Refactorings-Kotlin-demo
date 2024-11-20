plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "Refactorings-Kotlin-demo"
include("DIP")
include("SCP")
include("LowCoupling")
include("SRP")
include("Idiomatic")
include("DRY")
include("Fowler")
include("Legacy")
