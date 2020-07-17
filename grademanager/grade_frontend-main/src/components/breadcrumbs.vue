<template>
  <v-breadcrumbs :items="items">
    <template v-slot:item="{ item }">
      <v-breadcrumbs-item :href="item.href" :disabled="item.disabled">
        {{ item.text.toUpperCase() }}
      </v-breadcrumbs-item>
    </template>
  </v-breadcrumbs>
</template>

<script>
export default {
  name: 'breadcrumbs',
  created () {
    this.getBreadcrumb()
  },
  watch: {
    $route () {
      this.getBreadcrumb()
    }
  },
  methods: {
    getBreadcrumb () {
      const that = this
      const matched = this.$route.path
      const matchedArr = matched.toString().split('/')
      that.items = [{ text: 'index', disabled: false, href: '' }]
      for (let i = 1; i < matchedArr.length; i++) {
        if (matchedArr[i] === '') {
          continue
        }
        // eslint-disable-next-line no-inner-declarations
        // 工厂模式构造对象
        // eslint-disable-next-line no-inner-declarations
        function F (text, disabled, href) {
          this.text = text
          this.disabled = disabled
          this.href = href
        }
        const f1 = new F(matchedArr[i], false, '')
        that.items.push(f1)
      }
    }
  },
  data: () => ({
    items: []
  })
}
</script>

<style scoped>

</style>
