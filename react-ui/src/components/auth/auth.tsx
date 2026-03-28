import keycloak from "./keycloak.tsx";

let initialized = false;

export async function initKeycloak(): Promise<boolean> {
    if (initialized) {
        return !!keycloak.authenticated;
    }

    try {
        const authenticated = await keycloak.init({
            onLoad: "check-sso",
            pkceMethod: "S256",
            checkLoginIframe: false,
            silentCheckSsoRedirectUri: `${window.location.origin}/silent-check-sso.html`,
        });

        initialized = true;
        return authenticated;
    } catch (error) {
        console.error("Keycloak init failed", error);
        throw error;
    }
}

export function login(): Promise<void> {
    return keycloak.login();
}

export function logout(): Promise<void> {
    return keycloak.logout({
        redirectUri: window.location.origin,
    });
}

export function getToken(): string | undefined {
    return keycloak.token;
}

export async function refreshToken(minValidity = 30): Promise<string | null> {
    if (!keycloak.authenticated) {
        return null;
    }

    try {
        await keycloak.updateToken(minValidity);
        return keycloak.token ?? null;
    } catch (error) {
        console.error("Failed to refresh token", error);
        await logout();
        return null;
    }
}

export function getUsername(): string {
    return keycloak.tokenParsed?.preferred_username ?? "";
}

export function getRealmRoles(): string[] {
    const roles = keycloak.tokenParsed?.realm_access?.roles;
    return Array.isArray(roles) ? roles : [];
}

export function isAuthenticated(): boolean {
    return !!keycloak.authenticated;
}