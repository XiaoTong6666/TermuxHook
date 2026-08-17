// Top-level build file where you can add configuration options common to all sub-projects/modules.
fun runGitCommand(vararg args: String): String? =
    runCatching {
        val process = ProcessBuilder(*args)
            .directory(rootDir)
            .redirectErrorStream(true)
            .start()
        val output = process.inputStream.bufferedReader().use { it.readText().trim() }
        check(process.waitFor() == 0) { output }
        output
    }.getOrNull()

val gitCommitId = runGitCommand("git", "rev-parse", "--short", "HEAD") ?: "unknown"
val gitCommitCount = runGitCommand("git", "rev-list", "--count", "HEAD")?.toIntOrNull() ?: 1

extra["gitCommitId"] = gitCommitId
extra["gitCommitCount"] = gitCommitCount

plugins {
    alias(libs.plugins.android.application) apply false
}
