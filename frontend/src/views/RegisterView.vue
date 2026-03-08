<template>
  <div class="min-h-screen bg-gray-950 flex items-center justify-center p-4">
    <div class="bg-gray-900 rounded-2xl p-8 w-full max-w-md shadow-xl">
      <h1 class="text-3xl font-bold text-center text-indigo-400 mb-2">⚡ URL Shortener</h1>
      <p class="text-center text-gray-400 mb-8">Create your account</p>

      <div class="flex flex-col gap-4">
        <input
          v-model="name"
          type="text"
          placeholder="Full Name"
          class="w-full bg-gray-800 text-white rounded-xl px-4 py-3 outline-none focus:ring-2 focus:ring-indigo-500"
        />
        <input
          v-model="email"
          type="email"
          placeholder="Email"
          class="w-full bg-gray-800 text-white rounded-xl px-4 py-3 outline-none focus:ring-2 focus:ring-indigo-500"
        />
        <input
          v-model="password"
          type="password"
          placeholder="Password"
          class="w-full bg-gray-800 text-white rounded-xl px-4 py-3 outline-none focus:ring-2 focus:ring-indigo-500"
        />

        <div v-if="authStore.error" class="text-red-400 text-sm text-center">
          {{ authStore.error }}
        </div>

        <button
          @click="handleRegister"
          :disabled="authStore.loading"
          class="bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-3 px-6 rounded-xl transition-all"
        >
          {{ authStore.loading ? 'Creating account...' : 'Create Account' }}
        </button>

        <p class="text-center text-gray-400">
          Already have an account?
          <RouterLink to="/login" class="text-indigo-400 hover:underline">Sign In</RouterLink>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter, RouterLink } from 'vue-router'
import { useAuthStore } from '../stores/authStore'

const authStore = useAuthStore()
const router = useRouter()
const name = ref('')
const email = ref('')
const password = ref('')

async function handleRegister() {
  const success = await authStore.register(name.value, email.value, password.value)
  if (success) router.push('/')
}
</script>