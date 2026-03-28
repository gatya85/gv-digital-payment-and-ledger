import axios from "axios";
import { getToken, refreshToken } from "./auth.tsx";

const api = axios.create({
    baseURL: "http://localhost:9000",
});

api.interceptors.request.use(async (config) => {
    const token = await refreshToken(30);

    if (token) {
        config.headers.Authorization = `Bearer ${getToken()}`;
    }

    return config;
});

export default api;