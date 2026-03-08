import { defineStore } from 'pinia'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api/auth'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('token') || null,
    user: JSON.parse(localStorage.getItem('user') || 'null'),
    loading: false,
    error: null as string | null,
  }),

  getters: {
    isLoggedIn: (state) => !!state.token,
  },

  actions: {
    async register(name: string, email: string, password: string) {
      this.loading = true
      this.error = null
      try {
        const response = await axios.post(`${API_BASE}/register`, { name, email, password })
        this.token = response.data.token
        this.user = { email: response.data.email, name: response.data.name }
        localStorage.setItem('token', this.token!)
        localStorage.setItem('user', JSON.stringify(this.user))
        return true
      } catch (e) {
        this.error = 'Registration failed'
        return false
      } finally {
        this.loading = false
      }
    },

    async login(email: string, password: string) {
      this.loading = true
      this.error = null
      try {
        const response = await axios.post(`${API_BASE}/login`, { email, password })
        this.token = response.data.token
        this.user = { email: response.data.email, name: response.data.name }
        localStorage.setItem('token', this.token!)
        localStorage.setItem('user', JSON.stringify(this.user))
        return true
      } catch (e) {
        this.error = 'Invalid email or password'
        return false
      } finally {
        this.loading = false
      }
    },

    logout() {
      this.token = null
      this.user = null
      localStorage.removeItem('token')
      localStorage.removeItem('user')
    }
  }
})