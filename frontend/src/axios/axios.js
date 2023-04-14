import axios from "axios";

const baseAPI = "http://localhost:8888/api";

export const authorizedAxios = (jwt) => axios.create({
  baseURL: baseAPI,
  headers: {
    Authorization: `Bearer ${jwt}`,
    "Access-Control-Allow-Origin": '*',
    "Content-Type": "application/json"
  }
});

export const unauthorizedAxios = axios.create({
  baseURL: baseAPI,
  headers: {
    "Access-Control-Allow-Origin": '*'
  }
});
