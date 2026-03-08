<template>
  <div class="min-h-screen bg-gray-950 text-white">
    <!-- Navbar -->
    <nav class="bg-gray-900 px-8 py-4 flex justify-between items-center shadow-lg">
      <h1 class="text-xl font-bold text-indigo-400">⚡ Shortify</h1>
      <div class="flex items-center gap-4">
        <RouterLink to="/" class="text-gray-400 hover:text-white text-sm">Dashboard</RouterLink>
        <RouterLink to="/about" class="text-indigo-400 text-sm font-semibold">About</RouterLink>
      </div>
    </nav>

    <div class="max-w-2xl mx-auto px-8 py-16">
      <!-- Profile Card -->
      <div class="bg-gray-900 rounded-2xl p-8 shadow-xl text-center">

        <!-- Avatar -->
        <div class="w-24 h-24 rounded-full bg-indigo-600 flex items-center justify-center text-4xl font-bold mx-auto mb-4">
          P
        </div>

        <!-- Name & Title -->
        <h1 class="text-3xl font-bold text-white mb-1">Prathamesh Parab</h1>
        <p class="text-indigo-400 font-medium mb-4">Software Engineer</p>

        <!-- Description -->
        <p class="text-gray-400 text-sm leading-relaxed mb-6">
          Passionate software developer focused on building scalable, real-world applications using modern Java and JavaScript ecosystems.
          I love turning complex problems into clean, efficient solutions.
        </p>

        <!-- Motivation -->
        <div class="bg-gray-800 rounded-xl p-4 mb-6 text-left border-l-4 border-indigo-500">
          <p class="text-xs text-indigo-400 font-semibold uppercase mb-2">💡 Why I Built This</p>
          <p class="text-gray-300 text-sm leading-relaxed">
            Shortify was built for developers, marketers, and anyone who shares links online. Clean URLs, real analytics, and zero clutter. Whether you are sharing your portfolio, tracking campaign clicks, or simply tired of ugly links, Shortify gets it done. Fast, secure, and built to scale for millions of users.
          </p>
        </div>

        <!-- Tech Stack -->
        <div class="mb-6">
          <p class="text-xs text-gray-400 uppercase font-semibold mb-3">Built With</p>
          <div class="flex flex-wrap justify-center gap-2">
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">Java 21</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">Spring Boot 3</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">JWT</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">Redis</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">PostgreSQL</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">Docker</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">Vue 3</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">TypeScript</span>
            <span class="bg-gray-800 text-indigo-300 px-3 py-1 rounded-full text-xs">Tailwind CSS</span>
          </div>
        </div>

        <!-- Links -->
        <div class="flex justify-center gap-4">
          <a
            href="https://github.com/Prathu270710"
            target="_blank"
            class="flex items-center gap-2 bg-gray-800 hover:bg-gray-700 text-white px-5 py-2 rounded-xl text-sm transition-all"
          >
            🐙 GitHub
          </a>
          <a
            href="https://www.linkedin.com/in/prathameshparab27/"
            target="_blank"
            class="flex items-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-5 py-2 rounded-xl text-sm transition-all"
          >
            💼 LinkedIn
          </a>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>