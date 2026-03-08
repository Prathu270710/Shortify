import { defineStore } from 'pinia'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api/urls'

export interface UrlResponse {
  id: number
  originalUrl: string
  shortCode: string
  shortUrl: string
  createdAt: string
  expiresAt: string | null
  clickCount: number
  active: boolean
}

export const useUrlStore = defineStore('url', {
  state: () => ({
    urls: [] as UrlResponse[],
    loading: false,
    error: null as string | null,
  }),

  actions: {
    getToken() {
      return localStorage.getItem('token')
    },

    getHeaders() {
      return { Authorization: `Bearer ${this.getToken()}` }
    },

    async fetchUrls() {
      this.loading = true
      try {
        const response = await axios.get(API_BASE, { headers: this.getHeaders() })
        this.urls = response.data
      } catch (e) {
        this.error = 'Failed to fetch URLs'
      } finally {
        this.loading = false
      }
    },

    async createUrl(originalUrl: string, customAlias?: string) {
      this.loading = true
      try {
        const response = await axios.post(API_BASE,
          { originalUrl, customAlias },
          { headers: this.getHeaders() }
        )
        this.urls.unshift(response.data)
        return response.data
      } catch (e) {
        this.error = 'Failed to create URL'
      } finally {
        this.loading = false
      }
    },

    async deleteUrl(id: number) {
      try {
        await axios.delete(`${API_BASE}/${id}`, { headers: this.getHeaders() })
        this.urls = this.urls.filter(u => u.id !== id)
      } catch (e) {
        this.error = 'Failed to delete URL'
      }
    }
  }
})