<template>
  <div v-if="session && commentInfo.memberId === session.memberId">
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn large icon color="indigo" v-bind="attrs" v-on="on">
          <v-icon>more_horiz</v-icon>
        </v-btn>
      </template>
      <v-list>

        <v-list-item @click="showEdit">
          <v-icon class="iconBox">edit</v-icon>
          <v-list-item-title class="iconBox" >수정
          </v-list-item-title>
        </v-list-item>

        <v-list-item @click="deleteComment(commentInfo)">
          <v-icon class="iconBox">delete</v-icon>
          <v-list-item-title class="iconBox">삭제
          </v-list-item-title>
        </v-list-item>
        <v-list-item @click="reportComment(commentInfo)" v-if="!checkDuplicate(commentInfo)">
          <v-icon class="iconBox">warning</v-icon>
          <v-list-item-title class="iconBox">신고
          </v-list-item-title>
        </v-list-item>

      </v-list>
    </v-menu>
  </div>
</template>

<script>
import axios from "axios";
import {mapActions, mapState} from "vuex";

export default {
  name: "CommentEditMenu",
  props: {
    commentInfo: {
      type: Object,
      required: true
    },
    boardNo: {
      required: true
    },
  },
  computed:{
    ...mapState(['session'])
  },
  data() {
    return {
      isDeleted: true,
      content: '',
      editBox: false,
      dup: '',
    }
  },
  methods: {
    ...mapActions(['fetchCommentList']),
    deleteComment(commentInfo) {
      const { isDeleted } = this
      axios.put(`https://evsbackend.herokuapp.com/comment/delete/${commentInfo.commentNo}`,
          { memberId : commentInfo.memberId, isDeleted })
          .then(() => {
            alert('삭제 성공!')
            this.fetchCommentList(this.boardNo)
          })
          .catch(err => {
            alert(err.response.data.message)
          })
    },
    editComment(commentInfo) {
      const { content } = this
      axios.put(`https://evsbackend.herokuapp.com/comment/edit/${commentInfo.commentNo}`,
          { memberId : commentInfo.memberId, content })
          .then(() => {
            alert('수정 성공!')
            this.fetchCommentList(this.boardNo)
          })
          .catch(err => {
            alert(err.response.data.message)
          })
    },
    reportComment(commentInfo){
      const memberId = commentInfo.memberId
      axios.post(`https://evsbackend.herokuapp.com/comment/report/${commentInfo.commentNo}`, {memberId})
          .then(() => {
            alert("신고 완료!")
            this.fetchCommentList(this.boardNo)
          })
          .catch( res=> {
            console.log(res)
            alert("로그인 바랍니다.")
          })
    },
    checkDuplicate(commentInfo) {
      const memberId = commentInfo.memberId
      const commentNo = commentInfo.commentNo
      let temp;
      axios.get(`https://evsbackend.herokuapp.com/comment/report/check/${commentNo}/${memberId}`)
          .then(res => {
            temp = res.data
            this.dup = temp
          })
      return this.dup
    },
    showEdit(){
      this.$emit('showEdit')
    }
  }
}
</script>

<style scoped>
.iconBox{
  margin: 0 3px;
  cursor: pointer;
}
</style>
