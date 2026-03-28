import { useEffect, useState } from "react";
import api from "./components/auth/api.tsx";
import {
  initKeycloak,
  login,
  logout,
  getUsername,
  getRealmRoles,
} from "./components/auth/auth.tsx";

function App() {
  const [ready, setReady] = useState<boolean>(false);
  const [authenticated, setAuthenticated] = useState<boolean>(false);
  const [message, setMessage] = useState<string>("");
  const [adminMessage, setAdminMessage] = useState<string>("");
  const [username, setUsername] = useState<string>("");
  const [roles, setRoles] = useState<string[]>([]);

  useEffect(() => {
    const setup = async () => {
      try {
        const auth = await initKeycloak();
        setAuthenticated(auth);
        setUsername(getUsername());
        setRoles(getRealmRoles());
      } catch (error) {
        console.error(error);
      } finally {
        setReady(true);
      }
    };

    void setup();
  }, []);

  const callPublicApi = async (): Promise<void> => {
    try {
      const response = await api.get<string>("/api/accounts/fa21d46b-4806-494b-8f72-09997fb40ef6");
      setMessage(response.data);
    } catch (error) {
      console.error(error);
      setMessage("Error calling public API");
    }
  };

  const callUserApi = async (): Promise<void> => {
    try {
      const response = await api.get<string>("/api/user");
      setMessage(response.data);
    } catch (error) {
      console.error(error);
      setMessage("Error calling user API");
    }
  };

  const callAdminApi = async (): Promise<void> => {
    try {
      const response = await api.get<string>("/api/admin");
      setAdminMessage(response.data);
    } catch (error) {
      console.error(error);
      setAdminMessage("Error calling admin API");
    }
  };

  if (!ready) {
    return <div>Loading...</div>;
  }

  return (
      <div style={{ padding: "2rem", fontFamily: "Arial" }}>
        <h1>React + Keycloak PKCE + Spring Boot 4</h1>

        {!authenticated ? (
            <>
              <p>You are not logged in.</p>
              <button onClick={() => void login()}>Login</button>
            </>
        ) : (
            <>
              <p>
                Logged in as: <strong>{username}</strong>
              </p>
              <p>
                Roles: <strong>{roles.join(", ") || "none"}</strong>
              </p>

              <div style={{ display: "flex", gap: "1rem", marginBottom: "1rem" }}>
                <button onClick={() => void callPublicApi()}>Call Public API</button>
                <button onClick={() => void callUserApi()}>Call User API</button>
                <button onClick={() => void callAdminApi()}>Call Admin API</button>
                <button onClick={() => void logout()}>Logout</button>
              </div>

              <p>{message}</p>
              <p>{adminMessage}</p>
            </>
        )}
      </div>
  );
}

export default App;