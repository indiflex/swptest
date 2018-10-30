<template>
  <div class="home">
    <h1 class="red">{{jsonData}}</h1>
    <Brother msg="Hi Brother~" :jsonData="jsonData" @fromBrother="receiveBrother"/>
    <Sister msg="Hi Sister~" :name="jsonData.name" @fromSister="receiveSister"/>

    {{_.random(5)}}-{{_.upperFirst(_.camelCase(ttt))}} <input type="text" v-model="ttt">
    <img alt="Vue logo" src="../assets/logo.png">

    <ul>
      <li v-for="(reply, idx) in replies" :key="reply.rno">
        #{{reply.rno}} <input type="text" v-model="reply.replytext">
        <button @click="saveReply(idx, reply)">SAVE</button>
      </li>
    </ul>

    <TodoItem v-for="item in groceryList" :todo="item" :key="item.id"/>

    <todo-item :todo="{id:9999, text:'99999'}"></todo-item>
    <todo-item></todo-item>

    <span>{{rawHtml}}</span>, <span v-html="rawHtml">aaa: {{rawHtml}}</span>
    <h3 v-once :class="{red:isButtonDisabled}">Once: {{msg}}</h3>
    <h3 :class="{red:isButtonDisabled}">{{msg}}</h3>
    <input type="text" v-model="msg">

    <a href="#" @click.self="aaa()">This is aaa <span @click="bbb()">bbb</span></a>

    <button :disabled="isButtonDisabled" @click="isButtonDisabled=!isButtonDisabled">DisabledButton</button>

    <HelloWorld v-if="isButtonDisabled" msg="Welcome to Your Vue.js App!!"/>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld'
// import TodoItem from '@/components/todo-item'
// import Brother from '@/components/Brother'
// import Sister from '@/components/Sister'

export default {
  name: 'home',
  // components: {
  //   HelloWorld, TodoItem, Brother, Sister
  // },
  created() {
    console.log("HHHHHHHHHH>>", this._.random(20))
    this.fetchReplies();
    this.$watch('ttt', this._.debounce(this.aaa, 1000))
  },

  watch: {
    msg: function(newMsg, oldMsg) {
      console.log(".......", ...arguments)
    }
  },
  data() {
    return {
      ok: false,
      ttt: 'tttt',
      msg: "Message",
      isButtonDisabled: false,
      rawHtml: '<strong>AAA</strong>',
      jsonData: {id: 123, name: 'hong'},
      url: this.$URL + "/replies/265",
      groceryList: [
        { id: 9, text: 'Vegetables' },
        { id: 1, text: 'Cheese' },
        { id: 2, text: 'Whatever else humans are supposed to eat' }
      ],
      replies: []
    }
  },

  methods: {
    fetchReplies() {
      this.$http.get(this.url).then(ret => {
        console.log("rrrrrrrrrrrrr>>", ret)
        this.replies = ret.data;
      });
    },

    saveReply(idx, r) {
      console.log("saveReply>>", r)
      this.$http.put(this.url + "/" + r.rno, r).then(ret => {
      // this.$http.put(this.url + "/" + r.rno, {reply: r}).then(ret => {
        console.log("rrrrrrrrrrrrr>>", ret)
        if (ret && ret.data)
        alert(ret.data.result + '개 댓글이 수정되었습니다.')
      });
    },

    receiveBrother(jd) {
      console.log("receiveBrother>>", jd.name)
      console.log("receiveBrother>>", ...arguments)
      this.jsonData = jd;
    },

    receiveSister(jd) {
      console.log("receiveSister>>", ...arguments)
      this.jsonData.name = jd;
    },

    aaa() {
      console.log("aaaaaaaaaaaaaaaaa", this.ttt)
    },

    bbb() {
      console.log("bbbbbbbbbbbbb")
    }
  }
}
</script>

<style>
  .red { color: red; }
</style>
