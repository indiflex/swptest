<template>
  <li>TodoItem: <strong>{{todo.text}}</strong></li>
</template>

<script>
  export default {
    // props: ['todo']
    props: {
      todo: {
        type: Object,
        // required: true,
        default() {
          return {id: 0, text: 'default text'};
        }
      }
    }
    
  }
    
</script>