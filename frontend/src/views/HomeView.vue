<template>
  <div class="min-h-screen bg-gray-950 text-white">
    <!-- Navbar -->
    <nav class="bg-gray-900 px-8 py-4 flex justify-between items-center shadow-lg">
      <h1 class="text-xl font-bold text-indigo-400">⚡ URL Shortener</h1>
      <div class="flex items-center gap-4">
        <span class="text-gray-400">👋 {{ authStore.user?.name }}</span>
        <button
          @click="handleLogout"
          class="bg-red-900 hover:bg-red-800 text-white px-4 py-2 rounded-xl text-sm"
        >
          Logout
        </button>
      </div>
    </nav>

    <div class="max-w-4xl mx-auto p-8">
      <!-- Form -->
      <div class="bg-gray-900 rounded-2xl p-6 mb-8 shadow-xl">
        <div class="flex flex-col gap-4">
          <input
            v-model="originalUrl"
            type="text"
            placeholder="Paste your long URL here... (https://)"
            class="w-full bg-gray-800 text-white rounded-xl px-4 py-3 outline-none focus:ring-2 focus:ring-indigo-500"
          />
          <input
            v-model="customAlias"
            type="text"
            placeholder="Custom alias (optional)"
            class="w-full bg-gray-800 text-white rounded-xl px-4 py-3 outline-none focus:ring-2 focus:ring-indigo-500"
          />
          <button
            @click="handleShorten"
            :disabled="store.loading"
            class="bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-3 px-6 rounded-xl transition-all"
          >
            {{ store.loading ? 'Shortening...' : 'Shorten URL' }}
          </button>
        </div>

        <!-- Success Message -->
        <div v-if="lastCreated" class="mt-4 bg-green-900 rounded-xl p-4">
          <p class="text-green-400 font-semibold">✅ URL Shortened!</p>
          <div class="flex items-center gap-2 mt-2">
            <a :href="lastCreated.shortUrl" target="_blank" class="text-indigo-400 hover:underline">
              {{ lastCreated.shortUrl }}
            </a>
            <button @click="copyToClipboard(lastCreated.shortUrl)" class="text-xs bg-gray-700 px-2 py-1 rounded-lg">
              Copy
            </button>
          </div>
        </div>
      </div>

      <!-- URL Table -->
      <div class="bg-gray-900 rounded-2xl shadow-xl overflow-hidden">
        <div class="p-6 border-b border-gray-800">
          <h2 class="text-xl font-semibold">Your URLs</h2>
        </div>

        <div v-if="store.loading" class="p-8 text-center text-gray-400">Loading...</div>

        <div v-else-if="store.urls.length === 0" class="p-8 text-center text-gray-400">
          No URLs yet — shorten your first one above!
        </div>

        <div v-else class="overflow-x-auto">
          <table class="w-full">
            <thead class="bg-gray-800 text-gray-400 text-sm">
              <tr>
                <th class="px-6 py-3 text-left">Original URL</th>
                <th class="px-6 py-3 text-left">Short URL</th>
                <th class="px-6 py-3 text-left">Clicks</th>
                <th class="px-6 py-3 text-left">Created</th>
                <th class="px-6 py-3 text-left">Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="url in store.urls" :key="url.id" class="border-t border-gray-800 hover:bg-gray-800 transition-all">
                <td class="px-6 py-4 max-w-xs truncate text-gray-300">{{ url.originalUrl }}</td>
                <td class="px-6 py-4">
                  <a :href="url.shortUrl" target="_blank" class="text-indigo-400 hover:underline">
                    {{ url.shortCode }}
                  </a>
                </td>
                <td class="px-6 py-4">
                  <span class="bg-indigo-900 text-indigo-300 px-2 py-1 rounded-lg text-sm">
                    {{ url.clickCount }}
                  </span>
                </td>
                <td class="px-6 py-4 text-gray-400 text-sm">{{ url.createdAt }}</td>
                <td class="px-6 py-4">
                  <button
                    @click="copyToClipboard(url.shortUrl)"
                    class="text-xs bg-gray-700 hover:bg-gray-600 px-3 py-1 rounded-lg mr-2"
                  >
                    Copy
                  </button>
                  <button
                    @click="handleDelete(url.id)"
                    class="text-xs bg-red-900 hover:bg-red-800 px-3 py-1 rounded-lg"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useUrlStore } from '../stores/urlStore'
import { useAuthStore } from '../stores/authStore'
import type { UrlResponse } from '../stores/urlStore'

const store = useUrlStore()
const authStore = useAuthStore()
const router = useRouter()
const originalUrl = ref('')
const customAlias = ref('')
const lastCreated = ref<UrlResponse | null>(null)

onMounted(() => {
  store.fetchUrls()
})

async function handleShorten() {
  if (!originalUrl.value) return
  const result = await store.createUrl(originalUrl.value, customAlias.value || undefined)
  if (result) {
    lastCreated.value = result
    originalUrl.value = ''
    customAlias.value = ''
  }
}

async function handleDelete(id: number) {
  await store.deleteUrl(id)
}

function copyToClipboard(text: string) {
  navigator.clipboard.writeText(text)
  alert('Copied to clipboard!')
}

function handleLogout() {
  authStore.logout()
  router.push('/login')
}
</script>