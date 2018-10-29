export const utils = {
  methods: {
    fromSisterMessage(rname) {
      if (!rname) return '';

      return "누나로부터 " + rname + "이라고 메시지가 왔습니다!";
    }
  }
}