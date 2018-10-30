import Vue from 'vue'
import upperFirst from 'lodash/upperFirst'
import camelCase from 'lodash/camelCase'
const requireComponent = require.context(
  '.', false, /[\w-].vue$/
)
console.log("==>", requireComponent)
requireComponent.keys().forEach(fileName => {
  const componentConfig = requireComponent(fileName)
  
  console.log("QQ>>", fileName, componentConfig)

  const componentName = upperFirst(
    camelCase(fileName.replace(/^\.\//, '').replace(/\.\w+$/, ''))
  )
  Vue.component(componentName, componentConfig.default || componentConfig)
})