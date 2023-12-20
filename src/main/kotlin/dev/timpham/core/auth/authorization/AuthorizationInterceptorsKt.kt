package dev.timpham.core.auth.authorization

import dev.timpham.core.auth.authentication.AppJWTPrincipal
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

class RoleBasedAuthConfiguration {
    var requiredRole: Role? = null
}

class AuthorizedRouteSelector(private val description: String) : RouteSelector() {
    override fun evaluate(context: RoutingResolveContext, segmentIndex: Int) = RouteSelectorEvaluation.Constant

    override fun toString(): String = "(authorize ${description})"
}

class RoleBasedAuthPluginConfiguration {
    var roleExtractor: ((AppJWTPrincipal) -> Role?) = { null }
        private set

    fun extractRoles(extractor: (AppJWTPrincipal) -> Role?) {
        roleExtractor = extractor
    }
}

val RoleBasedAuthPlugin =
    createRouteScopedPlugin(name = "RoleBasedAuthorization", createConfiguration = ::RoleBasedAuthConfiguration) {
        if(::pluginGlobalConfig.isInitialized.not()){
            error("RoleBasedAuthPlugin not initialized. Setup plugin by calling AuthenticationConfig#roleBased in authenticate block")
        }
        with(pluginConfig) {
            on(AuthenticationChecked) { call ->
                val principal = call.principal<AppJWTPrincipal>() ?: return@on
                val userRoles = pluginGlobalConfig.roleExtractor.invoke(principal)
                requiredRole?.let { role ->
                    if (role != userRoles) {
                        val message = "User ${principal.userId} has no required role $role"
                        if(application.developmentMode){
                            application.log.warn("Authorization failed for ${call.request.path()} $message")
                        }
                        throw UnauthorizedAccessException(message)
                    }
                }
            }
        }
    }

private lateinit var pluginGlobalConfig: RoleBasedAuthPluginConfiguration

fun roleBased(config: RoleBasedAuthPluginConfiguration.() -> Unit){
    pluginGlobalConfig = RoleBasedAuthPluginConfiguration().apply(config)
}

private fun Route.buildAuthorizedRoute(
    requiredRole: Role,
    build: Route.() -> Unit
): Route {
    val authorizedRoute = createChild(AuthorizedRouteSelector(requiredRole.toString()))
    authorizedRoute.install(RoleBasedAuthPlugin) {
        this.requiredRole = requiredRole
    }
    authorizedRoute.build()
    return authorizedRoute
}

fun Route.withRole(role: Role, build: Route.() -> Unit) =
    buildAuthorizedRoute(requiredRole = role, build = build)

class UnauthorizedAccessException(val denyReasons: String) : Exception()

